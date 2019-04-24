package boot.deer.service.leave.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import boot.deer.component.enumm.LeaveBillEnum;
import boot.deer.service.leave.ActivitiesViewService;

/** 
 * @ClassName: ActivitiesViewServiceImpl.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月24日 上午9:47:08 
 * @Description: 查看当前流程进度节点的 ServiceImpl
 */
@Service
@Transactional
public class ActivitiesViewServiceImpl implements ActivitiesViewService {

	private final RepositoryService repositoryService;
	private final RuntimeService runtimeService;
	private final TaskService taskService;

	@Autowired
	public ActivitiesViewServiceImpl(RepositoryService repositoryService, RuntimeService runtimeService,
			TaskService taskService) {
		this.repositoryService = repositoryService;
		this.runtimeService = runtimeService;
		this.taskService = taskService;
	}

	/**
	 * 根据请假单ID 查看流程图
	 * 
	 * 我们使用图片生成器（Activiti自带的）进行图片的生成
	 * 需要获取流程引擎，然后从流程引擎中在进行配置和获取
	 * 
	 * @param leaveBillId 请假单ID
	 * @return 图片流
	 */
	@Override
	public InputStream checkNowProcessActivitiesByLeaveBillId(String leaveBillId) {
		// 拼装成 BusinessKey
		String processInstanceBusinessKey = LeaveBillEnum.PROCESS_DEFINE_KEY.getCod() + ":" + leaveBillId;
		// 根据 BusinessKey 查询任务对象
		Task task = taskService.createTaskQuery().processInstanceBusinessKey(processInstanceBusinessKey).singleResult();
		// 获取执行实例
		Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
		// 获取ActId
		String activityId = execution.getActivityId();
		// 获取流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();

		// 获取流程引擎
		ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
		// 获取引擎配置
		ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) defaultProcessEngine
				.getProcessEngineConfiguration();

		// 获取默认图片生成器
		ProcessDiagramGenerator processDiagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
		// 获取要生成的流程图的BpmnModel
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		// 设置需要标注高亮的节点
		List<String> hightLightElements = new ArrayList<>();
		hightLightElements.add(activityId);
		// 返回图片流，字体不设置的话，中文会乱码
		InputStream imageStream = processDiagramGenerator.generateDiagram(bpmnModel, "PNG", hightLightElements,
				new ArrayList<>(), "黑体", "黑体", "黑体", processEngineConfiguration.getClassLoader(), 1.0);

		return imageStream;
	}
}
