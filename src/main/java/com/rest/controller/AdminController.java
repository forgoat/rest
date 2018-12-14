package com.rest.controller;

import com.rest.entity.Admin;
import com.rest.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "admin")
public class AdminController {
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
    @GetMapping(value = "findByName")
    public Admin findByName(String account)
    {
        return adminService.findByName(account);
    }
    @GetMapping(value = "login")
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
