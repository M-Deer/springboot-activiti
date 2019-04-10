package boot.deer.note.process.definition;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/** 
 * @ClassName: ProcessDefinitionNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月2日 上午10:02:29 
 * @Description: "流程定义"笔记 - 使用 Junit 进行测试
 */
public class ProcessDefinitionNote extends SpringbootActivitiApplicationTests {

	// 注入 RepositoryService
	@Autowired
	private RepositoryService repositoryService;

	/**
	 * 查询流程定义
	 * 
	 * 操作对应数据表：
	 * 		ACT_RE_PROCDEF
	 */
	@Test
	public void processDefinitionQuery() {
		// 获取"流程定义"查询器
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

		List<ProcessDefinition> processDefinitions = processDefinitionQuery

		// 查询匹配条件

		/* 流程部署ID (DEPLOYMENT_ID_) - 参数类型：String */
//		.deploymentId(deploymentId)
		/* 流程部署ID (DEPLOYMENT_ID_) - 参数类型：Set<String> - 集合查询 IN */
//		.deploymentIds(deploymentIds)
		/* 流程定义ID (ID_) - 参数类型：String */
//		.processDefinitionId(processDefinitionId)
		/* 流程定义ID (ID_) - 参数类型：Set<String> - 集合查询 IN */
//		.processDefinitionIds(processDefinitionIds)
		/* 流程定义Key (KEY_)  - 参数类型：String */
//		.processDefinitionKey(processDefinitionKey)
		/* 流程定义Key (KEY_)  - 参数类型：String - 模糊匹配 %param% */
//		.processDefinitionKeyLike(processDefinitionKeyLike)
		/* 流程定义Name (NAME_) - 参数类型：String */
//		.processDefinitionName(processDefinitionName)
		/* 流程定义Name (NAME_) - 参数类型：String - 模糊匹配 %param% */
//		.processDefinitionNameLike(processDefinitionNameLike)
		/* 流程资源文件Name (RESOURCE_NAME_) - 参数类型：String */
//		.processDefinitionResourceName(resourceName)
		/* 流程资源文件Name (RESOURCE_NAME_) - 参数类型：String - 模糊匹配 %param% */
//		.processDefinitionResourceNameLike(resourceNameLike)
		/* 流程定义的版本 (VERSION_) - 参数类型：String */
//		.processDefinitionVersion(processDefinitionVersion)
		/* 流程定义的版本 (VERSION_) - 参数类型：String - VERSUIN_ > 参数版本 */
//		.processDefinitionVersionGreaterThan(processDefinitionVersion) */
		/* 流程定义的版本 (VERSION_) - 参数类型：String - VERSUIN_ >= 参数版本 */
//		.processDefinitionVersionGreaterThanOrEquals(processDefinitionVersion)
		/* 流程定义的版本 (VERSION_) - 参数类型：String - VERSUIN_ < 参数版本 */
//		.processDefinitionVersionLowerThan(processDefinitionVersion)
		/* 流程定义的版本 (VERSION_) - 参数类型：String - VERSUIN_ <= 参数版本 */
//		.processDefinitionVersionLowerThanOrEquals(processDefinitionVersion)

		// 排序规则

		/* 流程部署ID (DEPLOYMENT_ID_)  - 升序排序 */
//      .orderByDeploymentId().asc()
		/* 流程定义ID (ID_) - 升序排序 */
//      .orderByProcessDefinitionId().asc() 
		/* 流程定义Key (KEY_)- 升序排序 */
//      .orderByProcessDefinitionKey().asc() 
		/* 流程定义Name (NAME_) - 升序排序 */
//      .orderByProcessDefinitionName().asc()
		/* 流程定义的版本 (VERSION_) - 升序排序 */
//      .orderByProcessDefinitionVersion().asc() 

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
		processDefinitions.forEach(definition -> {
			System.out.println("流程部署 ID: " + definition.getDeploymentId());
			System.out.println("流程定义 ID: " + definition.getId());
			System.out.println("流程定义 Key: " + definition.getKey());
			System.out.println("流程定义 名称: " + definition.getName());
			System.out.println("流程定义 版本:" + definition.getVersion());
			System.out.println("流程资源 BPMN 文件名:" + definition.getResourceName());
			System.out.println("流程资源 PNG 文件名:" + definition.getDiagramResourceName());
			System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = ");
		});
	}

	/**
	 * 查询所有流程的最新版本
	 * 
	 * 操作对应数据表：
	 * 		ACT_RE_PROCDEF
	 */
	@Test
	public void processDefinitionQueryFinal() {
		// 获取"流程定义"查询器
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

		// 这里我们没有设置查询条件，就是查询目前所有的流程，每个流程的最新版本
		List<ProcessDefinition> processDefinitions = processDefinitionQuery.latestVersion().list();

		// 循环结果集
		processDefinitions.forEach(definition -> {
			System.out.println("流程部署 ID: " + definition.getDeploymentId());
			System.out.println("流程定义 ID: " + definition.getId());
			System.out.println("流程定义 Key: " + definition.getKey());
			System.out.println("流程定义 名称: " + definition.getName());
			System.out.println("流程定义 版本:" + definition.getVersion());
			System.out.println("流程资源 BPMN 文件名:" + definition.getResourceName());
			System.out.println("流程资源 PNG 文件名:" + definition.getDiagramResourceName());
			System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = ");
		});
	}

	/**
	 * 读取流程图文件
	 * 把流程图的 png 读到硬盘的某个文件里
	 */
	@Test
	public void processDefinitionQueryView() {
		// 视图存放地址路径
		String viwePath = System.getProperty("user.dir") + "\\view";

		// 判断文件夹是否存在
		File folder = new File(viwePath);
		if (!folder.exists())
			// 不存在则创建一个
			folder.mkdir();

		// 这里可以通过查询查询到资源文件名，这里是测试，暂时写死
		viwePath += "\\LeaveProcess.png";
		System.out.println(viwePath);

		// 创建文件，给出相对应的 path
		File file = new File(viwePath);

		// 流程定义 ID
		String processDefinitionId = "LeaveProcess:1:4";
		try (InputStream inputStream = repositoryService.getProcessDiagram(processDefinitionId);
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
			// 开始写入文件
			int len;
			byte[] buf = new byte[1024];
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
				outputStream.flush();
			}
			System.out.println("视图读取完成");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("视图读取失败");
		}
	}
}
