package com.haoche.chat.controller;

import com.haoche.chat.controller.advice.Template;
import com.haoche.chat.entity.ChatUser;
import com.haoche.chat.service.ChatRoomService;
import com.haoche.chat.service.ChatUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 控制器
 * Created by Ken on 2017/1/23.
 */
@Controller
@RequestMapping("/chat")
public class ChatController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

	@Resource
	private ChatUserService chatUserService;
    @Resource
    private ChatRoomService chatRoomService;
	
    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    @ResponseBody
    public Template register(@RequestBody ChatUser chatUser) {
        Template template = new Template();

		if(chatUserService.addChatUser(chatUser)){
			template.setSuccess(chatUser);
		}else{
			template.setFailMessage("注册失败!");
		}
		
        return template;
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public Template login(@RequestBody ChatUser chatUser) {
        Template template = new Template();
		ChatUser user = chatUserService.getChatUser(chatUser);
		if(user == null){
			template.setFailMessage("账号密码错误!");
		}else{
			template.setSuccess(user);
		}	
        return template;
    }

    @RequestMapping(value = "/getUser.do", method = RequestMethod.POST)
    @ResponseBody
    public Template getUser(@RequestBody ChatUser chatUser) {
        Template template = new Template();
		ChatUser user = chatUserService.getChatUser(chatUser);
		if(user == null){
			template.setFailMessage("获取用户失败错误!");
		}else{
			template.setSuccess(user);
		}
        return template;
    }

    @RequestMapping(value = "/addChatRoom.do", method = RequestMethod.POST)
    @ResponseBody
    public Template addChatRoom() {
        Template template = new Template();
        template.setData(chatRoomService.createRoom());
        return template;
    }

    @RequestMapping(value = "/getChatRoom.do", method = RequestMethod.POST)
    @ResponseBody
    public Template getChatRoom() {
        Template template = new Template();
        template.setData(chatRoomService.getRoom());
        return template;
    }
}
