package com.haoche.chat.comm.invoker;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.haoche.chat.api.RestAPIInvoker;
import com.haoche.chat.comm.ClientContext;
import com.haoche.chat.comm.MessageTemplate;
import com.haoche.chat.comm.constant.HTTPMethod;
import com.haoche.chat.comm.utils.RestAPIUtils;
import com.haoche.chat.comm.wrapper.BodyWrapper;
import com.haoche.chat.comm.wrapper.HeaderWrapper;
import com.haoche.chat.comm.wrapper.QueryWrapper;
import com.haoche.chat.comm.wrapper.ResponseWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpClientRestAPIInvoker implements RestAPIInvoker {

    private static final Logger log = LoggerFactory.getLogger(HttpClientRestAPIInvoker.class);

    public ResponseWrapper sendRequest(String method, String url, HeaderWrapper header, BodyWrapper body, QueryWrapper query) {

        ResponseWrapper responseWrapper = new ResponseWrapper();
        ObjectNode responseNode = JsonNodeFactory.instance.objectNode();

        responseWrapper.setResponseBody(responseNode);

        if (!HTTPMethod.METHOD_GET.equalsIgnoreCase(method) && !HTTPMethod.METHOD_POST.equalsIgnoreCase(method) && !HTTPMethod.METHOD_PUT.equalsIgnoreCase(method) && !HTTPMethod.METHOD_DELETE.equalsIgnoreCase(method)) {
            String msg = MessageTemplate.print(MessageTemplate.UNKNOWN_TYPE_MSG, new String[]{method, "HTTP methods"});
            responseWrapper.addError(msg);
        }
        if (StringUtils.isBlank(url)) {
            String msg = MessageTemplate.print(MessageTemplate.BLANK_OBJ_MSG, new String[]{"Parameter url"});
            responseWrapper.addError(msg);
        }
        if (!RestAPIUtils.match("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", url)) {
            String msg = MessageTemplate.print(MessageTemplate.INVAILID_FORMAT_MSG, new String[]{"Parameter url"});
            responseWrapper.addError(msg);
        }
        if (null != body && !body.validate()) {
            responseWrapper.addError(MessageTemplate.INVALID_BODY_MSG);
        }
        if (responseWrapper.hasError()) {
            return responseWrapper;
        }

        log.debug("=============Request=============");
        log.debug("Method: " + method);
        log.debug("URL: " + url);
        log.debug("Header: " + header);
        log.debug("Body: " + ((null == body) ? "" : body.getBody()));
        log.debug("Query: " + query);
        log.debug("===========Request End===========");

        url = buildQuery(url, query);
        String cacertFilePath = ClientContext.getInstance().getCacertFilePath();
        String cacertFilePassword = ClientContext.getInstance().getCacertFilePassword();
        HttpClient client = RestAPIUtils.getHttpClient(StringUtils.startsWithIgnoreCase(url, "HTTPS"), cacertFilePath, cacertFilePassword);

        URL target;
        try {
            target = new URL(url);
        } catch (MalformedURLException e) {
            responseWrapper.addError(e.getMessage());
            return responseWrapper;
        }

        HttpUriRequest request;
        HttpResponse response;
        try {
            switch (method) {
                case HTTPMethod.METHOD_POST:
                    request = new HttpPost(target.toURI());
                    break;
                case HTTPMethod.METHOD_PUT:
                    request = new HttpPut(target.toURI());
                    break;
                case HTTPMethod.METHOD_GET:
                    request = new HttpGet(target.toURI());
                    break;
                case HTTPMethod.METHOD_DELETE:
                    request = new HttpDelete(target.toURI());
                    break;
                default:
                    String msg = MessageTemplate.print(MessageTemplate.UNKNOWN_TYPE_MSG, new String[]{method, "Http Method"});
                    log.error(msg);
                    throw new RuntimeException(msg);
            }
        } catch (URISyntaxException e) {
            responseWrapper.addError(e.getMessage());
            return responseWrapper;
        }

        if (null != body && null != body.getBody()) {
            ((HttpEntityEnclosingRequestBase) request).setEntity(new StringEntity(body.getBody(), "UTF-8"));
        }
        buildHeader(request, header);

        try {
            response = client.execute(request);
        } catch (IOException e) {
            responseWrapper.addError(e.getMessage());
            return responseWrapper;
        }

        responseWrapper = readResponse(responseWrapper, response, false);

        log.debug("=============Response=============");
        log.debug(responseWrapper.toString());
        log.debug("===========Response End===========");
        return responseWrapper;
    }

    private void buildHeader(HttpUriRequest request, HeaderWrapper header) {
        if (null != header && !header.getHeaders().isEmpty()) {
            for (NameValuePair nameValuePair : header.getHeaders()) {
                request.addHeader(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
    }

    private String buildQuery(String url, QueryWrapper query) {
        if (null != url && null != query && !query.getQueries().isEmpty()) {
            url += "?";
            for (NameValuePair nameValuePair : query.getQueries()) {
                url += nameValuePair.getName() + "=" + nameValuePair.getValue();
                url += "&";
            }
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }

    private ResponseWrapper readResponse(ResponseWrapper responseWrapper, HttpResponse response, boolean isFile) {
        ObjectNode responseNode;
        HttpEntity entity = response.getEntity();
        if (null != entity) {
            responseWrapper.setResponseStatus(response.getStatusLine().getStatusCode());

            Object responseContent;
            try {
                if (isFile) {
                    responseContent = entity.getContent();
                } else {
                    responseContent = EntityUtils.toString(entity, "UTF-8");
                    EntityUtils.consume(entity);
                }
            } catch (ParseException e) {
                responseWrapper.addError(e.getMessage());
                return responseWrapper;
            } catch (IOException e) {
                responseWrapper.addError(e.getMessage());
                return responseWrapper;
            }

            responseWrapper.setResponseBody(JSONObject.parseObject(responseContent.toString()));
        }
        return responseWrapper;
    }

}
