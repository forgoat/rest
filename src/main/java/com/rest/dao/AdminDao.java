package com.rest.dao;

import com.rest.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface AdminDao {
    /**
     * 返回所有admin
     * @return
     */
    public List<Admin> adminlist();
    public Admin findAdminById(BigInteger id);
    public Admin findByName(String account);
}
