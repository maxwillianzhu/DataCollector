package com.glufine.enums;

public enum Status {
    ERROR(0,"失败"),
    OK(1,"成功");
    // 成员变量
    private String message;
    
    private int status;
    
    // 覆盖方法
    public String getMessage() {
        return message;
    }
    public int getStatus() {
        return status;
    }
    @Override
    public String toString() {
        return this.status + "_" + this.message;
    }
    private Status(int type, String message) {
        this.status = type;
        this.message = message;
    }
}
