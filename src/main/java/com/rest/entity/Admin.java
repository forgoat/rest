package com.rest.entity;

public class Admin {
    private Long id;
    private String account;
    private String password;


    public Admin() {
    }

    public com.rest.po.Admin getAdmin(){
        com.rest.po.Admin admin=new com.rest.po.Admin();
        admin.setId(id);
        admin.setAccount(account);
        admin.setPassword(password);
        return admin;
    }
    public Admin(com.rest.po.Admin admin){
        id=admin.getId();
        account=admin.getAccount();
        password=admin.getPassword();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
