package boot.activiti.note.basis;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/**
 * @ClassName: DeployProcessNote.java
 * @Author: Mr_Deer
 * @Date: 2019年3月28日 下午7:09:37
 * @Description: 流程部署，将做好的流程告诉 Activiti
 */
public class DeployProcessNote extends SpringbootActivitiApplicationTests {
	/**
	 * 注入 repositoryService
	 */
	@Autowired
	private RepositoryService repositoryService;

	/**
	 * 我们使用 Junit 进行测试
	 */
	@Test
	public void deployProcess() {
		/**
		 * 通过 repositoryService 创建一个部署，
		 * 
		 * @param name: 流程部署的名字
		 * @param addClasspathResource: 添加资源文件 这里添加资源文件需要添加两个，分别是我们之前绘制的流程图的 .bpmn 文件和
		 *        .png 图片 deploy 是部署流程的对象
		 */
		Deployment deploy = repositoryService.createDeployment().name("请假流程")
				.addClasspathResource("processes/first/FirstProcess.bpmn")
				.addClasspathResource("processes/first/FirstProcess.png").deploy();
		// 我们最后打印一下部署之后的 id，这个 ID 对应的就是 ACT_RE_DEPLOYMENT 里的 ID
		System.out.println(deploy.getId());
		// 都是可以 通过部署流程对象获取相对应的信息
		/*
		 * deploy.getName(); deploy.getDeploymentTime();
		 */
	}
}
