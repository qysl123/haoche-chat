package com.haoche.chat.comm.body;

import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.haoche.chat.comm.constant.MsgType;
import org.apache.commons.lang3.StringUtils;

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

	public ContainerNode<?> getBody() {
		if(!this.isInit()){
			ObjectNode msg = this.getMsgBody().putObject("msg");
			msg.put("type", MsgType.CMD);
			msg.put("action", action);
			this.setInit(true);
		}
		return this.getMsgBody();
	}

	@Override
	public Boolean validate() {
		return super.validate() && StringUtils.isNotBlank(action);
	}
}
