package boot.deer.note.process.start;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/** 
 * @ClassName: ExecutionNote 
 * @Author: Mr_Deer
 * @Date: 2019年4月2日 下午8:24:19 
 * @Description: "执行实例"笔记 - 使用 Junit 进行测试
 */
public class ExecutionNote extends SpringbootActivitiApplicationTests {

	// 注入 RuntimeService
	@Autowired
	private RuntimeService runtimeService;

	/**
	 * 查询执行实例 
	 * 
	 * 操作对应数据表：
	 * 		ACT_RU_EXECUTION
	 */
	@Test
	public void executionQuery() {
		// 获取"执行实例"查询器
		ExecutionQuery executionQuery = runtimeService.createExecutionQuery();

		List<Execution> executions = executionQuery

		// 查询匹配条件

		/* 活动节点ID(ACT_ID_) - 数据类型：String */
//		.activityId(activityId)
		/* 执行实例ID(ID_) - 数据类型：String */
//		.executionId(executionId)
		/* 父节点ID(PARENT_ID_) - 数据类型：String */
//		.parentId(parentId)
		/* 流程实例ID (PROC_INST_ID_) - 数据类型：String */
//		.processInstanceId(processInstanceId)
		/* 业务Key(BUSINESS_KEY_) - 数据类型：String */
//		.processInstanceBusinessKey(processInstanceBusinessKey)
		/* 流程定义ID (PROC_DEF_ID_) - 数据类型：String */
//		.processDefinitionId(processDefinitionId)
		/* 流程定义Name(ACT_RE_PROCDEF.NAME_) - 数据类型：String */
//		.processDefinitionName(processDefinitionName)
		/* 流程定义Key(ACT_RE_PROCDEF.KEY_) - 数据类型：String */
//		.processDefinitionKey(processDefinitionKey)
		/* 流程定义Key(ACT_RE_PROCDEF.KEY_) - 数据类型：Set<String> - 集合查询 IN */
//		.processDefinitionKeys(processDefinitionKeys)

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
		executions.forEach(execution -> {
			System.out.println("执行实例ID: " + execution.getId());
			System.out.println("执行实例Name: " + execution.getName());
			System.out.println("父节点ID: " + execution.getParentId());
			System.out.println("流程实例ID: " + execution.getProcessInstanceId());
			System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = ");
		});
	}
}
