package com.haoche.chat.comm.body;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ResetPasswordBody implements BodyWrapper {
    private String newPassword;

    public ResetPasswordBody(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getBody() {
        Map<String, String> map = new HashMap<>();
        map.put("newpassword", newPassword);
        return JSONObject.toJSONString(map);
    }

    public Boolean validate() {
        return StringUtils.isNotBlank(newPassword);
    }
}
