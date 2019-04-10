package boot.deer.note.task.group;
/** 
 * @ClassName: GroupTaskNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月3日 上午10:16:38 
 * @Description: "组任务"笔记 - 使用 Junit 进行测试
 */

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;
import boot.deer.component.util.DateUtil;
import boot.deer.component.util.GlobalUtil;

public class GroupTaskNote extends SpringbootActivitiApplicationTests {

	// 注入 TaskService
	@Autowired
	private TaskService taskService;

	/**
	 * 查询任务 - "办理人"查询
	 * 
	 * 操作对应数据表：
	 * 		ACT_RU_TASK
	 */
	@Test
	public void taskQueryByAssignee() {
		String assignee = "userA";

		List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();

		if (GlobalUtil.isEmpty(tasks))
			System.out.println("当前办理人的任务为空");
		else {
			// 循环结果集
			tasks.forEach(task -> {
				System.out.println("任务ID: " + task.getId());
				System.out.println("流程实例ID: " + task.getProcessInstanceId());
				System.out.println("执行实例ID: " + task.getExecutionId());
				System.out.println("任务办理人: " + task.getAssignee());
				System.out.println("任务Name: " + task.getName());
				System.out.println("任务创建时间: " + DateUtil.dateTime2String(task.getCreateTime()));
				System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = ");
			});
		}
	}

	/**
	 * 查询当前组内成员 - 任务ID 查询
	 * 
	 * 操作对应数据表：		 
	 * 		ACT_RU_IDENTITYLINK
	 */
	@Test
	public void groupTaskQueryByTaskId() {
		String taskId = "15005";
		List<IdentityLink> identityLinks = taskService.getIdentityLinksForTask(taskId);

		identityLinks.forEach(identity -> {
			System.out.println("用户ID:  " + identity.getUserId());
			System.out.println("任务ID:  " + identity.getTaskId());
			System.out.println("任务实例ID:  " + identity.getProcessInstanceId());
			System.out.println("任务类型:  " + identity.getType());
			System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
		});
	}

	/**
	 * 任务拾取
	 * 
	 * 操作对应数据表：
	 * 		ACT_RU_TASK
	 */
	@Test
	public void taskClaim() {
		String taskId = "15005";
		String userId = "userA";

		taskService.claim(taskId, userId);
		System.out.println(userId + "：任务拾取成功");
	}

	/**
	 * 任务回退
	 * 
	 * 操作对应数据表：
	 * 		ACT_RU_TASK
	 */
	@Test
	public void taskRollback() {
		String taskId = "15005";

		taskService.claim(taskId, null);
		System.out.println("任务回退成功");

	}

	/**
	 * 办理任务
	 * 
	 * 操作对应数据表：
	 * 		ACT_RU_TASK
	 */
	@Test
	public void completeTaskById() {
		String taskId = "15005";
		taskService.complete(taskId);

		System.out.println("任务办理成功");
	}

	/**
	 * 添加组员
	 * 
	 * 操作对应数据表：
	 * 		ACT_RU_IDENTITYLINK
	 */
	@Test
	public void addCrew() {
		String taskId = "15005";
		String userId = "userD";
		taskService.addCandidateUser(taskId, userId);

		System.out.println(userId + "：成员添加成功");
	}

	/**
	 * 删除组员
	 * 
	 * 操作对应数据表：
	 * 		ACT_RU_IDENTITYLINK
	 */
	@Test
	public void deleteCrew() {
		String taskId = "15005";
		String userId = "userD";
		taskService.deleteCandidateUser(taskId, userId);

		System.out.println("组员删除成功");
	}
}
