package com.haoche.chat.comm.body;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.haoche.chat.comm.wrapper.BodyWrapper;

public class UserNamesBody implements BodyWrapper {
    private String[] users;

    public UserNamesBody(String[] users) {
        this.users = users;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public String getBody() {
        return JSONObject.toJSONString(users);
    }

    public Boolean validate() {
        return null != users && users.length > 0;
    }
}
