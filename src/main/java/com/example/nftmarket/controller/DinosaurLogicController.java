package com.example.nftmarket.controller;
import com.alibaba.fastjson2.JSONObject;
import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.repository.elasticsearch.DinosaurRepository;
import com.example.nftmarket.repository.mongodb.PersonRepository;
import com.example.nftmarket.service.Breeding;
import com.example.nftmarket.service.Hatched;
import com.example.nftmarket.service.PersonContent;
import com.example.nftmarket.utils.DinosaurRandomUtils;
import jakarta.annotation.Resource;
import jakarta.json.Json;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import org.bouncycastle.math.raw.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

//做相关的恐龙逻辑操作（孵化动作以及恐龙相关的交配操作）
@Controller
public class DinosaurLogicController {
    @Resource
    Hatched hatched;
    @Resource
    Breeding breeding;
    @Resource
    PersonContent personContent;
    private final DinosaurRepository dinosaurRepository;
    private final PersonRepository personRepository;

    public DinosaurLogicController(DinosaurRepository dinosaurRepository,
                                   PersonRepository personRepository) {
        this.dinosaurRepository = dinosaurRepository;
        this.personRepository = personRepository;
    }
    //此处暂时不进行相关的操作
    @ResponseBody
    @RequestMapping(value = "/getTheDinosaueEgg",method = RequestMethod.POST)
    public JSONObject getTheDinosaurEgg(@RequestParam(value = "userAdd",required = false)String userAddress){
        Person person=new Person();
        System.out.println("传递过来的地址信息"+userAddress);
        person.setPersonHash(userAddress);
        personContent.addTheDinosaurEgg(person);
        personContent.toHatchTheDinosaurEgg(person, 0);
        return new JSONObject();
    }
    //用户进行相关的蛋信息操作，也就是初始系统传递给用户的两个蛋
    @RequestMapping(value = "/getDinosaurInfo",method = RequestMethod.GET)
    public String setTheEggInfo(Model model,
                                @RequestParam(value = "userAdd",required = false)String userAddress){
        // TODO 根据用户地址然后查询用户是否含有恐龙蛋,如果有的话那么直接返回具有的恐龙蛋信息，如果没有系统则进行操作一下
        Person person=new Person();
        System.out.println("传递过来的地址信息"+userAddress);
        person.setPersonHash(userAddress);
        personContent.addTheDinosaurEgg(person);
        Dinosaur dinosaur = personContent.toHatchTheDinosaurEgg(person, 0);
        System.out.println(dinosaur);
        model.addAttribute("eggInfo", person.getDinosaurEggsRepository());
        model.addAttribute("dinosaurInfo", dinosaur);
        return "personDetails";
    }

    @RequestMapping("/hatch")
    public String hatchTheDinosaur(Person person, DinosaurEgg dinosaurEgg, DinosaurRandomUtils dinosaurRandomUtils){
        Dinosaur dinosaur = hatched.toHatch(person, dinosaurEgg, dinosaurRandomUtils);
        //TODO 将恐龙参数传递给合约进行交互操作
        return "null";
    }
    @RequestMapping("/breeding")
    public String breedingTheDinosaur(Person person,Dinosaur father,Dinosaur mother){
        DinosaurEgg dinosaurEgg = breeding.creatDinosaurEgg(person, father, mother);
        //TODO 孵化逻辑与合约进行交互操作
        //孵化恐龙操作
        return "null";
    }
}
