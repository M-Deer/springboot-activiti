package boot.deer.service.leave;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import boot.deer.model.bill.LeaveBillModel;

/** 
 * @ClassName: LeaveBillService.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月12日 上午10:33:55 
 * @Description: 请假单管理 Service
 */
public interface LeaveBillService extends IService<LeaveBillModel> {

	/**
	 * 分页查询
	 * 
	 * @param current 当前页码
	 * @param size 显示数量
	 * @return 结果集
	 */
	IPage<LeaveBillModel> getItemsByPage(Integer current, Integer size);

	/**
	 * 添加数据
	 * @param leaveBillModel 请假单实体类
	 */
	void insertItem(LeaveBillModel leaveBillModel) throws Exception;

	/**
	 * 根据ID 获取请假单详情
	 * @param billId 请假单 ID
	 * @return 结果
	 */
	LeaveBillModel getItemById(Long billId);
}
