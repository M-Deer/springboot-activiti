package boot.deer.service.leave.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import boot.deer.component.enumm.LeaveBillEnum;
import boot.deer.component.util.GlobalUtil;
import boot.deer.mapper.leave.LeaveBillMapper;
import boot.deer.model.bill.LeaveBillModel;
import boot.deer.model.user.UserModel;
import boot.deer.service.leave.LeaveBillService;
import boot.deer.service.system.SystemService;

/** 
 * @ClassName: LeaveBillServiceImpl.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月12日 上午10:34:35 
 * @Description:  请假单管理 ServiceImpl
 */
@Service
@Transactional
public class LeaveBillServiceImpl extends ServiceImpl<LeaveBillMapper, LeaveBillModel> implements LeaveBillService {

	// 请假流程定义的 key
	private static final String PROCESS_DEFINE_KEY = "LeaveBillProcess";

	private final LeaveBillMapper leaveBillMapper;
	private final SystemService systemService;
	private final RuntimeService runtimeService;

	@Autowired
	public LeaveBillServiceImpl(LeaveBillMapper leaveBillMapper, SystemService systemService,
			RuntimeService runtimeService) {
		this.leaveBillMapper = leaveBillMapper;
		this.systemService = systemService;
		this.runtimeService = runtimeService;
	}

	/**
	 * 分页查询
	 * 
	 * @param current 当前页码
	 * @param size 显示数量
	 * @return 结果集
	 */
	@Override
	public IPage<LeaveBillModel> getItemsByPage(Integer current, Integer size) {
		// 获取当前用户
		Long userId = GlobalUtil.getCurrentUser().getId();
		Page<LeaveBillModel> page = new Page<>(current, size);
		IPage<LeaveBillModel> resultIPage = leaveBillMapper.getItemsByPage(page, userId);

		return resultIPage;
	}

	/**
	 * 添加数据
	 * @param leaveBillModel 请假单实体类
	 */
	@Override
	public void insertItem(LeaveBillModel leaveBillModel) throws Exception {
		UserModel userInfo = systemService.getUserInfo();

		leaveBillModel.setLeaveTime(new Date());
		leaveBillModel.setTitle("【" + userInfo.getUsername() + "】请假单");
		leaveBillModel.setUser(userInfo);
		leaveBillModel.setStatus(LeaveBillEnum.EXAMINATION.getCod());

		// 新增请假单数据
		leaveBillMapper.insert(leaveBillModel);
		// 启动流程
		startProcessByKey(leaveBillModel.getId());
	}

	/**
	 * 根据流程部署key 启动流程
	 * 
	 * @param leaveBillId 请假单id
	 */
	private void startProcessByKey(Long leaveBillId) {
		// 获取当前登陆用户
		UserModel userModel = GlobalUtil.getCurrentUser();

		// businessKey 格式 流程定义key+：+请假单id
		String businessKey = PROCESS_DEFINE_KEY + ":" + leaveBillId;
		// 流程变量，办理人(首次的提交办理人)
		Map<String, Object> variables = new HashMap<>();
		variables.put(LeaveBillEnum.ASSIGNEE_USER.getCod(), userModel.getUsername());

		// 启动流程
		runtimeService.startProcessInstanceByKey(PROCESS_DEFINE_KEY, businessKey, variables);
	}

	/**
	 * 根据ID 获取请假单详情
	 * @param billId 请假单 ID
	 * @return 结果
	 */
	@Override
	public LeaveBillModel getItemById(Long billId) {
		LeaveBillModel leaveBillModel = leaveBillMapper.selectById(billId);
		return leaveBillModel;
	}
}
