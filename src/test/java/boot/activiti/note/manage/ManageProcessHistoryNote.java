package boot.activiti.note.manage;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/**
 * @ClassName: ManageProcessHistoryNote.java
 * @Author: Mr_Deer
 * @Date: 2019年3月28日 下午7:17:46
 * @Description: 流程的所有历史记录操作
 * 查询的都是 ACT_HI_* 的相关表
 * <p>
 * 所有的模糊查询都需要自己在参数上手动加上%： %param%
 */
public class ManageProcessHistoryNote extends SpringbootActivitiApplicationTests {
	@Autowired
	private HistoryService historyService;

	/**
	 * 查询“历史流程实例记录” 操作的数据表“ACT_HI_PROCINST”
	 */
	@Test
	public void queryHistoryProcessInstance() {
		// 获取“历史流程实例任务”查询器
		HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
		List<HistoricProcessInstance> historicProcessInstanceList = historicProcessInstanceQuery
//        .processDefinitionId() 根据“流程定义ID (PROC_DEF_ID_)”查询 - 数据类型：String
//        .processInstanceId(instanceId) 根据“流程实例ID (PROC_INST_ID_)”查询 - 数据类型：String
//        .processInstanceIds(instanceIdSet) 根据“流程实例ID (PROC_INST_ID_)”集合查询 - 数据类型：Set<String>
		// 排序
//        .orderByProcessDefinitionId().asc() 根据“流程定义ID (PROC_DEF_ID_)”排序 - 升序排序
//        .orderByProcessInstanceId().desc()根据“流程实例ID (PROC_INST_ID_)”排序 - 降序排序
		// 结果集
//        .list() 返回结果集
//        .listPage(firstResult, maxResults) 分页集合
//        .count() 返回个数
//        .singleResult() 单个结果
				.list();

		historicProcessInstanceList.forEach(i -> {
			System.out.println("执行实例 ID：" + i.getId());
			System.out.println("流程定义 ID：" + i.getProcessDefinitionId());
			System.out.println("任务名称：" + i.getName());
			System.out.println("任务创建时间：" + i.getStartTime());
			System.out.println("任务结束时间：" + i.getEndTime());
			System.out.println("任务持续时间（毫秒数）：" + i.getDurationInMillis());
			System.out.println(" = = = = = = = = = = = = = = = ");
		});
	}

	/**
	 * 查询“历史任务记录” 操作的数据表“ACT_HI_TASKINST”
	 */
	@Test
	public void queryHistoryTask() {
		// 获取“历史任务记录”查询器
		HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery();

		List<HistoricTaskInstance> historicTaskInstances = historicTaskInstanceQuery
//        .processInstanceId(instanceId) 根据“流程实例ID (PROC_INST_ID_)”查询 - 数据类型：String
//        .processInstanceIdIn(instanceIdList) 根据“流程实例ID ()”集合查询 - 数据类型：List<String>
//        .processDefinitionId(defineId) 根据“流程定义ID (PROC_DEF_ID_)”查询 - 数据类型：String
//        .executionId(executionId) 根据“执行实例ID (EXECUTION_ID_)”查询 - 数据类型：String
		// 排序
//        .orderByProcessDefinitionId().asc() 根据“流程定义ID (PROC_DEF_ID_)”排序 - 升序排序
//        .orderByProcessInstanceId().desc() 根据“流程实例ID (PROC_INST_ID_)”排序 - 降序排序
		// 结果集
//        .list() 返回结果集
//        .listPage(firstResult, maxResults) 分页集合
//        .count() 返回个数
//        .singleResult() 单个结果
				.list();
		historicTaskInstances.forEach(i -> {
			System.out.println("任务 ID：" + i.getId());
			System.out.println("任务办理人：" + i.getAssignee());
			System.out.println("执行实例 ID：" + i.getExecutionId());
			System.out.println("任务名称：" + i.getName());
			System.out.println("流程定义 ID：" + i.getProcessDefinitionId());
			System.out.println("流程实例 ID：" + i.getProcessInstanceId());
			System.out.println("任务创建时间：" + i.getStartTime());
			System.out.println("任务结束时间：" + i.getEndTime());
			System.out.println("任务持续时间（毫秒数）：" + i.getDurationInMillis());
			System.out.println(" = = = = = = = = = = = = = = = ");
		});
	}

	/**
	 * 查询“历史活动节点” 所谓的活动节点，就是我们流程图中的每一个元素，开始元素、任务元素、结束元素等等
	 * 这个元素是我们流程中执行的，如果有多个分支，但我们只执行了其中一条分支，那么对于本次流程来说
	 * 历史的活动节点，就是只是该流程执行的那一条分支上的所有元素，其他分支上的元素并不在其中出现 操作的数据表“ACT_HI_ACTINST”
	 */
	@Test
	public void queryHistoryActivityInstance() {
		// 获取“历史活动节点”查询器
		HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService
				.createHistoricActivityInstanceQuery();

		String activityInstanceId = "17504";
		List<HistoricActivityInstance> list = historicActivityInstanceQuery
		/**
		 * 根据“活动节点（ACT_ID_） ID”查询,也就是我们在绘制流程图的时候，每个节点元素上的“ID” - 参数类型：String
		 * 该匹配条件查询出来的肯定是多条的 因为该流程只要启动一次就会出现该流程的节点元素，那么再次启动会再次出现一样的节点
		 *
		 * <p>
		 * 张三启动请假流程，有四个节点 李四启动请假流程，有四个节点 那么这四个节点的id 都是一样的
		 * </p>
		 */
//        .activityId(activityId) 根据“活动节点ID (ACT_ID_)”查询 - 数据类型：String
//        .activityInstanceId(activityInstanceId) 根据“活动节点的实例ID (ID_)”查询 - 数据类型：String
//        .activityName(activityName) 根据“活动节点Name (ACT_NAME_)”查询 - 数据类型：String
		// 排序
//        .orderByActivityId()
//        .orderByActivityName()
		// 结果集
//        .list() 返回结果集
//        .listPage(firstResult, maxResults) 分页集合
//        .count() 返回个数
//        .singleResult() 单个结果
				.activityInstanceId(activityInstanceId).list();

		list.forEach(i -> {
			System.out.println("活动表节点 ID: " + i.getId());
			System.out.println("活动节点 ID: " + i.getActivityId());
			System.out.println("活动节点 Name: " + i.getActivityName());
			System.out.println("活动节点 Type: " + i.getActivityType());
			System.out.println("活动节点的实例 ID: " + i.getProcessInstanceId());
		});
	}

	/**
	 * 查询历史流程变量 操作的数据表“ACT_HI_VARINST”
	 */
	@Test
	public void getHistoryVariable() {
		// 获取“历史流程变量”查询器
		HistoricVariableInstanceQuery historicVariableInstanceQuery = historyService
				.createHistoricVariableInstanceQuery();
		List<HistoricVariableInstance> historicVariableInstanceList = historicVariableInstanceQuery
		// 查询条件
//        .id(historyVariableId) 根据“历史流程变量ID (ID_)”查询 - 数据类型：String
//        .executionId(executionId) 根据“执行实例ID (EXECUTION_ID_)”查询 - 数据类型：String
//        .executionIds(executionIdSet) 根据“执行实例ID (EXECUTION_ID_)”集合查询 - 数据类型：Set<String>
//        .processInstanceId(instanceId)根据“流程实例ID (PROC_INST_ID_)”查询 - 数据类型：String
//        .taskId(taskId) 根据“任务ID (TASK_ID_)”查询 - 数据类型：String
//        .taskIds(taskIdSet) 根据“任务（ACT_RU_TASK） ID”集合查询 - 数据类型：Set<String>
		// 排序
//        .orderByVariableName().asc() 根据“变量名称”排序 - 升序排序
//        .orderByProcessInstanceId().desc() 根据“流程实例 ID”排序 - 降序排序
		// 结果集
//        .list() 返回结果集
//        .listPage(firstResult, maxResults) 分页集合
//        .count() 返回个数
//        .singleResult() 单个结果
				.list();
		historicVariableInstanceList.forEach(i -> {
			System.out.println("变量 ID: " + i.getId());
			System.out.println("流程实例 ID: " + i.getProcessInstanceId());
			System.out.println("任务 ID: " + i.getTaskId());
			System.out.println("变量名称: " + i.getVariableName());
			System.out.println("变量类型: " + i.getVariableTypeName());
			System.out.println("变量值: " + i.getValue());
			System.out.println("= = = = = = = = = = = = = = = =");
		});
	}
}
