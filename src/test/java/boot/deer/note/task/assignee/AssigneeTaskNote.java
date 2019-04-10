package boot.deer.note.task.assignee;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;
import boot.deer.component.util.DateUtil;

/** 
 * @ClassName: AssigneeTaskNote 
 * @Author: Mr_Deer
 * @Date: 2019年4月2日 下午9:09:53 
 * @Description: "个人任务"笔记 - 使用 Junit 进行测试
 */
public class AssigneeTaskNote extends SpringbootActivitiApplicationTests {

	// 注入 Task
	@Autowired
	private TaskService taskService;

	/**
	 * 查询个人任务
	 * 
	 * 操作对应数据表：
	 * 		ACT_RU_TASK
	 */
	@Test
	public void assigneeTaskQuery() {
		// 获取“任务”查询器
		List<Task> tasks = taskService.createTaskQuery()

		// 查询匹配条件

		/* 任务ID(ID_) - 数据类型：String */
//		.taskId(taskId)
		/* 办理人ID(ASSIGNEE_) - 数据类型：String */
//		.taskAssignee(assignee)
		/* 办理人ID(ASSIGNEE_) - 数据类型：List<String> - 集合查询 IN */
//		.taskAssigneeIds(assigneeListIds)
		/* 办理人ID(ASSIGNEE_) - 数据类型：String - 模糊匹配 %param% */
//		.taskAssigneeLike(assigneeLike)
		/* 流程定义ID (PROC_DEF_ID_) - 数据类型：String */
//		.processDefinitionId(processDefinitionId)\
		/* 流程定义Key(ACT_RE_PROCDEF.KEY_) - 数据类型：String */
//		.processDefinitionKey(processDefinitionKey)
		/* 流程定义Key(ACT_RE_PROCDEF.KEY_) - 数据类型：List<String> - 集合查询 IN */
//		.processDefinitionKeyIn(processDefinitionKeys)
		/* 流程定义Key(ACT_RE_PROCDEF.KEY_) - 数据类型：String - 模糊匹配 %param% */
//		.processDefinitionKeyLike(processDefinitionKeyLike)
		/* 流程实例ID (PROC_INST_ID_) - 数据类型：String */
//		.processInstanceId(processInstanceId)
		/* 流程实例ID (PROC_INST_ID_) - 数据类型：List<String> - 集合查询 IN */
//		.processInstanceIdIn(processInstanceIds)
		/* 业务Key(BUSINESS_KEY_) - 数据类型：String */
//		.processInstanceBusinessKey(processInstanceBusinessKey)
		/* 业务Key(BUSINESS_KEY_) - 数据类型：String - 模糊匹配 %param% */
//		.processInstanceBusinessKeyLike(processInstanceBusinessKeyLike)

		// 排序

		/* 任务创建时间 (CREATE_TIME_) - 升序排序 */
//      .orderByTaskCreateTime().ace()
		/* 办理人 (ASSIGNEE_) -降序排序 */
//      .orderByTaskAssignee().desc()

		// 结果类型

		/* List 结果集 */
//		.list()
		/* List 结果集 - 分页 */
//		.listPage(firstResult, maxResults)
		/* 唯一的结果 */
//		.singleResult()
		/* 结果计数 */
//		.count()

				.list();

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

	/**
	 * 办理任务
	 * 
	 * 操作对应数据表：
	 * 		ACT_RU_TASK
	 */
	@Test
	public void completeTaskById() {
		String taskId = "7502";
		taskService.complete(taskId);

		System.out.println("任务办理成功");
	}
}
