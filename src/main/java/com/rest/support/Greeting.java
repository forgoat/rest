package com.rest.support;
/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
public class Greeting {
    private String content;

    public Greeting(String content) {
        this.content = content;
    }

    public Greeting() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
