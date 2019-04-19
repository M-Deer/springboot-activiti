package boot.deer.component.enumm;

/** 
 * @ClassName: GlobalEnum.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月17日 下午5:31:47 
 * @Description: 全局枚举类
 */
public enum GlobalEnum {

	SESSION_USER("session 中的key", "SESSION_USER");
	private String code;

	private GlobalEnum(String description, String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}
