package com.viewhigh.util;

import cn.hutool.core.util.StrUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * string工具类
 */
public class Strings {
    /**
     * 将字符串转换为boolean类型，为空时使用默认值
     *
     * @param value        需要转换的内容
     * @param defaultValue 默认值，未设定时为false
     * @return
     * @author seven
     */
    public static boolean toBoolean(String value, boolean defaultValue) {
        boolean ret = defaultValue;
        try {
            ret = Boolean.parseBoolean(value);
        } catch (Exception e) {
        }
        return ret;
    }

    /**
     * 将字符串转换为boolean类型，为空时使用默认值
     *
     * @param value 需要转换的内容
     * @return
     * @author seven
     */
    public static boolean toBoolean(String value) {
        return toBoolean(value, DefaultValue.BOOLEAN_DEFAULT);
    }

    /**
     * 将字符串转换为int类型，为空时使用默认值
     *
     * @param value        需要转换的内容
     * @param defaultValue 默认值，未设定时为-1
     * @return
     * @author seven
     */
    public static int toInt(String value, int defaultValue) {
        int ret = defaultValue;
        try {
            ret = Integer.parseInt(value);
        } catch (Exception e) {
        }
        return ret;
    }

    /**
     * 将字符串转换为int类型，为空时使用默认值
     *
     * @param value 需要转换的内容
     * @return
     * @author seven
     */
    public static int toInt(String value) {
        return toInt(value, DefaultValue.INT_DEFAULT);
    }

    /**
     * 将字符串转换为int类型，为空时使用默认值
     *
     * @param value        需要转换的内容
     * @param defaultValue 默认值，未设定时为-1
     * @return
     * @author seven
     */
    public static Long toLong(String value, Long defaultValue) {
        Long ret = defaultValue;
        try {
            ret = Long.parseLong(value);
        } catch (Exception e) {
        }
        return ret;
    }

    /**
     * 将字符串转换为Long类型，为空时使用默认值
     *
     * @param value 需要转换的内容
     * @return
     * @author seven
     */
    public static Long toLong(String value) {
        return toLong(value, DefaultValue.Long_DEFAULT);
    }


    /**
     * 首字母转小写
     *
     * @param s
     * @return java.lang.String
     * @Author zhaoxizi
     * @Date 18/1/3
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }


    /**
     * 首字母转大写
     *
     * @param s
     * @return java.lang.String
     * @Author zhaoxizi
     * @Date 18/1/3
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 检查年份格式
     *
     * @param y
     * @return java.lang.Boolean
     * @Author zhaoxizi
     * @Date 18/1/3
     */


    public static Boolean checkYear(Integer y) {
        try {
            Date s = new SimpleDateFormat("yyyy").parse(y.toString());
            s = null;
        } catch (ParseException e) {
            e.printStackTrace();
            ;
            return false;
        }
        return true;
    }

    /**
     * 把对象转换为指定类型对象，如果传入对角为非字符串则原样返回
     *
     * @param jType  java类型，如：int、String等
     * @param object 需要转换的对象
     * @return
     */
    public static Object parseObject(String jType, Object object) {
        if (object == null || !(object instanceof String)) {
            return object;
        }
        String value = (String) object;
        if (jType.equalsIgnoreCase("int") || jType.equalsIgnoreCase("java.lang.Integer")) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return object;
            }
        } else if (jType.equalsIgnoreCase("byte") || jType.equalsIgnoreCase("java.lang.Byte")) {
            try {
                return Byte.parseByte(value);
            } catch (NumberFormatException e) {
                return object;
            }
        } else if (jType.equalsIgnoreCase("short") || jType.equalsIgnoreCase("java.lang.Short")) {
            try {
                return Short.parseShort(value);
            } catch (NumberFormatException e) {
                return object;
            }
        } else if (jType.equalsIgnoreCase("long") || jType.equalsIgnoreCase("java.lang.Long")) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                return object;
            }
        } else if (jType.equalsIgnoreCase("float") || jType.equalsIgnoreCase("java.lang.Float")) {
            try {
                return Float.parseFloat(value);
            } catch (NumberFormatException e) {
                return object;
            }
        } else if (jType.equalsIgnoreCase("double") || jType.equalsIgnoreCase("java.lang.Double")) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return object;
            }
        } else if (jType.equalsIgnoreCase("java.lang.String")) {
            return value;
        } else if (jType.equalsIgnoreCase("boolean") || jType.equalsIgnoreCase("java.lang.Boolean")) {
            try {
                return Boolean.valueOf(value);
            } catch (Exception e) {
                return object;
            }
        } else if (jType.equalsIgnoreCase("java.sql.Date") || jType.equalsIgnoreCase("java.sql.Timestamp")) {
            try {
                return StrUtil.isEmpty(value) ? value : Timestamp.valueOf(value);
            } catch (Exception ex) {
                SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = null;
                try {
                    date = myFormat.parse(value);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return new Timestamp(date.getTime());
            }
        } else {
            return value;
        }
    }
    
    /**
     * 判断字符串是否为null或者undefined
     * @param s
     * @return
     */
	public static boolean nullOrUndefined(String s) {
		if (s == null) {
			return true;
		}
		if (s.toUpperCase().equals("NULL")
				|| s.toUpperCase().equals("UNDEFINED")) {
			return true;
		}
		return false;
	}
	
	public static String unicode2String(String utfString){  
	    StringBuilder sb = new StringBuilder();  
	    int i = -1;  
	    int pos = 0;  
	      
	    while((i=utfString.indexOf("\\u", pos)) != -1){  
	        sb.append(utfString.substring(pos, i));  
	        if(i+5 < utfString.length()){  
	            pos = i+6;  
	            sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));  
	        }  
	    }  
	    return sb.toString();  
	}  
}
