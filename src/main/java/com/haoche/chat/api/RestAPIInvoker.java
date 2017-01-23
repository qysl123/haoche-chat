package com.haoche.chat.api;

import com.haoche.chat.comm.wrapper.BodyWrapper;
import com.haoche.chat.comm.wrapper.HeaderWrapper;
import com.haoche.chat.comm.wrapper.QueryWrapper;
import com.haoche.chat.comm.wrapper.ResponseWrapper;

public interface RestAPIInvoker {
	ResponseWrapper sendRequest(String method, String url, HeaderWrapper header, BodyWrapper body, QueryWrapper query);
}
