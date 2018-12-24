package com.rest.dao;

import com.rest.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface AdminDao {
    /**
     * 返回所有admin
     * @return
     */
     List<Admin> adminlist();
     Admin findAdminById(Long id);
     Admin findByName(@Param("account") String account);
}
