package com.glufine.service;

import java.util.Map;

import com.glufine.common.RouterKey;

/**
 * 路由消息bean定义接口
 * @author syj
 *
 */
public interface RouterBeanDefination {
    
    /**
     * 
     * 执行具体工作，内容为解析本层内容并根据需要是否需要执行下一层解析
     * @param key
     * @return
     */
    String doWork(RouterKey routerKey,Map<String,Object> session);
    /**
     * 执行具体工作，内容为解析本层内容并根据需要是否需要执行下一层解析
     * @param routerKey
     * @param session
     * @return
     * 		对象
     */
    Object doWorkReturnObject(RouterKey routerKey,Map<String,Object> session);
    

}
