package boot.deer.service.complete.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import boot.deer.component.enumm.LeaveBillEnum;
import boot.deer.component.util.GlobalUtil;
import boot.deer.mapper.complete.CompleteTaskMapper;
import boot.deer.mapper.leave.LeaveBillMapper;
import boot.deer.model.bill.LeaveBillModel;
import boot.deer.model.complete.CommentModel;
import boot.deer.model.complete.CompleteTaskModel;
import boot.deer.model.user.UserModel;
import boot.deer.service.complete.CompleteTaskService;

/** 
 * @ClassName: CompleteTaskServiceImpl.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月19日 上午9:58:57 
 * @Description: 代办任务的 ServiceImpl
 */
@Service
@Transactional
public class CompleteTaskServiceImpl extends ServiceImpl<CompleteTaskMapper, CompleteTaskModel>

		implements CompleteTaskService {

	private final LeaveBillMapper leaveBillMapper;
	private final RepositoryService repositoryService;
	private final RuntimeService runtimeService;
	private final TaskService taskService;

	@Autowired
	public CompleteTaskServiceImpl(LeaveBillMapper leaveBillMapper, RepositoryService repositoryService,
			RuntimeService runtimeService, TaskService taskService) {
		this.leaveBillMapper = leaveBillMapper;
		this.repositoryService = repositoryService;
		this.runtimeService = runtimeService;
		this.taskService = taskService;
	}

	/**
	 * 分页查询
	 * 
	 * @param current 当前页码
	 * @param size 显示数量
	 * @return 结果集
	 */
	@Override
	public IPage<CompleteTaskModel> getItemsByPage(Integer current, Integer size) {
		// 获取当前登陆用户
		UserModel currentUser = GlobalUtil.getCurrentUser();
		String assignee = currentUser.getUsername();

		// 获取查询器
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查询总数
		long count = taskQuery.taskAssignee(assignee).count();
		// 查询数据。
		List<Task> taskList = taskQuery.taskAssignee(assignee).listPage(current - 1, size);
		// 待办任务实体类集合
		ArrayList<CompleteTaskModel> resultList = new ArrayList<>();
		taskList.forEach(task -> {
			CompleteTaskModel completeTask = new CompleteTaskModel();
			// 将数据进行 copy
			BeanUtils.copyProperties(task, completeTask);
			resultList.add(completeTask);
		});

		IPage<CompleteTaskModel> page = new Page<>();
		page.setTotal(count);
		page.setRecords(resultList);
		page.setCurrent(current);
		page.setSize(size);
		return page;
	}

	/**
	 * 根据任务ID 查询到具体的请假单信息
	 * @param taskId 任务ID
	 * @return 结果
	 */
	@Override
	public Map<String, Object> getItemById(String taskId) {
		// 获取任务
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		// 获取流程实例 ID
		String processInstanceId = task.getProcessInstanceId();
		// 获取流程实例
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		// 获取 businessKey
		String businessKey = processInstance.getBusinessKey();
		// 根据 businessKey 获取请假单 id
		String leaveBillId = businessKey.split(":")[1];

		// 获取请假单详情
		LeaveBillModel leaveBillModel = leaveBillMapper.selectById(leaveBillId);
		// 按钮信息
		List<String> sequenceFlowInfo = getSequenceFlowInfo(taskId);

		// 返回结果
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("dataInfo", leaveBillModel);
		resultMap.put("btnInfo", sequenceFlowInfo);

		return resultMap;
	}

	/**
	 * 根据任务ID 查询连线信息，作用于页面上的按钮信息
	 * Activiti 6.0 之后想获取连线或者节点与之前的版本有很大的差别
	 * 可以通过 FlowElement 进行强转
	 * 
	 * @param taskId 任务 ID
	 * @return 连线选择
	 */
	private List<String> getSequenceFlowInfo(String taskId) {
		List<String> resultList = new ArrayList<>();

		// 1、根据任务ID 查询任务
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		// 2、获取流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		// 3、获取所有的连线集合
		Collection<FlowElement> flowElements = repositoryService.getBpmnModel(processDefinitionId).getMainProcess()
				.getFlowElements();
		// 4、获取执行实例ID
		String executionId = task.getExecutionId();
		// 5、获取执行实例
		Execution execution = runtimeService.createExecutionQuery().executionId(executionId).singleResult();
		// 6、获取ActId
		String activityId = execution.getActivityId();
		// 7、 通过循环获取我们需要的节点
		flowElements.forEach(item -> {
			// 判断类型
			if (item instanceof SequenceFlow) {
				// 强转
				SequenceFlow flow = (SequenceFlow) item;
				// 判断是否为当前的节点
				if (flow.getSourceRef().equals(activityId))
					// 将信息放入集合
					resultList.add(item.getName());
			}
		});
		return resultList;
	}

	/**
	 * 根据任务ID 查询批注信息
	 * 
	 * @param taskId 任务ID
	 * @return 结果集
	 */
	@Override
	public List<CommentModel> getCommentsByTaskId(String taskId) {
		// 获取任务
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		// 获取流程实例 ID
		String processInstanceId = task.getProcessInstanceId();
		// 因为是过去整个流程实例的所有批注信息，所以使用的是 ProcessInstanceId
		List<Comment> taskComments = taskService.getProcessInstanceComments(processInstanceId);
		// 自定义的实体类集合
		ArrayList<CommentModel> commentModels = new ArrayList<>();

		// 循环 copy
		taskComments.forEach(item -> {
			CommentModel commentModel = new CommentModel();
			BeanUtils.copyProperties(item, commentModel);
			commentModels.add(commentModel);
		});

		return commentModels;
	}

	/**
	 * 办理任务
	 * 
	 * @param paramMap 参数Map
	 */
	@Override
	public void completeTask(Map<String, String> paramMap) throws Exception {
		// 批注信息
		String comment = paramMap.get("comment");
		// 结果
		String outcome = paramMap.get("outcome");
		// 任务ID
		String taskId = paramMap.get("taskId");
		// 请假单ID
		String leaveBillId = paramMap.get("leaveBillId");
		// 根据任务ID 获取任务信息
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		// 流程实例ID
		String processInstanceId = task.getProcessInstanceId();

		// 添加批注信息
		insertComment(taskId, processInstanceId, outcome, comment);
		// 办理任务
		completeTask(taskId, outcome);
		// 修改请假单状态
		updateStatus(processInstanceId, leaveBillId, outcome);
	}

	/**
	 * 设置批注信息
	 * 
	 * @param comment 批注信息
	 */
	private void insertComment(String taskId, String processInstanceId, String outcome, String comment) {
		/** 
		 * 设置批注人
		 * 在 Activiti 中设置批注信息时，他会自动的获取当前线程中的用户
		 * 所以我们在这里需要对当前线程中的用户变量进行设置
		 * 
		 * 具体位置在：org.activiti.engine.impl.cmd.AddCommentCmd 95~96行代码
		 * 
		 * String userId = Authentication.getAuthenticatedUserId();
		 *	CommentEntity comment = commandContext.getCommentEntityManager().create(); 
		 */
		String username = GlobalUtil.getCurrentUser().getUsername();
		Authentication.setAuthenticatedUserId(username);

		// 添加批注信息
		taskService.addComment(taskId, processInstanceId, "【" + outcome + "】" + comment);
	}

	/**
	 * 办理任务
	 * 
	 * @param taskId 任务ID
	 * @param outcome 办理结果
	 */
	private void completeTask(String taskId, String outcome) {
		// 参数 Map
		Map<String, Object> variables = new HashMap<>();
		variables.put(LeaveBillEnum.OUTCOME.getCod(), outcome);

		// 办理任务
		taskService.complete(taskId, variables);
	}

	/**
	 * 修改请假单状态
	 * 
	 * @param processInstanceId 流程实例ID
	 * @param leaveBillId 请假单ID
	 * @param outcome 办理结果
	 */
	private void updateStatus(String processInstanceId, String leaveBillId, String outcome) {
		// 首先判断流程是否结束
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		// 修改对象信息
		LeaveBillModel leaveBillModel = new LeaveBillModel();
		leaveBillModel.setId(Long.parseLong(leaveBillId));

		if (GlobalUtil.isEmpty(processInstance)) {
			// 流程结束有两种情况：审批全部通过结束；自己放弃结束
			if (outcome.equals("放弃")) {
				// 修改请假单状态
				leaveBillModel.setStatus(LeaveBillEnum.GIVE_UP.getCod());
			} else {
				leaveBillModel.setStatus(LeaveBillEnum.COMPLETE.getCod());
			}
			leaveBillMapper.updateById(leaveBillModel);
		} else {
			if (outcome.equals("驳回")) {
				// 修改请假单状态
				leaveBillModel.setStatus(LeaveBillEnum.TURN_DOWN.getCod());
				leaveBillMapper.updateById(leaveBillModel);
			}
		}
	}
}
