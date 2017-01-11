package com.glufine.message.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.glufine.message.MessageListen;

public class LogSearch implements MessageListen{
	
	private static final Log log = LogFactory.getLog(LogSearch.class);
	
	public void doListen(Object message) {
		// TODO Auto-generated method stub
		log.info(message);
	}

}
