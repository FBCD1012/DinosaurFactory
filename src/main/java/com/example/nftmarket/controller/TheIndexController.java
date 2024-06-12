package com.example.nftmarket.controller;


import com.alibaba.fastjson2.JSONObject;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.service.PersonContent;
import com.example.nftmarket.utils.JfreeUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TheIndexController {
    @Resource
    Person person;
    @Resource
    HttpServletRequest request;
    @Resource
    PersonContent personContent;

    @RequestMapping(value = "/")
    public String getIndex() throws InterruptedException {
        return "index";
    }
    //市场页面
    @RequestMapping(value = "/market")
    public String getTheMarketIndex(){
//        JfreeUtils.testPie();
//        JfreeUtils.testLine();
        return "market";
    }
    //个人信息页面
    @SneakyThrows
    @RequestMapping(value="/person",method = RequestMethod.GET)
    public String getThePersonInfo(Model model) {
        Object userAdd = request.getSession().getAttribute("userAdd");
        if (userAdd==null){
            return "blankIndex";
        }else {
            //TODO 此处调用合约逻辑检验用户是否具备恐龙蛋
            System.out.println("用户登陆成功");
            System.out.println(request.getSession().getAttribute("userAdd"));
        }
        person.setPersonHash((String) userAdd);
        model.addAttribute("egg", person.getDinosaurEggsRepository());
        model.addAttribute("personalDinosaur", personContent.getDinosaurInfo(person));
        //此处进行相关的Attribute逻辑判定操作
        return "person";
    }
    //市场页面
    @RequestMapping(value = "/button")
    public String getTheButtonIndex(){
        JfreeUtils.testPie();
        JfreeUtils.testLine();
        return "button";
    }

    //获取前端传递的地址参数信息
    //传递参数之后调用相关合约的参数进行实现
    @ResponseBody
    @RequestMapping(value = "/address",method = RequestMethod.POST)
    public JSONObject getIndex(@RequestParam("userAdd")String userAdd) {
        String userAdd1 = request.getParameter("userAdd");
        request.getSession().setAttribute("userAdd", userAdd1);
        System.out.println(userAdd);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        return jsonObject;
    }
}
