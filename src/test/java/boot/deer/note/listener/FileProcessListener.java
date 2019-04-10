package boot.deer.note.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import lombok.extern.slf4j.Slf4j;

/** 
 * @ClassName: FileProcessListener.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月10日 下午9:20:07 
 * @Description: 文件审批流程 listener
 */
@Slf4j
@SuppressWarnings("serial")
public class FileProcessListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		log.info("进入任务监听类...");
		String assignee = "userB";
		delegateTask.setAssignee(assignee);
		log.info("任务办理人设置完毕...");
	}

}
