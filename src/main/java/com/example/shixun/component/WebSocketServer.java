package com.example.shixun.component;

import com.example.shixun.component.MessageConverter;
import com.example.shixun.entity.Message;
import com.example.shixun.mapper.TrainerMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint(value = "/chatWindow.html/{friendName}/{friendId}")
@Component
public class WebSocketServer {

    @Autowired
    private TrainerMapper trainerMapper;
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    //记录当前连接
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();


    //连接建立成功后会的调用的方法
    @OnOpen
    public void onOpen(Session session, @PathParam("friendName") String friendName, @PathParam("friendId") String friendId) {


        this.trainerMapper = SpringContexHolder.getBean(TrainerMapper.class);
        sessionMap.put(friendName, session);
        //查询自己的ID
        int userID = trainerMapper.getUserIDWeb(friendName);
        //查询历史消息id
        int history_messages_id = trainerMapper.getMessageIDWeb(userID, friendId);
        //查询历史消息，提取出来
        List<Message> history_messages = trainerMapper.getHistoryMessages(history_messages_id);
        JSONArray jsonArray = MessageConverter.convertMessagesToJSON(history_messages);
        //查询我的头像地址和好友的头像地址
        String myAvatar = trainerMapper.getAvatarWeb(userID);
        String friendAvatar = trainerMapper.getAvatarWeb(Integer.parseInt(friendId));
        //将历史消息发送给好友

        //向当前建立连接的客户端发送消息
        JSONObject message = new JSONObject();
        try {
            message.put("type", "connect");
            message.put("friendName", friendName);
            message.put("myAvatar", myAvatar);
            message.put("friendAvatar", friendAvatar);
            message.put("history_messages", jsonArray);
            session.getBasicRemote().sendText(message.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*
        //向所有客户端(除自己以外)发送消息
        for (Map.Entry<String, Session> entry : sessionMap.entrySet()) {
            JSONObject message2 = new JSONObject();
            if (!entry.getKey().equals(friendName)) {
                try {
                    message2.put("type", "connect_2");
                    message2.put("message", "正在与您对话中...");
                    message2.put("friendName", friendName);
                    entry.getValue().getBasicRemote().sendText(message2.toString());
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }
        }*/


    }

    //接收客户端消息
    @OnMessage
    public void onMessage(String message, Session session,@PathParam("friendName") String friendName, @PathParam("friendId") String friendId) {

        this.trainerMapper = SpringContexHolder.getBean(TrainerMapper.class);
        //将当前连接添加到sessionMap中
        sessionMap.put(friendName, session);

        //查询好友姓名
        String friendName2 = trainerMapper.getFriendNameWed(friendId);
        //查询客户端的ID
        int userID = trainerMapper.getUserIDWeb(friendName);
        //查询历史消息id
        int history_messages_id = trainerMapper.getMessageIDWeb(userID, friendId);
        //查询历史消息，插入进去
        LocalDateTime currentTime = LocalDateTime.now();
        int result = trainerMapper.insertMessage(history_messages_id, message, friendName, currentTime);
        //查询客户端的头像
        String userAvatar = trainerMapper.getUserAvatarWeb(userID);

        //向指定客户端发送消息
        for (Map.Entry<String, Session> entry : sessionMap.entrySet()) {
                if(entry.getKey().equals(friendName2)) {
                    JSONObject message2 = new JSONObject();
                    try {
                        message2.put("type", "friend_message");
                        message2.put("message", message);
                        message2.put("userAvatar", userAvatar);
                        message2.put("friendName", session.getPathParameters().get("friendName"));
                        entry.getValue().getBasicRemote().sendText(message2.toString());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
                if(entry.getKey().equals(friendName)) {
                    JSONObject message2 = new JSONObject();
                    try {
                        message2.put("type", "my_message");
                        message2.put("message", message);
                        message2.put("userAvatar", userAvatar);
                        message2.put("friendName", session.getPathParameters().get("friendName"));
                        entry.getValue().getBasicRemote().sendText(message2.toString());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
        }
        System.out.println("服务器结束onMessage方法");
    }

    //连接关闭后调用的方法
    @OnClose
    public void onClose(Session session) {
        //从sessionMap中移除当前连接
        sessionMap.remove(session.getPathParameters().get("friendName"));

        //向所有客户端发送消息
        for (Map.Entry<String, Session> entry : sessionMap.entrySet()) {
            JSONObject message2 = new JSONObject();
            try {
                message2.put("type", "disconnect");
                message2.put("message", "退出聊天");
                message2.put("friendName", session.getPathParameters().get("friendName"));
                entry.getValue().getBasicRemote().sendText(message2.toString());
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        }

    }

    //发生错误时调用的方法
    @OnError
    public void onError(Session session, Throwable error) {
        //log.error("WebSocket error", error);
    }


}

