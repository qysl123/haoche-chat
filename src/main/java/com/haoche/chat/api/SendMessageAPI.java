package com.haoche.chat.api;


/**
 * This interface is created for RestAPI of Sending Message, it should be
 * synchronized with the API list.
 * 
 * @author Eric23 2016-01-05
 */
public interface SendMessageAPI {
	/**
	 * 发送消息 <br>
	 * POST
	 * 
	 * @param payload
	 *            消息体
	 * @return
	 */
	Object sendMessage(Object payload);
}
