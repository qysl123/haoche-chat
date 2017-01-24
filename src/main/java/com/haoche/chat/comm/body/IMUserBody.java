package com.haoche.chat.comm.body;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class IMUserBody implements BodyWrapper {
	
	private String userName;
	
	private String password;
	
	private String nickName;
	
	public IMUserBody(String userName, String password, String nickName) {
		super();
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String getBody() {
		Map<String, String> map = new HashMap<>();
		map.put("username", userName);
		map.put("password", password);
		map.put("nickname", nickName);
		return JSONObject.toJSONString(map);
	}

	@Override
	public Boolean validate() {
		return StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password);
	}

}
