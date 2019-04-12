package boot.deer.model.user;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/** 
 * @ClassName: UserModel.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月11日 上午10:42:12 
 * @Description: 用户实体类
 */
@Data
@TableName(value = "SBA_USER")
public class UserModel implements Serializable {

	private static final long serialVersionUID = -343486690811705383L;

	/**
	 * 主键
	 * 自增长
	 */
	@TableId(value = "ID", type = IdType.AUTO)
	private Long id;

	/**
	 * 用户名
	 */
	@TableField(value = "USERNAME")
	private String username;

	/**
	 * 密码
	 */
	@TableField(value = "PASSWORD")
	private String password;

	/**
	 * 上级领导ID
	 */
	@TableField(value = "SUPERIOR_ID")
	private Long superiorId;
}
