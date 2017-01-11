package com.glufine.entity.message;

import java.io.Serializable;
import java.util.Date;

/**
 * 推送日志消息
 * @author Administrator
 *
 */
public class LogMessage  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1440185676257197882L;

	private String code;
	
	private String message;
	
	private String sn;
	
	private Date time;
	
	public LogMessage() {
		super();
	}
	
	public LogMessage(String code, String message,String sn) {
		this(code,message,sn, new Date());
	}

	public LogMessage(String code, String message,String sn, Date time) {
		super();
		this.code = code;
		this.message = message;
		this.sn = sn;
		this.time = time;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
