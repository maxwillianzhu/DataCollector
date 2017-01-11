package com.glufine.enums;

public enum DriverType {
    PRESSURE(1,"血压计");
    // 成员变量
    private String message;
    
    private int type;
    
    // 覆盖方法
    public String getMessage() {
        return message;
    }
    public int getType() {
        return type;
    }
    @Override
    public String toString() {
        return this.type + "_" + this.message;
    }
    private DriverType(int type, String message) {
        this.type = type;
        this.message = message;
    }
}
