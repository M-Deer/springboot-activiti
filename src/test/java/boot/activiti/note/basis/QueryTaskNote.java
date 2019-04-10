package boot.activiti.note.basis;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/**
 * @ClassName: QueryTaskNote.java
 * @Author: Mr_Deer
 * @Date: 2019年3月28日 下午7:12:12
 * @Description: 对于任务的查询
 */
public class QueryTaskNote extends SpringbootActivitiApplicationTests {

	@Autowired
	private TaskService taskService;

	/**
	 * 通过 junit 测试一下 通过当前的办理人进行查询任务的相信
	 */
	@Test
	public void queryTask() {
		// 通过办理人查询，我们查询张三的任务
		String assignee = "王五";
		// 当前办理人可能存在多个任务，所以我们获取的是当前办理人的所有任务
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(assignee).list();

		if (taskList != null && !(taskList.isEmpty())) {
			// 在数据不为空的情况下，我们遍历任务信息
			taskList.forEach(task -> {
				System.out.println("任务 ID：" + task.getId());
			});
		} else {
			System.out.println("当前没有任务任务");
		}
	}
}
