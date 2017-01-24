package com.haoche.chat.comm.body;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ModifyChatGroupBody implements BodyWrapper {
    private String groupName;
    private String desc;
    private Long maxUsers;

    public ModifyChatGroupBody(String groupName, String desc, Long maxUsers) {
        this.groupName = groupName;
        this.desc = desc;
        this.maxUsers = maxUsers;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDesc() {
        return desc;
    }

    public Long getMaxUsers() {
        return maxUsers;
    }

    public String getBody() {
        Map<String, Object> map = new HashMap<>();
        map.put("groupname", groupName);
        map.put("description", desc);
        map.put("maxusers", maxUsers);
        return JSONObject.toJSONString(map);
    }

    public Boolean validate() {
        return StringUtils.isNotBlank(groupName) && StringUtils.isNotBlank(desc) && null != maxUsers;
    }
}
