package com.haoche.chat.comm.body;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ChatGroupBody implements BodyWrapper {
    private String groupName;
    private String desc;
    private Boolean isPublic;
    private Long maxUsers;
    private Boolean approval;
    private String owner;
    private String[] members;

    public ChatGroupBody(String groupName, String desc, Boolean isPublic, Long maxUsers, Boolean approval, String owner, String[] members) {
        this.groupName = groupName;
        this.desc = desc;
        this.isPublic = isPublic;
        this.maxUsers = maxUsers;
        this.approval = approval;
        this.owner = owner;
        this.members = members;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDesc() {
        return desc;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public Long getMaxUsers() {
        return maxUsers;
    }

    public Boolean getApproval() {
        return approval;
    }

    public String getOwner() {
        return owner;
    }

    public String[] getMembers() {
        return members;
    }

    public String getBody() {
        Map<String, Object> map = new HashMap<>();
        map.put("groupname", groupName);
        map.put("desc", desc);
        map.put("public", isPublic);
        map.put("approval", approval);
        map.put("owner", owner);
        if(null != maxUsers) {
            map.put("maxusers", maxUsers);
        }
        if(ArrayUtils.isNotEmpty(members)) {
            map.put("members", members);
        }
        return JSONObject.toJSONString(map);
    }

    public Boolean validate() {
        return StringUtils.isNotBlank(groupName) && StringUtils.isNotBlank(desc) && null != isPublic && null != approval && StringUtils.isNotBlank(owner);
    }
}
