package com.glufine.common;

import java.util.ArrayList;
import java.util.List;

public class RouterKeyDefination {
    /**
     * 路由表达key结果集
     */
    private List<RouterSplitDefination> keyDefinations = new ArrayList<RouterSplitDefination>();
    /**
     * 当前分发器所属类别
     */
    private String keyName;
    
    public RouterKeyDefination(String keyName) {
        super();
        this.keyName = keyName;
    }
    public String getKeyName() {
        return keyName;
    }
    public void putKey(RouterSplitDefination value){
        keyDefinations.add(value);
    }
    public void removeKey(RouterSplitDefination value){
        keyDefinations.remove(keyDefinations.indexOf(value));
    }
    public List<RouterSplitDefination> getKeyDefinations(){
        return keyDefinations;
    }
    
    public RouterSplitDefination getFirstSplitDefination(){
        return keyDefinations.get(0);
    }
    
    public RouterSplitDefination getSplitDefinationByindex(int index){
        return keyDefinations.get(index);
    }
    
}
