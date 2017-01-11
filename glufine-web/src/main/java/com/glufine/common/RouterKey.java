package com.glufine.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 路由➹
 * @author syj
 *
 */
public class RouterKey {
    
    private RouterKeyDefination routerKeyDefination;
    //要处理的字符串
    private String sourceValue;
    
    private String routerKeyName;
    
    private Map<String, Object> dataMap;
    
    private byte[] sourceByte;
    
    //routerkey的结果集
    private  List<RouterSplitDefination> keyValues = new ArrayList<RouterSplitDefination>();

    private volatile int currentStep = 0;
    
    public RouterKey(RouterKeyDefination routerKeyDefination, String sourceValue,byte[] sourceByte) {
        super();
        this.routerKeyDefination = routerKeyDefination;
        this.sourceValue = sourceValue;
        this.routerKeyName = routerKeyDefination.getKeyName();
        this.sourceByte = sourceByte;
        cloneSplitDefination(routerKeyDefination.getKeyDefinations());
        generateKeyValues(sourceValue);
    }
    
    private void cloneSplitDefination(List<RouterSplitDefination> keys){
        for(RouterSplitDefination key:keys){
            keyValues.add(key.clone());
        }
    }
    /**
     * 获取当前路由器名次
     * @return
     */
    public String getRouterKeyName() {
        return routerKeyName;
    }

    //当前routerkey
    public RouterSplitDefination getCurrentKey(){
        return keyValues.get(currentStep);
    }
    
    public RouterSplitDefination getFirstKey(){
        return keyValues.get(0);
    }
    //下一个routerkey
    public RouterSplitDefination getNextKey(){
        return keyValues.get(currentStep+1);
    }
    /**
     * 返回下一个表达式，游标向下移动
     *  如果不存在，则返回空，游标不动
     * @return
     */
    public RouterSplitDefination moveNextKey(){
        currentStep += 1;
        if(currentStep >= keyValues.size()){
            currentStep -- ;
            return null;
        }
        return keyValues.get(currentStep);
    }
    private void generateKeyValues( String sourceValue){
        for(RouterSplitDefination keyDefination :keyValues){
            keyDefination.genarateSplitVale(sourceValue);
        }
    }
    public RouterKeyDefination getRouterKeyDefination() {
        return routerKeyDefination;
    }
    public String getSourceValue() {
        return sourceValue;
    }
    public int getCurrentStep() {
        return currentStep;
    }
    public List<RouterSplitDefination> getKeyValues() {
        return keyValues;
    }
    
    
    public byte[] getSourceByte() {
		return sourceByte;
	}

	public void setSourceByte(byte[] sourceByte) {
		this.sourceByte = sourceByte;
	}

	public void setSourceValue(String sourceValue) {
		this.sourceValue = sourceValue;
	}

	/**
     * 将数据包装成map集合
     * @return
     */
    public Map<String,Object> generateDataMap(){
        if(dataMap == null){
            dataMap = new HashMap<String,Object>();
            for(RouterSplitDefination bean:keyValues){
                dataMap.put(bean.getSpiltKey(), bean.getSplitValue());
            }
        }
        return dataMap;
    }
}
