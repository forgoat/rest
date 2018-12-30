package com.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value = "/index")
    public String index(){
        return "index.html";
    }
    @RequestMapping(value = "/login")
    public String adminLogin(){
        return "a_Login.html";
    }
}
