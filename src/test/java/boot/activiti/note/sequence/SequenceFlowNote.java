package boot.activiti.note.sequence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/** 
 * @ClassName: SequenceFlowNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年3月28日 下午7:45:52 
 * @Description: Activit 中的连接/连接
 * 使用“流程变量”控制流程的走向
 *
 * <p>
 * 我们的流程图“SequenceFlow.bpmn” 文件中绘制了两个走向
 * 在原本的“单条分支”上，我们升级为，“重要”的才由总经理审批
 * 而“不重要”的则部门经理就可以直接审批
 * 在连线中我们要设置一个“Main Config - - > Condition”
 * 这个里面的表达式：${key==value}    对应的就是我们流程变量中的 key - - value
 * 因为是一个boolean 所以用 “==”来表示
 * </p>
 * 
 * 总结：
 * 一个活动节点可以指定一个或多个SequenceFLow，Start节点只能存在一个节点，End一个都没有
 */
public class SequenceFlowNote extends SpringbootActivitiApplicationTests {
	
	// 注入 RepositoryService
	@Autowired
	private RepositoryService repositoryService;
	// 注入 RuntimeService
	@Autowired
	private RuntimeService runtimeService;
	// 注入 TaskService
	@Autowired
	private TaskService taskService;

	/**
	 * 部署流程图
	 * "报销流程"
	 */
	@Test
	public void deploymentProcess() {
		Deployment deployment = repositoryService.createDeployment().name("报销流程")
				.addClasspathResource("processes/sequence/SequenceFlow.bpmn")
				.addClasspathResource("processes/sequence/SequenceFlow.png").deploy();

		System.out.println("流程部署成功，流程部署ID：" + deployment.getId());
	}

	/**
	 * 启动流程，通过 key 的方式
	 * “报销流程”
	 */
	@Test
	public void startProcessByKey() {
		String processDefinitionKey = "sequenceFlow";
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
		System.out.println("流程启动成功，流程实例ID：" + processInstance.getProcessInstanceId());
	}

	/**
	 * 查询我的任务
	 * "报销流程"
	 * <p>
	 * 流程是：
	 * 张三
	 * |
	 * 李四 -- (不重要) -- 流程结束
	 * | (重要)
	 * -- 王五 -- 流程结束
	 * </p>
	 */
	@Test
	public void queryMyTask() {
//		String assignee = "张三";
		String assignee = "李四";
//        String assignee = "王五";
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(assignee).list();

		taskList.forEach(i -> {
			System.out.println("任务ID: " + i.getId());
			System.out.println("任务Name: " + i.getName());
			System.out.println("任务办理人: " + i.getAssignee());
			System.out.println("= = = = = = = = = = = = = = = =");
		});
	}

	/**
	 * 办理任务
	 * "报销流程"
	 */
	@Test
	public void completeTask() {
		String taskId = "35003";
		taskService.complete(taskId);
		System.out.println("任务办理成功~");
	}

	/**
	 * 在判定分支时设置"流程变量"
	 * 办理流程携带参数
	 * "报销流程"
	 * <p>
	 * 在办理流程的时候，在没有条件分支的情况下，正常办理没由任何问题
	 * 但当办理到有流程分支的时候，需要依靠"流程变量"来判定分支的时候
	 * 按照正常的办理时会报错：
	 * <errorMessage>
	 * Unknown property used in expression: ${key==value}
	 * </errorMessage>
	 * 就是我们在连线时，设置的"Condition"的条件
	 * </p>
	 */
	@Test
	public void completeTaskWithVariables() {
		String taskId = "32502";
		Map<String, Object> variablesMap = new HashMap<>();
		// 这里的参数设置的就是我们连线"Condition"里的 key -- value
//		variablesMap.put("outcome", "不重要");
		variablesMap.put("outcome", "重要");
		taskService.complete(taskId, variablesMap);
		System.out.println("任务办理成功~");
	}
}
