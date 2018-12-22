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
@Primary
public class AdminService {

    private final AdminDao adminDao;

    public AdminService(AdminDao adminDao){
        this.adminDao=adminDao;
    }
    public List<Admin> adminList(){
        return adminDao.adminlist();
    }
    public Admin findAdminById(BigInteger id){
        return adminDao.findAdminById(id);
    }
    public Admin findByName(String account){
        System.out.println("=========Admin findByName(String account)=======");
        return adminDao.findByName(account);
    }


}
