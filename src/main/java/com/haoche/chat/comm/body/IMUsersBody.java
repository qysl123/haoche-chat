package com.haoche.chat.comm.body;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class IMUsersBody implements BodyWrapper {
	
	private List<IMUserBody> users;
	
	public IMUsersBody(List<IMUserBody> users) {
		super();
		this.users = users;
	}
	
	public String getBody() {
		return JSONObject.toJSONString(users);
	}

	public Boolean validate() {
		if( null == users || users.isEmpty() ) {
			return Boolean.FALSE;
		}
		
		for( IMUserBody user : users ) {
			if( null == user || !user.validate() ) {
				return Boolean.FALSE;
			}
		}
		
		return Boolean.TRUE;
	}

}
