package boot.deer.component.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import boot.deer.component.util.GlobalUtil;
import boot.deer.mapper.user.UserMapper;
import boot.deer.model.user.UserModel;

/** 
 * @ClassName: LeaveBillAssigneeListener.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月17日 下午6:10:05 
 * @Description: 请假流程办理人 listener
 */
@SuppressWarnings("serial")
public class LeaveBillAssigneeListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		// 获取当前登陆人
		UserModel userModel = GlobalUtil.getCurrentUser();
		// 获取上级领导ID
		Long superiorId = userModel.getSuperiorId();
		if (!GlobalUtil.isEmpty(superiorId)) {
			// 从 ApplicationContext 中获取 UserMapper
			UserMapper userMapper = GlobalUtil.getBean(UserMapper.class);
			// 根据ID获取用户，也就是获取上级领导
			UserModel superiorUser = userMapper.selectById(superiorId);
			// 设置下一步的办理人
			delegateTask.setAssignee(superiorUser.getUsername());
		}
	}

}
