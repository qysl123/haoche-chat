package com.haoche.chat.service;

import com.haoche.chat.entity.ChatRoom;
import com.haoche.chat.entity.ChatUser;

/**
 * Created by Ken on 2017/1/23.
 */
public interface ChatRoomService {
    ChatRoom createRoom();

    ChatRoom getRoom();

    boolean addUserInRomm(ChatUser chatUser);
}
