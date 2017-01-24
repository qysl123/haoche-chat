package com.haoche.chat.comm.body;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ModifyChatRoomBody implements BodyWrapper {
    private String name;
    private String desc;
    private Long maxUsers;

    public ModifyChatRoomBody(String name, String desc, Long maxUsers) {
        this.name = name;
        this.desc = desc;
        this.maxUsers = maxUsers;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Long getMaxUsers() {
        return maxUsers;
    }

    public String getBody() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("description", desc);
        map.put("maxusers", maxUsers);
        return JSONObject.toJSONString(map);
    }

    public Boolean validate() {
        return StringUtils.isNotBlank(name) && StringUtils.isNotBlank(desc) && null != maxUsers;
    }
}
