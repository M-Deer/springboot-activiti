package boot.deer.model.complete;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/** 
 * @ClassName: CompleteTaskModel.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月19日 上午9:43:27 
 * @Description: 代办任务的 Model
 * 
 * 因为在 Activiti 提供的实体类中，有些字段是没有办法转换成 json 的，会报错
 * 所以这里我们自己创建一个实体类，将需要的字段贴过来就好
 */
@Data
public class CompleteTaskModel {
	protected String id;
	protected String assignee;
	protected String name;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	protected Date createTime;
}
