package boot.activiti.note.task.group;

import java.util.Arrays;
import java.util.Collection;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: GroupListener 
 * @Author: Mr_Deer
 * @Date: 2019年3月30日 下午3:49:47 
 * @Description: 组任务流程
 * 这里我们使用监听的方式进行值的注入
 * 
 * 这样我们的业务逻辑就可以放在这个Listener中进行处理
 */
@Slf4j
@SuppressWarnings("serial")
public class GroupListener implements TaskListener {

	/**
	 * 重写监听的方法
	 * 这里添加用户有两种方式
	 * 一种是一个一个的进行添加
	 * 另一种是直接放入一个 集合
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		log.info("进入任务监听类...");
		delegateTask.addCandidateUser("userA");
		delegateTask.addCandidateUser("userB");
		delegateTask.addCandidateUser("userC");
		
		// 使用集合进行添加
		Collection<String> candidateUsers = Arrays.asList("userA","userB","userC");
		delegateTask.addCandidateUsers(candidateUsers);
		log.info("任务监听类完成...");
	}
}
