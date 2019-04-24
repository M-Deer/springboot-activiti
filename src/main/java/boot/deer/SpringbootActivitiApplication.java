package boot.deer;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import boot.deer.component.util.GlobalUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * exclude = SecurityAutoConfiguration.class
 * 		SecurityAutoConfiguration有冲突，启动类排除即可，排除的是 Activiti 中的
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
/**
 * MyBatis Plus 的配置扫描路径
 */
@MapperScan({ "com.baomidou.mybatisplus.samples.quickstart.mapper", "boot.deer.mapper" })
@EnableTransactionManagement
public class SpringbootActivitiApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringbootActivitiApplication.class, args);
		// 设置上下文
		GlobalUtil.setApplicationContext(applicationContext);
		log.info("Spring Boot - Activiti Success~~~");
	}
}
