package boot.activiti.note.basis;

import java.io.IOException;

import javax.sql.DataSource;

import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @ClassName: ActivitiConfigurationNote.java
 * @Author: Mr_Deer
 * @Date: 2019年3月28日 下午6:21:46
 * @Description: Activiti 的配置类
 */
//@Configuration
public class ActivitiConfigurationNote extends AbstractProcessEngineAutoConfiguration {

	private final DataSource dataSource;
	private final PlatformTransactionManager platformTransactionManager;

	/**
	 * 注入数据源和事务模板
	 *
	 * @param dataSource                 数据源
	 * @param platformTransactionManager 事务模板
	 */
	@Autowired
	public ActivitiConfigurationNote(DataSource dataSource, PlatformTransactionManager platformTransactionManager) {
		this.dataSource = dataSource;
		this.platformTransactionManager = platformTransactionManager;
	}

	/**
	 * 配置流程引擎，acticiti 最重要的就是流程引擎，这里就是注入 dataSource 一些教程会有使用
	 * StandaloneInMemProcessEngineConfiguration 的配置，这个是 H2数据库的In Memory的流程引擎配置。
	 * SpringProcessEngineConfiguration 与Spring集成的流程引擎配置，当然可以用MYSQL了
	 *
	 * @param springAsyncExecutor 同步执行
	 * @return SpringProcessEngineConfiguration
	 * @throws IOException io 异常
	 */
	@Bean
	public SpringProcessEngineConfiguration springProcessEngineConfiguration(SpringAsyncExecutor springAsyncExecutor)
			throws IOException {
		return baseSpringProcessEngineConfiguration(dataSource, platformTransactionManager, springAsyncExecutor);
	}

	@Bean
	public SpringProcessEngineConfiguration _springProcessEngineConfiguration(SpringAsyncExecutor springAsyncExecutor)
			throws IOException {
		SpringProcessEngineConfiguration configuration = baseSpringProcessEngineConfiguration(dataSource,
				platformTransactionManager, springAsyncExecutor);
		/**
		 * ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE false 即使数据表不存在，也不会自动创建
		 * ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP 创建使用后，自动删除
		 * ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE 数据表不存在的时候自动创建 "drop-create"
		 * 先删除原本的数据表，在重新创建
		 */
		configuration.setDatabaseSchemaUpdate("drop-create");
		return baseSpringProcessEngineConfiguration(dataSource, platformTransactionManager, springAsyncExecutor);
	}
}
