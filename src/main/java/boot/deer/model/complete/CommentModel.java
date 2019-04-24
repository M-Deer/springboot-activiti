package boot.deer.model.complete;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/** 
 * @ClassName: CommentModel.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月22日 上午10:25:00 
 * @Description: 批注信息 Model
 * 
 * 因为在 Activiti 提供的实体类中，有些字段是没有办法转换成 json 的，会报错
 * 所以这里我们自己创建一个实体类，将需要的字段贴过来就好
 */
@Data
public class CommentModel {

	private String userId;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date time;
	private String message;
}
