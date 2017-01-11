package com.glufine.entity.message;

import java.io.Serializable;
import java.util.Map;

import com.glufine.common.RouterKey;

public class DegeleteMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -482911448145256360L;

	private RouterKey routerKey = null;
	
	private Map<String, Object> session = null;
	
	private int number;
	
	
	

	public DegeleteMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DegeleteMessage(RouterKey routerKey, Map<String, Object> session, int number) {
		super();
		this.routerKey = routerKey;
		this.session = session;
		this.number = number;
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RouterKey getRouterKey() {
		return routerKey;
	}

	public void setRouterKey(RouterKey routerKey) {
		this.routerKey = routerKey;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
