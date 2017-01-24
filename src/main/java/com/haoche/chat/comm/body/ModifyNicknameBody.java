package com.haoche.chat.comm.body;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ModifyNicknameBody implements BodyWrapper {

    private String nickname;

    public ModifyNicknameBody(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBody() {
        Map<String, String> map = new HashMap<>();
        map.put("nickname", nickname);
        return JSONObject.toJSONString(map);
    }

    public Boolean validate() {
        return StringUtils.isNotBlank(nickname);
    }
}
