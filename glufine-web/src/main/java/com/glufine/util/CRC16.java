package com.glufine.util;

import java.math.BigDecimal;

/**
 * Created by bohan123 on 2015/3/16.
 */
public class CRC16 {
    private static char[] crc_tb = {
            0x0000, 0x1021, 0x2042, 0x3063, 0x4084, 0x50a5, 0x60c6, 0x70e7,
            0x8108, 0x9129, 0xa14a, 0xb16b, 0xc18c, 0xd1ad, 0xe1ce, 0xf1ef,
            0x1231, 0x0210, 0x3273, 0x2252, 0x52b5, 0x4294, 0x72f7, 0x62d6,
            0x9339, 0x8318, 0xb37b, 0xa35a, 0xd3bd, 0xc39c, 0xf3ff, 0xe3de,
            0x2462, 0x3443, 0x0420, 0x1401, 0x64e6, 0x74c7, 0x44a4, 0x5485,
            0xa56a, 0xb54b, 0x8528, 0x9509, 0xe5ee, 0xf5cf, 0xc5ac, 0xd58d,
            0x3653, 0x2672, 0x1611, 0x0630, 0x76d7, 0x66f6, 0x5695, 0x46b4,
            0xb75b, 0xa77a, 0x9719, 0x8738, 0xf7df, 0xe7fe, 0xd79d, 0xc7bc,
            0x48c4, 0x58e5, 0x6886, 0x78a7, 0x0840, 0x1861, 0x2802, 0x3823,
            0xc9cc, 0xd9ed, 0xe98e, 0xf9af, 0x8948, 0x9969, 0xa90a, 0xb92b,
            0x5af5, 0x4ad4, 0x7ab7, 0x6a96, 0x1a71, 0x0a50, 0x3a33, 0x2a12,
            0xdbfd, 0xcbdc, 0xfbbf, 0xeb9e, 0x9b79, 0x8b58, 0xbb3b, 0xab1a,
            0x6ca6, 0x7c87, 0x4ce4, 0x5cc5, 0x2c22, 0x3c03, 0x0c60, 0x1c41,
            0xedae, 0xfd8f, 0xcdec, 0xddcd, 0xad2a, 0xbd0b, 0x8d68, 0x9d49,
            0x7e97, 0x6eb6, 0x5ed5, 0x4ef4, 0x3e13, 0x2e32, 0x1e51, 0x0e70,
            0xff9f, 0xefbe, 0xdfdd, 0xcffc, 0xbf1b, 0xaf3a, 0x9f59, 0x8f78,
            0x9188, 0x81a9, 0xb1ca, 0xa1eb, 0xd10c, 0xc12d, 0xf14e, 0xe16f,
            0x1080, 0x00a1, 0x30c2, 0x20e3, 0x5004, 0x4025, 0x7046, 0x6067,
            0x83b9, 0x9398, 0xa3fb, 0xb3da, 0xc33d, 0xd31c, 0xe37f, 0xf35e,
            0x02b1, 0x1290, 0x22f3, 0x32d2, 0x4235, 0x5214, 0x6277, 0x7256,
            0xb5ea, 0xa5cb, 0x95a8, 0x8589, 0xf56e, 0xe54f, 0xd52c, 0xc50d,
            0x34e2, 0x24c3, 0x14a0, 0x0481, 0x7466, 0x6447, 0x5424, 0x4405,
            0xa7db, 0xb7fa, 0x8799, 0x97b8, 0xe75f, 0xf77e, 0xc71d, 0xd73c,
            0x26d3, 0x36f2, 0x0691, 0x16b0, 0x6657, 0x7676, 0x4615, 0x5634,
            0xd94c, 0xc96d, 0xf90e, 0xe92f, 0x99c8, 0x89e9, 0xb98a, 0xa9ab,
            0x5844, 0x4865, 0x7806, 0x6827, 0x18c0, 0x08e1, 0x3882, 0x28a3,
            0xcb7d, 0xdb5c, 0xeb3f, 0xfb1e, 0x8bf9, 0x9bd8, 0xabbb, 0xbb9a,
            0x4a75, 0x5a54, 0x6a37, 0x7a16, 0x0af1, 0x1ad0, 0x2ab3, 0x3a92,
            0xfd2e, 0xed0f, 0xdd6c, 0xcd4d, 0xbdaa, 0xad8b, 0x9de8, 0x8dc9,
            0x7c26, 0x6c07, 0x5c64, 0x4c45, 0x3ca2, 0x2c83, 0x1ce0, 0x0cc1,
            0xef1f, 0xff3e, 0xcf5d, 0xdf7c, 0xaf9b, 0xbfba, 0x8fd9, 0x9ff8,
            0x6e17, 0x7e36, 0x4e55, 0x5e74, 0x2e93, 0x3eb2, 0x0ed1, 0x1ef0
    };

    public static char caluCRC(byte[] pByte){
        int len = pByte.length;
        char crc;
        byte da;
        crc = 0x0;
        int i = 0;
        while(len-- != 0){
            da = (byte)(crc/256);
            crc <<= 8;
            int num = da ^pByte[i];
//                System.out.println("the num is: " + num);
            if(num < 0)
                num += 256;
            crc ^= crc_tb[num];
            ++i;
        }
        return crc;
    }

    public static String crc16Calu(byte crc){
        byte[] test = {crc};
        char ch = CRC16.caluCRC(test);
        int i = (int)ch;
        String str = Integer.toHexString(i);
        return str;
    }

    public static String crc16CaluByte(byte[] test){
        char ch = CRC16.caluCRC(test);
        int i = (int)ch;
        String str = Integer.toHexString(i);
        if (str.length() < 4){
            int length = 4 - str.length();
            for (int j = 0; j < length; j++){
                str = "0"+str;
            }
        }
        return str;
    }

    public static void main(String[] args) {

            String result = Utils.toStringHex("033030303130303030303133373076DE");
            System.out.println(result);
//            System.out.println(result.length());
//            System.out.println("1===== "+result.substring(0,1));
//            System.out.println("2===== "+result.substring(1,2));
//            System.out.println("3===== "+result.substring(2,5));
//            System.out.println("4===== "+result.substring(5,11));
//            System.out.println("5===== "+result.substring(11,14));
////            System.out.println("CRC整数类型的数据是:" + i);
//
//            //01 30 30 31 30 30 30 31 5D C6
//            System.out.println("CRC十六进制字符串是:" + CRC16.crc16CaluByte(new byte[]{(byte) 01, (byte) 0x30, (byte) 0x30,(byte) 0x31,(byte) 0x30,(byte) 0x30,(byte) 0x30,(byte) 0x31}));
//
//            String str2 = "063030303030313030303030313EBC";
//            String str3 = "07313530313238313631333031192C";
//            String str4 = "0831323334353637383930303031043A";
//
//            System.out.println(str2.length());
//            System.out.println(str3.length());
//            System.out.println(str4.length());
//            String str5 = str2+str3+str4;
//            System.out.println(str5);
//            System.out.println(str5.length());
//            System.out.println(str5.substring(0,30));
//            System.out.println(str5.substring(30,60));
//            System.out.println(str5.substring(60,92));
//            String result2 = Utils.toStringHex(str2);
//            String result3 = Utils.toStringHex(str3);
//            String result4 = Utils.toStringHex(str4);
//            System.out.println(result2);
//            System.out.println(result3);
//            System.out.println(result4);
//
//            System.out.println(result2.substring(1,7));
//            System.out.println(result2.substring(7,13));
//            System.out.println(result4.substring(1,10));
//            System.out.println(result4.substring(10,14));
//
//            String bloodInfo = "08313030313530313238313631336280";
//            System.out.println(bloodInfo.length());
//            String info = Utils.toStringHex(bloodInfo);
//            System.out.println(info);
//            System.out.println(info.substring(2,5));
//            System.out.println(info.substring(5,14));

//            System.out.println(Utils.toHexString("001"));
//            System.out.println(Utils.toHexString("0001"));
//            System.out.println(Utils.toStringHex("303031"));
//            System.out.println(Utils.toStringHex("30303031"));

//            float f1 = 5.800000000000001f;
//            BigDecimal   b  =   new BigDecimal(f1);
//            float   f2   =  b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
//            System.out.println(f2);

//        byte[] b = new byte[10];
//        System.out.println(b);
//        for (int i = 0; i<b.length; i++){
//            b[i] = 0x31;
//        }
//
//        String str = CRC16.crc16CaluByte(b);
//        System.out.println(str);
//        System.out.println(hexStringToBytes(str));
//        System.out.println(bytesToHexString(hexStringToBytes(str)));
//
//        byte b1 = (byte) Integer.parseInt(str, 16);
//        char c = CRC16.caluCRC(b);
//        System.out.println(b1);
    }

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if(src==null||src.length<=0){
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
