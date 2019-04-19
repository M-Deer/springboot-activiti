package boot.deer.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boot.deer.component.util.ResponseJsonResult;
import boot.deer.model.user.UserModel;
import boot.deer.service.system.SystemService;

/** 
 * @ClassName: SystenController.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月11日 下午12:11:57 
 * @Description: 关于系统的 Controller
 */
@RestController
@RequestMapping(value = "/system")
public class SystemController {

	private final SystemService systemService;

	@Autowired
	public SystemController(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 用户登陆
	 * @return 结果
	 */
	@PostMapping
	public ResponseJsonResult login(@RequestBody UserModel userModel) {
		try {
			return ResponseJsonResult.successResult("", systemService.login(userModel));
		} catch (Exception e) {
			return ResponseJsonResult.unsuccessResult(e.getMessage());
		}
	}

	/**
	 * 获取用户信息
	 * @return 用户信息
	 */
	@GetMapping(value = "/uerInfo")
	public ResponseJsonResult getUserInfo() {
		try {
			UserModel userInfo = systemService.getUserInfo();
			return ResponseJsonResult.successResult(userInfo);
		} catch (Exception e) {
			return ResponseJsonResult.unsuccessResult(e.getMessage());
		}
	}

	/**
	 * 获取用户信息
	 * @return 用户信息
	 */
	@GetMapping(value = "/logout")
	public void logout() {
		systemService.logout();
	}
}
