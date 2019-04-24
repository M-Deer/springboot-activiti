package boot.deer.controller.complete;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import boot.deer.component.util.ResponseJsonResult;
import boot.deer.model.complete.CommentModel;
import boot.deer.model.complete.CompleteTaskModel;
import boot.deer.service.complete.CompleteTaskService;

/** 
 * @ClassName: CompleteTaskController.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月19日 上午9:34:59 
 * @Description: 我的待办任务 Controller
 */
@RestController
@RequestMapping(value = "/completeTask")
public class CompleteTaskController {

	private CompleteTaskService completeTaskService;

	@Autowired
	public CompleteTaskController(CompleteTaskService completeTaskService) {
		this.completeTaskService = completeTaskService;
	}

	/**
	 * 获取分数数据
	 */
	@GetMapping(value = "/getItemsByPage")
	public IPage<CompleteTaskModel> getItemsByPage(@RequestParam(name = "pageNumber") Integer current,
			@RequestParam(name = "pageSize") Integer size) {
		IPage<CompleteTaskModel> page = completeTaskService.getItemsByPage(current, size);
		return page;
	}

	/**
	 * 根据任务ID 查询到具体的请假单信息
	 * @param taskId 任务ID
	 * @return Json 结果
	 */
	@GetMapping(value = "/getItemById")
	public ResponseJsonResult getItemById(@RequestParam(name = "taskId") String taskId) {
		Map<String, Object> map = completeTaskService.getItemById(taskId);
		return ResponseJsonResult.successResult(map);
	}

	/**
	 * 根据任务ID 查询批注信息
	 * 
	 * @param taskId 任务ID
	 * @return
	 */
	@GetMapping(value = "/getCommentsByTaskId")
	public List<CommentModel> getCommentsByTaskId(@RequestParam(name = "taskId") String taskId) {
		List<CommentModel> list = completeTaskService.getCommentsByTaskId(taskId);
		return list;
	}

	/**
	 * 办理任务
	 * 
	 * @param paramMap 参数
	 * @return Json 结果
	 */
	@PostMapping
	public ResponseJsonResult completeTask(@RequestBody Map<String, String> paramMap) {
		try {
			completeTaskService.completeTask(paramMap);
			return ResponseJsonResult.successResult("任务办理成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseJsonResult.successResult("任务办理失败");
		}
	}
}
