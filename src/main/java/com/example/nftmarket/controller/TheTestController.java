package com.example.nftmarket.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TheTestController {
    @ResponseBody
    @RequestMapping(value = "/")
    public String getIndex() throws InterruptedException {
        return "index";
    }
}
