package com.glufine.util;

public class NumberUtil {
	/**
	 * 四舍六入五看后
	 * 
	 * @param sourceValue
	 * @return
	 */
	public static double numberForment4down6up5next(double sourceValue) {
		return numberFormentNext(sourceValue, 4, 6);
	}

	/**
	 * 7舍8入
	 * 
	 * @param sourceValue
	 * @return
	 */
	public static double numberForment7down8up(double sourceValue) {
		return numberFormentNext(sourceValue, 7, 8);
	}

	/**
	 * 舍入方式（舍入差为看后规则）
	 * 
	 * @param sourceValue
	 *            数据源
	 * @param down
	 *            舍
	 * @param up
	 *            入
	 * @return
	 */
	public static double numberFormentNext(final double sourceValue, final int down, final int up) {
		String valuestr = sourceValue + "";
		String[] subStr = valuestr.split("\\.");
		double targetValue = sourceValue;
		if (subStr.length == 2) {
			int high = Integer.parseInt(subStr[0]);
			int downFirst = Integer.parseInt(subStr[1].substring(0, 1).toString());
			for (int i = 1; i < subStr[1].length(); i++) {
				int nextValue = Integer.parseInt(subStr[1].substring(i, i + 1).toString());
				if (nextValue <= down) {
					break;
				}
				if (nextValue >= up) {
					downFirst += 1;
					break;
				}
			}
			double relTempvalue = high * 10 + downFirst;
			targetValue = relTempvalue / 10;
		}
		return targetValue;
	}
	/**
	 * 四舍五入向前舍入
	 * 		从最大长度开始逐步向前舍入
	 * @param sourceValue
	 * 			数据数据
	 * @param length
	 * 			最大读取长度
	 * @return
	 */
	public static double numberFormentForwead(final double sourceValue, final int length) {
		String valuestr = sourceValue + "";
		String[] subStr = valuestr.split("\\.");
		double targetValue = sourceValue;
		if (subStr.length == 2) {
			int high = Integer.parseInt(subStr[0]);
			int downFirst = Integer.parseInt(subStr[1].substring(0, 1).toString());
			String formentStr = subStr[1];
			if (formentStr.length() > length) {
				formentStr = formentStr.substring(0, length);
			}
			int allSteps = formentStr.length();
			int tempValue = 0;
			for (int i = allSteps; i > 1; i--) {
				int nextValue = Integer.parseInt(formentStr.substring(i - 1, i).toString()) + tempValue;
				if (nextValue <= 4) {
					tempValue = 0;
				}
				if (nextValue >= 5) {
					tempValue = 1;
				}
			}
			downFirst = downFirst + tempValue;
			double relTempvalue = high * 10 + downFirst;
			targetValue = relTempvalue / 10;
		}
		return targetValue;
	}

	public static void main(String[] args) {
		System.out.println(numberFormentForwead(1.244445,6));
	}
}
