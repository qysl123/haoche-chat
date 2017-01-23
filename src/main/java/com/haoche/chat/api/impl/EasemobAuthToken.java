package com.haoche.chat.api.impl;

import com.haoche.chat.api.AuthTokenAPI;
import com.haoche.chat.api.EasemobRestAPI;
import com.haoche.chat.comm.body.AuthTokenBody;
import com.haoche.chat.comm.constant.HTTPMethod;
import com.haoche.chat.comm.helper.HeaderHelper;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import com.haoche.chat.comm.wrapper.HeaderWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EasemobAuthToken extends EasemobRestAPI implements AuthTokenAPI {
	
	public static final String ROOT_URI = "/token";
	
	private static final Logger log = LoggerFactory.getLogger(EasemobAuthToken.class);
	
	@Override
	public String getResourceRootURI() {
		return ROOT_URI;
	}

	public Object getAuthToken(String clientId, String clientSecret) {
		String url = getContext().getSeriveURL() + getResourceRootURI();
		BodyWrapper body = new AuthTokenBody(clientId, clientSecret);
		HeaderWrapper header = HeaderHelper.getDefaultHeader();
		
		return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
	}
}
