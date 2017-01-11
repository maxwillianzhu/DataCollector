package com.glufine.service.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glufine.common.RouterKey;
import com.glufine.dao.PressureMapper;
import com.glufine.entity.Pressure;
import com.glufine.service.RouterBeanDefination;
import com.glufine.util.DateUtil;
/**
 * 血压数据处理
 * @author syj
 *
 */
@Service("pressuredriver")
public class PressureDriverImpl implements RouterBeanDefination{
    @Autowired
    private PressureMapper pressureMapper;
    
	private static final Log log = LogFactory.getLog(PressureDriverImpl.class);

    public String doWork(RouterKey routerKey,Map<String,Object> param) {
        Map<String, Object> sourceMap = routerKey.generateDataMap();
        Pressure pressure = new Pressure();
        try {
            BeanUtils.populate(pressure, sourceMap);
            Date testDate = DateUtil.fomatDate(pressure.getYear()+pressure.getMonth()+pressure.getDay()+pressure.getHoure()+pressure.getMinute(), "yyMMddHHmm");
            pressure.setTestTime(testDate);
            pressure.setCreatTime(new Date());
            pressure.setDateKey(DateUtil.formatDate(testDate, "yyyyMMdd"));
            pressure.setTimeKey(DateUtil.formatDate(testDate, "HHmmss"));
            //保存数据
            pressureMapper.insert(pressure);
            String result =  generateResult();
            log.info(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        } 
    }
    private  String generateResult() {
        String result = "+IP";
        String Ip = "182.92.163.229.90.02";
        String[] ipSplits = Ip.split("\\.");
        String sum="";
        for(String ipSplit:ipSplits){
            String hex = strToHex(ipSplit);
            result += hex;
            if(sum.equals("")){
                sum = hex;
            }else{
                sum = sumHex(sum,hex);
            }
        }
        System.out.println(sum);
        result+=sum;
        String spiltDatesForment = "yy-MM-dd-HH-mm";
        String[] splitDates = spiltDatesForment.split("-");
        sum="";
        Date now = new Date();
        for(String splitDate:splitDates){
            String hex = dateToHex(now,splitDate);
            result += hex;
            if(sum.equals("")){
                sum = hex;
            }else{
                sum = sumHex(sum,hex);
            }
        }
        result+=sum;
        result+="OK";
        return result = result.toUpperCase();
    }
    
    private  String dateToHex(Date date,String formentType){
        return strToHex(DateUtil.formatDate(date, formentType));
    }
    
    private  String strToHex(String str){
        int intDate = Integer.parseInt(str);
        String hex = Integer.toHexString(intDate);
        if(hex.length()==1){
            hex = "0"+hex;
        }
        return hex;
    }
    
    private  String sumHex(String sum1,String sum2){
       return Long.toHexString(Long.parseLong(sum1, 16) ^ Long.parseLong(sum2, 16));
    }
    
	public Object doWorkReturnObject(RouterKey routerKey, Map<String, Object> session) {
		return null;
	}

}
