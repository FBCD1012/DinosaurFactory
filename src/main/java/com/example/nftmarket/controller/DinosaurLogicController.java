package com.example.nftmarket.controller;
import com.example.nftmarket.entity.Dinosaur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//做相关的恐龙逻辑操作（孵化动作以及恐龙相关的交配操作）
@Controller
public class DinosaurLogicController {
    @RequestMapping("/hatch")
    public String hatchTheDinosaur(Dinosaur dinosaur){
        return "null";
    }
    @RequestMapping("/breeding")
    public String breedingTheDinosaur(){
        //孵化恐龙操作
        return "null";
    }
}
