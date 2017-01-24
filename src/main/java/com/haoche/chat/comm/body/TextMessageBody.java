package com.haoche.chat.comm.body;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.haoche.chat.comm.constant.MsgType;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class TextMessageBody extends MessageBody {
	private String msg;

	public TextMessageBody(String targetType, String[] targets, String from, Map<String, String> ext, String msg) {
		super(targetType, targets, from, ext);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

    public String getBody() {
		if (!this.isInit()) {
			Map<String, String> map = new HashMap<>();
			map.put("type", MsgType.CMD);
			map.put("msg", this.msg);
			this.setInit(true);
			this.getMsgBody().put("msg", map);
		}
		return JSONObject.toJSONString(this.getMsgBody());
    }

    public Boolean validate() {
		return super.validate() && StringUtils.isNotBlank(msg);
	}
}
