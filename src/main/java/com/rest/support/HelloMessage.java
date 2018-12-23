package com.rest.support;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
public class HelloMessage {
    private String name;

    public HelloMessage(String name) {
        this.name = name;
    }

    public HelloMessage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
