package com.haoche.chat.comm.body;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class AuthTokenBody implements BodyWrapper {
	
	private String grantType = "client_credentials";
	
	private String clientId;
	
	private String clientSecret;

	public AuthTokenBody(String clientId, String clientSecret) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getBody() {
		Map<String, String> map = new HashMap<>();
		map.put("grant_type", grantType);
		map.put("client_id", clientId);
		map.put("client_secret", clientSecret);
		return JSONObject.toJSONString(map);
	}

	public Boolean validate() {
		return StringUtils.isNotBlank(clientId) && StringUtils.isNotBlank(clientSecret);
	}

}
