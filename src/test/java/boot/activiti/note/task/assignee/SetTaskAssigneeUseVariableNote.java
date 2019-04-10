package boot.activiti.note.task.assignee;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/** 
 * @ClassName: SetTaskAssigneeUseVariableNote 
 * @Author: Mr_Deer
 * @Date: 2019年3月30日 上午10:10:44 
 * @Description: 之前的流程中我们都是把办理人直接写死在流程图中
 * 但在实际开发中，这么做是不妥当的，因为办理人会经常的变动
 * 我们可以把这个变量做活，有两种方式
 * 
 * 第一种：
 * 把办理人进行"变量"来处理
 *  在绘制流程图的时候，在原本的 "Main config"中的"Assignee"设置一个变量"#{username}"
 *  这个变量是自定义的名字的，就是我们流程中的变量的 key
 *  
 */
public class SetTaskAssigneeUseVariableNote extends SpringbootActivitiApplicationTests {

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
	 * 流程部署
	 * "请假流程"
	 */
	@Test
	public void deploymentProcess() {
		repositoryService.createDeployment().name("请假流程").addClasspathResource("processes/assignee/variable/TaskAssignee.bpmn")
				.addClasspathResource("processes/assignee/variable/TaskAssignee.png").deploy();

		System.out.println("流程部署成功");
	}

	/**
	 * 启动流程
	 * "请假流程"
	 * 
	 * 因为我们在流程中从第一步的提交申请的时候，就是需要去设置办理人是谁
	 * 所以这里我们在启动的时候，就需要携带参数进行启动
	 * 也就是说，只要下一步还需要"流程变量"，那么我们就需要提前吧这个变量进行准备然后传递下去
	 * 如果不设置"流程变量"则会抛出异常
	 */
	@Test
	public void startProcessByKey() {
		String processDefinitionKey = "taskAssignee";
		// 设置流程变量
		Map<String, Object> variableMap = new HashMap<>();
		variableMap.put("username", "张三");

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variableMap);

		System.out.println("流程启动成功，流程实例ID：" + processInstance.getProcessInstanceId());
	}

	/**
	 * 办理任务
	 * "请假流程"
	 * 
	 * 因为每一步我们都需要一个办理人
	 * 所以在办理这一步的时候，我们就需要将下一步骤的"流程变量"设置进去
	 */
	@Test
	public void completeTaskWithVariable() {
		String taskId = "7502";

		// 设置流程变量
		Map<String, Object> variableMap = new HashMap<>();
		variableMap.put("username", "王五");

		taskService.complete(taskId, variableMap);
		System.out.println("任务办理成功");
	}

	/**
	 * 办理任务
	 * "请假流程"
	 * 
	 * 在流程结束的前一步，那么办理任务的时候，就不需要在携带参数了
	 * 因为办理完之后，整个流程就结束了
	 */
	@Test
	public void completeTask() {
		String taskId = "10002";

		taskService.complete(taskId);
		System.out.println("任务办理成功");
	}

}
