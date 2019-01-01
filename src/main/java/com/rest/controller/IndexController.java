package com.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value = "/index")
    public String index(){
        return "index.html";
    }
    @GetMapping("/admin")
    public String user(){
        return "a_ConStu.html";
    }
    @GetMapping(value = "/login" )
    public String login()
    {
        return "a_Login.html";
    }
    @GetMapping("/studentListPage")
    public String admin(){
        return "a_ConStu.html";
    }
    @RequestMapping(value = "/client")
    public String websocket(){
        return "Client.html";
    }
    @RequestMapping(value = "/seminarPPT")
    public String seminarFile(){
        return "s_SemSubmit.html";
    }
    @RequestMapping(value = "/seminarInfo")
    public String seminarInfo(){
        return "s_SemInfoW.html";
    }

}
