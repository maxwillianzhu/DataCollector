package com.glufine.common;

public class RouterSplitDefination implements Cloneable{
    
    private String spiltKey;
    
    private String splitValue;
    
    private int start;
    
    private int end;

    public RouterSplitDefination(String spiltKey, int start, int end) {
        super();
        this.spiltKey = spiltKey;
        this.start = start;
        this.end = end;
    }
    
    public RouterSplitDefination() {
        super();
    }
    /**
     * 大于0正向截取,小于0,逆向截取
     * @param sourceValue
     */
    public void genarateSplitVale(String sourceValue){
    	if(end<0 || start<0){
    		end = sourceValue.length()+end;
    		start = sourceValue.length()+start;
    	}
        splitValue = sourceValue.substring(start, end);
    }

    public String getSplitValue() {
        return splitValue;
    }

    public void setSplitValue(String splitValue) {
        this.splitValue = splitValue;
    }

    public String getSpiltKey() {
        return spiltKey;
    }

    public void setSpiltKey(String spiltKey) {
        this.spiltKey = spiltKey;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
    
    public RouterSplitDefination clone() {  
        RouterSplitDefination o = null;  
        try {  
            o = (RouterSplitDefination) super.clone();  
        } catch (CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return o;  
    } 

}
