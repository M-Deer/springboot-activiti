package boot.deer.service.leave;

import java.io.InputStream;

/** 
 * @ClassName: ActivitiesViewService.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月24日 上午9:46:30 
 * @Description: 查看当前流程进度节点的 Service
 */
public interface ActivitiesViewService {

	/**
	 * 根据请假单ID 查看流程图
	 * 
	 * @param leaveBillId 请假单ID
	 */
	InputStream checkNowProcessActivitiesByLeaveBillId(String leaveBillId);
}
