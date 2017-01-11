package com.glufine.entity.message;

import java.io.Serializable;

public class HttpSendMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1053696930434475276L;
	/**
	 * 发送的url
	 */
	private String url = "";
	/**
	 * 发送的数据内容
	 */
	private String data = "" ;
	/**
	 * 发送的方式
	 */
	private String sendMethon = "POST";
	
	

	public HttpSendMessage() {
		super();
	}

	public HttpSendMessage(String url, String data) {
		super();
		this.url = url;
		this.data = data;
	}

	public HttpSendMessage(String url, String data, String sendMethon) {
		super();
		this.url = url;
		this.data = data;
		this.sendMethon = sendMethon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSendMethon() {
		return sendMethon;
	}

	public void setSendMethon(String sendMethon) {
		this.sendMethon = sendMethon;
	} 
}
