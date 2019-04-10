package boot.activiti.note.gateway.parallel;

import java.util.List;

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
 * @ClassName: ParallelGatewayNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年3月29日 上午10:27:44 
 * @Description: 并行网关 
 * 绘制流程图中，图标是"+"的就是"并行网关 ParallelGateway"
 * 
 * "并行网关ParallelGateway"就是在开始时就可以由多条分支的流程控制
 * 在我们启动流程后，在"ACT_RU_EXECUTION"表中，我们可以发现是三条数据
 * 那么这三条数据分别就是代表：
 * <p>
 * 流程实例
 * 执行实例(1)
 * 执行实例(2)
 * </p>
 * 
 * 之前就有说过"执行实例"和"流程实例的"区别，所以这里就体现出来了
 * 两条执行实例都归属于一整个流程实例
 * 
 * "并行网关"启动之后，就会出下两个任务(有几条分支，就会出现几个，因为他们是并行出发的)
 * 然后再结尾处进行汇合，结尾处的"并行网关"会等待所有的流程分支全部都完之后，才会进行流程的放行
 * 也就是等待所有流程都完毕后，才会从下一个"并行网关"进行流出
 * 
 * 对于"购物流程"的整个流程执行后，"ACT_HI_ACTINST"中的活动几点会出现9个，从中我们会发现
 * 最后一个"并行网关"出现了两次，这也就说明了"并行网关"会等待所有的分支都执行后才流出
 * 
 * <p>
 * 总结：
 * 1.一个流程中"流程实例"只有一个，"执行实例"会存在多个
 * 2."并行网关"的功能是基于进入和外出的顺序流
 * 	1) 分支(fork)：
 * 		并行后的所有外出顺序流，为每个顺序流都创建一个并发分支。
 * 		也就是我们流程已启动，就会执行第一个"并发网关"后面的所有顺序流且并发执行
 * 
 * 	2) 汇聚(join):
 * 		所有到达"并行网关"，在此等候，直到所有进入顺序流的分支都到达后，流程才会通过"汇聚网关"
 * 
 * 3."并行网关"的进入和流出使用的都是一样的图标
 * 4.如果一个"并行网关"有多个进入和多个流出的顺序流，那么他就同时具备分支和汇聚的功能
 * 	  这时，网关会先汇聚所有的进入顺序流，然后再切分多个并行分支(流出)
 * 5."并行网关"不会解析条件，即使再 sequenceFlow 中定义了条件，也会别忽略
 * 6."并行网关"不需要具备"平衡性",则随意使用
 * </p>
 */
public class ParallelGatewayNote extends SpringbootActivitiApplicationTests {

	// 注入 RepositoryServer
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
	 * "购物流程"
	 */
	@Test
	public void deploymentProcess() {
		Deployment deployment = repositoryService.createDeployment().name("购物流程")
				.addClasspathResource("processes/gateway/parallel/ParallelGateway.bpmn")
				.addClasspathResource("processes/gateway/parallel/ParallelGateway.png").deploy();

		System.out.println("流程部署成功，流程部署ID：" + deployment.getId());
	}

	/**
	 * 启动流程
	 * "购物流程"
	 */
	@Test
	public void startProcessByKey() {
		String processDefinitionKey = "parallelGateway";
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

		System.out.println("流程启动成功，流程实例ID：" + processInstance.getProcessInstanceId());
	}

	/**
	 * 查询个人任务
	 * "购物流程"
	 */
	@Test
	public void queryMyTask() {
		String assignee = "买家";
//		String assignee = "卖家";
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
	 * "购物流程"
	 */
	@Test
	public void completeTask() {
		String taskId = "10002";
		taskService.complete(taskId);

		System.out.println("任务办理完成");
	}
}
