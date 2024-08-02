package com.example.shixun.mapper;

import com.example.shixun.entity.Message;
import com.example.shixun.entity.Trainer;
import com.example.shixun.model.PokemonInfo;
import org.apache.ibatis.annotations.*;


import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TrainerMapper {
    //获取Id
    @Select("select Tid from trainer where Tname = #{tname}")
    int getid(@Param("tname") String tname);
    // 添加训练师
    @Insert("INSERT INTO trainer (Tname, age, gender, password, TrainerPic, location) VALUES (#{Tname}, #{age}, #{gender}, #{password}, #{TrainerPic}, #{location})")
    void insertTrainer(Trainer trainer);
    //查最大id
    @Select("SELECT MAX(Tid) FROM trainer")
    Integer findMaxTid();

    // 登录用，确认数据库是否有这个用户
    @Select("SELECT COUNT(*) FROM trainer WHERE Tname = #{tname} AND password = #{password}")
    int verifyUser(@Param("tname") String tname, @Param("password") String password);
    //获取id
    @Select("SELECT Tid FROM trainer WHERE Tname = #{tname} AND password = #{password}")
    int getUserID(@Param("tname") String tname, @Param("password") String password);

    // 修改密码
    @Update("UPDATE trainer SET password = #{password} WHERE Tid = #{tid}")
    int updatePassword(@Param("password") String password,@Param("tid") int tid);

    // 查找训练师
    @Select("SELECT * FROM trainer WHERE Tid = #{tid}")
    Trainer findByTid(@Param("tid") int Tid);

    // 通过ID查找训练师在仓库的宝可梦
    @Select("SELECT pokemon.Pname, pokemon.location, pokemon.Pid, pokemon.type1, pokemon.type2, pokemon.imageUrl, " +
            "tp.gender, tp.lev, tp.flag " +
            "FROM tp " +
            "JOIN pokemon ON tp.Pid = pokemon.Pid " +
            "WHERE tp.statement = 0 " +
            "AND tp.Tid = #{tid}")
    List<PokemonInfo> findPokemonInfoByTid0(@Param("tid") int tid);

    // 统计背包中宝可梦数量
    @Select("SELECT COUNT(*) FROM tp WHERE Tid = #{tid} AND statement = 1")
    int countPokemonInBag(@Param("tid") int tid);

    // 更改宝可梦状态
    @Update("UPDATE tp SET statement = #{statement} WHERE flag = #{flag}")
    int updatePokemonStatement(@Param("statement") int statement, @Param("flag") int flag);
    // 捕捉宝可梦
    @Insert("INSERT INTO TP (flag, Pid, statement, lev, Tid, gender) VALUES (#{flag}, #{pid}, #{statement}, #{lev}, #{tid}, #{gender})")
    int catchPokemon(@Param("flag") int flag, @Param("pid") int pid, @Param("statement") int statement, @Param("lev") int lev, @Param("tid") int tid, @Param("gender") String gender);
    // 找到flag最大值
    @Select("SELECT MAX(flag) AS max_flag FROM TP")
    int findFlagByTid();
    //放生宝可梦
    @Delete("DELETE FROM TP WHERE flag = #{flag}")
    int releasePokemon(@Param("flag") int flag);

    //根据id搜索宝可梦
    @Select("SELECT pokemon.Pname, pokemon.location, pokemon.Pid, pokemon.type1, pokemon.type2, pokemon.imageUrl"+
            " FROM pokemon WHERE Pid = #{pid}")
    List<PokemonInfo> idfindPokemon(@Param("pid") int pid);

    //根据属性搜索宝可梦
    @Select("SELECT pokemon.Pname, pokemon.location, pokemon.Pid, pokemon.type1, pokemon.type2, pokemon.imageUrl" +
            " FROM pokemon WHERE type1 like '%' #{type} '%' OR type2 like '%' #{type} '%' order by Pid")
    List<PokemonInfo> typefindPokemon(@Param("type") String type);

    //根据名称搜索宝可梦
    @Select("SELECT pokemon.Pname, pokemon.location, pokemon.Pid, pokemon.type1, pokemon.type2, pokemon.imageUrl"+
            " FROM pokemon WHERE Pname like '%' #{pname} '%' ")
    List<PokemonInfo> namefindPokemon(@Param("pname") String pname);

    //在仓库中根据id搜索宝可梦
    @Select("SELECT pokemon.*, TP.* FROM pokemon JOIN TP ON pokemon.Pid = TP.Pid"+
            " WHERE TP.statement = 0 AND pokemon.Pid = #{pid}")
    List<PokemonInfo> StoreIdfindPokemon(@Param("pid") int pid);

    //在仓库中根据属性搜索宝可梦
    @Select("SELECT pokemon.*, TP.* FROM pokemon JOIN TP ON pokemon.Pid = TP.Pid"+
            " WHERE TP.statement = 0 AND (type1 like '%' #{type} '%' OR type2 like '%' #{type} '%') order by pokemon.Pid")
    List<PokemonInfo> StoreTypefindPokemon(@Param("type") String type);

    //在仓库中根据名称搜索宝可梦
    @Select("SELECT pokemon.*, TP.* FROM pokemon JOIN TP ON pokemon.Pid = TP.Pid"+
            " WHERE TP.statement = 0 AND  Pname like '%' #{pname} '%' ")
    List<PokemonInfo> StoreNamefindPokemon(@Param("pname") String pname);

    //获取好友列表
    @Select("SELECT t.* " +
            "FROM trainer t " +
            "WHERE t.Tid IN ( " +
            "SELECT s.userID " +
            "FROM statuses s " +
            "WHERE s.status = 1 AND s.friendsID = #{tid} " +
            ");")
    List<Trainer> getFriendList(@Param("tid") int tid);



    //////////////////////////////////////////////
    @Select("SELECT t.* " +
            "FROM trainer t " +
            "WHERE t.Tid IN ( " +
            "SELECT s.userID " +
            "FROM statuses s " +
            "WHERE s.status = 1 AND s.friendsID = #{userID} )" +
            "AND t.Tname = #{tname};")
    List<Trainer> getFriendListByName(@Param("userID") int userID, @Param("tname") String Tname);

    @Select("SELECT t.* " +
            "FROM trainer t " +
            "WHERE t.Tid IN ( " +
            "SELECT s.userID " +
            "FROM statuses s " +
            "WHERE s.status = 1 AND s.friendsID = #{userID} )" +
            "AND t.tid = #{id};")
    List<Trainer> getFriendListByID(@Param("userID") int userID, @Param("id") int Tid);

    //删除好友
    @Delete("DELETE FROM statuses WHERE userID = #{userID} AND friendsID = #{friendId}")
    int deleteFriendInStatus(@Param("userID") int userID, @Param("friendId") int friendId);
    @Delete("DELETE FROM friends WHERE userID = #{userID} AND friendsID = #{friendId}")
    int deleteFriendInFriend(@Param("userID") int userID, @Param("friendId") int friendId);


    //发送好友申请
    @Insert("INSERT INTO statuses (userID, friendsID, status, time) VALUES (#{userID}, #{friendsID}, #{status}, #{time})")
    int SendFriendRequest(@Param("userID") int userID, @Param("friendsID") int friendsID, @Param("status") int status, @Param("time") LocalDateTime time);

    //同意好友申请
    @Update("UPDATE statuses SET status = 1, time = #{time} WHERE userID = #{userID} AND friendsID = #{friendsID}")
    int UpdateStatus(@Param("time") LocalDateTime time, @Param("userID") int userID, @Param("friendsID") int friendsID);

    //查询会话ID
    @Select("SELECT MAX(messageID) AS maxMessageID FROM friends")
    int findMaxMessageID();
    @Insert("INSERT INTO friends (userID, friendsID,messageID) VALUES (#{userID}, #{friendsID}, #{messageID})")
    int insertMessageID(@Param("userID") int userID, @Param("friendsID") int friendsID, @Param("messageID") int messageID);

    @Select("SELECT t.* " +
            "FROM trainer t " +
            "WHERE t.Tid IN ( " +
            "SELECT s.userID " +
            "FROM statuses s " +
            "WHERE s.status = 0 AND s.friendsID = #{tid} " +
            ");")
    List<Trainer> getFriendRequestList(@Param("tid") int userID);

    @Select("SELECT COUNT(DISTINCT userID) FROM statuses WHERE status = 0 AND friendsID = #{userID}" )
    int getUnreadRequestsCount(@Param("userID")int userID);

    @Select("SELECT messageID FROM friends WHERE userID = #{userID} AND friendsID = #{friendsID}")
    int getMessageID(@Param("userID") int userID, @Param("friendsID") int friendId);

    @Select("SELECT * FROM messages WHERE messageID = #{dialogID}")
    List<Message> getMessageList(@Param("dialogID") int dialogID);

    @Insert("INSERT INTO messages (messageID, message, senderName, time) VALUES (#{dialogID}, #{message}, #{senderName}, #{currentTime})")
    int insertMessage(@Param("dialogID") int dialogID, @Param("message") String message, @Param("senderName") String senderName, @Param("currentTime") LocalDateTime currentTime);

    ////////////////////////////////////////////////////////////
    //给webSocketServer用的


    @Select("SELECT TrainerPic FROM trainer WHERE Tid = #{tid}")
    String getUserAvatarWeb(@Param("tid") int userID);

    @Select("SELECT TrainerPic FROM trainer WHERE Tid = #{tid}")
    String getAvatarWeb(@Param("tid") int userID);

    //获取用户ID
    @Select("SELECT Tid FROM trainer WHERE Tname = #{tname}")
    int getUserIDWeb(@Param("tname") String friendName);
    //获取messageID,用来查询历史信息/将发送信息存入数据库
    @Select("SELECT messageID FROM friends WHERE userID = #{userID} AND friendsID = #{friendId}")
    int getMessageIDWeb(@Param("userID") int userID, @Param("friendId") String friendId);

    @Select("SELECT * FROM messages WHERE messageID = #{dialogID}")
    List<Message> getHistoryMessages(@Param("dialogID") int historyMessagesId);
    //获取名字
    @Select("SELECT Tname FROM trainer WHERE Tid = #{tid}")
    String getTrainerName(@Param("tid") int tid);
    //获取朋友名字
    @Select("SELECT Tname FROM trainer Where Tid = #{tid}")
    String getFriendNameWed(@Param("tid") String friendId);



//获取用户余额
@Select("SELECT points FROM trainer WHERE Tid = #{tid} ")
int findPoints(@Param("tid") int Tid);

//更新用户余额
@Update("UPDATE trainer SET points = #{points} WHERE Tid = #{tid}")
int updatePoints(@Param("tid") int Tid, @Param("points") int points);

//插入记录表示背包多了一个物品
@Insert("INSERT INTO own (propid, ownerId, num) VALUES (#{propid}, #{ownerId}, 1)")
int insertItem(@Param("propid") int propid, @Param("ownerId") int ownerId);

@Update("UPDATE own SET num = #{num} WHERE propid = #{propid} AND ownerId = #{ownerId}")
int addItem(@Param("propid") int propid, @Param("ownerId") int ownerId,@Param("num") int num);

@Select("SELECT num FROM own WHERE ownerId = #{tid} AND propid = #{propid} ")
Integer findHave(@Param("tid") int Tid,@Param("propid") int propid);

//更新卖家余额
@Update("UPDATE trainer SET points = #{points} WHERE Tname = #{Tname}")
int updateSellPoints(@Param("Tname") String Tname, @Param("points") int points);

//获取用户余额
@Select("SELECT points FROM trainer WHERE Tname = #{Tname} ")
int findPointsByname(@Param("Tname") String Tname);

@Select("select Tid from trainer where Tname= #{Tname} ")
int getOwnerId(@Param("Tname") String Tname);

@Update("UPDATE own SET num = num - 1 WHERE ownerId = #{ownerId}")
void decreaseNum(@Param("ownerId") int ownerId);
}
