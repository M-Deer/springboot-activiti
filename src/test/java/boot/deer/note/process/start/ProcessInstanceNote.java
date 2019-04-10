package boot.deer.note.process.start;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;
import boot.deer.component.util.DateUtil;

/** 
 * @ClassName: ProcessInstanceNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月2日 上午11:17:38 
 * @Description: "流程实例"笔记 - 使用 Junit 进行测试
 */
public class ProcessInstanceNote extends SpringbootActivitiApplicationTests {

	// 注入 RuntimeService
	@Autowired
	private RuntimeService runtimeService;

	/**
	 * 启动流程 - 使用"流程定义 ID"
	 * 
	 * 操作对应数据表：
	 * 		ACT_RU_EXECUTION
	 * 		ACT_RU_TASK
	 * 		ACT_RU_VARIABLE
	 * 		ACT_RU_IDENTITYLINK
	 */
	@Test
	public void startProcessByDefinitionId() {
		// 流程定义 ID
		String processDefinitionId = "LeaveProcess:1:4";
		// 启动流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
		System.out.println("流程启动成功，流程实例ID：" + processInstance.getProcessInstanceId());
	}

	/**
	 * 启动流程 - 使用"流程定义 Key"
	 * 
	 * 操作对应数据表：
	 * 		ACT_RU_EXECUTION
	 * 		ACT_RU_TASK
	 * 		ACT_RU_VARIABLE
	 * 		ACT_RU_IDENTITYLINK
	 */
	@Test
	public void startProcessByKey() {
		// 流程定义 Key
		String processDefinitionKey = "LeaveProcess";
		// 启动流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
		System.out.println("流程启动成功，流程实例ID：" + processInstance.getProcessInstanceId());
	}

	/**
	 * 查询流程实例
	 * 
	 * 操作对应数据表：
	 * 		ACT_RU_EXECUTION
	 */
	@Test
	public void processInstanceQuery() {
		// 获取“流程实例”查询器
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();

		List<ProcessInstance> processInstances = processInstanceQuery

		// 查询匹配条件

		/* 流程实例ID (PROC_INST_ID_) - 数据类型：String */
//		.processInstanceId(processInstanceId)
		/* 流程实例ID (PROC_INST_ID_) - 数据类型：Set<String> - 集合查询 IN */
//		.processInstanceIds(processInstanceIds)
		/* 流程实例Name (NAME_) - 数据类型：String */
//		.processInstanceName(name)
		/* 流程实例Name (NAME_) - 数据类型：String - 模糊匹配 %param% */
//		.processInstanceNameLike(nameLike)
		/* 业务Key(BUSINESS_KEY_) - 数据类型：String */
//		.processInstanceBusinessKey(processInstanceBusinessKey)
		/*
		 * 业务Key(BUSINESS_KEY_) - 数据类型：String
		 * 流程定义Key(ACT_RE_PROCDEF.KEY_)
		 * */
//		.processInstanceBusinessKey(processInstanceBusinessKey, processDefinitionKey)
		/* 流程定义ID (PROC_DEF_ID_) - 数据类型：String */
//		.processDefinitionId(processDefinitionId)
		/* 流程定义ID (PROC_DEF_ID_) - 数据类型：Set<String> - 集合查询 IN */
//		.processDefinitionIds(processDefinitionIds)
		/* 流程定义Key(ACT_RE_PROCDEF.KEY_) - 数据类型：String */
//		.processDefinitionKey(processDefinitionKey)
		/* 流程定义Key(ACT_RE_PROCDEF.KEY_) - 数据类型：Set<String> - 集合查询 IN */
//		.processDefinitionKeys(processDefinitionKeys)
		/* 流程定义Name(ACT_RE_PROCDEF.NAME_) - 数据类型：String */
//		.processDefinitionName(processDefinitionName)

		// 排序

		/* 流程定义ID (PROC_DEF_ID_) - 升序排序 */
//      .orderByProcessDefinitionId().asc()
		/*流程实例ID (PROC_INST_ID_) - 降序排序 */
//      .orderByProcessInstanceId().desc()

		// 结果类型

		/* List 结果集 */
//		.list()
		/* List 结果集 - 分页 */
//		.listPage(firstResult, maxResults)
		/* 唯一的结果 */
//		.singleResult()
		/* 结果计数 */
//		.count()

				.list();

		// 循环结果集
		processInstances.forEach(processInstance -> {
			System.out.println("执行实例 ID: " + processInstance.getId());
			System.out.println("流程实例 ID: " + processInstance.getProcessInstanceId());
			System.out.println("流程定义 ID: " + processInstance.getDeploymentId());
			System.out.println("流程启动时间: " + DateUtil.dateTime2String(processInstance.getStartTime()));
			System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = ");
		});
	}
}
