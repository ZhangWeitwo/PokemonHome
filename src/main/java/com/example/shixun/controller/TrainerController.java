package com.example.shixun.controller;

import com.example.shixun.entity.ItemInfo;
import com.example.shixun.entity.Message;
import com.example.shixun.entity.TradeItem;
import com.example.shixun.mapper.ItemMapper;
import com.example.shixun.mapper.TpMapper;
import com.example.shixun.mapper.TrainerMapper;
import com.example.shixun.model.PokemonInfo;
import com.example.shixun.entity.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TrainerController {
    @Autowired
    private TrainerMapper trainerMapper;
    @Autowired
    private TpMapper tpMapper;
    @Autowired
    private ItemMapper itemMapper;
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credentials, HttpSession session) {
        String username = credentials.get("Tname");
        String password = credentials.get("password");
        Map<String, Object> response = new HashMap<>();
        int count = trainerMapper.verifyUser(username, password);

        if (count > 0) {
            response.put("success", true);
            int userID = trainerMapper.getUserID(username, password);
            session.setAttribute("userID", userID);
            response.put("userID", userID);
        } else {
            response.put("success", false);
        }

        return response;
    }

    @PostMapping("/changePassword")
    public Map<String, Object> update(@RequestBody Map<String, String> requestBody, HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        String password = requestBody.get("password");

        int i = trainerMapper.updatePassword(password, userID);

        Map<String, Object> response = new HashMap<>();
        if (i > 0) {
            response.put("success", true);
        } else {
            response.put("success", false);
        }
        return response;
    }

    @GetMapping("/get-trainer")
    public Trainer findById(HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        return trainerMapper.findByTid(userID);
    }
    //添加训练师
    //添加训练师
     @PostMapping("/add-trainer")
    public ResponseEntity<?> addTrainer(@RequestParam("name") String Tname,
                                        @RequestParam("age") int age,
                                        @RequestParam("gender") String gender,
                                        @RequestParam("password") String password,
                                        @RequestParam("trainerPic") MultipartFile trainerPic,
                                        @RequestParam("location") String location) {
        try {
            // 获取当前最大Tid
            Integer maxTid = trainerMapper.findMaxTid();
            int newTid = (maxTid == null) ? 1 : maxTid + 1;
            String newFilename = newTid + ".png";
            // 拼接图片上传的路径
            ApplicationHome applicationHome = new ApplicationHome(this.getClass());
            String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static\\trainer\\";
            String path = pre + newFilename;

            File uploadDirFile = new File(pre);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            // 检查文件格式并转换为png格式
            if (!trainerPic.getOriginalFilename().endsWith(".png")) {
                BufferedImage bufferedImage = ImageIO.read(trainerPic.getInputStream());
                File outputfile = new File(path);
                ImageIO.write(bufferedImage, "png", outputfile);
            } else {
                trainerPic.transferTo(new File(path));
            }

            // 创建Trainer对象并保存到数据库
            Trainer newTrainer = new Trainer();
            newTrainer.setTname(Tname);
            newTrainer.setAge(age);
            newTrainer.setGender(gender);
            newTrainer.setPassword(password);
            newTrainer.setTrainerPic("trainer/" + newFilename);
            newTrainer.setLocation(location);
            trainerMapper.insertTrainer(newTrainer);
            return ResponseEntity.ok().body("{\"success\": true}");
        } catch (IOException e) {
            e.printStackTrace(); // 添加详细日志
            return ResponseEntity.status(500).body("{\"success\": false, \"message\": \"图像保存失败\"}");
        } catch (Exception e) {
            e.printStackTrace(); // 添加详细日志
            return ResponseEntity.status(500).body("{\"success\": false, \"message\": \"注册失败，请重试\"}");
        }
    }


    @GetMapping("/CheckPokemonInBag")
    public Map<String, List<PokemonInfo>> getPokemonsbag(HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        List<PokemonInfo> pokemonInfos = tpMapper.findPokemonInfoByTid1(userID);

        Map<String, List<PokemonInfo>> response = new HashMap<>();
        response.put("pokemons", pokemonInfos);
        return response;
    }
    //查看对方交换池中的宝可梦
    @GetMapping("getTrainerPokemons")
    public Map<String, List<PokemonInfo>> getTrainerPokemons(@RequestParam("trainerId") int trainerId) {


        List<PokemonInfo> pokemonInfos = tpMapper.findPokemonInfoinpool(trainerId); // 假设你可以从某处获取 userID

        Map<String, List<PokemonInfo>> response = new HashMap<>();
        response.put("pokemons", pokemonInfos);

        return response;
    }
     //展示交换池所有宝可梦
    @GetMapping("/CheckPokemonInPool")
    public Map<String, List<PokemonInfo>> getPokemoninpool(HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        List<PokemonInfo> pokemonInfos = tpMapper.findPokemonInfoinpool(userID); // 假设你可以从某处获取 userID
        Map<String, List<PokemonInfo>> response = new HashMap<>();
        response.put("pokemons", pokemonInfos);

        return response;
    }
    @GetMapping("/CheckPokemonInWarehouse")
    public Map<String, List<PokemonInfo>> getPokemonshouse(HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        List<PokemonInfo> pokemonInfos = tpMapper.findPokemonInfoByTid2(userID);
        Map<String, List<PokemonInfo>> response = new HashMap<>();
        response.put("pokemons", pokemonInfos);
        return response;
    }

    @PostMapping("/catch-pokemon-in-warehouse")
    public Map<String, Object> catchPokemon(@RequestBody Map<String, Object> requestBody, HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        int flag = trainerMapper.findFlagByTid() + 1;
        int pid = Integer.parseInt((String) requestBody.get("Pid"));
        int statement = (int) requestBody.get("statement");
        int lev = Integer.parseInt((String) requestBody.get("lev"));
        String gender = (String) requestBody.get("gender");
        int result = trainerMapper.catchPokemon(flag, pid, statement, lev, userID, gender);
        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("success", true);
        } else {
            response.put("success", false);
        }
        return response;
    }

    @PostMapping("/catch-pokemon-in-bag")
    public Map<String, Object> catchPokemonInBag(@RequestBody Map<String, Object> requestBody, HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        Map<String, Object> response = new HashMap<>();

        int PokemonNumInBag = trainerMapper.countPokemonInBag(userID);
        if (PokemonNumInBag < 6) {
            int flag = trainerMapper.findFlagByTid() + 1;
            int pid = Integer.parseInt((String) requestBody.get("Pid"));
            int statement = (int) requestBody.get("statement");
            int lev = Integer.parseInt((String) requestBody.get("lev"));
            String gender = (String) requestBody.get("gender");

            int result = trainerMapper.catchPokemon(flag, pid, statement, lev, userID, gender);
            if (result > 0) {
                response.put("success", true);
            } else {
                response.put("success", false);
            }
        } else {
            response.put("success", false);
            response.put("message", "每一个训练师不可携带超过6只宝可梦");
        }
        return response;
    }

    @PostMapping("/exchange-Pokemon")
    public Map<String, Object> exchangePokemon(@RequestBody Map<String, Object> requestBody, HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        int tid = Integer.parseInt((String) requestBody.get("trainer_inPoll_id"));
        int flag_in_pool = (int) requestBody.get("Flag_inPool");
        int my_pokemon_flag = (int) requestBody.get("flag");

        int result0 = tpMapper.exchangePokemon(tid, my_pokemon_flag);
        int result1 = tpMapper.exchangePokemon(userID, flag_in_pool);

        Map<String, Object> response = new HashMap<>();
        if (result0 > 0 && result1 > 0) {
            response.put("success", true);
        } else {
            response.put("success", false);
        }
        return response;
    }

    @PostMapping("/release-pokemon")
    public Map<String, Object> releasePokemon(@RequestBody Map<String, Object> requestBody) {
        Map<String, Object> response = new HashMap<>();

        int flag = (int) requestBody.get("flag");
        int result = trainerMapper.releasePokemon(flag);
        if (result > 0) {
            response.put("success", true);
        } else {
            response.put("success", false);
        }
        return response;
    }

    @PostMapping("/release-pokemon_in_bag")
    public Map<String, Object> releasePokemonInBag(@RequestBody Map<String, Object> requestBody, HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        Map<String, Object> response = new HashMap<>();

        int flag = (int) requestBody.get("flag");

        int pokemon_num_in_bag = trainerMapper.countPokemonInBag(userID);

        if (pokemon_num_in_bag > 1) {
            int result = trainerMapper.releasePokemon(flag);

            if (result > 0) {
                response.put("success", true);
            } else {
                response.put("success", false);
            }
        } else {
            response.put("success", false);
            response.put("message", "背包中至少要有一只宝可梦");
        }
        return response;
    }

    @PostMapping("/AddPokemonIntoBag")
    public ResponseEntity<?> addPokemonToBag(@RequestBody Map<String, Integer> requestBody, HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        try {
            int count = trainerMapper.countPokemonInBag(userID);
            int flag = requestBody.get("flag");

            if (count < 6) {
                int result = trainerMapper.updatePokemonStatement(1, flag);
                if (result > 0) {
                    return ResponseEntity.ok().body("{\"success\": true, \"message\": \"宝可梦已成功加入背包\"}");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"message\": \"加入背包失败\"}");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"success\": false, \"message\": \"背包已满，无法加入更多宝可梦\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"message\": \"服务器处理请求时出错\"}");
        }
    }

    @PostMapping("/AddPokemonIntoStore")
    public ResponseEntity<?> addPokemonIntoStore(@RequestBody Map<String, Integer> requestBody) {
        int flag = requestBody.get("flag");
        int result = trainerMapper.updatePokemonStatement(0, flag);

        if (result > 0) {
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"宝可梦已成功加入仓库\"}");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"message\": \"加入仓库失败\"}");
        }
    }

    @PostMapping("/RemovePokemonFromeBag")
    public ResponseEntity<?> addPokemonToWarehouse(@RequestBody Map<String, Integer> requestBody, HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        int count = trainerMapper.countPokemonInBag(userID);
        int flag = requestBody.get("flag");

        if (count > 1) {
            int result = trainerMapper.updatePokemonStatement(0, flag);
            if (result > 0) {
                return ResponseEntity.ok().body("{\"success\": true, \"message\": \"宝可梦已成功加入仓库\"}");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"message\": \"加入仓库失败\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"success\": false, \"message\": \"至少有一个宝可梦在背包中\"}");
        }
    }

    @PostMapping("/AddPokemonIntoPool")
    public ResponseEntity<?> addPokemonToPool(@RequestBody Map<String, Integer> requestBody) {
        int flag = requestBody.get("flag");
        int result = trainerMapper.updatePokemonStatement(2, flag);

        if (result > 0) {
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"宝可梦已成功加入交换池\"}");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"message\": \"加入交换池失败\"}");
        }
    }

    @GetMapping("/search-PokemonbyPid/{Pid}")
    public Map<String, List<PokemonInfo>> findPokemonById(@PathVariable("Pid") int Pid) {
        List<PokemonInfo> pokemonInfos = trainerMapper.idfindPokemon(Pid);

        Map<String, List<PokemonInfo>> response = new HashMap<>();
        response.put("pokemons", pokemonInfos);

        return response;
    }

    @GetMapping("/search-Pokemonbytype/{type}")
    public Map<String, List<PokemonInfo>> findPokemonBytype(@PathVariable("type") String type) {
        List<PokemonInfo> pokemonInfos = trainerMapper.typefindPokemon(type);

        Map<String, List<PokemonInfo>> response = new HashMap<>();
        response.put("pokemons", pokemonInfos);

        return response;
    }

    @GetMapping("/search-Pokemonbyname/{Pname}")
    public Map<String, List<PokemonInfo>> findPokemonByname(@PathVariable("Pname") String name) {
        List<PokemonInfo> pokemonInfos = trainerMapper.namefindPokemon(name);

        Map<String, List<PokemonInfo>> response = new HashMap<>();
        response.put("pokemons", pokemonInfos);

        return response;
    }

    @GetMapping("/searchs-PokemonbyPid/{Pid}")
    public Map<String, List<PokemonInfo>> findwokemonById(@PathVariable("Pid") int Pid) {
        List<PokemonInfo> pokemonInfos = trainerMapper.StoreIdfindPokemon(Pid);

        Map<String, List<PokemonInfo>> response = new HashMap<>();
        response.put("pokemons", pokemonInfos);

        return response;
    }

    @GetMapping("/searchs-Pokemonbytype/{type}")
    public Map<String, List<PokemonInfo>> findwokemonBytype(@PathVariable("type") String type) {
        List<PokemonInfo> pokemonInfos = trainerMapper.StoreTypefindPokemon(type);

        Map<String, List<PokemonInfo>> response = new HashMap<>();
        response.put("pokemons", pokemonInfos);

        return response;
    }

    @GetMapping("/searchs-Pokemonbyname/{Pname}")
    public Map<String, List<PokemonInfo>> findwokemonByname(@PathVariable("Pname") String name) {
        List<PokemonInfo> pokemonInfos = trainerMapper.StoreNamefindPokemon(name);

        Map<String, List<PokemonInfo>> response = new HashMap<>();
        response.put("pokemons", pokemonInfos);

        return response;
    }

    @GetMapping("/get-friends")
    public Map<String, List<Trainer>> getFriends(HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        List<Trainer> trainers = trainerMapper.getFriendList(userID);

        Map<String, List<Trainer>> response = new HashMap<>();
        response.put("trainers", trainers);
        return response;
    }


    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    @GetMapping("/search-friends-by-name/{Tname}")
    public Map<String,List<Trainer>> findFriendsByName(@PathVariable("Tname") String name, HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        List<Trainer> trainers = trainerMapper.getFriendListByName(userID, name);

        Map<String, List<Trainer>> response = new HashMap<>();
        response.put("trainers", trainers);

        return response;
    }
    //通过ID查找好友
    @GetMapping("/search-friends-by-id/{Tid}")
    public Map<String,List<Trainer>> findFriendsByName(@PathVariable("Tid") int id, HttpSession session) {
        int userID = (int) session.getAttribute("userID");
        List<Trainer> trainers = trainerMapper.getFriendListByID(userID, id);

        Map<String, List<Trainer>> response = new HashMap<>();
        response.put("trainers", trainers);

        return response;
    }
    //////////添加好友//////////////
    //发送好友请求
    @PostMapping("/send-friend-request")
    public Map<String, Object> SendFriendRequest(@RequestBody Map<String, Object> requestBody,HttpSession session) {
        int userID = (int) session.getAttribute("userID");


        int friendID = Integer.parseInt((String) requestBody.get("friendId"));
        LocalDateTime currentTime = LocalDateTime.now();

        int result = trainerMapper.SendFriendRequest(userID, friendID, 0, currentTime);//发送请求
        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("success", true);
        } else {
            response.put("success", false);
            response.put("message", "发送出错");
        }
        return response;
    }
    //查看好友请求
    @GetMapping("/get-friend-requests-lists")
    public Map<String, List<Trainer>> getFriendRequest(HttpSession session) {
        int userID = (int) session.getAttribute("userID");

        List<Trainer> trainers = trainerMapper.getFriendRequestList(userID);

        Map<String, List<Trainer>> response = new HashMap<>();
        response.put("trainers", trainers);

        return response;
    }
    //同意好友请求
    @PostMapping("/receive-friend-request/{id}")
    public Map<String, Object> receiveFriendRequest(@PathVariable("id") int id,HttpSession session) {
        int userID = (int) session.getAttribute("userID");

        int friendID = id;//获取好友id
        LocalDateTime currentTime = LocalDateTime.now();

        //同意好友请求
        //更新statuses表
        int result0 = trainerMapper.UpdateStatus(currentTime,friendID, userID);//更新申请状态
        int result1 = trainerMapper.SendFriendRequest(userID, friendID, 1, currentTime);//与发送申请是同一个函数在处理，只是status不同
        //更新friends表
        int maxMessageID = trainerMapper.findMaxMessageID();
        int result2 = trainerMapper.insertMessageID(userID, friendID,maxMessageID+1);
        int result3 = trainerMapper.insertMessageID(friendID, userID,maxMessageID+1);

        Map<String, Object> response = new HashMap<>();
        if (result0 > 0 && result1 > 0 && result2 > 0 && result3 > 0) {
            response.put("success", true);
            response.put("message", "你们已成为好友");
        } else {
            response.put("success", false);
            response.put("message", "处理出错");
        }
        return response;
    }
    //拒绝好友请求
    @PostMapping("/refuse-friend-request/{id}")
    public Map<String, Object> refuseFriendRequest(@PathVariable("id") int id,HttpSession session) {
        int userID = (int) session.getAttribute("userID");

        int friendID = id;//获取好友id

        //拒绝好友请求
        int result4 = trainerMapper.deleteFriendInStatus(friendID, userID);

        Map<String, Object> response = new HashMap<>();
        if ( result4 > 0) {
            response.put("success", true);
            response.put("message", "已拒绝好友请求");
        } else {
            response.put("success", false);
            response.put("message", "处理出错");
        }
        return response;
    }

    //删除好友
    @PostMapping("/delete-friend")
    public Map<String, Object> deleteFriend(@RequestBody Map<String, Object> requestBody,HttpSession session) {
        int userID = (int) session.getAttribute("userID");

        int friendId = (int) requestBody.get("friendId");

        //删除好友

        int result0 = trainerMapper.deleteFriendInFriend(userID, friendId);
        int result1 = trainerMapper.deleteFriendInFriend(friendId, userID);
        int result2 = trainerMapper.deleteFriendInStatus(friendId, userID);
        int result3 = trainerMapper.deleteFriendInStatus(userID, friendId);

        Map<String, Object> response = new HashMap<>();
        if (result0 > 0 && result1 > 0 && result2 > 0 && result3 > 0) {
            response.put("success", true);
        } else {
            response.put("success", false);
            response.put("message", "删除失败");
        }
        return response;
    }
    //查询未处理的好友申请
    @GetMapping({"/get-unread-requests-count"})
    public Map<String, Object> getUnreadRequestsCount(HttpSession session) {
        int userID = (int) session.getAttribute("userID");

        int count = trainerMapper.getUnreadRequestsCount(userID);
        if (count > 0) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "您有"+count+"条未处理的好友申请");
            return response;
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "无好友申请");
            return response;
        }
    }

    //查看消息列表
    @GetMapping("/get-chat")
    public Map<String, List<Message> > getMessageList(@RequestParam("friendId") int friendId,HttpSession session) {
        int userID = (int) session.getAttribute("userID");

        int dialogID = trainerMapper.getMessageID(userID, friendId);

        List<Message> messages = trainerMapper.getMessageList(dialogID);
        Map<String, List<Message>> response = new HashMap<>();
        response.put("messages", messages);
        return response;
    }
    //发送消息
    @PostMapping("/send-message")
    public Map<String, Object> sendMessage(@RequestBody Map<String, Object> requestBody,HttpSession session) {
        int userID = (int) session.getAttribute("userID");

        System.out.println("requestBody:" + requestBody);
        /**
         * 1.查询message数据库所需要的信息messageID,message,senderName,time
         * 2.插入message数据库
         * 3.返回成功信息
         */
        LocalDateTime currentTime = LocalDateTime.now();//time
        String senderName = trainerMapper.getTrainerName(userID);//senderName
        String message = (String) requestBody.get("content");//message
        int friendId = (int) requestBody.get("friendId");
        int dialogID = trainerMapper.getMessageID(userID, friendId);//messageID

        int result = trainerMapper.insertMessage(dialogID, message, senderName, currentTime);
        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("success", true);
            response.put("message", "发送成功");
            response.put("name", senderName);
        } else {
            response.put("success", false);
            response.put("message", "发送失败");
        }
        return response;

    }
    //****************************************************************
//道具图鉴
// 通过ID查找道具
@GetMapping("/search-ItembyId/{Id}")
public Map<String, List<ItemInfo>> findItemById(@PathVariable("Id") int Id) {
    List<ItemInfo> itemInfos = itemMapper.findById(Id);

    Map<String, List<ItemInfo>> response = new HashMap<>();
    response.put("items", itemInfos);

    return response;
}

// 通过名称查找道具
@GetMapping("/search-Itembyname/{Pname}")
public Map<String, List<ItemInfo>> findItemByName(@PathVariable("Pname") String name) {
    List<ItemInfo> itemInfos = itemMapper.findByName(name);

    Map<String, List<ItemInfo>> response = new HashMap<>();
    response.put("items", itemInfos);

    return response;
}

@GetMapping("/getPoints")
public Map<String, Object> getPoints(HttpSession session) {
    int userID = (int) session.getAttribute("userID");
    int points = itemMapper.getUserPoints(userID);

    Map<String, Object> response = new HashMap<>();
    response.put("points", points);

    return response;
}

// 处理购买道具图鉴页面道具的POST请求
@PostMapping("/buyItem/{id}")
public ResponseEntity<?> buyItem(@PathVariable("id") int itemId,HttpSession session) {
    int userID = (int) session.getAttribute("userID");
    int price = itemMapper.findPrice(itemId);
    int remainingPoints = trainerMapper.findPoints(userID);

    if (price > remainingPoints) {//如果商品价格比用户的剩余积分多
        return new ResponseEntity<>("购买失败，积分不足。", HttpStatus.BAD_REQUEST);
    }else{
        //扣除用户积分对应的积分，并且往own表中插入记录
        remainingPoints = remainingPoints - price ;
        int count = trainerMapper.updatePoints(userID,remainingPoints);
        //已有物品数
        Integer have = trainerMapper.findHave(userID,itemId);
        if (have == null){
            trainerMapper.insertItem(itemId,userID);
        }else {
            trainerMapper.addItem(itemId,userID,have+1);
        }
        if (count > 0) {
            return new ResponseEntity<>("购买成功！", HttpStatus.OK);
        }else {
            // 异常处理
            return new ResponseEntity<>("服务器错误，请稍后重试。", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
//获取用户背包中的道具
@GetMapping("/getItem")
public Map<String, List<ItemInfo>> getItem(HttpSession session) {
    int userID = (int) session.getAttribute("userID");
    List<ItemInfo> itemInfos = itemMapper.getItem(userID);

    Map<String, List<ItemInfo>> response = new HashMap<>();
    response.put("items", itemInfos);

    return response;
}
//出售物品（处理表单）
@PostMapping("/sell-item")
public Map<String, Object> sellItem(@RequestBody Map<String, Object> requestBody, HttpSession session) {
    // 从session中获取用户ID
    int userID = (int) session.getAttribute("userID");

    // 直接从请求体对象中获取参数
    int propid = Integer.parseInt(requestBody.get("propid").toString());
    String propname = (String) requestBody.get("propname");
    int ownerId = Integer.parseInt(requestBody.get("ownerId").toString());
    String ownerName = (String) requestBody.get("ownerName");
    int price =  Integer.parseInt(requestBody.get("price").toString());
    String special = (String) requestBody.get("special");
    int num =  Integer.parseInt(requestBody.get("num").toString());


    // 调用Mapper方法处理出售请求,往trade表中加一条记录
    int result = itemMapper.insertTradeItem(propid, propname, ownerId, ownerName,price,special,num);

    // 创建响应Map
    Map<String, Object> response = new HashMap<>();
    // 根据数据库操作结果设置响应状态
    if (result > 0) {
        response.put("success", true);
        //把own表对应num减少，前端再次调用fetchItem,num0为减去前的num,num为表单的num
        int num0 = itemMapper.Getnum(propid,userID);
        int NUM = num0 - num;
        itemMapper.decrease(propid,userID,NUM);
    } else {
        response.put("success", false);
    }
    return response;
}

// 处理购买道具图鉴页面道具的POST请求
@PostMapping("/buyTradeItem")
public ResponseEntity<?> buyTradeItem(HttpSession session,@RequestBody TradeItem data) {
    int userID = (int) session.getAttribute("userID");
    // 从data对象中获取数据
    String ownerName = data.getOwnerName();
    int ownerId = trainerMapper.getOwnerId(ownerName);
    int propid = data.getPropid();

    int price = itemMapper.findPrice(propid);
    int remainingPoints = trainerMapper.findPoints(userID);

    if (price > remainingPoints) {//如果商品价格比用户的剩余积分多
        return new ResponseEntity<>("购买失败，积分不足。", HttpStatus.BAD_REQUEST);
    }else{
        //扣除用户积分对应的积分，并且往own表中插入记录,给用户加积分，修改own表
        remainingPoints = remainingPoints - price ;
        int count = trainerMapper.updatePoints(userID,remainingPoints);
        //加分
        int searchPoints = trainerMapper.findPointsByname(ownerName);
        trainerMapper.updateSellPoints(ownerName,searchPoints+price);
        //修改own表
        trainerMapper.decreaseNum(ownerId);
        //已有物品数
        Integer have = trainerMapper.findHave(userID,propid);
        if (have == null){
            trainerMapper.insertItem(propid,userID);
        }else {
            trainerMapper.addItem(propid,userID,have+1);
        }
        if (count > 0) {
            return new ResponseEntity<>("购买成功！", HttpStatus.OK);
        }else {
            // 异常处理
            return new ResponseEntity<>("服务器错误，请稍后重试。", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
}
