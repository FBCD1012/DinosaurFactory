package com.example.nftmarket.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.service.Breeding;
import com.example.nftmarket.service.Hatched;
import com.example.nftmarket.service.PersonContent;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Hash;

import java.io.IOException;
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
    @Resource
    Person person;
    
    private static Dinosaur maledinosaur;
    private static Dinosaur femaledinosaur;

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
    public JSONObject hatchTheDinosaur(@RequestParam("dinosaurEggInfo")String eggId,HttpServletResponse httpServletResponse) throws IOException {
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
        Dinosaur dinosaur=Dinosaur
                .builder()
                .build()
                .setDinosaurId(Hash.sha3String("fbcd"))
                .setIsBreeding(true)
                .setDinosaurPhotoUri(null)
                .setDinosaurSex("MALE")
                .setSourceHash(null)
                .setSaleSate("空闲")
                .setDinosaurOwner(Hash.sha3String(String.valueOf(System.identityHashCode(person))));
        Dinosaur dinosaur1=Dinosaur
                .builder()
                .build()
                .setDinosaurId(Hash.sha3String("slz"))
                .setIsBreeding(true)
                .setDinosaurPhotoUri(null)
                .setDinosaurSex("FEMALE")
                .setSourceHash(null)
                .setSaleSate("空闲")
                .setDinosaurOwner(Hash.sha3String(String.valueOf(System.identityHashCode(person))));
        person.getMaleDinosaurRepository().add(dinosaur);
        person.getMaleDinosaurRepository().add(dinosaur1);
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
    @RequestMapping(value = "/breeding",method = RequestMethod.POST)
    public String breedingTheDinosaur(@RequestParam("fatherStringHash") String fartherString,
                                      @RequestParam("motherStringHash") String motherString,
                                      HttpServletResponse httpServletResponse) throws IOException {
        List<Dinosaur> maleDinosaurRepository = person.getMaleDinosaurRepository();
        List<Dinosaur> feMaleDinosaurRepository = person.getFeMaleDinosaurRepository();
        if (fartherString.equals("") || motherString.equals("")){
            httpServletResponse.getWriter().write("<script>alert('input is null')</script>");
        }
        for (Dinosaur maleDinosaurs:maleDinosaurRepository) {
            String trim = maleDinosaurs.getDinosaurId().substring(0, 16).trim();
            if (trim.equals(fartherString)){
                //将父恐龙抛出来
                maledinosaur=maleDinosaurs;
            }
        }
        for (Dinosaur femaleDinosaurs:feMaleDinosaurRepository) {
            String femaleString=femaleDinosaurs.getDinosaurId().substring(0, 16).trim();
            if (femaleString.equals(motherString)){
                //将母恐龙抛出
                femaledinosaur=femaleDinosaurs;
            }
        }
        DinosaurEgg dinosaurEgg = breeding.creatDinosaurEgg(person, maledinosaur, femaledinosaur);
        System.out.println(dinosaurEgg);

        //TODO 孵化逻辑与合约进行交互操作
        //孵化恐龙操作
        return "redirect:getTheDinosaurInfo";
    }
}
