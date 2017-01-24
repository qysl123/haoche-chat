package com.haoche.chat.comm.body;

import com.haoche.chat.comm.wrapper.BodyWrapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public abstract class MessageBody implements BodyWrapper {
    private Map<String, Object> msgBody;

    private String targetType;

    private String[] targets;

    private String from;

    private Map<String, String> ext;

    private boolean init = false;

    public MessageBody(String targetType, String[] targets, String from, Map<String, String> ext) {
        this.targetType = targetType;
        this.targets = targets;
        this.from = from;
        this.ext = ext;
    }

    public String getTargetType() {
        return targetType;
    }


    public String[] getTargets() {
        return targets;
    }

    public String getFrom() {
        return from;
    }

    public Map<String, String> getExt() {
        return ext;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    protected Map<String, Object> getMsgBody() {
        if (msgBody == null) {
            this.msgBody = new HashMap<>();
            msgBody.put("target_type", targetType);
            msgBody.put("target", targets);
            msgBody.put("from", from);

            if (null != ext) {
                msgBody.put("ext", ext);
            }
        }
        return msgBody;
    }

    public Boolean validate() {
        return StringUtils.isNotBlank(targetType) && isValidTargetType() && ArrayUtils.isNotEmpty(targets);
    }

    private boolean isValidTargetType() {
        return "users".equals(targetType) || "chatgroups".equals(targetType) || "chatrooms".equals(targetType);
    }
}
