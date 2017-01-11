package com.glufine.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glufine.service.RouterBeanDefination;

/**
 * 路由控制器
 * 
 * @author syj
 *
 */
@Component("routerMonitor")
public class RouterMonitor {
	@Autowired
	private RouterFactory routerFactory;

	/**
	 * 执行分发工作
	 */
	public String doRouter(String value, byte[] sourceByte, Map<String, Object> session) {
		// 获取指定的routerKey
		RouterKey routerKey = routerFactory.fetchRouterKey(value, sourceByte);
		if (routerKey == null) {
			return "error";
		}
		RouterBeanDefination bean = findBeanDefination(routerKey);
		if (bean != null) {
			return bean.doWork(routerKey, session);
		}
		return "error";
	};

	/**
	 * 获取到对应的beanDefination 只获取第一个，接下的有用户自己去操作
	 * 规则：查询当前key,如果不存在，查询nextKey，如果不存在，返回null
	 * 
	 * @param routerKey
	 *            路由➹
	 * @return
	 */
	private RouterBeanDefination findBeanDefination(RouterKey routerKey) {
		// spring 命名规则
		String beanId = creatSpringBeanId(routerKey.getRouterKeyName(), routerKey.getCurrentKey().getSpiltKey());
		RouterBeanDefination bean = routerFactory.fetchRouterBeanDefination(beanId);
		while (bean == null) {
			RouterSplitDefination cuurentStep = routerKey.moveNextKey();
			String splitKey = cuurentStep != null ? cuurentStep.getSpiltKey() : null;
			if (splitKey == null) {
				break;
			}
			beanId = creatSpringBeanId(routerKey.getRouterKeyName(), splitKey);
			bean = routerFactory.fetchRouterBeanDefination(beanId);
		}
		return bean;
	}

	private String creatSpringBeanId(String id1, String id2) {
		return id1 + id2;
	}

}
