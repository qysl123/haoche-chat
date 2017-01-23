package com.haoche.chat.service;

import com.haoche.chat.entity.ChatUser;

public interface ChatUserService{
	Boolean addChatUser(ChatUser chatUser);
	ChatUser getChatUser(ChatUser chatUser);
}