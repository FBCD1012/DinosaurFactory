package com.example.nftmarket.controller.advice;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(NoSuchFieldException.class)
    public String getTheErrorInfo(Exception e , Model model){
        e.printStackTrace();
        model.addAttribute("errorInfo", "The corresponding NFT cannot be found");
        return "error";
    }

}
