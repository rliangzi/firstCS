package com.chinamobile.flow.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.Assert;

/**
 * @Description: 转换工具类
 * @ClassName ConvertUtils
 * @author: shosho
 * @Created 2013 2013-1-16 下午12:19:28
 */
public abstract class ConvertUtils {
	public static final String DATE_FMT = "yyyy-MM-dd";
	public static final String TIME_FMT = "HH:mm:ss";
	public static final String MONTH_FMT = "yyyyMM";
	public static final String DATE_DD = "yyyyMMdd";
	public static final String MH_FMT_ = "yyyy-MM";
	public static final String DT_FMT = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_FMT = DATE_FMT;

	/**
	 * @Description: 解析传入日期得到日期字符串,将来可能会根据不同的需要扩展该函数,适应不同的日期格式
	 * @param dateStr
	 * @return
	 * @ReturnType DateFormat
	 * @author: shosho
	 * @Created 2013 2013-8-28 下午5:46:55
	 */
	public static DateFormat getDateFormat(String dateStr) {
		int pos1 = dateStr.indexOf("-");
		int pos2 = dateStr.indexOf(":");
		DateFormat dt = null;
		if (pos1 != -1 && pos2 != -1) {
			dt = new SimpleDateFormat(DT_FMT);
		} else if (pos1 != -1 && pos2 == -1) {
			dt = new SimpleDateFormat(DATE_FMT);
		} else if (pos1 == -1 && pos2 != -1) {
			dt = new SimpleDateFormat(TIME_FMT);
		} else if (dateStr.length() == "yyyyMM".length()) {
			dt = new SimpleDateFormat("yyyyMM");
		} else if (dateStr.length() == "yyyyMMdd".length()) {
			dt = new SimpleDateFormat("yyyyMMdd");
		} else {
			dt = new SimpleDateFormat(DATE_FMT);
		}
		return dt;
	}

	/**
	 * @Description:字符串转为日期
	 * @param dateStr
	 * @param pattern
	 * @param defaultDate
	 * @return
	 * @ReturnType Date
	 * @author: shosho
	 * @Created 2013 2013-5-10 上午11:54:13
	 */
	public static Date stringToDate(String dateStr, String pattern) {
		if (dateStr != null && pattern != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				return sdf.parse(dateStr);
			} catch (ParseException e) {

			}
		}
		return null;
	}

	/**
	 * @Description: 往后推一天
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @ReturnType Date
	 * @author: shosho
	 * @Created 2015 2015年1月14日 下午2:41:52
	 */
	public static Date stringToNextDate(String dateStr, String pattern) {
		if (dateStr != null && pattern != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				Date date = sdf.parse(dateStr);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.DATE, 1);
				return cal.getTime();
			} catch (ParseException e) {

			}
		}
		return null;
	}

	/**
	 * @Description: 转换成 boolean
	 * @param object
	 * @return
	 * @ReturnType boolean
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午12:21:35
	 */
	public static boolean toBool(Object object) {
		Assert.notNull(object);
		if (object instanceof Boolean) {
			return ((Boolean) object).booleanValue();
		}
		return Boolean.parseBoolean(object.toString());
	}

	/**
	 * @Description: 转换成 boolean
	 * @param object
	 *            传入参数
	 * @param defaultValue
	 *            默认值
	 * @return
	 * @ReturnType boolean
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午12:21:42
	 */
	public static boolean toBool(Object object, boolean defaultValue) {
		try {
			return toBool(object);
		} catch (Throwable e) {
			return defaultValue;
		}
	}

	/**
	 * @Description: 转换成 java.util.Date
	 * @param object
	 * @return
	 * @ReturnType Date
	 * @author: shosho
	 * @Created 2013 2013-8-28 下午5:46:44
	 */
	public static Date toDate(Object object) {
		if (object == null) {
			return null;
		} else if (object instanceof java.sql.Date) {
			Date date = new Date();
			date.setTime(((java.sql.Date) object).getTime());
			return date;
		} else if (object instanceof Timestamp) {
			Date date = new Date();
			date.setTime(((Timestamp) object).getTime());
			return date;
		}
		if (object instanceof Date) {
			Date date = new Date();
			date.setTime(((Date) object).getTime());
			return date;
		} else if (object instanceof Calendar) {
			return ((Calendar) object).getTime();
		} else {
			try {
				return getDateFormat(object.toString()).parse(object.toString());
			} catch (ParseException pe) {
				return null;
			}
		}
	}

	/**
	 * @Description: 转换成double类型
	 * @param object
	 * @return
	 * @ReturnType double
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午2:34:06
	 */
	public static double toDouble(Object object) {
		Assert.notNull(object);
		if (object instanceof Double) {
			return ((Double) object).doubleValue();
		} else if (object instanceof Short) {
			return ((Short) object).doubleValue();
		} else if (object instanceof Integer) {
			return ((Integer) object).doubleValue();
		} else if (object instanceof Long) {
			return ((Long) object).doubleValue();
		} else if (object instanceof Double) {
			return ((Double) object).doubleValue();
		} else if (object instanceof Number) {
			return ((Number) object).doubleValue();
		} else {
			return Double.parseDouble(object.toString());
		}
	}

	/**
	 * @Description: 转换成 double
	 * @param object
	 * @param defaultValue
	 * @return
	 * @ReturnType double
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午2:51:15
	 */
	public static double toDouble(Object object, double defaultValue) {
		try {
			return toDouble(object);
		} catch (Throwable e) {
			return defaultValue;
		}
	}

	/**
	 * @Description: 转换成 double[]
	 * @param doubleArray
	 * @return
	 * @ReturnType double[]
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午2:50:16
	 */
	public static double[] toDouble(Object[] doubleArray) {
		if (doubleArray == null) {
			return null;
		}
		double[] returnDoubleArray = new double[doubleArray.length];
		for (int i = 0; i < returnDoubleArray.length; i++) {
			returnDoubleArray[i] = toDouble(doubleArray[i]);
		}
		return returnDoubleArray;
	}

	/**
	 * @Description: 转换成 double[]
	 * @param doubleArray
	 * @param defaultValue
	 * @return
	 * @ReturnType double[]
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午2:50:02
	 */
	public static double[] toDouble(Object[] doubleArray, double[] defaultValue) {
		try {
			return toDouble(doubleArray);
		} catch (Throwable e) {
			return defaultValue;
		}
	}

	/**
	 * @Description:
	 * @param object
	 * @param decimalNum
	 *            小数位数
	 * @return
	 * @ReturnType double
	 * @author: shosho
	 * @Created 2013 2013-9-7 下午11:45:15
	 */
	public static double toDoubleFormat(Object object, int decimalNum) {
		try {
			double f = toDouble(object, 0d);
			// 声明格式化对象
			DecimalFormat df = null;
			// 如果是0位
			if (decimalNum == 0) {
				df = new DecimalFormat("#");
				return Double.valueOf(df.format(f));
			} else if (decimalNum == 1) {
				df = new DecimalFormat("#.#");
				return Double.valueOf(df.format(f));
			} else if (decimalNum == 2) {
				df = new DecimalFormat("#.##");
				return Double.valueOf(df.format(f));
			} else if (decimalNum == 3) {
				df = new DecimalFormat("#.###");
				return Double.valueOf(df.format(f));
			} else if (decimalNum == 4) {
				df = new DecimalFormat("#.####");
				return Double.valueOf(df.format(f));
			} else if (decimalNum == 5) {
				df = new DecimalFormat("#.#####");
				return Double.valueOf(df.format(f));
			}
		} catch (Throwable e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	/**
	 * @Description: 转换成 int
	 * @param object
	 * @return
	 * @ReturnType int
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午12:21:28
	 */
	public static int toInt(Object object) {
		Assert.notNull(object);
		if (object instanceof Integer) {
			return ((Integer) object).intValue();
		} else if (object instanceof Short) {
			return ((Short) object).intValue();
		} else if (object instanceof Double) {
			return ((Double) object).intValue();
		} else if (object instanceof Long) {
			return ((Long) object).intValue();
		} else if (object instanceof Number) {
			return ((Number) object).intValue();
		} else {
			return Integer.parseInt(object.toString());
		}
	}

	/**
	 * @Description: 转换成 int
	 * @param object
	 *            传入参数
	 * @param defaultValue
	 *            默认值
	 * @return
	 * @ReturnType int
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午12:21:51
	 */
	public static int toInt(Object object, int defaultValue) {
		try {
			return toInt(object);
		} catch (Throwable e) {
			return defaultValue;
		}
	}

	/**
	 * @Description: 转换成 int[]
	 * @param objects
	 * @return
	 * @ReturnType int[]
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午2:50:45
	 */
	public static int[] toInt(Object[] objects) {
		if (objects == null) {
			return null;
		}
		int[] intObjects = new int[objects.length];
		for (int i = 0; i < intObjects.length; i++) {
			intObjects[i] = toInt(objects[i]);
		}
		return intObjects;
	}

	/**
	 * @Description: 转换成 Integer
	 * @param object
	 * @return
	 * @ReturnType Integer
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午2:49:13
	 */
	public static Integer toInteger(Object object) {
		if (object == null) {
			return null;
		}
		return Integer.valueOf(toInt(object));
	}

	/**
	 * @Description: 转换成 Integer数组
	 * @param objects
	 * @return
	 * @ReturnType Integer[]
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午2:49:21
	 */
	public static Integer[] toInteger(Object[] objects) {
		if (objects == null) {
			return null;
		}
		Integer[] intObjects = new Integer[objects.length];
		for (int i = 0; i < intObjects.length; i++) {
			intObjects[i] = toInteger(objects[i]);
		}
		return intObjects;
	}

	/**
	 * @Description:
	 * @param cal
	 * @return
	 * @ReturnType String
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午2:33:33
	 */
	public static String toString(Calendar cal) {
		return toString(cal, DEFAULT_FMT);
	}

	/**
	 * @Description: 通过相应的格式将 java.util.Calendar 转换成 String
	 * @param cal
	 * @param dateFormatString
	 * @return
	 * @ReturnType String
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午2:32:36
	 */
	public static String toString(Calendar cal, String dateFormatString) {
		Assert.notNull(dateFormatString, "Date format string is required");
		return toString(cal, dateFormatString, "");
	}

	/**
	 * @Description:
	 * @param cal
	 * @param dateFormatString
	 * @param defaultValue
	 * @return
	 * @ReturnType String
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午2:32:54
	 */
	public static String toString(Calendar cal, String dateFormatString, String defaultValue) {
		if (cal == null) {
			return defaultValue;
		}
		try {
			return new SimpleDateFormat(dateFormatString).format(cal.getTime());
		} catch (Throwable e) {
			return defaultValue;
		}
	}

	/**
	 * @Description: 转化lst中数据为字符串 token 分隔标记
	 * @param collection
	 * @param token
	 * @return
	 * @ReturnType String
	 * @author: shosho
	 * @Created 2013 2013-8-28 下午5:45:58
	 */
	@SuppressWarnings("unchecked")
	public static String toString(Collection collection, String token) {
		if (collection == null || collection.isEmpty()) {
			return null;
		}
		Assert.notNull(token, "token is required; it must not be null");

		StringBuffer sb = new StringBuffer();
		for (Iterator it = collection.iterator(); it.hasNext();) {
			Object object = it.next();
			sb.append(object == null ? null : object.toString()).append(token);
		}
		if (sb.length() - token.length() >= 0) {
			sb.delete(sb.length() - token.length(), sb.length());
		}
		return sb.toString();
	}

	/**
	 * @Description: 通过 yyyy-MM-dd 格式将java.util.date转换成String
	 * @param date
	 * @return
	 * @ReturnType String
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午2:31:18
	 */
	public static String toString(Date date) {
		return toString(date, DEFAULT_FMT);
	}

	/**
	 * @Description: 通过相应的格式将java.util.date转换成String
	 * @param date
	 * @param dateFormatString
	 * @return
	 * @ReturnType String
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午2:31:26
	 */
	public static String toString(Date date, String dateFormatString) {
		Assert.notNull(dateFormatString, "Date format string is required");
		return toString(date, dateFormatString, "");
	}

	/**
	 * @Description:
	 * @param date
	 * @param dateFormatString
	 * @param defaultValue
	 * @return
	 * @ReturnType String
	 * @author: shosho
	 * @Created 2013 2013-1-16 下午2:31:46
	 */
	public static String toString(Date date, String dateFormatString, String defaultValue) {
		if (date == null) {
			return defaultValue;
		}
		try {
			return new SimpleDateFormat(dateFormatString).format(date);
		} catch (Throwable e) {
			return defaultValue;
		}
	}

	/**
	 * @Description: 转化 map 中数据为字符串 token 分隔标记
	 * @param map
	 * @param keyValueToken
	 * @param token
	 * @return
	 * @ReturnType String
	 * @author: shosho
	 * @Created 2013 2013-8-28 下午5:45:42
	 */
	@SuppressWarnings("unchecked")
	public static String toString(Map map, String keyValueToken, String token) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Assert.notNull(token, "token is required; it must not be null");
		Assert.notNull(keyValueToken, "keyValueToken is required; it must not be null");
		StringBuffer sb = new StringBuffer();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry entry = (Entry) it.next();
			String key = entry.getKey() == null ? null : entry.getKey().toString();
			String value = entry.getValue() == null ? null : entry.getValue().toString();
			sb.append(key).append(keyValueToken).append(value).append(token);
		}

		if (sb.length() - token.length() >= 0) {
			sb.delete(sb.length() - token.length(), sb.length());
		}

		return sb.toString();
	}

	/**
	 * @Description:
	 * @param object
	 * @return
	 * @ReturnType String
	 * @author: shosho
	 * @Created 2013 2013-8-28 下午5:45:39
	 */
	public static String toString(Object object) {
		return toString(object, null);
	}

	/**
	 * @Description: 转换成 String
	 * @param object
	 * @param formatString
	 * @return
	 * @ReturnType String
	 * @author: shosho
	 * @Created 2013 2013-8-28 下午5:45:31
	 */
	@SuppressWarnings("unchecked")
	public static String toString(Object object, String formatString) {
		if (object == null) {
			return "";
		} else if (object instanceof String) {
			return (String) object;
		} else if (object instanceof Integer) {
			if (formatString == null) {
				return ((Integer) object).toString();
			} else {
				return toString(toInt(object), formatString);
			}
		} else if (object instanceof Double) {
			if (formatString == null) {
				return ((Double) object).toString();
			} else {
				return toString(toDouble(object), formatString);
			}
		} else if (object instanceof Number) {
			if (formatString == null) {
				return ((Number) object).toString();
			} else {
				return toString(toDouble(object), formatString);
			}
		} else if (object instanceof Calendar) {
			if (formatString == null) {
				return toString((Calendar) object);
			} else {
				return toString((Calendar) object, formatString);
			}
		} else if (object instanceof Date) {
			if (formatString == null) {
				return toString((Date) object);
			} else {
				return toString((Date) object, formatString);
			}
		} else if (object instanceof Collection) {
			return "[" + toString(object, ", ") + "]";
		} else if (object instanceof Map) {
			return "[" + toString((Map) object, "=", ", ") + "]";
		} else if (object.getClass().isArray()) {
			return "[" + toString(object, ", ") + "]";
		} else {
			return String.valueOf(object);
		}
	}
}
