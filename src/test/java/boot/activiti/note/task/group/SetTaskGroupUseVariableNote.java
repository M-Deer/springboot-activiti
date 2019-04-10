package boot.activiti.note.task.group;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/** 
 * @ClassName: SetTaskGroupUseVariableNote 
 * @Author: Mr_Deer
 * @Date: 2019年3月30日 下午1:45:57 
 * @Description: 组任务，那么组任务就是指：一个任务会被多个人看到，可以指定其中一个人进行操作
 * 核心点就是在于"任务的拾取"
 * 同时我们可以对这个组任务内的"组成员"进行添加和删除
 * 
 * 在绘制流程图的时候，在"Main config"- - > "Candidate users"进行设置，多个值中间英文逗号隔开
 * 
 * 同样我们可以通过三种方式来进行"组任务"的成员设置
 * 1. 直接在写入在流程图中
 * 2. 使用流程变量的方式，#{users}这样的流程变量进行设置
 * 3. 使用监听器的方式，选取一个监听器，然后将任务关联这个监听器，设置监听就不需要设置"流程变量"了
 * 		在节点的"Listener"中进行设置
 */
public class SetTaskGroupUseVariableNote extends SpringbootActivitiApplicationTests {

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
	 * "组任务流程"
	 */
	@Test
	public void deploymentProcess() {
		repositoryService.createDeployment().name("组任务流程").addClasspathResource("processes/group/GroupTask.bpmn")
				.addClasspathResource("processes/group/GroupTask.png").deploy();

		System.out.println("流程部署成功");
	}

	/**
	 * 启动流程
	 * "组任务流程"
	 * 
	 * 当流程启动后，我们可以看到"ACT_RU_TASK"表中存在数据字段"ASSIGNEE_"是没有任何人的
	 * 那么也就是说当前的任务是没有被"拾取"
	 * 而"ACT_RU_IDENTITYLINK"表中，则出现了6条数据，每个用户都出现了两条数据
	 * 
	 * candidate：候选者
	 * 		候选者则代表该用户具备"拾取"人物的资格
	 * 
	 * participant：参与者
	 * 		参与者则是代表该用户曾经出现过这个任务当中，即使后面被删除，也会存在"participant"
	 * 
	 * 在启动流程的时候，如果我们在流程图中使用的是"流程变量"，这里我们同样也需要设置参数
	 * 如果是"Listener"或者直接写在流程图中的，则无需设置
	 */
	@Test
	public void startProcessByKey() {
		String processDefinitionKey = "groupTask";

		/**
		 * 设置组的"流程变量"并不是使用集合或者数组
		 * 同样是使用字符串，多个值英文逗号隔开
		 */
		/*		Map<String, Object> variableMap = new HashMap<>();
				variableMap.put("users", "userA,userB,userC");
				ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variableMap);*/
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

		System.out.println("流程启动成功，流程实例ID：" + processInstance.getProcessInstanceId());
	}

	/**
	 * 查询个人任务
	 * "组任务流程"
	 * 
	 * 如果当前查询的这个人并没有去"拾取"任务
	 * 那么根据"办理人"查询时查询不到任何信息的
	 * 只有"拾取"后才能通过"办理人"查询到信息
	 */
	@Test
	public void queryAssigneeTask() {
		String assignee = "userA";
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(assignee).list();

		taskList.forEach(i -> {
			System.out.println("任务ID: " + i.getId());
			System.out.println("任务Name: " + i.getName());
			System.out.println("任务办理人: " + i.getAssignee());
			System.out.println("= = = = = = = = = = = = = = = =");
		});
	}

	/**
	 * 查询这个组任务中的所有的成员
	 * "组任务流程"
	 * 
	 * 通过任务ID 可以查询到这个任务中所有的"候选者"
	 */
	@Test
	public void queryGroupTask() {
		String taskId = "7506";
		List<IdentityLink> taskList = taskService.getIdentityLinksForTask(taskId);

		taskList.forEach(i -> {
			System.out.println("用户ID:  " + i.getUserId());
			System.out.println("任务ID:  " + i.getTaskId());
			System.out.println("任务实例ID:  " + i.getProcessInstanceId());
			System.out.println("任务类型:  " + i.getType());
			System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
		});
	}

	/**
	 * 拾取任务
	 * "组任务流程"
	 * 
	 * 我们将这个成员内的某一个人让他去"拾取"这个任务
	 * 使用 "claim" 方法
	 * 需要2个参数：任务ID，用户ID
	 */
	@Test
	public void taskClaim() {
		String taskId = "2505";
		String userId = "userA";
		taskService.claim(taskId, userId);

		System.out.println("任务拾取成功");
	}

	/**
	 * 回退任务
	 * "组任务流程"
	 * 
	 * 我们在指派任务后，想换个人去执行或者不让这个人去执行了
	 * 那么就是任务的回退
	 * 任务的回退就是把"ASSIGNEE_"设置为NULL
	 */
	@Test
	public void taskRollback() {
		String taskId = "2505";
		taskService.claim(taskId, null);

		System.out.println("任务回退成功");
	}

	/**
	 * 添加组内成员
	 * "组任务流程"
	 * 
	 * 有的时候我们需要在现有的成员上再添加新的成员
	 * 使用 "addCandidateUser" 方法
	 * 需要两个参数：任务ID，用户ID
	 */
	@Test
	public void addUser() {
		String taskId = "2505";
		String userId = "userD";
		taskService.addCandidateUser(taskId, userId);

		System.out.println("添加成员成功");
	}

	/**
	 * 删除组内成员
	 * "组任务流程"
	 * 
	 * 在删除成员的时候，改名成员删除后表中仍会存在一条数据，代表的是这名成员之前存在过这个任务中
	 * 只不过后来被踢出去了而已，字段就是"participant"
	 * 使用 "deleteCandidateUser" 方法
	 * 需要两个参数：任务ID，用户ID
	 */
	@Test
	public void deleteUser() {
		String taskId = "2505";
		String userId = "userD";
		taskService.deleteCandidateUser(taskId, userId);

		System.out.println("删除用户成功");
	}
}
