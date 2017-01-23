package com.haoche.chat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.haoche.chat.api.impl.EasemobChatRoom;
import com.haoche.chat.comm.ClientContext;
import com.haoche.chat.comm.EasemobRestAPIFactory;
import com.haoche.chat.comm.body.ChatRoomBody;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import com.haoche.chat.comm.wrapper.ResponseWrapper;
import com.haoche.chat.entity.ChatRoom;
import com.haoche.chat.entity.ChatUser;
import com.haoche.chat.service.ChatRoomService;
import org.springframework.stereotype.Service;

/**
 * Created by Ken on 2017/1/23.
 */
@Service("chatRoomService")
public class ChatRoomServiceImpl implements ChatRoomService {

    @Override
    public ChatRoom createRoom() {
        EasemobChatRoom easemobChatRoom = (EasemobChatRoom) ClientContext.getInstance().getAPIFactory().newInstance(EasemobRestAPIFactory.CHATROOM_CLASS);
        BodyWrapper bodyWrapper = new ChatRoomBody("测试聊天室");
        ResponseWrapper responseWrapper = (ResponseWrapper) easemobChatRoom.createChatRoom(bodyWrapper);
        return ((JSONObject) responseWrapper.getResponseBody()).getJSONArray("data").getObject(0, ChatRoom.class);
    }

    @Override
    public ChatRoom getRoom() {
        EasemobChatRoom easemobChatRoom = (EasemobChatRoom) ClientContext.getInstance().getAPIFactory().newInstance(EasemobRestAPIFactory.CHATROOM_CLASS);
        ResponseWrapper responseWrapper = (ResponseWrapper) easemobChatRoom.getAllChatRooms();
        return ((JSONObject) responseWrapper.getResponseBody()).getJSONArray("data").getObject(0, ChatRoom.class);
    }

    @Override
    public boolean addUserInRomm(ChatUser chatUser) {
        EasemobChatRoom easemobChatRoom = (EasemobChatRoom) ClientContext.getInstance().getAPIFactory().newInstance(EasemobRestAPIFactory.CHATROOM_CLASS);
        ResponseWrapper responseWrapper = (ResponseWrapper)easemobChatRoom.addSingleUserToChatRoom(String.valueOf(getRoom().getId()), chatUser.getChatUsername());
        return responseWrapper.hasError();
    }
}
