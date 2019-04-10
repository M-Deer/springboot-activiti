package boot.deer.note.variable.supplement;

import java.io.Serializable;

import lombok.Data;

/** 
 * @ClassName: UserModel.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月10日 上午9:41:55 
 * @Description: user 实体类
 */
@Data
public class UserModel implements Serializable {

	private static final long serialVersionUID = 6649416232096654479L;

	private String name;
	private Integer age;

}
