package com.zjhj.maxapp.screensame.util;

public class EventBean {
    private String msg;
    private String content;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public EventBean(String msg, String content, Integer type) {
        this.msg = msg;
        this.content = content;
        this.type = type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EventBean(String msg, String content) {
        this.msg = msg;
        this.content = content;
    }

    public EventBean(String msg, Integer type) {
        this.msg = msg;
        this.type = type;
    }

    public EventBean(String msg) {
        this.msg = msg;
    }

    public EventBean() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
