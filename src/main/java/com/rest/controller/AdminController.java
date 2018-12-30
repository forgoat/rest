package com.rest.controller;

import com.rest.entity.Admin;
import com.rest.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@AllArgsConstructor
//@RequestMapping(value = "/")
public class   AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping(value = "/adminlist")
    public List<Admin> adminList(){
        return adminService.adminList();
    }
//    @GetMapping(value = "findbyid")
//    public Admin findAdminById(Long id){
//        return adminService.findAdminById(id);
//    }

    @GetMapping(value = "/login" )
    public String login()
    {
        return "a_Login.html";
    }

    @GetMapping("/studentListPage")
    public String admin(){
        return "a_ConStu.html";
    }

//
//    @GetMapping("/admin")
//    public String user(){
//        return "a_ConStu.html";
//    }

//
//    @GetMapping(value = "/findByAccount")
//    public String login(String account,String password){
//        System.out.print(account+password);
//        Admin admin=adminService.findByName(account);
//        if(admin.getPassword().equals(password)){
//            return "success";
//        }
//        else {
//            return "error";
//        }
//    }
}
