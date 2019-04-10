package boot.activiti.note.gateway.exclusive;

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
 * @ClassName: ExclusiveGatewayNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年3月29日 上午9:02:17 
 * @Description: 排他网关
 * 
 * 其功能就是判断该流程具体需要走哪一条分支
 * 类似 Java 中的 if{} else if{} else if{} else{}
 * 
 * 在绘制流程图中，排他网管就是一个 “X” 的图标，绘制流程的时候，在排他网关处可以设置一个"默认连线"
 * 设置了"默认连线"的那条流程连线后，就相当于 Java 中的"else"，都匹配不到的时候，就会走这条"默认连线"
 * 这条"默认连线"是不需要设置条件的，除此之外的流程连线都需要条件。
 * 当然也可以不用设置默认连线，那么就需要每条连线都设置匹配条件。
 * 
 * 在开发过程张，只要任务办理到"排他网关(ExclusiveGateway)"前一个任务的时候
 * 那么就需要携带参数进去网关进行判断流程连线，如果不携带参数会抛出
 * 
 * 总结：
 * 1.一个"排他网关"对应一个以上的顺序流
 * 2.由"排他网关"流出的顺序流都有个"conditionExpression：匹配规则"，该匹配规则都是返回boolean
 * 3."排他网关"指挥选择一条结果，执行到"排他网关"时，流程引擎会自动检索网关出口，从上到下检索出一个匹配规则返回 true的
 * 分支流进行流出
 * 	1) 如果设置设立"默认连线"时，那么该流出的连线会最后进行匹配，同时也是默认为 true
 *  2) 如果没有设置"默认连线"时，同时也没有匹配的规则，则会抛出异常
 * 
 * 
 */
public class ExclusiveGatewayNote extends SpringbootActivitiApplicationTests {

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
	 * "出差报销流程"
	 */
	@Test
	public void deploymentProcess() {
		Deployment deployment = repositoryService.createDeployment().name("出差报销流程")
				.addClasspathResource("processes/gateway/exclusive/Exclusive.bpmn")
				.addClasspathResource("processes/gateway/exclusive/Exclusive.png").deploy();

		System.out.println("流程部署成功，流程部署ID:" + deployment.getId());
	}

	/**
	 * 启动流程
	 * "出差报销流程"
	 */
	@Test
	public void startProcessByKey() {
		String processDefinitionKey = "exclusiveGateway";
		ProcessInstance instanceProcess = runtimeService.startProcessInstanceByKey(processDefinitionKey);

		System.out.println("流程启动成功，流程实例ID：" + instanceProcess.getProcessInstanceId());
	}

	/**
	 * 查询个人任务
	 * "出差报销流程"
	 */
	@Test
	public void queryMyTaskByAssignee() {
//		String assignee = "张三";
		String assignee = "赵六";
//		String assignee = "张三";
//		String assignee = "张三";
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
	 * "出差报销流程"
	 */
	@Test
	public void completeTaskById() {
		String taskId = "32505";
		taskService.complete(taskId);

		System.out.println("任务办理完成");
	}

	/**
	 * 办理任务，携带参数
	 * 由于本次任务都是依靠金钱的变量办理的
	 * 所以再提交申请之后，就需要携带"money"参数去办理任务
	 * 否则就会报异常:
	 * "Unknown property used in expression: ${money<500}"
	 * "出差报销流程"
	 */
	@Test
	public void completeTaskByIdWithVariable() {
		String taskId = "32505";

		// 流程变量
		Map<String, Object> variableMap = new HashMap<>();
		variableMap.put("money", 480);
//		variableMap.put("money", 600);
//		variableMap.put("money", 1300);
		taskService.complete(taskId, variableMap);

		System.out.println("任务办理完成");
	}
}
