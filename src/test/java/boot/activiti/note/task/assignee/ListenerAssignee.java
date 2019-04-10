package boot.activiti.note.task.assignee;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: ListenerAssignee 
 * @Author: Mr_Deer
 * @Date: 2019年3月30日 下午12:14:40 
 * @Description: 这是一个来监听任务的类，我们可以在这个类里面写关于办理人的业务逻辑
 * 同时添加到任务节点，就可以去监听任务节点了
 * 要先实现 TaskListener 接口
 */
@Slf4j
@SuppressWarnings("serial")
public class ListenerAssignee implements TaskListener {

	/**
	 * 重写接口里面的方法
	 * 接口中的方法就是让我们来设置办理人
	 * 办理人的业务逻辑就可以写在这个监听类中
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		log.info("进入任务监听类...");
		String assignee = "王五";
		delegateTask.setAssignee(assignee);
		log.info("任务办理人设置完毕...");
	}

}
