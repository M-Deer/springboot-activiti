package boot.deer.note.history.variable;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;
import boot.deer.component.util.DateUtil;

/** 
 * @ClassName: HistoryVariableNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月9日 上午10:16:01 
 * @Description: "历史流程变量"笔记 - 使用 Junit 进行测试
 */
public class HistoryVariableNote extends SpringbootActivitiApplicationTests {

	// 注入 HistoryService
	@Autowired
	private HistoryService historyService;

	/**
	 * 查询历史流程变量
	 * 
	 * 操作对应数据表：
	 * 		ACT_HI_VARINST
	 */
	@Test
	public void historyVariableQuery() {
		// 获取“历史流程变量”查询器
		HistoricVariableInstanceQuery historicVariableInstanceQuery = historyService
				.createHistoricVariableInstanceQuery();

		List<HistoricVariableInstance> variableInstances = historicVariableInstanceQuery

		// 查询匹配条件

		/* 流程实例ID (PROC_INST_ID_) - 数据类型：String */
//		.processInstanceId(processInstanceId)
		/* 变量名称(NAME_)  - 数据类型：String */
//		.variableName(variableName)
		/* 变量名称(NAME_)  - 数据类型：String - 模糊匹配 %param% */
//		.variableNameLike(variableNameLike)
		/*
		 * 变量名称(NAME_)  - 数据类型：String
		 * 变量值 - 数据类型：String 
		 * 相等匹配
		 */
//		.variableValueEquals(variableName, variableValue)
		/*
		 * 变量名称(NAME_)  - 数据类型：String
		 * 变量值 - 数据类型：String 
		 * 模糊匹配
		 */
//		.variableValueLike(variableName, variableValue)

		// 排序

		/*流程实例ID (PROC_INST_ID_) - 升序排序 */
//		.orderByProcessInstanceId().asc()
		/*变量名称 (NAME) - 降序排序 */
//		.orderByVariableName().desc()

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
		variableInstances.forEach(variable -> {
			System.out.println("历史变量ID：" + variable.getId());
			System.out.println("历史流程实例ID：" + variable.getProcessInstanceId());
			System.out.println("历史变量Name：" + variable.getVariableName());
			System.out.println("历史变量值类型：" + variable.getVariableTypeName());
			System.out.println("历史变量值：" + variable.getValue());
			System.out.println("历史变量创建时间：" + DateUtil.dateTime2String(variable.getCreateTime()));
			System.out.println("历史变量最后修改时间：" + DateUtil.dateTime2String(variable.getLastUpdatedTime()));
			System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = ");
		});
	}
}
