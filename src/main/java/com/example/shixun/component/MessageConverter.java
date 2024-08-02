package com.example.shixun.component;

import com.example.shixun.entity.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MessageConverter {

    public static JSONArray convertMessagesToJSON(List<Message> messages) {
        JSONArray jsonArray = new JSONArray();
        for (Message message : messages) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("messageID", message.getMessageID());
            jsonObject.put("message", message.getMessage());
            jsonObject.put("senderName", message.getSenderName());
            jsonObject.put("time", message.getTime().toString());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
}
