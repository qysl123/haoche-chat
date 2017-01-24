package com.haoche.chat.comm.body;

import com.alibaba.fastjson.JSONObject;
import com.haoche.chat.comm.constant.MsgType;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class CommandMessageBody extends MessageBody {
    private String action;

    public CommandMessageBody(String targetType, String[] targets, String from, Map<String, String> ext, String action) {
        super(targetType, targets, from, ext);
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public String getBody() {
        if (!this.isInit()) {
            Map<String, String> map = new HashMap<>();
            map.put("type", MsgType.CMD);
            map.put("action", action);
            this.setInit(true);
            this.getMsgBody().put("msg", map);
        }
        return JSONObject.toJSONString(this.getMsgBody());
    }

    @Override
    public Boolean validate() {
        return super.validate() && StringUtils.isNotBlank(action);
    }
}
