package boot.deer.note.history.task;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;
import boot.deer.component.util.DateUtil;

/** 
 * @ClassName: HistoryTaskNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月8日 上午10:31:09 
 * @Description: "历史任务"笔记 - 使用 Junit 进行测试
 */
public class HistoryTaskNote extends SpringbootActivitiApplicationTests {

	// 注入 HistoryService
	@Autowired
	private HistoryService historyService;

	/**
		 * 查询历史任务流程
		 * 
		 * 操作对应数据表：
		 * 		ACT_HI_TASKINST
		 */
	@Test
	public void historyTaskQuery() {
		// 获取“历史任务”查询器
		HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery();

		List<HistoricTaskInstance> historicTaskInstances = historicTaskInstanceQuery

		// 查询匹配条件

		/* 流程定义ID (PROC_DEF_ID_) - 数据类型：String */
//			.processDefinitionId(processDefinitionId)
		/* 流程定义Key(ACT_RE_PROCDEF.KEY_) - 数据类型：String */
//			.processDefinitionKey(processDefinitionKey)
		/* 流程定义Key(ACT_RE_PROCDEF.KEY_) - 数据类型：String - 模糊匹配 %param% */
//			.processDefinitionKeyLike(processDefinitionKeyLike)
		/* 流程定义Key(ACT_RE_PROCDEF.KEY_) - 数据类型：List<String> - 集合查询 IN */
//			.processDefinitionKeyIn(processDefinitionKeys)
		/* 流程定义Name(ACT_RE_PROCDEF.NAME_) - 数据类型：String */
//			.processDefinitionName(processDefinitionName)
		/* 流程定义Name(ACT_RE_PROCDEF.NAME_) - 数据类型：String - 模糊匹配 %param% */
//			.processDefinitionNameLike(processDefinitionNameLike)
		/* 流程实例ID (PROC_INST_ID_) - 数据类型：String */
//			.processInstanceId(processInstanceId)
		/* 流程实例ID (PROC_INST_ID_) - 数据类型：Set<String> - 集合查询 IN */
//			.processInstanceIdIn(processInstanceIds)

		// 排序

		/* 任务开始时间 (START_TIME_) - 升序排序 */
//			.orderByHistoricTaskInstanceEndTime().asc()
		/* 任务结束时间 (END_TIME_) - 升序排序 */
//			.orderByHistoricTaskInstanceEndTime().desc()

		// 结果类型

		/* List 结果集 */
//			.list()
		/* List 结果集 - 分页 */
//			.listPage(firstResult, maxResults)
		/* 唯一的结果 */
//			.singleResult()
		/* 结果计数 */
//			.count()

				.list();

		// 循环结果集
		historicTaskInstances.forEach(historyTask -> {
			System.out.println("历史任务ID：" + historyTask.getId());
			System.out.println("历史任务执行实例ID：" + historyTask.getExecutionId());
			System.out.println("历史任务实例ID：" + historyTask.getProcessInstanceId());
			System.out.println("历史任务定义ID：" + historyTask.getProcessDefinitionId());
			System.out.println("历史任务开始时间：" + DateUtil.dateTime2String(historyTask.getStartTime()));
			System.out.println("历史任务结束时间：" + DateUtil.dateTime2String(historyTask.getEndTime()));
			System.out.println("历史任务名称：" + historyTask.getName());
			System.out.println("历史任务办理人：" + historyTask.getAssignee());
			System.out.println(
					"历史任务办理持续周期时间（：秒）：" + DateUtil.milliseconds2Second(historyTask.getDurationInMillis()) + "s");
			System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = ");
		});
	}
}
