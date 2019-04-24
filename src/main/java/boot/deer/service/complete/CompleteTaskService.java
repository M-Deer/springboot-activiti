package boot.deer.service.complete;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import boot.deer.model.complete.CommentModel;
import boot.deer.model.complete.CompleteTaskModel;

/** 
 * @ClassName: CompleteTaskService.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月19日 上午9:57:39 
 * @Description: 代办任务的 Service
 */
public interface CompleteTaskService extends IService<CompleteTaskModel> {

	/**
	 * 分页查询
	 * 
	 * @param current 当前页码
	 * @param size 显示数量
	 * @return 结果集
	 */
	IPage<CompleteTaskModel> getItemsByPage(Integer current, Integer size);

	/**
	 * 根据任务ID 查询到具体的请假单信息
	 * @param taskId 任务ID
	 * @return 结果
	 */
	Map<String, Object> getItemById(String taskId);

	/**
	 * 根据任务ID 查询批注信息
	 * 
	 * @param taskId 任务ID
	 * @return 结果集
	 */
	List<CommentModel> getCommentsByTaskId(String taskId);

	/**
	 * 办理任务
	 * 
	 * @param paramMap 参数Map
	 */
	void completeTask(Map<String, String> paramMap) throws Exception;
}
