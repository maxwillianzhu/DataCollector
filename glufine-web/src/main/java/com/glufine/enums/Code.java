package com.glufine.enums;

public enum Code {
    
    OK("0","成功"),
    
    ERROR("1","失败"),

    START_ALADY("2","已经启动")
    ;
    // 成员变量
    private String message;
    
    private String code;
    
    // 覆盖方法
    public String getMessage() {
        return message;
    }
    public String getCode() {
        return code;
    }
    @Override
    public String toString() {
        return this.code + "_" + this.message;
    }
    private Code(String code, String message) {
        this.code = code;
        this.message = message;
    }
    /**
     * 通过索引获得名称
     * @param index
     * @return
     */
    public static String getMessageByCode(String code) {    //    手写的从int到enum的转换函数
        try {
            for(Code bean:Code.values()){
                if(bean.getCode().equals(code)){
                    return bean.getMessage();
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    /**
     * 通过code码查询code
     * @param code
     * @return
     */
    public static Code getCodeEnumByCode(String code) {    //    手写的从int到enum的转换函数
        try {
            for(Code bean:Code.values()){
                if(bean.getCode().equals(code)){
                    return bean;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    private static  String writeTable(){
    	StringBuffer htmlString = new StringBuffer();
    	htmlString.append("[{Table \n \n");
    	htmlString.append("||Code编码 \n");
    	htmlString.append("||Code说明  \n");
    	htmlString.append("||备注 \n \n");
    	for(Code bean:Code.values()){
    		htmlString.append("|");
    		htmlString.append(bean.getCode()+"\n");
    		htmlString.append("|");
    		htmlString.append(bean.getMessage()+"\n");
    		htmlString.append("| \n \n");
    	}
    	
    	htmlString.append("}] \n");
    	return htmlString.toString();
    }
    
    public static void main(String[] args) {
    	System.out.println(writeTable());
	}

}
