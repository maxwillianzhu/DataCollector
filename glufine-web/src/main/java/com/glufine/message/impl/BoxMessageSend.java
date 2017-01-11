package com.glufine.message.impl;

import com.glufine.message.MessageListen;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
/**
 * 盒子数据推送实现类
 * @author Administrator
 *
 */
public class BoxMessageSend implements MessageListen{
	
	private RabbitTemplate rabbitTemplate;
	
	private static final Log log = LogFactory.getLog(BoxMessageSend.class);
	
	public void doListen(final Object message){
		log.info(message);
		Gson gson = new Gson();
	}
}
