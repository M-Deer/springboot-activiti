package boot.activiti.note.manage;

import java.io.Serializable;

import lombok.Data;

/** 
 * @ClassName: User.java 
 * @Author: Mr_Deer
 * @Date: 2019年3月28日 下午7:28:38 
 * @Description: 
 */
@Data
public class User implements Serializable {
	
	private static final long serialVersionUID = -4399737278669445832L;

	private Integer id;
	private String name;

	public User(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
