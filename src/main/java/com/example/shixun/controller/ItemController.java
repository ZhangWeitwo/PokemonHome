package com.example.shixun.controller;

import com.example.shixun.entity.ItemInfo;
import com.example.shixun.entity.TradeItem;
import com.example.shixun.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {
    @Autowired
    private ItemMapper itemMapper;

    //获取道具图鉴所有道具
    @GetMapping("/allItems")
    public Map<String, List<ItemInfo>> getAllItems() {
        List<ItemInfo> itemInfos = itemMapper.findAllItemInfo();

        Map<String, List<ItemInfo>> response = new HashMap<>();
        response.put("items", itemInfos);

        return response;
    }
    //获取道聚城所有道具
    @GetMapping("/TradeItems")
    public Map<String, List<TradeItem>> getTradeItems() {
        List<TradeItem> itemInfos = itemMapper.findAllTradeItem();

        Map<String, List<TradeItem>> response = new HashMap<>();
        response.put("items", itemInfos);

        return response;
    }

}