package boot.deer.note.listener;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/** 
 * @ClassName: VariableListenerNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月10日 下午9:10:52 
 * @Description: "监听器"笔记 - 使用 Junit 进行测试
 */
public class VariableListenerNote extends SpringbootActivitiApplicationTests {
	// 注入 RepositoryService
	@Autowired
	private RepositoryService repositoryService;
	// 注入 RuntimeService
	@Autowired
	private RuntimeService runtimeService;
	// 注入 TaskService
	@Autowired
	private TaskService taskservice;

	/**
	 * 部署流程
	 */
	@Test
	public void deploymentProcess() {
		// zip 文件的路径
		String zipPath = "/processes/note/file/version_3/FileProcess.zip";

		// 获取一个输入流
		InputStream resourceAsStream = this.getClass().getResourceAsStream(zipPath);

		// 这里使用的就是 zip 的一个封装类型
		Deployment deployment = repositoryService.createDeployment().name("文件审批流程")
				.addZipInputStream(new ZipInputStream(resourceAsStream)).deploy();

		System.out.println("流程部署成功，流程部署ID：" + deployment.getId());
	}

	/**
	 * 启动流程 
	 */
	@Test
	public void startProcessByKey() {
		String processDefinitionKey = "FileProcess";
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

		System.out.println("流程启动成功，流程实例ID：" + processInstance.getProcessInstanceId());
	}

	/**
	 * 任务办理
	 */
	@Test
	public void completeByTaskId() {
		String taskId = "12505";
		taskservice.complete(taskId);

		System.out.println("任务办理成功");
	}
}
