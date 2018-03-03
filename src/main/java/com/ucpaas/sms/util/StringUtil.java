package com.ucpaas.sms.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author huangwenjie
 * @create 2016-12-31-19:01
 */
public class StringUtil {

    public static boolean isEmpty(String... strings) {
        for (String s : strings) {
            if (s == null || "".equals(s.trim()))
                return true;
        }
        return false;
    }

    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
//    public static String getMD5(String str) throws NoSuchAlgorithmException {
//        // 生成一个MD5加密计算摘要
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        // 计算md5函数
//        md.update(str.getBytes());
//        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
//        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
//        return new BigInteger(1, md.digest()).toString(16);
//    }
    
    public static String getHostFromURL(String url){
		String host = "";
		Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");  
        Matcher matcher = p.matcher(url);  
        if (matcher.find()) {  
            host = matcher.group();  
        }  
		return host; 
	}
    
    
    /**
	 * 对字符串md5加密
	 *
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
    public static String getMD5(String str) throws NoSuchAlgorithmException {
    	MessageDigest messageDigest =MessageDigest.getInstance("MD5");
    	
    	// 输入的字符串转换成字节数组
    	byte[] inputByteArray = str.getBytes();
    	
    	// inputByteArray是输入字符串转换得到的字节数组
    	messageDigest.update(inputByteArray);
    	
    	// 转换并返回结果，也是字节数组，包含16个元素
    	byte[] resultByteArray = messageDigest.digest();
    	
    	// 字符数组转换成字符串返回
    	return byteArrayToHex(resultByteArray);
    }

    
	public static String byteArrayToHex(byte[] byteArray) {
		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];
		
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}
    
    
}
