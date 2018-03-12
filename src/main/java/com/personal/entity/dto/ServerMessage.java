package com.personal.entity.dto;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/3/12 9:21
 * @Path: com.personal.entity.dto
 */
public class ServerMessage {
    private String responseMessage;

    public ServerMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
