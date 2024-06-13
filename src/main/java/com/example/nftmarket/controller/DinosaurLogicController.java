package com.example.nftmarket.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.service.Breeding;
import com.example.nftmarket.service.ContractConnection;
import com.example.nftmarket.service.Hatched;
import com.example.nftmarket.service.PersonContent;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

//做相关的恐龙逻辑操作（孵化动作以及恐龙相关的交配操作）
@Controller
public class DinosaurLogicController {
    //注入合约进行相关的操作
    @Resource
    ContractConnection contractConnection;
    @Resource
    Hatched hatched;
    @Resource
    Breeding breeding;
    @Resource
    PersonContent personContent;
    @Resource
    Person person;

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

    //用户调用孵化逻辑，然后生成对应的恐龙蛋信息
    @ResponseBody
    @RequestMapping(value = "/hatch",method = RequestMethod.POST)
    public JSONObject hatchTheDinosaur(@RequestParam("dinosaurEggInfo")String eggId) {
        //根据龙蛋信息来对对应的龙蛋进行孵化操作
        //TODO 将恐龙参数传递给合约进行交互操作
        int i = Integer.parseInt(eggId);
        JSONObject jsonObject = new JSONObject();
        DinosaurEgg dinosaurEgg = person.getDinosaurEggsRepository().get(i);
        if (!dinosaurEgg.isHatched()){
            jsonObject.put("success",false);
        }else{
            personContent.toHatchTheDinosaurEgg(person, i);
            jsonObject.put("success",true);
        }
        return jsonObject;
    }

    @RequestMapping(value="/getTheDinosaurInfo",method = RequestMethod.GET)
    public String getTheDinosaur(Model model){
        //用户进行相关的蛋信息操作，也就是初始系统传递给用户的两个蛋
        if (person.getDinosaurEggsRepository().size() <2) {
            personContent.addTheDinosaurEgg(person);
        }
        model.addAttribute("eggInfo", person.getDinosaurEggsRepository());
        model.addAttribute("dinosaurInfo",personContent.getDinosaurInfo(person));
        model.addAttribute("DinosaurTitle","Your Dinosaur");
        return "personDetails";
    }

    //此处待定进行操作
    @RequestMapping(value = "/getTheUploadInfo",method = RequestMethod.GET)
    public String getTheUploadInfo(@CookieValue("dinosaurIndex")String dinosaurIndex, Model model){
        System.out.println(dinosaurIndex);
        int dinosaurIndex0=Integer.parseInt(dinosaurIndex);
        Dinosaur dinosaur = personContent.getDinosaurInfo(person).get(dinosaurIndex0);
        System.out.println(dinosaur);
        model.addAttribute("dinosaurUploadInfo", dinosaur);
        return "personDetails";
    }

    //实现恐龙之间的孵化逻辑操作 恐龙之间的孵化逻辑操作？？？
    //传递参数 恐龙性别 恐龙的对应的哈希值操作
    @ResponseBody
    @RequestMapping(value = "/breeding",method = RequestMethod.POST)
    public JSONObject breedingTheDinosaur(@RequestParam("DinosaurStringHash") String DinosaurStringOne,
                                          @RequestParam("DinosaurStringTwo") String DinosaurStringTwo) throws IOException {
        JSONObject jsonObject = new JSONObject();
        //获取总的恐龙类进行操作，然后实现其中的
       Dinosaur fatherDinosaur=Dinosaur.builder().build();
       Dinosaur motherDinosaur=Dinosaur.builder().build();
       List<Dinosaur> dinosaurInfo = personContent.getDinosaurInfo(person);
       //第一次迭代操作进行相关的恐龙寻找操作
       for (Dinosaur dinosaur : dinosaurInfo) {
           String dinosaurId=dinosaur.getDinosaurId().substring(0,16);
            if (dinosaurId.equals(DinosaurStringOne.substring(11, 27))){
                if (dinosaur.getDinosaurSex().equals("MALE")){
                    fatherDinosaur=dinosaur;
                }else {
                    motherDinosaur=dinosaur;
                    break;
                }
            }
        }
       for (int i = dinosaurInfo.size()-1; i>0;i--) {
           //倒序搜索相关的操作，然后根据其中的ID来对不同的恐龙进行处理操作
           if (dinosaurInfo.get(i).getDinosaurId().substring(0,16).trim()
                   .equals(DinosaurStringTwo.substring(11,27))){
               if(dinosaurInfo.get(i).getDinosaurSex().equals("MALE")){
                   fatherDinosaur=dinosaurInfo.get(i);
               }else{
                   motherDinosaur=dinosaurInfo.get(i);
               }
           }
       }
       //此处如何实现迭代进行操作理解？
        DinosaurEgg dinosaurEgg = breeding.creatDinosaurEgg(person, motherDinosaur, fatherDinosaur);
        System.out.println(dinosaurEgg);
//        System.out.println(dinosaurEgg);
        jsonObject.put("success",true);
        //TODO 孵化逻辑与合约进行交互操作
        //孵化恐龙操作
        return jsonObject;
    }
}
