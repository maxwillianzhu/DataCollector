package com.glufine.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
//此类用于将 网页中的request对象封装到一个map中去,并重写了一些map的方法；
public class PageData extends HashMap implements Map{
	
	private static final long serialVersionUID = 1L;
	//将httpservletrequest中的页面参数值 遍历存储到map中
	Map map = null;
	HttpServletRequest request;
	//带参数的构造方法
	public PageData(HttpServletRequest request){
		this.request = request;
		//取得request中的参数map
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		//取得参数map的迭代器
		Iterator entries = properties.entrySet().iterator(); 
		Map.Entry entry; 
		String name = "";  
		String value = "";  
		//便利参数map，将
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next(); 
			name = (String) entry.getKey(); 
			Object valueObj = entry.getValue(); 
			//如果为空
			if(null == valueObj){ 
				value = ""; 
			}
			//如果value为数组对象，进行遍历存入value;
			else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value = values[i] + ",";
				}
				//截取字符串值，去掉最后一个逗号；
				value = value.substring(0, value.length()-1); 
			}else{
				value = valueObj.toString(); 
			}
			returnMap.put(name, value); 
		}
		map = returnMap;
	}
	
	public PageData() {
		map = new HashMap();
	}
	
	@Override
	//取得key对应的value方法
	public Object get(Object key) {
		Object obj = null;
		if(map.get(key) instanceof Object[]) {
			Object[] arr = (Object[])map.get(key);
			obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	//取得key对应的value的string类型
	public String getString(Object key) {
		return (String)get(key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	
	//往里边放入
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}
	
	@Override
	//移除
	public Object remove(Object key) {
		return map.remove(key);
	}
    //清空
	public void clear() {
		map.clear();
	}
     //判断是否有这个key
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}
     //判断是否有这个value
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}
	
}
