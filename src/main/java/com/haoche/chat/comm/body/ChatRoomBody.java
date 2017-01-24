package com.haoche.chat.comm.body;

import com.alibaba.fastjson.JSONObject;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ChatRoomBody implements BodyWrapper {
    private String name;
    private String desc;
    private Long maxUsers;
    private String owner;
    private String[] members;

    public ChatRoomBody(String name, String desc, Long maxUsers, String owner, String[] members) {
        this.name = name;
        this.desc = desc;
        this.maxUsers = maxUsers;
        this.owner = owner;
        this.members = members;
    }

    public ChatRoomBody(String name) {
        this.name = name;
        this.desc = "聊天室";
        this.owner = "test1";
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

    public String getOwner() {
        return owner;
    }

    public String[] getMembers() {
        return members;
    }

    public String getBody() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("description", desc);
        map.put("owner", owner);
        if (null != maxUsers) {
            map.put("maxusers", maxUsers);
        }
        if (ArrayUtils.isNotEmpty(members)) {
            map.put("members", members);
        }
        return JSONObject.toJSONString(map);
    }

    public Boolean validate() {
        return StringUtils.isNotBlank(name) && StringUtils.isNotBlank(desc) && StringUtils.isNotBlank(owner);
    }
}
