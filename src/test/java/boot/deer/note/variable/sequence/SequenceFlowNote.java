package boot.deer.note.variable.sequence;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
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
 * @ClassName: SequenceFlowNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月3日 下午3:52:21 
 * @Description: "连线"笔记 - 使用 Junit 进行测试
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
	 * 流程部署
	 */
	@Test
	public void processDeployment() {
		String path = "/processes/note/file/version_2/FileProcess.zip";
		InputStream inputStream = this.getClass().getResourceAsStream(path);

		String processName = "文件审批流程";
		Deployment deployment = repositoryService.createDeployment().name(processName)
				.addZipInputStream(new ZipInputStream(inputStream)).deploy();

		System.out.println("流程部署成功，流程部署ID：" + deployment.getId());
	}

	/**
	 * 启动流程
	 */
	@Test
	public void processStartByKey() {
		String processDefinitionKey = "FileProcess";
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

		System.out.println("流程启动成功，流程实例ID：" + processInstance.getProcessInstanceId());
	}

	/**
	 * 办理任务
	 */
	@Test
	public void completeTaskById() {
		String taskId = "30003";
		taskService.complete(taskId);

		System.out.println("任务办理成功");
	}

	/**
	 * 办理任务 - 携带参数
	 */
	@Test
	public void completeTaskByIdWithVariable() {
		String taskId = "27502";
		Map<String, Object> variableMap = getVariableMap("isImportant", "重要");
		taskService.complete(taskId, variableMap);

		System.out.println("任务办理成功");
	}

	/**
	 * 获取流程变量
	 * 
	 * @param key key
	 * @param value value
	 * @return Map 集合
	 */
	private Map<String, Object> getVariableMap(String key, Object value) {
		HashMap<String, Object> variableMap = new HashMap<>(16);
		variableMap.put(key, value);

		return variableMap;
	}
}
