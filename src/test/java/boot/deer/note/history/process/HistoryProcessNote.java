package boot.deer.note.history.process;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;
import boot.deer.component.util.DateUtil;

/** 
 * @ClassName: HistoryProcessNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月3日 下午1:02:34 
 * @Description: "历史流程"笔记 - 使用 Junit 进行测试
 */
public class HistoryProcessNote extends SpringbootActivitiApplicationTests {

	// 注入 HistoryService
	@Autowired
	private HistoryService historyService;

	/**
	 * 查询历史流程
	 * 
	 * 操作对应数据表：
	 * 		ACT_HI_PROCINST
	 */
	@Test
	public void historyProcessQuery() {
		// 获取“历史流程”查询器
		HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();

		List<HistoricProcessInstance> historicProcessInstances = historicProcessInstanceQuery

		// 查询匹配条件

		/* 流程定义ID (PROC_DEF_ID_) - 数据类型：String */
//		.processDefinitionId(processDefinitionId)
		/* 流程定义Key(ACT_RE_PROCDEF.KEY_) - 数据类型：String */
//		.processDefinitionKey(processDefinitionKey)
		/* 流程定义Key(ACT_RE_PROCDEF.KEY_) - 数据类型：List<String> - 集合查询 IN */
//		.processDefinitionKeyIn(processDefinitionKeys)
		/* 流程定义Name(ACT_RE_PROCDEF.NAME_) - 数据类型：String */
//		.processDefinitionName(processDefinitionName)
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
		historicProcessInstances.forEach(processInstance -> {
			System.out.println("历史流程 ID: " + processInstance.getId());
			System.out.println("流程定义 ID: " + processInstance.getDeploymentId());
			System.out.println("历史流程开始时间: " + DateUtil.dateTime2String(processInstance.getStartTime()));
			System.out.println("历史流程结束时间: " + DateUtil.dateTime2String(processInstance.getEndTime()));
			System.out.println(
					"流程持续周期时间（：秒）: " + DateUtil.milliseconds2Second(processInstance.getDurationInMillis()) + "s");
			System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = ");
		});
	}
}
