package com.glufine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by bohan123 on 2015/4/1/0001.
 */

import com.google.gson.Gson;

public class HttpRequest {
    private static String appkey = "6c3f37d523730a6e4649692a1dd610c5";
    private static String app_id = "1";
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        param = "app_id="+app_id+"&"+param;
        String result = "";
        BufferedReader in = null;
        String md5_params = "";
        try {
            md5_params = param;
            String str = param+appkey;
            String md5str = DigestUtils.md5Hex(str.getBytes());
            md5_params = md5_params+"&sign="+md5str;
            String urlNameString = url + "?" + md5_params;
            URL realUrl = new URL(urlNameString);
            HttpURLConnection connection = (HttpURLConnection) realUrl
                    .openConnection();
            // 打开和URL之间的连接
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url   发送请求的 URL
     * @param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String,Object> params, String type, OnSendPostListener listener) {
        
    	StringBuffer body = builtParamData(params,app_id,appkey);
        
        return sendMessage(url, type,  body.toString(),listener);
    }

	public static StringBuffer builtParamData(Map<String, Object> params,String appId,String appKeys) {
		params.put("app_id",appId);
        StringBuffer body = new StringBuffer();
        Map<String,Object> md5_params = new HashMap<String, Object>();
        md5_params.put("data",params);
        
        Gson gson = new Gson();
        String toJson = gson.toJson(params)+appKeys;
        String md5str = DigestUtils.md5Hex(toJson.getBytes());
        md5_params.put("sign",md5str);
        body.append(gson.toJson(md5_params));
		return body;
	}

	public static String sendMessage(String url, String type, String body, OnSendPostListener listener) {
        BufferedReader httpIn = null;
        OutputStream httpOut = null;
        String result = "";
        try {
            URL postUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) postUrl
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(type);
            httpOut = conn.getOutputStream();
            httpOut.write(body.getBytes());
            httpOut.flush();
            // 定义BufferedReader输入流来读取URL的响应
            httpIn = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = httpIn.readLine()) != null) {
                result += line;
            }

            listener.onSendSuccess(result);
        } catch (Exception e) {
            System.out.println("发送 "+type+" 请求出现异常！" + e);
            listener.onSendFail();
        }finally {
            try {
            	if(httpIn != null){
            		httpIn.close();
            	}
				if(httpOut != null){
					httpOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return result;
	}
	
    public static String sendPostNoMd5(String url, Map<String,Object> params, String type, OnSendPostListener listener) {
        String paramsStr = "";
        int size  = params.size();
        int i =0;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (++i == size){
                paramsStr += entry.getKey() + "=" + entry.getValue();
            }else{
                paramsStr += entry.getKey() + "=" + entry.getValue()+"&";
            }

        }
        return sendMessage(url, type,  paramsStr,listener);
    }

    public interface OnSendPostListener{
        public void onSendSuccess(String result);
        public void onSendFail();
    }

}