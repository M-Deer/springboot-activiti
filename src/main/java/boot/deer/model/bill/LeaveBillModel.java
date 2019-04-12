package boot.deer.model.bill;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import boot.deer.model.user.UserModel;
import lombok.Data;

/** 
 * @ClassName: LeaveBillModel.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月11日 下午4:51:53 
 * @Description: 请假单 Model
 */
@Data
@TableName(value = "SBA_LEAVE_BILL")
public class LeaveBillModel implements Serializable {

	private static final long serialVersionUID = 2202605235715629845L;

	/**
	 * 主键
	 * 自增长
	 */
	@TableId(value = "ID", type = IdType.AUTO)
	private Long id;

	/**
	 * 请假用户
	 */
	@TableField(value = "USER_ID", el = "user.id")
	private UserModel user;

	/**
	 * 请假标题
	 */
	@TableField(value = "TITLE")
	private String title;

	/**
	 * 请假内容
	 */
	@TableField(value = "CONTENT")
	private String content;

	/**
	 * 请假天数
	 */
	@TableField(value = "DAYS")
	private Integer days;

	/**
	 * 请假单状态
	 * 
	 * -1：放弃
	 * 0：驳回
	 * 1：审批中
	 * 2：审批完成
	 */
	@TableField(value = "STATUS")
	private Integer status;

	/**
	 * 请假时间
	 */
	@TableField(value = "LEAVE_TIME")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date leaveTime;
}
