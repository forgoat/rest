package com.rest.entity;

import lombok.Data;

import java.math.BigInteger;
@Data
public class Admin {
    private BigInteger id;
    private String account;
    private String password;


    public Admin() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
