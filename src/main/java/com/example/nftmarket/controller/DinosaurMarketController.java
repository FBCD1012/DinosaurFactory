package com.example.nftmarket.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.entity.Market;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.repository.elasticsearch.DinosaurMarketRepository;
import com.example.nftmarket.service.PersonContent;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DinosaurMarketController {
    @Resource
    Person person;
    @Resource
    PersonContent personContent;
    @Resource
    Market market;

    @Resource
    DinosaurMarketRepository dinosaurMarketRepository;


    //做es的搜索逻辑操作
    @ResponseBody
    @RequestMapping(value = "/searchTheDinosaur",method = RequestMethod.POST)
    public JSONObject searchTheDinosaur(@RequestParam(value = "search",required = false)String searchInfo){
        System.out.println(searchInfo);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        return jsonObject;
    }
    //根据传入的参数来进行模糊查询操作，实现ElasticSearch集成操作
    @RequestMapping(value = "/getTheDinosaur/{searchValue}",method = RequestMethod.GET)
    public String getTheDinosaur(@PathVariable("searchValue")String dinosaurFiledValue,Model model){
        Dinosaur dinosaur = dinosaurMarketRepository.findById(dinosaurFiledValue).get();
        model.addAttribute("dinosaur", dinosaur);
        return "market";
    }
    //前端传递恐龙参数信息，然后将恐龙参数信息上架到对应的市场中进行操作
    @ResponseBody
    @RequestMapping(value = "/insertTheDinosaur",method = RequestMethod.POST)
    public JSONObject insertTheDinosaur(@RequestParam(value = "dinosaurHalfId")String dinosaurId){
        System.out.println(dinosaurId);
        List<Dinosaur> theDinosaurEgg = personContent.getDinosaurInfo(person);
        for (Dinosaur dinosaur:theDinosaurEgg) {
            if (dinosaur.getDinosaurId().substring(0,16).equals(dinosaurId)){
                //存储到es中，然后将此数据放入合约中进行存储
                dinosaurMarketRepository.save(dinosaur);
            }
        }
        //TODO 将恐龙添加到市场上，然后通过合约进行恐龙信息的注入操作
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("success", true);
        return jsonObject;
    }
}
