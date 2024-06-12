package com.example.nftmarket.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.Market;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.repository.elasticsearch.DinosaurMarketRepository;
import com.example.nftmarket.service.PersonContent;
import jakarta.annotation.Resource;
import jakarta.json.Json;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DinosaurMarketController {
    @Resource
    Person person;
    @Resource
    PersonContent personContent;
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
        List<Dinosaur> theDinosaur = personContent.getDinosaurInfo(person);
        for (Dinosaur dinosaur:theDinosaur) {
            if (dinosaur.getDinosaurId().substring(0,16).equals(dinosaurId)){
                dinosaur.setSaleSate("售卖中");
                //存储到es中，然后将此数据放入合约中进行存储
                dinosaurMarketRepository.save(dinosaur);
            }
        }
        //TODO 将恐龙添加到市场上，然后通过合约进行恐龙信息的注入操作
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("success", true);
        return jsonObject;
    }
    //获取后端的操作来实现推荐价格的操作
    @ResponseBody
    @RequestMapping(value = "/getTheRecommendedPrice",method = RequestMethod.POST)
    public String getTheRecommendedPrice(@RequestParam(value = "dId")String dId){
        List<Dinosaur> theDinosaur = personContent.getDinosaurInfo(person);
        Double dinosaurPrice=0.0;
        for (Dinosaur dinosaur:theDinosaur) {
            if (dinosaur.getDinosaurId().substring(0,16).equals(dId)){
                dinosaurPrice = dinosaur.getDinosaurPrice();
                System.out.println(dinosaurPrice);
            }
        }
        return dinosaurPrice.toString();
    }
    @ResponseBody
    @RequestMapping(value = "/changeTheDPrice",method = RequestMethod.POST)
    public JSONObject changeTheDPrice(@RequestParam(value = "dinosaurIds")String dinosaurId,
                                @RequestParam(value = "changePrice")String changePrice){
        JSONObject jsonObject=new JSONObject();
        List<Dinosaur> theDinosaur = personContent.getDinosaurInfo(person);
        System.out.println(changePrice);
        System.out.println(dinosaurId);
        for (Dinosaur dinosaur:theDinosaur) {
            if (dinosaur.getDinosaurId().substring(0,16).equals(dinosaurId)){
                dinosaur.setDinosaurPrice(Double.parseDouble(changePrice));
                Optional<Dinosaur> byId = dinosaurMarketRepository.findById(dinosaur.getDinosaurId());
                byId.get().setDinosaurPrice(Double.parseDouble(changePrice));
            }
        }
        jsonObject.put("success", true);
        return jsonObject;
    }
    @RequestMapping(value = "/removedShelves",method = RequestMethod.POST)
    public JSONObject removeTheShelves(@RequestParam(value = "dinosaurId")String dinosaurId){
        JSONObject jsonObject=new JSONObject();
        List<Dinosaur> theDinosaur = personContent.getDinosaurInfo(person);
        for (Dinosaur dinosaur:theDinosaur) {
            if (dinosaur.getDinosaurId().substring(0,16).equals(dinosaurId)){
                dinosaur.setSaleSate("空闲");
                //根据查询出来的ID来删除对应的恐龙
                dinosaurMarketRepository.deleteById(dinosaur.getDinosaurId());
            }
        }
        jsonObject.put("success",true);
        return jsonObject;
    }
}
