package boot.deer.note.activti_api.service;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/** 
 * @ClassName: GetActivitiService 
 * @Author: Mr_Deer
 * @Date: 2019年3月31日 上午11:55:41 
 * @Description: 获取 Activiti 自带的 Service
 * 通过注入的方式
 */
public class GetActivitiService extends SpringbootActivitiApplicationTests {

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private FormService formService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private ManagementService managementService;

	/**
	 * 进行测试
	 */
	@Test
	public void isEmpty() {
		if (repositoryService != null)
			System.out.println("repositoryService 获取成功");
		else
			System.out.println("repositoryService 获取失败");

		if (runtimeService != null)
			System.out.println("runtimeService 获取成功");
		else
			System.out.println("runtimeService 获取失败");

		if (taskService != null)
			System.out.println("taskService 获取成功");
		else
			System.out.println("taskService 获取失败");

		if (historyService != null)
			System.out.println("historyService 获取成功");
		else
			System.out.println("historyService 获取失败");

		if (formService != null)
			System.out.println("formService 获取成功");
		else
			System.out.println("formService 获取失败");

		if (identityService != null)
			System.out.println("identityService 获取成功");
		else
			System.out.println("identityService 获取失败");

		if (managementService != null)
			System.out.println("managementService 获取成功");
		else
			System.out.println("managementService 获取失败");

		if (repositoryService != null)
			System.out.println("repositoryService 获取成功");
		else
			System.out.println("repositoryService 获取失败");
	}
}
