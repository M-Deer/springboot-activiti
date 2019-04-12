package boot.deer.service.leave.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import boot.deer.component.enumm.LeaveBillEnum;
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
public class LeaveBillServiceImpl extends ServiceImpl<LeaveBillMapper, LeaveBillModel> implements LeaveBillService {

	private final LeaveBillMapper leaveBillMapper;
	private final SystemService systemService;

	@Autowired
	public LeaveBillServiceImpl(LeaveBillMapper leaveBillMapper, SystemService systemService) {
		this.leaveBillMapper = leaveBillMapper;
		this.systemService = systemService;
	}

	/**
	 * 分页查询
	 * 
	 * @param current 当前页码
	 * @param size 显示数量
	 * @return 结果集
	 */
	@Override
	public IPage<LeaveBillModel> getItemByPage(Integer current, Integer size) {
		Page<LeaveBillModel> page = new Page<>(current, size);
		IPage<LeaveBillModel> resultIPage = leaveBillMapper.getItemByPage(page);

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
		leaveBillModel.setTitle("请假单");
		leaveBillModel.setUser(userInfo);
		leaveBillModel.setStatus(LeaveBillEnum.TURN_DOWN.getStatus());

		leaveBillMapper.insert(leaveBillModel);
	}

}
