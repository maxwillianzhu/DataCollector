package com.glufine.message.impl;

import com.glufine.entity.message.DegeleteMessage;
import com.glufine.message.MessageListen;
import com.glufine.service.RouterBeanDefination;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 血糖数据解析，因为盒子要求立马返回
 * 
 * @author Administrator
 *
 */
public class BoxMessageMessageDegelate implements MessageListen {
	public static String PUT = "PUT";

	public static String POST = "POST";
	/**
	 * 分割基准量
	 */
	final int BLOODSPILTCOUNT = 14;
	/**
	 * 首个分割位置
	 */
	final int FIRSTSTARTSPLIT = 2;
	/**
	 * 结束分割标志符
	 */
	final String ENDCODE = "@";

	@Autowired
	private RouterBeanDefination bloodInfobloodValue;


//	@Autowired
//	private RabbitTemplate rabbitTemplate;
	private static final Log log = LogFactory.getLog(BoxMessageMessageDegelate.class);
	@SuppressWarnings("unchecked")
	public void doListen(Object message) {
		Gson gson = new Gson();
		DegeleteMessage degeleteMessage  = gson.fromJson(message.toString(), DegeleteMessage.class);
	}
}
