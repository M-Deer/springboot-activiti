package boot.deer.note.variable.type;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/** 
 * @ClassName: VariableTypeNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月9日 上午11:39:29 
 * @Description: "变量类型"笔记 - 使用 Junit 进行测试
 */
public class VariableTypeNote extends SpringbootActivitiApplicationTests {

	// 注入 RepositoryService
	@Autowired
	private RepositoryService repositoryService;
	// 注入 RuntimeService
	@Autowired
	private RuntimeService runtimeService;

	/**
	 * 部署流程
	 */
	@Test
	public void deploymentProcess() {
		// zip 文件的路径
		String zipPath = "/processes/note/leave/LeaveProcess.zip";

		// 获取一个输入流
		InputStream resourceAsStream = this.getClass().getResourceAsStream(zipPath);

		// 这里使用的就是 zip 的一个封装类型
		Deployment deployment = repositoryService.createDeployment().name("请假流程")
				.addZipInputStream(new ZipInputStream(resourceAsStream)).deploy();

		System.out.println("流程部署成功，流程部署ID：" + deployment.getId());
	}

	/**
	 * 启动流程同时携带一些参数
	 */
	@Test
	public void startProcessWithVariable() {
		String processDefinitionKey = "LeaveProcess";
		// 参数
		Map<String, Object> variableMap = new HashMap<>();
		// 请假天数 5天
		variableMap.put("days", 5);
		// 请假理由
		variableMap.put("description", "约会");

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variableMap);
		System.out.println("流程启动成功，流程实例ID：" + processInstance.getProcessInstanceId());
	}

	/**
	 * 改变流程变量
	 */
	@Test
	public void changeVariable() {
		// 执行实例ID
		String executionId = "37501";
		// 变量Map
		Map<String, Object> variableMap = new HashMap<>(16);
		// 请假天数 3天
		variableMap.put("days", 3);

		// 通过 setVariables 来放置变量
		runtimeService.setVariables(executionId, variableMap);
		System.out.println("变量放置成功");
	}

	/**
	 * 对象变量
	 */
	@Test
	public void objectVariable() {
		// 执行实例ID
		String executionId = "37501";
		// 变量Map
		Map<String, Object> variableMap = new HashMap<>(16);

		// 这里我们放置 user 对象，与常规的变量放置方式一样
		UserModel user = new UserModel();
		user.setName("Mr_Deer");
		user.setAge(24);

		variableMap.put("user", user);

		// 通过 setVariables 来放置变量
		runtimeService.setVariables(executionId, variableMap);
		System.out.println("变量放置成功");
	}

	/**
	 * 获取变量
	 */
	@Test
	public void getVariable() {
		// 执行实例ID
		String executionId = "37501";

		// 需要获取的变量 key
		String daysKey = "days";
		String descriptionKey = "description";
		String userKey = "user";

		// 通过 runtimeService 获取
		Integer daysValue = (Integer) runtimeService.getVariable(executionId, daysKey);
		String descriptionValue = (String) runtimeService.getVariable(executionId, descriptionKey);
		UserModel userValue = (UserModel) runtimeService.getVariable(executionId, userKey);

		System.out.println("请假天数：" + daysValue);
		System.out.println("请假理由：" + descriptionValue);
		System.out.println("用户对象：" + userValue.toString());
	}
}
