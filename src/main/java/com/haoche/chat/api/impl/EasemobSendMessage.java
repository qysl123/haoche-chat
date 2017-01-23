package com.haoche.chat.api.impl;


import com.haoche.chat.api.EasemobRestAPI;
import com.haoche.chat.api.SendMessageAPI;
import com.haoche.chat.comm.constant.HTTPMethod;
import com.haoche.chat.comm.helper.HeaderHelper;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import com.haoche.chat.comm.wrapper.HeaderWrapper;

public class EasemobSendMessage extends EasemobRestAPI implements SendMessageAPI {
    private static final String ROOT_URI = "/messages";

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }

    public Object sendMessage(Object payload) {
        String  url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
    }
}
