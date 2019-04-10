package boot.deer.component.util;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/** 
 * @ClassName: Util.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月2日 上午9:43:40 
 * @Description: 全局通用工具类
 */
@SuppressWarnings("rawtypes")
public class GlobalUtil {

	/**
	 * 判断对象是否为空
	 * 其中包括 集合、对象、字符串
	 *
	 * @param object 需要判断的对象
	 * @return 空返回 true / 非空返回 false
	 */

	public static boolean isEmpty(Object object) {
		boolean result = false;
		if (object == null)
			result = true;
		if (object instanceof String)
			result = strEmpty(object.toString());
		if (object instanceof List)
			result = ((List) object).isEmpty();
		if (object instanceof Map)
			result = ((Map) object).isEmpty();
		return result;
	}

	/**
	 * 判断对象是否为空
	 *
	 * @param obj       需要判断的对象
	 * @param predicate 函数式接口，匹配规则
	 * @param <T>       type
	 * @return 根据调用者规则返回
	 */
	public static <T> boolean isEmpty(T obj, Predicate<T> predicate) {
		return predicate.test(obj);
	}

	/**
	 * 判断字符串是否为空
	 *
	 * @param str 需要判断的字符串
	 * @return 空：true/ 非空：false
	 */
	private static boolean strEmpty(String str) {
		if (str == null)
			return true;
		str = str.trim();
		if (str.length() == 0)
			return true;
		if (str.equals(" "))
			return true;
		return false;
	}
}
