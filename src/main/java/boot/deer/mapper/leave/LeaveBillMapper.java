package boot.deer.mapper.leave;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import boot.deer.model.bill.LeaveBillModel;

/** 
 * @ClassName: LeaveBillMapper.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月12日 上午10:32:26 
 * @Description: 请假单管理 Mapper
 */
public interface LeaveBillMapper extends BaseMapper<LeaveBillModel> {

	/**
	 * 分页查询
	 * @param page 分页数据
	 * @return 结果集
	 */
	IPage<LeaveBillModel> getItemByPage(Page<LeaveBillModel> page);
}
