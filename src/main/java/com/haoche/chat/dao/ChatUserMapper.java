package com.haoche.chat.dao;

import com.haoche.chat.entity.ChatUser;

/**
 * level_change_log
 * Created by Ken on 2016/11/11.
 */
public interface ChatUserMapper {
    ChatUser selectChatUser(ChatUser chatUser);

    Long insertChatUser(ChatUser chatUser);
}
