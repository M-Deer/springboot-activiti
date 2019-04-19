package boot.deer.component.enumm;

/** 
 * @ClassName: BillStatus.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月12日 下午4:57:22 
 * @Description: 请假单枚举类
 */
public enum LeaveBillEnum {
	GIVE_UP("放弃", "-1"), TURN_DOWN("驳回", "0"), EXAMINATION("审批中", "1"), COMPLETE("审批完成", "2"),
	ASSIGNEE_USER("办理人变量 key", "ASSIGNEE_USER"), OUTCOME("连线的变量 key", "OUTCOME");
	private String cod;

	private LeaveBillEnum(String name, String cod) {
		this.cod = cod;
	}

	public String getCod() {
		return this.cod;
	}
}
