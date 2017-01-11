package com.glufine.util;

import java.util.Date;

public class HexStringUtil {
	/**
	 * 日期转换为16hex
	 * @param date
	 * @param formentType
	 * @return
	 */
	public static String dateToHex(Date date, String formentType) {
		return strToHexFix0(DateUtil.formatDate(date, formentType));
	}
	/**
	 * 16hex转为字符串，长度为1时，自动补位
	 * @param str
	 * @return
	 */
	public static String strToHexFix0(String str) {
		int intDate = Integer.parseInt(str);
		String hex = Integer.toHexString(intDate);
		if (hex.length() == 1) {
			hex = "0" + hex;
		}
		return hex;
	}
	/**
	 * int 转16进制字符串，长度为1时，自动补0
	 * @param value
	 * @return
	 */
	public static String intToHexFix0(int value) {
		String hex = Integer.toHexString(value);
		if (hex.length() == 1) {
			hex = "0" + hex;
		}
		return hex;
	}
	/**
	 * 16进制转字符串
	 * @param str
	 * @return
	 */
	public static String strToHex(String str) {
		int intDate = Integer.parseInt(str);
		String hex = Integer.toHexString(intDate);
		return hex;
	}
	/**
	 * 16进制与或运算
	 * @param sum1
	 * @param sum2
	 * @return
	 */
	public static String sumHex(String sum1, String sum2) {
		return Long.toHexString(Long.parseLong(sum1, 16) ^ Long.parseLong(sum2, 16));
	}
	
	public static void main(String[] args) {
		System.out.println(Integer.toHexString(0x05));
	}

}
