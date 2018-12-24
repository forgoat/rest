package com.rest.service;

import com.rest.dao.AdminDao;
import com.rest.entity.Admin;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;

    public List<Admin> adminList(){
        return adminDao.adminlist();
    }
    public Admin findAdminById(Long id){
        return adminDao.findAdminById(id);
    }
    public Admin findByName(String account){
        System.out.println("=========Admin findByName(String account)=======");
        return adminDao.findByName(account);
    }


}
