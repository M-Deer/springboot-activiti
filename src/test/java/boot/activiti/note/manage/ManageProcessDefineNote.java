package boot.activiti.note.manage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/** 
 * @ClassName: ManageProcessDefineNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年3月28日 下午7:40:30 
 * @Description: 管理流程定义的笔记
 * 关于流程的增删改查部署方式等等
 * <p>
 * 所有的模糊查询都需要自己在参数上手动加上%： %param%
 */
public class ManageProcessDefineNote extends SpringbootActivitiApplicationTests {

	// 注入 RepositoryService
	@Autowired
	private RepositoryService repositoryService;

	/* = = = = = = = = = = = = = = = = = = = = = = = = = = = 流程部署 Start = = = = = = = = = = = = = = = = = = = = = = = = = = = */

	/**
	 * 部署流程方式一
	 * 通过资源文件（bpmn、png）
	 * 关于流程部署，如果部署相同的流程（指的是绘制流程图时，给与的 ID 一样的），部署多次，在数据表中是不会抹掉之前的数据的
	 * 部署几次，就会出现几条数据，但数据后面的 "version" 字段是会迭代上升的
	 * 同时也会去执行最新的版本号的流程部署
	 * <p>
	 * 这也是修改流程的方式，因为有可能前面的流程还有人在使用，那么不能直接修改或者添加、删除节点
	 * 只能通过流程图的版本迭代升级做到流程的更新，向后兼容
	 */
	@Test
	public void deployProcessResource() {
		Deployment deploy = repositoryService.createDeployment().name("请假流程")
				.addClasspathResource("processes/first/FirstProcess.bpmn")
				.addClasspathResource("processes/first/FirstProcess.png").deploy();
		System.out.println("流程部署成功：" + deploy.getId());
	}

	/**
	 * 部署流程方式二
	 * 通过 zip，改方法再 junit 中是找不到资源的，只能使用绝对路径
	 * 而在项目中是可以使用相对路径找到的
	 */
	@Test
	public void deployProcessZip() {
		/**
		 * 获取一个输入流，这个路径对应的就是我们资源文件 .zip 的路径
		 * 这个 zip 中要包含 .bpmn文件和 .png
		 * 路径最前面加了 / 是指从 classpath 下面去找
		 */
		InputStream resourceAsStream = this.getClass().getResourceAsStream("/processes/first/FirstProcess.zip");
		/*
		 * InputStream resourceAsStream = this.getClass() .getResourceAsStream("E:\\IDEA\\WorkSpace\\springboot_activiti\\src\\main\\resources\\processes\\FirstProcess.zip");
		 */

		/**
		 * 我们通过 zip 流的方式进行部署
		 * 这里使用的就是 zip 的一个封装类型
		 */
		repositoryService.createDeployment().name("请假流程").addZipInputStream(new ZipInputStream(resourceAsStream))
				.deploy();
		System.out.println("流程部署成功");
	}

	/**
	 * 查询流程部署信息
	 * 操作对应的表：ACT_RE_DEPLOYMENT
	 */
	@Test
	public void queryProcessDeploy() {
		// 首先我们获取一个“流程部署”的查询器
		DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();

		// 接下来我们再查询器里面进行相对应的条件查询
		String deployId = "1";
		Deployment deployment = deploymentQuery
		// 查询条件
//        .deploymentId(deployId) 根据“流程部署ID (ID_)” 查询 - 参数类型：String
//        .deploymentName(deployName) 根据“流程部署Name (NAME_)” 查询 - 参数类型：String
//        .deploymentNameLike(deployName) 根据“流程部署Name (NAME_)” 模糊查询 - 参数类型：String

		// 排序方式
//        .orderByDeploymentId().asc() 根据“流程部署ID (ID_)” 排序 - 升序排序
//        .orderByDeploymenTime().desc() 根据“流程部署Time (DEPLOY_TIME_)” 排序 - 降序排序
//        .orderByDeploymentName().asc() 根据“流程部署Name (NAME_)” 排序 - 升序排序

		// 返回的结果集类型
//        .list() 返回 list 结果集
//        .listPage(firstResult,maxResults) 分页查询集合
//        .singleResult(); 单独的结果，根据 id 查询，返回的肯定的唯一的
//        .count(); 返回共有多少个
				.deploymentId(deployId).singleResult();

		System.out.println("流程部署 ID: " + deployment.getId());
		System.out.println("流程部署 Name: " + deployment.getName());
		System.out.println("流程部署 Time: " + deployment.getDeploymentTime());
	}
	/* = = = = = = = = = = = = = = = = = = = = = = = = = = = 流程部署 End = = = = = = = = = = = = = = = = = = = = = = = = = = = */

	/* = = = = = = = = = = = = = = = = = = = = = = = = = = = 流程定义 Start = = = = = = = = = = = = = = = = = = = = = = = = = = = */

	/**
	 * 查询流程定义相关的信息
	 * 操作对应的表：ACT_RE_PROCDEF
	 */
	@Test
	public void queryProcessDefine() {
		// 获取一个“流程定义”的查询器
		ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();

		// 接下来我们再查询器里面进行相对应的条件查询
		String defineId = "firstProcess:1:4";
		ProcessDefinition definition = definitionQuery
//        .deploymentId(deployId) //根据“流程部署ID (DEPLOYMENT_ID_)”查询 - 参数类型：String
//        .deploymentIds(idSet) 根据“流程部署ID (DEPLOYMENT_ID_)”集合查询 - 参数类型：Set<String>
//        .processDefinitionId(defineId)  根据“流程定义ID (ID_)”查询 - 参数类型：String
//        .processDefinitionIds(idSet) 根据“流程定义ID (ID_)”集合查询 - 参数类型：Set<String>
//        .processDefinitionKey(key) 根据“流程定义Key (KEY_)”查询 - 参数类型：String
//        .processDefinitionKeyLike(key) 根据“流程定义Key (KEY_)”模糊查询 - 参数类型：String
//        .processDefinitionName(name) 根据“流程定义Name (NAME_)”查询 - 参数类型：Stirng
//        .processDefinitionNameLike(name) 根据“流程定义Name (NAME_)”模糊查询 - 参数类型：Stirng
//        .processDefinitionResourceName(resourceName) 根据“流程资源文件Name (RESOURCE_NAME_)”查询，是结尾 bpmn 的资源文件 - 参数类型：Stirng
//        .processDefinitionResourceNameLike(resourceName) 根据“流程资源文件Name (RESOURCE_NAME_)”模糊查询，是结尾 bpmn 的资源文件 - 参数类型：Stirng
//        .processDefinitionVersion(version) 根据“流程定义的版本 (VERSION_)”查询 - 参数类型：Integer
//        .processDefinitionVersionGreaterThan(version) 根据“流程定义的版本 (VERSION_)”查询，version > 参数版本 - 参数类型：Integer
//        .processDefinitionVersionGreaterThanOrEquals(version) 根据“流程定义的版本 (VERSION_)”查询，version >= 参数版本 - 参数类型：Integer
//        .processDefinitionVersionLowerThan(version) 根据“流程定义的版本 (VERSION_)”查询，version < 参数版本 - 参数类型：Integer
//        .processDefinitionVersionLowerThanOrEquals(version) 根据“流程定义的版本 (VERSION_)”查询，version <= 参数版本 - 参数类型：Integer

		// 排序规则
//        .orderByDeploymentId().asc() 根据“流程部署ID (DEPLOYMENT_ID_)” 排序 - 升序排序
//        .orderByProcessDefinitionId().asc() 根据“流程定义ID (ID_)” 排序 - 升序排序
//        .orderByProcessDefinitionKey().asc() 根据“流程定义Key (KEY_) 排序 - 升序排序
//        .orderByProcessDefinitionName().asc() 根据“流程定义Name (NAME_)” 排序 - 升序排序
//        .orderByProcessDefinitionVersion().asc() 根据“流程定义的版本 (VERSION_)” 排序 - 升序排序

		// 结果集
//        .list() 返回 list 结果集
//        .listPage(firstResult,maxResults) 分页查询集合
//        .singleResult(); 单独的结果，根据 id 查询，返回的肯定的唯一的
//        .count(); 返回共有多少个
				.processDefinitionId(defineId).singleResult();
		System.out.println("流程部署 ID: " + definition.getDeploymentId());
		System.out.println("流程定义 ID: " + definition.getId());
		System.out.println("流程定义 Key: " + definition.getKey());
		System.out.println("流程定义 名称: " + definition.getName());
		System.out.println("流程定义 版本:" + definition.getVersion());
		System.out.println("流程资源 BPMN 文件名:" + definition.getResourceName());
		System.out.println("流程资源 PNG 文件名:" + definition.getDiagramResourceName());
	}

	/**
	 * 删除流程定义
	 * 删除操作中，使用的 ID 都是 `ACT_RE_DEPLOYMENT` 流程部署 ID
	 * 操作对应的表：
	 * ACT_RE_DEPLOYMENT
	 * ACT_RE_PROCDEF
	 * ACT_RUN_*
	 * ACT_HI_*
	 */
	@Test
	public void deleteProcessDefine() {
		String deployId = "1";
		repositoryService
		// 根据“流程部署 ID”删除，如果当前流程有正在执行的，那么会删除失败，同时报错 - 参数类型：String
//      .deleteDeployment(deployId);
				/**
				 * 根据“流程部署 ID”删除。
				 * 第二个参数如果设置为 false，那么效果等同于第一种
				 * 第二个参数如果设置为 true，就会删除流程部署、流程定义、正在执行的流程信息、该流程的历史信息
				 * ACT_RUN_*
				 * ACT_HI_*
				 */
				.deleteDeployment(deployId, true);
		System.out.println("删除成功");
	}

	/**
	 * 查询流程图
	 * 把数据库中的流程图 png 读到磁盘上
	 */
	@Test
	public void queryViewProcess() {
		// 指定存放视图的路径
		String path = System.getProperty("user.dir") + "\\file";
		// 判断文件夹是否存在
		File folder = new File(path);
		if (!folder.exists())
			// 不存在则创建一个
			folder.mkdir();

		// 这里可以通过查询查询到资源文件名，这里是测试，暂时写死
		path += "\\firstProcess.png";
		System.out.println(path);

		// 创建文件，给出相对应的 path
		File file = new File(path);

		// 但我们只有流程部署 ID 的时候，那么我们先要通过 流程部署ID 去查询 流程定义ID，然后再使用 流程定义ID 去做查询
		/*
		 * ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult(); definition.getId(); 流程定义ID definition.getName() 流程名字 definition.getDiagramResourceName() 流程图片资源名字
		 */

		// 通过流程定义ID 获取图片资源文件的输入流
		// `ACT_RE_PROCDEF` 流程定义 ID
		String processDefinitionId = "firstProcess:1:4";
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

	/**
	 * 查询最新的流程定义
	 */
	@Test
	public void queryNewProcessDefine() {
		// 获取”流程定义“ 查询器
		ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
		// 如果想查询某个流程最新的，可以通过添加 key 来查询想要的某个流程的最新版本
//        ProcessDefinition definition = definitionQuery.processDefinitionKey(key).latestVersion().singleResult();

		// 通过查询器查所有的版本最新的流程
		List<ProcessDefinition> list = definitionQuery.latestVersion().list();
		System.out.println(list.toString());
	}
	/* = = = = = = = = = = = = = = = = = = = = = = = = = = = 流程定义 End = = = = = = = = = = = = = = = = = = = = = = = = = = = */
}
