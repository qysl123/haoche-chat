package com.haoche.chat.entity;

import com.haoche.chat.comm.body.IMUserBody;
import com.haoche.chat.comm.wrapper.BodyWrapper;

import java.io.Serializable;

/**
 * 用户
 * Created by Ken on 2017/1/23.
 */
public class ChatUser implements Serializable{
    private static final long serialVersionUID = 6706270116211029736L;

    private Long id;
    private String phone;
    private String password;
    private String chatUsername;
    private String chatPassword;
    private String nickName;
    private String userIcon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChatUsername() {
        return chatUsername;
    }

    public void setChatUsername(String chatUsername) {
        this.chatUsername = chatUsername;
    }

    public String getChatPassword() {
        return chatPassword;
    }

    public void setChatPassword(String chatPassword) {
        this.chatPassword = chatPassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public IMUserBody createIMUser(){
        chatUsername = "haoche" + phone;
        chatPassword = password;
        nickName = chatUsername;
        return new IMUserBody(chatUsername, chatPassword, nickName);
    }
}
