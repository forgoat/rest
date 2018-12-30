package com.rest.mapper;

import com.rest.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    /**
     * 返回所有admin
     * @return
     */
     List<Admin> adminlist();
     Admin findAdminById(Long id);
     Admin findByName(@Param("account") String account);
}
