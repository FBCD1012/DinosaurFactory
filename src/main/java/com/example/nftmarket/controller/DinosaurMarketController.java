package com.example.nftmarket.controller;


import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DinosaurMarketController {
    //做es的搜索逻辑操作
    @ResponseBody
    @RequestMapping(value = "/searchTheDinosaur",method = RequestMethod.POST)
    public JSONObject getTheDinosaur(@RequestParam(value = "search",required = false)String searchInfo){
        System.out.println(searchInfo);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        return jsonObject;
    }
}
