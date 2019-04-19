package boot.deer.service.system;

import boot.deer.model.user.UserModel;

/** 
 * @ClassName: SystemService.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月11日 下午12:17:46 
 * @Description: 
 */
public interface SystemService {

	/**
	 * 用户登陆
	 * @param userModel 用户对象
	 * @return 主页链接
	 * @throws Exception 不存在用户
	 */
	String login(UserModel userModel) throws Exception;

	/**
	 * 获取用户信息
	 * @return 用户信息
	 */
	UserModel getUserInfo() throws Exception;

	/**
	 * 退出登录
	 */
	void logout();
}
