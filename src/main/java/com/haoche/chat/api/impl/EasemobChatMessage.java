package com.haoche.chat.api.impl;


import com.haoche.chat.api.ChatMessageAPI;
import com.haoche.chat.api.EasemobRestAPI;
import com.haoche.chat.comm.constant.HTTPMethod;
import com.haoche.chat.comm.helper.HeaderHelper;
import com.haoche.chat.comm.wrapper.HeaderWrapper;
import com.haoche.chat.comm.wrapper.QueryWrapper;

public class EasemobChatMessage extends EasemobRestAPI implements ChatMessageAPI {

    private static final String ROOT_URI = "/chatmessages";

    public Object exportChatMessages(Long limit, String cursor, String query) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        QueryWrapper queryWrapper = QueryWrapper.newInstance().addLimit(limit).addCursor(cursor).addQueryLang(query);

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, queryWrapper);
    }

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }
}
