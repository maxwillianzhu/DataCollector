package com.glufine.common;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.glufine.service.RouterBeanDefination;
@Component("routerFactory")
public class RouterFactory implements ApplicationContextAware {
    
    private ApplicationContext applicationContext;
    /**
     * 获取RouterKey
     * @param value
     * @return
     */
    public RouterKey fetchRouterKey(String value,byte[] sourceByte){
        RouterTable.newInstence();
        Map<String,String> routerMapper =  RouterTable.getRouterMapperMap();
        Map<String,RouterKeyDefination> router = RouterTable.getRouterMap();
        //获取一级路由分发器
        RouterKeyDefination baseRouterKey = router.get("driverMapper");
        RouterKey baseRouter = new RouterKey(baseRouterKey,value,sourceByte);
        RouterSplitDefination baseRouterSplitDefination= baseRouter.getFirstKey();
        String routerMapperValue = routerMapper.get(baseRouterSplitDefination.getSpiltKey()+baseRouterSplitDefination.getSplitValue());
        //根据value获取指定的路由分发器
        RouterKeyDefination routerkey = router.get(routerMapperValue);
        if(routerkey==null){
            return null;
        }
        RouterKey resultRouter = new RouterKey(routerkey,value,sourceByte);
        return resultRouter; 
    }
    
    public RouterBeanDefination fetchRouterBeanDefination(String beanId){
        try {
            return (RouterBeanDefination) applicationContext.getBean(beanId); 
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
//        RouterFactory factory = new RouterFactory();
//        factory.fetchRouterKey("5A18000000100000000111806607316020216250053");
        System.out.println("5A18000000100000000111806607316020216250053".length());
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
