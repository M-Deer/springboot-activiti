package boot.deer.note.history.activity;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;
import boot.deer.component.util.DateUtil;

/** 
 * @ClassName: HistoryActivityNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月8日 上午11:18:41 
 * @Description: "历史活动节点"笔记 - 使用 Junit 进行测试
 */
public class HistoryActivityNote extends SpringbootActivitiApplicationTests {

	// 注入 HistoryService
	@Autowired
	private HistoryService historyService;

	/**
	 * 查询历史活动节点
	 * 
	 * 操作对应数据表：
	 * 		ACT_HI_ACTINST
	 */
	@Test
	public void historyActivityQuery() {
		// 获取“历史活动节点”查询器
		HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService
				.createHistoricActivityInstanceQuery();

		List<HistoricActivityInstance> activityInstances = historicActivityInstanceQuery

		// 查询匹配条件

		/* 流程定义ID (PROC_DEF_ID_) - 数据类型：String */
//		.processDefinitionId(processDefinitionId)
		/* 流程实例ID (PROC_INST_ID_) - 数据类型：String */
//		.processInstanceId(processInstanceId)
		/* 活动节点ID(ACT_ID_) - 数据类型：String */
//		.activityId(activityId)
		/* 任务节点Name(ACT_NAME_) - 数据类型：String */
//		.activityName(activityName)
		/* 任务节点类型(ACT_TYPE) - 数据类型：String */
//		.activityType(activityType)
		/* 任务办理人(ASSIGNEE_) */
//		.taskAssignee(userId)

		// 排序

		/* 活动节点ID(ACT_ID) - 升序排序 */
//		.orderByActivityId().asc()
		/* 活动节点类型(ACT_ID) - 降序排序 */
//		.orderByActivityType().desc()

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
		activityInstances.forEach(activity -> {
			System.out.println("历史活动节点ID：" + activity.getActivityId());
			System.out.println("历史活动节点任务ID：" + activity.getTaskId());
			System.out.println("历史活动节点Name：" + activity.getActivityName());
			System.out.println("历史活动节点类型：" + activity.getActivityType());
			System.out.println("历史活动节点办理人：" + activity.getAssignee());
			System.out.println("历史活动节点开始时间：" + DateUtil.dateTime2String(activity.getStartTime()));
			System.out.println("历史活动节点结束时间：" + DateUtil.dateTime2String(activity.getEndTime()));
			System.out.println(
					"历史活动节点办理持续周期时间（：秒）：" + DateUtil.milliseconds2Second(activity.getDurationInMillis()) + "s");
			System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = ");
		});
	}
}
