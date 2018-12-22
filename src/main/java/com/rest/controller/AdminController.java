package com.rest.controller;

import com.rest.entity.Admin;
import com.rest.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.Principal;
import java.util.List;

@Controller
//@RequestMapping(value = "/admin")
public class   AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping(value = "adminlist")
    public List<Admin> adminList(){
        return adminService.adminList();
    }
    @GetMapping(value = "findbyid")
    public Admin findAdminById(BigInteger id){
        return adminService.findAdminById(id);
    }

    @RequestMapping(value = "/login")
    public String login()
    {
        return "a_Login.html";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/admin")
    @ResponseBody
    public String user(){
        return "a_ConStu.html";
    }


    @GetMapping(value = "/findByAccount")
    public String login(String account,String password){
        System.out.print(account+password);
        Admin admin=adminService.findByName(account);
        if(admin.getPassword().equals(password)){
            return "success";
        }
        else {
            return "error";
        }
    }
}
