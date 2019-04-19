package boot.deer.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: ViewController.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月11日 上午11:16:47 
 * @Description: 页面路由 controller
 */
@Controller
@RequestMapping(value = "/view")
public class ViewController {

	/**
	 * 登录页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String toLogin() {
		return "login";
	}

	/**
	 * 主页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/main")
	public String toMain() {
		return "main/main";
	}

	/**
	 * 请假申请
	 * 
	 * @return
	 */
	@RequestMapping(value = "/leaveBill")
	public String toLeaveBill() {
		return "leave/leaveBill";
	}

	/**
	 * 代办任务
	 * 
	 * @return
	 */
	@RequestMapping(value = "/complete")
	public String toCompleteTask() {
		return "complete/completeTask";
	}
}
