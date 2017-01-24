package com.haoche.chat.comm.body;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class GroupOwnerTransferBody implements BodyWrapper {
    private String newOwner;

    public GroupOwnerTransferBody(String newOwner) {
        this.newOwner = newOwner;
    }

    public String getNewOwner() {
        return newOwner;
    }

    public String getBody() {
        Map<String, String> map = new HashMap<>();
        map.put("newowner", newOwner);
        return JSONObject.toJSONString(map);
    }

    public Boolean validate() {
        return StringUtils.isNotBlank(newOwner);
    }
}
