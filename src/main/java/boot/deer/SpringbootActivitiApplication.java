package boot.deer;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
		SpringApplication.run(SpringbootActivitiApplication.class, args);
		log.info("Spring Boot - Activiti Success~~~");
	}
}
