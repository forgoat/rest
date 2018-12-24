package com.rest.support;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
public class UserInfo {
    private Long userId;
    private String userName;
    private String userType;

    public UserInfo(Long userId, String userName, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
    }
    public UserInfo(BigInteger userId, String userName, String userType) {
        String changeId=userId.toString();
        this.userId = Long.valueOf(changeId);
        this.userName = userName;
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
