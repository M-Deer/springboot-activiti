package boot.activiti.note.basis;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/**
 * @ClassName: GetActivitiServiceNote.java
 * @Author: Mr_Deer
 * @Date: 2019年3月28日 下午7:10:17
 * @Description: 获取 Activiti 自带的封装好的 Service 的方式
 */
@SuppressWarnings("unused")
public class GetActivitiServiceNote extends SpringbootActivitiApplicationTests {
	/**
	 * 通过 spring @Autowired 进行注入
	 */
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
	 * 我们可以先获取流程引擎 然后通过流程引擎在获取 service 至于想获取什么流程引擎，是 spring的 还是 activiti 的自行选择
	 * 以下我们选用正常的做 example
	 */
	public void getServiceOfSSM() {
		// 获取原生的流程引擎
		ProcessEngineConfiguration processConfiguration = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration();
		ProcessEngine processEngine = processConfiguration.buildProcessEngine();

		/**
		 * getRepositoryService(); 流程部署/流程图的部署、修改、删除操作 提供的是对以下表的操作 ACT_GE_BYTEARRAY
		 * ACT_RE_DEPLOYMENT ACT_RE_MODEL ACT_RE_PROCDEF
		 */
		processConfiguration.getRepositoryService();

		/**
		 * getRuntimeService(); getTaskService(); 流程的运行，对以下表进行增删改查 ACT_RU_DEADLETTER_JOB
		 * ACT_RU_EVENT_SUBSCR ACT_RU_EXECUTION ACT_RU_IDENTITYLINK ACT_RU_JOB
		 * ACT_RU_SUSPENDED_JOB ACT_RU_TASK ACT_RU_TIMER_JOB ACT_RU_VARIABLE
		 */
		processConfiguration.getRuntimeService();
		processConfiguration.getTaskService();

		/**
		 * getHistoryService(); 流程的历史纪录，对以下表进行操作 ACT_HI_ACTINST ACT_HI_ATTACHMENT
		 * ACT_HI_COMMENT ACT_HI_DETAIL ACT_HI_IDENTITYLINK ACT_HI_PROCINST
		 * ACT_HI_TASKINST ACT_HI_VARINST
		 */
		processConfiguration.getHistoryService();

		/**
		 * getFormService(); 流程的页面表，该表并不会使用太多
		 */
		processConfiguration.getFormService();

		/**
		 * getIdentityService(); 对工作流用户管理的操作，对以下表进行操作 ACT_ID_GROUP ACT_ID_INFO
		 * ACT_ID_MEMBERSHIP ACT_ID_USER
		 */
		processConfiguration.getIdentityService();

		/**
		 * getManagementService(); 是一个管理器
		 */
		processConfiguration.getManagementService();
	}
}
