package boot.activiti.note.basis;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/**
 * @ClassName: StartProcessNote.java
 * @Author: Mr_Deer
 * @Date: 2019年3月28日 下午7:13:09
 * @Description: 流程启动，我们之前已经部署好的进行启动
 */
public class StartProcessNote extends SpringbootActivitiApplicationTests {

	@Autowired
	private RuntimeService runtimeService;

	/**
	 * 启动流程，一下是两种不同的启动方式，根据具体情况进行选择即可
	 */

	@Test
	public void startProcess() {
		/**
		 * 通过流程定义 ID 进行创建
		 */
		String processId = "firstProcess:1:4";
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId);

		/**
		 * 通过流程定义 NAME 进行创建
		 */
		/*
		 * String processKey= "firstProcess"; ProcessInstance processInstanceSecond =
		 * runtimeService.startProcessInstanceByKey(processKey);
		 */

		System.out.println("流程启动成功，流程实例ID:" + processInstance.getProcessInstanceId());
	}
}
