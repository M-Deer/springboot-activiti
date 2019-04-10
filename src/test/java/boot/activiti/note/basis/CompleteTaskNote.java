package boot.activiti.note.basis;

import org.activiti.engine.TaskService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/**
 * @ClassName: CompleteTaskNote.java
 * @Author: Mr_Deer
 * @Date: 2019年3月28日 下午7:08:52
 * @Description: 进行任务的办理，该知识点和代码关联“查询任务”的方法
 */
public class CompleteTaskNote extends SpringbootActivitiApplicationTests {
	// 注入 TaskService
	@Autowired
	private TaskService taskService;

	/**
	 * 通过 junit 进行测试 办理任务，我们通过任务 id 去办理任务
	 */
	@Test
	public void completeTask() {
		// 任务 ID
		String taskId = "7502";
		// 通过任务 ID 办理任务
		taskService.complete(taskId);
		System.out.println("任务办理完成");
	}
}
