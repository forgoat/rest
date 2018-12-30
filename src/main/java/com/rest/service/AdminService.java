package com.rest.service;

import com.rest.mapper.AdminMapper;
import com.rest.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public List<Admin> adminList(){
        return adminMapper.adminlist();
    }
    public Admin findAdminById(Long id){
        return adminMapper.findAdminById(id);
    }
    public Admin findByName(String account){
        System.out.println("=========Admin findByName(String account)=======");
        return adminMapper.findByName(account);
    }


}
