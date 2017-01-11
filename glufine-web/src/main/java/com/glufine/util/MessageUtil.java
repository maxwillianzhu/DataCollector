package com.glufine.util;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.glufine.entity.message.HttpSendMessage;
import com.glufine.entity.message.LogMessage;
import com.glufine.enums.Code;
import com.google.gson.Gson;

public class MessageUtil {
	private static Gson gson = new Gson();
	
	
	public final static String MESSAGE_SEND = "boxMessage.send";
	
	public static String MESSAGE_LOG = "log.#";

	/**
	 * 包装发送信息
	 * 
	 * @param date
	 * @param url
	 * @param type
	 * @return
	 */
	public static HttpSendMessage packHttpSendMessage(Object date, String url, String type) {
		String resultDate = "";
		if (date instanceof String) {
			resultDate = date.toString();
		} else {
			resultDate = gson.toJson(date);
		}
		HttpSendMessage message = new HttpSendMessage(url, resultDate);
		return message;
	}

	public static HttpSendMessage packHttpSendMessage(Object date, String url) {
		String type = "POST";
		return packHttpSendMessage(date, url, type);
	}

	public static String packHttpSendMessageReturnStr(Object date, String url, String type) {
		return gson.toJson(packHttpSendMessage(date, url, type));
	}

	public static String packHttpSendMessageReturnStr(Object date, String url) {
		return gson.toJson(packHttpSendMessage(date, url));
	}

	public static String packLogHttpSendMessageReturnStr(String code, String message,String sn, String url) {
		LogMessage logMessage = new LogMessage(code, message,sn);
		return packHttpSendMessageReturnStr(logMessage, url);
	}
	/**
	 * 包装日志数据
	 * @param code
	 * @param message
	 * @param sn
	 * @param url
	 * @return
	 */
	public static String packLogMessageReturnStr(String code, String message,String sn) {
		LogMessage logMessage = new LogMessage(code, message,sn);
		return gson.toJson(logMessage);
	}
	/**
	 * rabbit推送日志数据
	 * @param code
	 * @param url
	 * 			推送地址
	 * @param sn
	 * 			盒子号（11位）
	 * @param key
	 * 			路由表达key
	 * @param rabbitTemplate
	 * 			推送模版
	 */
	public static void rabbitLogSend(Code code,String url,String sn,String key,RabbitTemplate rabbitTemplate){
		String resultStr = MessageUtil.packLogHttpSendMessageReturnStr(code.getCode(), code.getMessage(), sn, url);
		rabbitTemplate.convertAndSend(key, resultStr);
	}
	
	public static void rabbitLogWrite(Code code,String sn,String key,RabbitTemplate rabbitTemplate){
		String resultStr = packLogMessageReturnStr(code.getCode(), code.getMessage(), sn);
		rabbitTemplate.convertAndSend(key, resultStr);
	}
}
