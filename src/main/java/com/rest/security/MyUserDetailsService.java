package com.rest.security;

import com.rest.entity.Admin;
import com.rest.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
<<<<<<< Updated upstream
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
=======
>>>>>>> Stashed changes
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final AdminService adminService;

    @Autowired
    MyUserDetailsService(AdminService adminService){
        this.adminService = adminService;
    }

    @Override
<<<<<<< Updated upstream
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        System.out.println("+++++++++++++++++public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException");

        Admin admin = adminService.findByName(account);
        System.out.println("account:"+account+"  password:"+admin.getPassword());

        //密码加密
        BCryptPasswordEncoder util = new BCryptPasswordEncoder();
        String password = util.encode(admin.getPassword());

        if (admin == null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities("ROLE_USER");
        return new User(admin.getAccount(), password, simpleGrantedAuthorities);
=======
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.findByName(username);
        if (admin == null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities("ADMIN");
        return new User(admin.getAccount(), admin.getPassword(), simpleGrantedAuthorities);
>>>>>>> Stashed changes
    }

    /**
     * 权限字符串转化
     *
     * 如 "USER,ADMIN" -> SimpleGrantedAuthority("USER") + SimpleGrantedAuthority("ADMIN")
     *
     * @param roleStr 权限字符串
     */
    private List<SimpleGrantedAuthority> createAuthorities(String roleStr){
        String[] roles = roleStr.split(",");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String role : roles) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return simpleGrantedAuthorities;
    }

}
