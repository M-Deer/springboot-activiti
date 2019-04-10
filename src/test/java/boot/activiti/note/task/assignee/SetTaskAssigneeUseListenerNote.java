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
 * @ClassName: SetTaskAssigneeUseListenerNote 
 * @Author: Mr_Deer
 * @Date: 2019年3月30日 下午12:28:27 
 * @Description:  通过监听器设置办理人
 * 
 *  第二种：
 *  不设置流程变量的情况下
 *  通过添加 Listener，监听任务的方式来设置办理人，这种方式需要结合第一种方式来一起使用
 *  原本的流程图是3个节点全部都设置了 #{username}，我们现在只需要给第一步设置就可以了(因为普遍在实际开发中第一步都是有当前人来进行提交申请的)
 *  后面的步骤，我们不需要设置"Main config"，我们需要设置 "Listener"，然后给出一个Listener的一个类
 *  然后我们的业务逻辑，办理人就可以写在这个类里面了
 *  每一个需要办人的节点如果都想使用Listener，那么每一个节点都需要进行添加这个监听的类
 *  
 *  总结：
 *  个人任务的办理人分配方式有三种：
 *  1. 在绘制流程图的时候，就在Assignee中写好办理人
 *  2. 在绘制流程图的时候，在Assignee中写入流程变量
 *  然后在具体的业务逻辑中去携带参数进行注入到底是哪个办理人来办理
 *  3. 在绘制流程变量的时候，给一个监听器，让监听器去监听每个任务的节点，把业务逻辑写在监听器中
 */
public class SetTaskAssigneeUseListenerNote extends SpringbootActivitiApplicationTests {

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
		repositoryService.createDeployment().name("请假流程")
				.addClasspathResource("processes/assignee/listener/ListenerAssignee.bpmn")
				.addClasspathResource("processes/assignee/listener/ListenerAssignee.png").deploy();

		System.out.println("流程部署成功");
	}

	/**
	 * 启动流程
	 * "请假流程"
	 * 第一次进行启动的时候，我们设置了"流程变量"
	 * 所以这里在启动的时候需要携带参数
	 */
	@Test
	public void startProcessByKey() {
		String processDefinitionKey = "listerenAssignee";

		Map<String, Object> variableMap = new HashMap<>();
		variableMap.put("username", "张三");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variableMap);

		System.out.println("流程启动成功，流程实例ID：" + processInstance.getProcessInstanceId());
	}

	/**
	 * 办理任务
	 * "请假流程"
	 * 
	 * 我们已经设置过流程的监听了，所以我们可以通过监听类来设置办理人
	 * 这里我们直接办理任务就行了
	 */
	@Test
	public void completeTask() {
		String taskId = "25002";
		taskService.complete(taskId);
		System.out.println("任务办理成功");
	}
}
