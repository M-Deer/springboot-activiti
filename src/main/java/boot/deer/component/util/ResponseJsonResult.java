package boot.deer.component.util;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/** 
 * @ClassName: ResponseJsonResult.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月11日 下午12:20:09 
 * @Description: 系统返回 json 结果封装类
 */
@Data
public class ResponseJsonResult implements Serializable {

	private static final long serialVersionUID = 7883830277373687855L;

	/**
	 * 返回代码
	 * 1：成功
	 * -1：失败
	 */
	private String status;

	/**
	 * 携带的信息
	 */
	private String message;

	/**
	 * 结果对象
	 */
	private Object object;

	/**
	 * 响应时间
	 */
	private String responseDate;

	/**
	 * 返回成功的响应结果
	 * 不携带消息
	 * 
	 * @param object 携带对象
	 * @return 封装结果
	 */
	public static ResponseJsonResult successResult(Object object) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		responseJsonResult.setStatus("1");
		responseJsonResult.setMessage("操作成功");
		responseJsonResult.setObject(object);
		responseJsonResult.setResponseDate(DateUtil.dateTime2String(new Date()));

		return responseJsonResult;
	}

	/**
	 * 返回成功的响应结果
	 * 不携带对象
	 * 
	 * @param successMessage 成功提示信息
	 * @return 封装结果
	 */
	public static ResponseJsonResult successResult(String successMessage) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		responseJsonResult.setStatus("1");
		responseJsonResult.setMessage(successMessage);
		responseJsonResult.setObject(null);
		responseJsonResult.setResponseDate(DateUtil.dateTime2String(new Date()));

		return responseJsonResult;
	}

	/**
	 * 返回成功的响应结果
	 * 携带消息、对象
	 * 
	 * @param successMessage 成功提示信息
	 * @param object 携带对象
	 * @return 封装结果
	 */
	public static ResponseJsonResult successResult(String successMessage, Object object) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		responseJsonResult.setStatus("1");
		responseJsonResult.setMessage(successMessage);
		responseJsonResult.setObject(object);
		responseJsonResult.setResponseDate(DateUtil.dateTime2String(new Date()));

		return responseJsonResult;
	}

	/**
	 * 返回失败的响应结果
	 * 不携带消息
	 * 
	 * @param object 携带对象
	 * @return 封装结果
	 */
	public static ResponseJsonResult unsuccessResult(Object object) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		responseJsonResult.setStatus("-1");
		responseJsonResult.setMessage("操作失败");
		responseJsonResult.setObject(object);
		responseJsonResult.setResponseDate(DateUtil.dateTime2String(new Date()));

		return responseJsonResult;
	}

	/**
	 * 返回失败的响应结果
	 * 不携带对象
	 * 
	 * @param errorMessage 失败提示信息
	 * @return 封装结果
	 */
	public static ResponseJsonResult unsuccessResult(String errorMessage) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		responseJsonResult.setStatus("-1");
		responseJsonResult.setMessage(errorMessage);
		responseJsonResult.setObject(null);
		responseJsonResult.setResponseDate(DateUtil.dateTime2String(new Date()));

		return responseJsonResult;
	}

	/**
	 * 返回失败的响应结果
	 * 携带消息、对象
	 * 
	 * @param errorMessage 失败提示信息
	 * @param object 携带对象
	 * @return 封装结果
	 */
	public static ResponseJsonResult unsuccessResult(String errorMessage, Object object) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		responseJsonResult.setStatus("-1");
		responseJsonResult.setMessage(errorMessage);
		responseJsonResult.setObject(object);
		responseJsonResult.setResponseDate(DateUtil.dateTime2String(new Date()));

		return responseJsonResult;
	}
}
