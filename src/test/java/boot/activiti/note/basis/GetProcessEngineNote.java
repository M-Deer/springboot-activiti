package boot.activiti.note.basis;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.spring.SpringProcessEngineConfiguration;

/**
 * @ClassName: GetProcessEngineNote.java
 * @Author: Mr_Deer
 * @Date: 2019年3月28日 下午7:11:30
 * @Description: 获取流程引擎方式，流程引擎是 Acticiti 最核心的关键类
 */
@SuppressWarnings("unused")
public class GetProcessEngineNote {
	/**
	 * 第一种获取流程引擎的方式，通过 build 的方式获取流程引擎
	 */
	public void getProcessEngineOne() {
		// 原生的
		ProcessEngineConfiguration processConfiguration = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration();

		ProcessEngine processEngine = processConfiguration.buildProcessEngine();
		// 获取 spring 的
		ProcessEngineConfiguration springConfiguration = SpringProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration();
		ProcessEngine springProcess = springConfiguration.buildProcessEngine();
	}

	/**
	 * 第二种获取流程引擎的方式，获取 default 的流程引擎 这种方式需要配置对应的 xml
	 */
	public void getProcessEngineTow() {
		// 正常的
		ProcessEngine processConfiguration = ProcessEngines.getDefaultProcessEngine();
	}
}
