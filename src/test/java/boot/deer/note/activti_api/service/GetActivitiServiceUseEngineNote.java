package boot.deer.note.activti_api.service;
/** 
 * @ClassName: GetActivitiServiceNote 
 * @Author: Mr_Deer
 * @Date: 2019年3月31日 上午11:46:29 
 * @Description: 获取 Activiti 自带的 Service
 * 通过流程引擎的方法获取
 */

import javax.sql.DataSource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

public class GetActivitiServiceUseEngineNote extends SpringbootActivitiApplicationTests {

	// 注入 DataSource
	@Autowired
	private DataSource dataSource;

	/**
	 * 获取流程方式
	 */
	@Test
	public void getService() {
		// 获取原生的流程引擎
		ProcessEngineConfiguration configuration = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration();
		// 配置表的初始化的方式
		configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		// 配置数据源
		configuration.setDataSource(dataSource);
		// 得到流程引擎
		ProcessEngine processEngine = configuration.buildProcessEngine();

		/**
		 * getRepositoryService();
		 * 流程部署/流程图的部署、修改、删除操作
		 * 提供的是对以下表的操作
		 * ACT_GE_BYTEARRAY     ACT_RE_DEPLOYMENT   ACT_RE_MODEL    ACT_GE_PROPERTY
		 */
		RepositoryService repositoryService = processEngine.getRepositoryService();

		/**
		 * getRuntimeService();
		 * getTaskService();
		 * 流程的运行，对以下表进行增删改查
		 * ACT_RU_DEADLETTER_JOB    ACT_RU_EVENT_SUBSCR     ACT_RU_EXECUTION    ACT_RU_IDENTITYLINK
		 * ACT_RU_JOB   ACT_RU_SUSPENDED_JOB    ACT_RU_TASK     ACT_RU_TIMER_JOB    ACT_RU_VARIABLE
		 */
		RuntimeService runtimeService = processEngine.getRuntimeService();
		TaskService taskService = processEngine.getTaskService();

		/**
		 * getHistoryService();
		 * 流程的历史纪录，对以下表进行操作
		 * ACT_HI_ACTINST   ACT_HI_ATTACHMENT   ACT_HI_COMMENT  ACT_HI_DETAIL   ACT_HI_IDENTITYLINK
		 * ACT_HI_PROCINST  ACT_HI_TASKINST     ACT_HI_VARINST
		 */
		HistoryService historyService = processEngine.getHistoryService();

		/**
		 * getFormService();
		 * 流程的页面表，该表并不会使用太多
		 */
		FormService formService = processEngine.getFormService();

		/**
		 * getIdentityService();
		 * 对工作流用户管理的操作，对以下表进行操作
		 * ACT_ID_GROUP     ACT_ID_INFO     ACT_ID_MEMBERSHIP   ACT_ID_USER
		 */
		IdentityService identityService = processEngine.getIdentityService();

		/**
		 * getManagementService();
		 * 是一个管理器
		 */
		ManagementService managementService = processEngine.getManagementService();

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
