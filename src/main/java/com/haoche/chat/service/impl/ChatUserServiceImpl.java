package com.haoche.chat.service.impl;

import com.haoche.chat.api.IMUserAPI;
import com.haoche.chat.comm.ClientContext;
import com.haoche.chat.comm.EasemobRestAPIFactory;
import com.haoche.chat.comm.wrapper.ResponseWrapper;
import com.haoche.chat.dao.ChatUserMapper;
import com.haoche.chat.entity.ChatUser;
import com.haoche.chat.service.ChatRoomService;
import com.haoche.chat.service.ChatUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("chatUserService")
public class ChatUserServiceImpl implements ChatUserService {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ChatUserServiceImpl.class);
    @Resource
    private ChatUserMapper chatUserMapper;
    @Resource
    private ChatRoomService chatRoomService;

    @Override
    public Boolean addChatUser(ChatUser chatUser) {
        IMUserAPI user = (IMUserAPI) ClientContext.getInstance().getAPIFactory().newInstance(EasemobRestAPIFactory.USER_CLASS);
        ResponseWrapper responseWrapper = (ResponseWrapper) user.createNewIMUserSingle(chatUser.createIMUser());
        if (responseWrapper.hasError()) {
            LOGGER.error("注册环信用户失败!" + responseWrapper.getResponseBody());
            return false;
        }
        if (chatRoomService.addUserInRomm(chatUser)) {
            LOGGER.error("注册环信用户成功加入默认聊天室失败!");
            return false;
        }
        chatUserMapper.insertChatUser(chatUser);
        return true;
    }

    @Override
    public ChatUser getChatUser(ChatUser chatUser) {
        return chatUserMapper.selectChatUser(chatUser);
    }
}