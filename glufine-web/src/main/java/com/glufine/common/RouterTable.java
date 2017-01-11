package com.glufine.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.glufine.util.PropertiesUtil;
/**
 * 路由表
 * @author syj
 *
 */
public class RouterTable {
    
    public static  RouterTable table = null;
    
    public static RouterTable newInstence(){
        if(table == null){
            table = new RouterTable();
        }
        return table;
    }
    //
    private static Map<String,String> routerMapperMap = new HashMap<String,String>();
    
    private static Map<String,RouterKeyDefination> routerMap =  new HashMap<String,RouterKeyDefination>();
    
    private RouterTable() {
        super();
        //路由匹配规则
        PropertiesUtil baseMapper = new PropertiesUtil("routeBaseMapper.properties");
        generateRouterMapperMap(baseMapper.getAllProperty());
        //路由key
        PropertiesUtil router = new PropertiesUtil("route.properties");
        generateRouterMap(router.getAllProperty());
    }
    /**
     * 获取如有分发解析集合
     *  规定特定的数据向分发到特定路由器
     * @return
     */
    public static Map<String, String> getRouterMapperMap() {
        return routerMapperMap;
    }
    /**
     * 获取所有的路由分发器
     * @return
     */
    public static Map<String, RouterKeyDefination> getRouterMap() {
        return routerMap;
    }
    
    private void generateRouterMap(Map<String,String> maps){
        Set<String> set = maps.keySet();
        for(String key:set){
            String value = maps.get(key);
            String[] splitvalues = value.split(";");
            RouterKeyDefination keyDefination = new RouterKeyDefination(key);
            for(String splitvalue : splitvalues){
                String[] result = splitvalue.split(",");
                RouterSplitDefination bean = new RouterSplitDefination(result[0],
                    Integer.parseInt(result[1]),Integer.parseInt(result[2]));
                keyDefination.putKey(bean);
            }
            routerMap.put(key, keyDefination);
        }
    };

    private void generateRouterMapperMap(Map<String,String> maps){
        Set<String> set = maps.keySet();
        for(String key:set){
            String value = maps.get(key);
            String[] splitvalues = value.split(";");
            for(String splitvalue : splitvalues){
                String[] result = splitvalue.split(",");
                routerMapperMap.put(result[0], result[1]);
            }
        }
    }
    
    public static void main(String[] args) {
        RouterTable.newInstence();
    }

}
