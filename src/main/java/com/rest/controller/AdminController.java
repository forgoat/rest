package com.rest.controller;

import com.rest.entity.Admin;
import com.rest.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "admin")
public class   AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping(value = "/adminlist")
    public List<Admin> adminList(){
        return adminService.adminList();
    }
    @GetMapping(value = "findbyid")
    public Admin findAdminById(Long id){
        return adminService.findAdminById(id);
    }

    @PostMapping(value = "/login")
    public HttpStatus login(String account, String password){
        System.out.print(account+password);
        return adminService.adminLogin(account,password);
    }
}
