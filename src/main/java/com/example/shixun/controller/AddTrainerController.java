package com.example.shixun.controller;

import com.example.shixun.mapper.TrainerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddTrainerController {
    // 自动注入
    @Autowired
    private TrainerMapper trainerMapper;
    //**********添加新训练师**********

}
