package boot.deer.component.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

/** 
 * @ClassName: MybatisPlusConfiguration.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月12日 上午10:47:33 
 * @Description: MybatisPlus 配置类
 */
@Configuration
public class MybatisPlusConfiguration {
	/**
	 * 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	/**
	 * 格式化 SQL
	 */
	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		performanceInterceptor.setFormat(true);
		return performanceInterceptor;
	}
}
