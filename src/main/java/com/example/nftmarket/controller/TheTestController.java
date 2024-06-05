package com.example.nftmarket.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TheTestController {

    @RequestMapping(value = "/")
    public String getIndex() throws InterruptedException {
        return "index";
    }
    //市场页面
    @ResponseBody
    @RequestMapping(value = "/market")
    public String getTheMarketIndex(){
        return "market";
    }
    //个人信息页面
    @ResponseBody
    @RequestMapping(value="/personInfo")
    public String getThePersonInfo(){
        return "personInfo";
    }

}
