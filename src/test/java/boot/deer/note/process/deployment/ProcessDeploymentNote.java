package boot.deer.note.process.deployment;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.DeploymentQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;
import boot.deer.component.util.DateUtil;

/** 
 * @ClassName: ProcessDeploymentNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月1日 上午10:50:15 
 * @Description: "流程部署"笔记 - 使用 Junit 进行测试
 */
public class ProcessDeploymentNote extends SpringbootActivitiApplicationTests {

	// 注入 RepositoryService
	@Autowired
	private RepositoryService repositoryService;

	/**
	 * 流程部署 — 使用流程文件资源部署
	 * 
	 * 操作对应数据表：
	 * 		ACT_GE_BYTEARRAY
	 * 		ACT_RE_DEPLOYMENT
	 * 		ACT_RE_PROCDEF
	 */
	@Test
	public void processDeploymentUseClasspathResource() {
		// 创建流程部署工具
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();

		// bpm 文件路径
		String bpmnResourcePath = "processes/note/leave/LeaveProcess.bpmn";
		// png 文件路径
		String pngResourcePath = "processes/note/leave/LeaveProcess.png";
		// 流程名
		String processName = "请假流程";

		/*
		 * 流程部署，其中就需要两个文件对应的路径
		 * 同时我们再部署的时候可以给这个流程起一个名字
		 * 部署后会返回一个部署对象
		 */
		Deployment deployment = deploymentBuilder.name(processName).addClasspathResource(bpmnResourcePath)
				.addClasspathResource(pngResourcePath).deploy();

		System.out.println("流程部署成功，流程部署ID：" + deployment.getId());
	}

	/**
	 * 流程部署 — 使用zip文件资源部署
	 * 
	 * 操作对应数据表：
	 * 		ACT_GE_BYTEARRAY
	 * 		ACT_RE_DEPLOYMENT
	 * 		ACT_RE_PROCDEF
	 */
	@Test
	public void processDeploymentUseZipResource() {
		// zip 文件的路径
		String zipPath = "/processes/example/leave/LeaveBillProcess.zip";

		// 获取一个输入流
		InputStream resourceAsStream = this.getClass().getResourceAsStream(zipPath);

		// 这里使用的就是 zip 的一个封装类型
		Deployment deployment = repositoryService.createDeployment().name("请假流程")
				.addZipInputStream(new ZipInputStream(resourceAsStream)).deploy();

		System.out.println("流程部署成功，流程部署ID：" + deployment.getId());
	}

	/**
	 * 查询流程部署
	 * 
	 * 操作对应数据表：
	 * 		ACT_RE_DEPLOYMENT
	 */
	@Test
	public void processDeploymentQuery() {
		// 获取"流程部署"查询器
		DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();

		List<Deployment> deployments = deploymentQuery

		// 查询匹配条件

		/* 流程部署ID (ID_) - 参数类型：String */
//		.deploymentId(deploymentId)
		/* 流程部署Name (NAME_) - 参数类型：String */
//		.deploymentName(name)
		/* 流程部署Name (NAME_) - 参数类型：String - 模糊匹配 %param% */
//		.deploymentNameLike(nameLike)

		// 排序规则

		/* 流程部署ID (ID_) - 升序排序 */
//		.orderByDeploymentId().asc()
		/* 流程部署Name (NAME_)  - 降序排序 */
//		.orderByDeploymentName()deploymentQuery.desc()
		/* 流程部署Time (DEPLOY_TIME_)  - 升序排序 */
//		.orderByDeploymenTime().asc()

		// 结果类型

		/* List 结果集 */
//		.list()
		/* List 结果集 - 分页 */
//		.listPage(firstResult, maxResults)
		/* 唯一的结果 */
//		.singleResult()
		/* 结果计数 */
//		.count()

				.list();

		// 循环结果集
		deployments.forEach(deployment -> {
			System.out.println("流程部署 ID: " + deployment.getId());
			System.out.println("流程部署 Name: " + deployment.getName());
			System.out.println("流程部署 Time: " + DateUtil.dateTime2String(deployment.getDeploymentTime()));
			System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = ");
		});
	}

	/**
	 * 删除流程部署
	 * 删除流程部署的同时，流程定义、正在执行的流程实例、该流程的历史记录都会一并删除
	 * 
	 * 操作对应数据表：
	 * 		ACT_GE_BYTEARRAY
	 * 		ACT_RE_*
	 * 		ACT_RU_*
	 * 		ACT_HI_*
	 */
	@Test
	public void processDeploymentDelete() {
		// 需要删除的流程部署 ID
		String deploymentId = "2501";
		/*
		 * 第一个参数就是需要删除的部署 ID
		 * 第二个参数是代表当前如果还存在尚未执行完毕的程序，则是否强制删除
		 * 		true：即使存在为执行完毕的流程，扔强制删除
		 * 		false：存在尚未删除的流程，则不删除
		 * */
		repositoryService.deleteDeployment(deploymentId, true);
		System.out.println("删除成功");
	}
}
