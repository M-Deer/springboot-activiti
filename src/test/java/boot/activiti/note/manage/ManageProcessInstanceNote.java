package boot.activiti.note.manage;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/**
 * @ClassName: ManageProcessInstanceNote.java
 * @Author: Mr_Deer
 * @Date: 2019年3月28日 下午7:20:11
 * @Description: 管理流程实例
 * 对于流程实例、执行实例、任务的管理
 * <p>
 * 所有的模糊查询都需要自己在参数上手动加上%： %param%
 * <p>
 * Execution：执行实例对象，按照流程定义的规则执行一次的过程
 * 对应的数据表：
 * ACT_RU_EXECUTION
 * ACT_HI_PROCINST
 * ACT_HI_ACTINST
 * <p>
 * ProcessInstance：流程实例，特使流程从开始到结束的那个最大的执行分支，一个执行流程中，有且只有一个流程实例
 * 如果单流程，执行实例ID 就是 流程实例ID
 * <p>
 * Task：任务，执行到某个任务环节时生成的任务信息
 * 对应的数据表：
 * ACT_RU_TASK
 * ACT_HI_TASKINST
 */
public class ManageProcessInstanceNote extends SpringbootActivitiApplicationTests {

	// 注入 RuntimeService
	@Autowired
	private RuntimeService runtimeService;
	// 注入 TaskService
	@Autowired
	private TaskService taskService;

	/**
	 * 启动流程，通过“流程部署 ID”或者“流程定义KEY”启动流程
	 * 这里在实际开发中比较推荐 key
	 * <p>
	 * 使用“流程定义KEY”的时候，如果一个流程存在多个版本
	 * 那么会自动寻找最新的版本进行流程启动
	 * <p>
	 * 操作数据表“ACT_RU_EXECUTION”
	 */
	@Test
	public void startProcess() {
		String processDefineKey = "firstProcess";
		runtimeService
		/**
		 * 通过流程“流程定义ID (ID_)启动流程 - 数据类型：String
		 */
//        .startProcessInstanceById(processDefineId)
		/**
		 * processDefineId
		 *  流程定义ID (ID_) - 数据类型：String
		 * paramMap
		 *  流程变量可以携带流程的参数，同时控制流程的走向（同意/驳回） - 数据类型：Map<String,Object>
		 */
//        .startProcessInstanceById(processDefineId, paramMap)
		/**
		 * processDefineId
		 *  流程定义ID (ID_) - 数据类型：String
		 * paramMap
		 *  流程变量可以携带流程的参数，同时控制流程的走向（同意/驳回） - 数据类型：Map<String,Object>
		 * businessKey
		 *  该流程绑定的实际任务 ID，就是这条流程实例对应的是谁的流程（张三？李四？王五）
		 *  这在实际开发中很重要，这样才有实际的开发效果
		 */
//        .startProcessInstanceById(processDefineId, businessKey, paramMap)
		/**
		 * 通过流程“流程定义Key (KEY_)”启动流程 - 数据类型：String
		 */
//        .startProcessInstanceByKey(processDefineKey)
		/**
		 * 这在开发过程中推荐使用
		 *
		 * instanceKey
		 *  流程定义Key (KEY_) - 数据类型：String
		 * paramMap
		 *  流程变量可以携带流程的参数，同时控制流程的走向（同意/驳回） - 数据类型：Map<String,Object>
		 */
//        .startProcessInstanceByKey(processDefineKey,paramMap)
		/**
		 * 这在开发过程中推荐使用
		 *
		 * processDefineId
		 *  流程定义Key (KEY_) - 数据类型：String
		 * paramMap
		 *  流程变量可以携带流程的参数，同时控制流程的走向（同意/驳回） - 数据类型：Map<String,Object>
		 * businessKey
		 *  该流程绑定的实际任务 ID，就是这条流程实例对应的是谁的流程（张三？李四？王五）
		 *  这在实际开发中很重要，这样才有实际的开发效果
		 */
//        .startProcessInstanceByKey(processDefineKey, businessKey, paramMap)
				.startProcessInstanceByKey(processDefineKey);
		System.out.println("流程启动成功");
	}

	/**
	 * 查询当前正在执行的流程实例
	 * 操作数据表“ACT_RU_EXECUTION”
	 */
	@Test
	public void queryProcessInstance() {
		// 获取“流程实例”查询器
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		String instanceId = "17501";
		ProcessInstance processInstance = processInstanceQuery
//        .processInstanceId(instanceId) 根据“流程实例ID (PROC_INST_ID_)”查询 - 数据类型：String
//        .processInstanceIds(instanceIdSet) 根据“流程实例ID (PROC_INST_ID_)”集合查询 - 数据类型：Set<String>
//        .processInstanceBusinessKey(businessKey) 根据“流程实例BusinessKey (BUSINESS_KEY_)”查询 - 数据类型：String
//        .processInstanceName(instanceName) 根据“流程实例Name (NAME_)”查询 - 数据类型：String
//        .processInstanceNameLike(instanceName) 根据“流程实例Name (NAME_)”模糊查询 - 数据类型：String
//        .processDefinitionId(defineId) 根据“流程定义ID (PROC_DEF_ID_)”查询 - 数据类型：String
//        .processDefinitionIds(defineSet) 根据“流程定义ID (PROC_DEF_ID_)”集合查询 - 数据类型：Set<String>
//        .processDefinitionKey(defineKey) 根据“流程定义Key (ACT_RE_PROCDEF_NAME_.KEY_)”查询 - 数据类型：String
//        .processDefinitionKeys(defineKeySet) 根据“流程定义Key (ACT_RE_PROCDEF_NAME_.KEY_)”集合查询 - 数据类型：Set<String>
//        .processDefinitionName(defineName) 根据“流程定义Name (ACT_RE_PROCDEF_NAME_.NAME_)”查询 - 数据类型：String
		// 排序
//        .orderByProcessDefinitionId().asc() 根据“流程定义ID (PROC_DEF_ID_)排序 - 升序排序
//        .orderByProcessInstanceId().desc() 根据“流程实例ID (PROC_INST_ID_)”排序 - 降序排序
		// 结果集
//        .list() 返回结果集
//        .listPage(firstResult, maxResults) 分页集合
//        .count() 返回个数
//        .singleResult() 单个结果
				.processInstanceId(instanceId).singleResult();

		/**
		 * 值得注意的是“ACT_RU_EXECUTION”数据表中的 ID 是执行实例ID，并不是流程实例ID
		 * processInstance.getId()：执行实例ID
		 * processInstance.getProcessInstanceId(): 流程实例ID
		 */
		System.out.println("执行实例 ID: " + processInstance.getId());
		System.out.println("流程实例 ID: " + processInstance.getProcessInstanceId());
		System.out.println("流程定义 ID: " + processInstance.getDeploymentId());
		System.out.println("流程启动时间: " + processInstance.getStartTime());
	}

	/**
	 * 查询我的个人任务
	 * 操作数据表“ACT_RU_TASK”
	 */
	@Test
	public void queryMyTask() {
		// 获取“任务”查询器
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> taskList = taskQuery
		// 查询条件
//        .taskAssignee(assignee) 根据“办理人 (ASSIGNEE_)”查询 - 数据类型：String
//        .deploymentId(deploymentId) 根据“流程定义ID (ACT_RE_DEPLOYMENT.ID_)”查询 - 数据类型：String
//        .deploymentIdIn(deploymentIdList) 根据“流程定义ID (ACT_RE_DEPLOYMENT.ID_)”集合查询 - 数据类型：List<String>
//        .processDefinitionId(defineId) 根据“流程定义ID (PROC_DEF_ID_)”查询 - 数据类型：String
//        .processDefinitionKey(defineKey) 根据“流程定义Key (ACT_RE_PROCDEF.KEY_)”查询 - 数据类型：String
//        .processDefinitionKeyIn(defineKeyList) 根据“流程定义Key (ACT_RE_PROCDEF.KEY_)”集合查询- 数据类型：List<String>
//        .processDefinitionKeyLike(defineKey)根据“流程定义Key (ACT_RE_PROCDEF.KEY_)”模糊查询 - 数据类型：String
//        .processDefinitionName(defineName) 根据“流程定义Name (ACT_RE_PROCDEF.NAME_)”查询 - 数据类型：String
//        .processDefinitionNameLike(defineName) 根据“流程定义Name (ACT_RE_PROCDEF.NAME_)” 模糊查询 - 数据类型：String
//        .processInstanceBusinessKey(businessKey) 根据“流程实例BusinessKey (ACT_RU_EXECUTION.BUSINESS_KEY_)”查询 - 数据类型：String
//        .processInstanceBusinessKeyLike(businessKey) 根据“流程实例BusinessKey (ACT_RU_EXECUTION.BUSINESS_KEY_)”模糊查询 - 数据类型：String
//        .processInstanceId(instanceId) 根据“流程实例ID (EXECUTION_ID_)”查询 - 数据类型：String
//        .executionId(executionId) 根据“执行实例ID (PROCE_INST_ID)”查询 - 数据类型：String
		// 排序
//        .orderByTaskCreateTime().ace() 根据“任务创建时间 (CREATE_TIME_)”排序 - 升序排序
//        .orderByTaskAssignee().desc() 根据“办理人 (ASSIGNEE_)”排序 - 降序排序
		// 结果集
//        .list() 返回结果集
//        .listPage(firstResult, maxResults) 分页集合
//        .count() 返回个数
//        .singleResult() 单个结果
				.list();

		System.out.println(taskList.toString());
	}

	/**
	 * 办理任务
	 * 操作数据表“ACT_RU_TASK”
	 */
	@Test
	public void completeTask() {
		String taskId = "17505";
		taskService
		/**
		 * 根据“任务ID (ACT_RU_TASK.ID_)”办理任务 - 数据类型：String
		 */
//        .complete(taskId)
		/**
		 * taskId
		 *  任务ID (ACT_RU_TASK.ID_) - 数据类型：String
		 * paramMap
		 *  paramMap 携带参数，决定流程走向 - 数据类型：Map<String, Object>
		 */
//        .complete(taskId, paramMap)
				.complete(taskId);
		System.out.println("任务办理完成");
	}

	/**
	 * 判断流程是否结束
	 * 其主要目的是更新当前的流程呈现出来的状态，业务的流程状态
	 */
	@Test
	public void isComplete() {
		// 已知“流程实例 ID”
		String instanceId = "17501";
		// 根据“流程实例 ID”查询
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId)
				.singleResult();
		/**
		 * 这里可以进行判断
		 * 如果不为 null，那么流程肯定尚未结束
		 * 如果为 null，那么流程肯定会结束
		 *
		 * 因为流程结束后，在 ACT_RU_* 表中的所有数据都会抹掉
		 */
		if (null == processInstance)
			System.out.println("流程已结束");
		else
			System.out.println("流程尚未结束");

		// 已知“任务实例 ID”
//        String taskId = "20002";
		/**
		 * 那我们可以通过“任务实例 ID”去查询到“任务实例 ID”
		 * 然后在重复上面的思路
		 */

//		  Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//		  String processInstanceId = task.getProcessInstanceId();

	}
}
