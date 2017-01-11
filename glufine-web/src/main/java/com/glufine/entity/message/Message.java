package com.glufine.entity.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.glufine.enums.Code;
/**
 * 前后端交互协议
 * @author syj
 *
 */
public class Message  implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 3156783447697510345L;

	private  String code = Code.OK.getCode();
    
    private  Map<String,Object> data = new HashMap<String,Object>();
    
    private static final String MESSAGE = "message";
    

    private Message(String code) {
        this.code = code;
    }

    private Message(){
    }
    /**
     * 默认成功
     * @return
     */
    public static Message newOk(){
        return new Message(Code.OK.getCode());
    }
    /**
     * 默认失败
     * @return
     */
    public static Message newDefaultError(){
        return new Message(Code.ERROR.getCode());
    }
    /**
     * 根据code码设置信息
     * 	不默认填充信息
     * @param code
     * @return
     */
    public static Message newCodeMessage(String code){
        return new Message(code);
    }
    /**
     * 根据code码设置信息
     * 	默认填充信息
     * @param code
     * 		Code
     * @return
     */
    public static Message newCodeWithMessage(Code code){
        Message message =  new Message(code.getCode());
        message.putData(MESSAGE, code.getMessage());
        return message;
    }
    /**
     * 根据code码设置信息
     * 	默认填充信息
     * @param strCode
     * 			String
     * @return
     */
    public static Message newCodeWithMessage(String strCode){
    	Code code = Code.getCodeEnumByCode(strCode);
    	if(code == null){
    		return null;
    	}
        return newCodeWithMessage(code);
    }
    /**
     * 插入数据
     * @param key
     * @param value
     */
    public  void putData(String key,Object value){
        data.put(key, value);
    }
    /**
     * 查询数据
     * @param key
     * @return
     */
    public  Object findData(String key){
        return data.get(key);
    }

    public  String getCode() {
        return code;
    }

    public  void setCode(String code) {
        this.code = code;
    }

    public  Map<String, Object> getData() {
        return data;
    }

    public  void setData(Map<String, Object> data) {
        this.data = data;
    }
}
