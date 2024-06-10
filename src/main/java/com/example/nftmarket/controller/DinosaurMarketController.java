package com.example.nftmarket.controller;


import com.alibaba.fastjson2.JSONObject;
import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.Market;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.repository.jpa.DinosaurImageRepository;
import com.example.nftmarket.repository.mongodb.DinosaurRepository;
import com.example.nftmarket.repository.mongodb.PersonRepository;
import com.example.nftmarket.service.PersonContent;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    DinosaurRepository dinosaurRepository;
    private final PersonRepository personRepository;
    private final DinosaurImageRepository dinosaurImageRepository;

    public DinosaurMarketController(PersonRepository personRepository,
                                    DinosaurImageRepository dinosaurImageRepository) {
        this.personRepository = personRepository;
        this.dinosaurImageRepository = dinosaurImageRepository;
    }

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
    @RequestMapping(value = "/getTheDinosaur",method = RequestMethod.GET)
    public String getTheDinosaur(Model model){
        return "market";
    }
    //前端传递恐龙参数信息，然后将恐龙参数信息上架到对应的市场中进行操作
    @RequestMapping(value = "/insertTheDinosaur",method = RequestMethod.GET)
    public String insertTheDinosaur(@CookieValue(value = "dinosaurId")String dinosaurId, Model model){
        System.out.println("个人哈希值:"+person.getPersonHash());
        //TODO 将恐龙添加到市场上，然后通过合约进行恐龙信息的注入操作
        System.out.println(person);
        List<Dinosaur> maleDinosaurRepository = person.getMaleDinosaurRepository();
        List<Dinosaur> feMaleDinosaurRepository = person.getFeMaleDinosaurRepository();
        for (Dinosaur dinosaur: maleDinosaurRepository) {
            if (dinosaur.getDinosaurId().equals(dinosaurId)){
                market.getDinosaurs().add(dinosaur);
            }
        }
        for (Dinosaur dinosaur: feMaleDinosaurRepository) {
            if (dinosaur.getDinosaurId().equals(dinosaurId)){
                market.getDinosaurs().add(dinosaur);
            }
        }
//        dinosaurRepository.saveAll(market.getDinosaurs());
        //创建一个恐龙类
        model.addAttribute("eggInfo", person.getDinosaurEggsRepository());
        if (!person.getFeMaleDinosaurRepository().isEmpty()) {
            //为什么此处无法进行显示呢？
            model.addAttribute("dinosaurInfo", person.getMaleDinosaurRepository());
        }
        if (!person.getMaleDinosaurRepository().isEmpty()) {
            model.addAttribute("dinosaurInfo", person.getFeMaleDinosaurRepository());
        }
        return "personDetails";
    }
}
