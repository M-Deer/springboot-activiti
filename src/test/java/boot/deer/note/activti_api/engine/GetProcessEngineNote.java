package boot.deer.note.activti_api.engine;

import javax.sql.DataSource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/** 
 * @ClassName: GetProcessEngineNote 
 * @Author: Mr_Deer
 * @Date: 2019年3月31日 上午11:03:25 
 * @Description: Acticiti 最核心的关键类，流程引擎（ProcessEngine）
 * 我们来获取流程引擎，这里我们可以获取两种不同的流程引擎
 */
public class GetProcessEngineNote extends SpringbootActivitiApplicationTests {

	// 注入数据源
	@Autowired
	private DataSource dataSource;

	/**
	 * 第一种，获取原生的流程引擎
	 */
	@Test
	public void getProcessEngine() {
		// 创建流程引擎
		ProcessEngineConfiguration configuration = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration();
		// 配置表的初始化的方式
		configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		// 配置数据源
		configuration.setDataSource(dataSource);
		// 得到流程引擎
		ProcessEngine processEngine = configuration.buildProcessEngine();

		if (processEngine != null)
			System.out.println("Engine = = = = = 流程引擎获取成功");
		else
			System.out.println("Engine = = = = = 流程引擎获取失败");
	}

	/**
	 * 第二种，获取 Spring 集成后的流程引擎
	 */
	@Test
	public void getSpringProcessEngine() {
		// 获取 spring 的
		ProcessEngineConfiguration springConfiguration = SpringProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration();

		// 配置表的初始化的方式
		springConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		// 配置数据源
		springConfiguration.setDataSource(dataSource);
		// 得到流程引擎
		ProcessEngine processEngine = springConfiguration.buildProcessEngine();

		if (processEngine != null)
			System.out.println("Spring Engine = = = = = 流程引擎获取成功");
		else
			System.out.println("Spring Engine = = = = = 流程引擎获取失败");
	}
}
