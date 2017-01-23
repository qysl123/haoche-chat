package com.haoche.chat.comm.body;

import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.haoche.chat.comm.constant.MsgType;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class AudioMessageBody extends MessageBody {
    private String url;
    private String filename;
    private String secret;
    private Long length;

    public AudioMessageBody(String targetType, String[] targets, String from, Map<String, String> ext, String url, String filename, String secret, Long length) {
        super(targetType, targets, from, ext);
        this.url = url;
        this.filename = filename;
        this.secret = secret;
        this.length = length;
    }

    public String getUrl() {
        return url;
    }

    public String getFilename() {
        return filename;
    }

    public String getSecret() {
        return secret;
    }

    public Long getLength() {
        return length;
    }

    public ContainerNode<?> getBody() {
        if (!this.isInit()) {
            ObjectNode msg = this.getMsgBody().putObject("msg");
            msg.put("type", MsgType.AUDIO);
            msg.put("url", url);
            msg.put("filename", filename);
            msg.put("secret", secret);
            msg.put("length", length);
            this.setInit(true);
        }

        return this.getMsgBody();
    }

    @Override
    public Boolean validate() {
        return super.validate() && StringUtils.isNoneBlank(url) && StringUtils.isNoneBlank(filename) && StringUtils.isNoneBlank(secret) && null != length;
    }
}
