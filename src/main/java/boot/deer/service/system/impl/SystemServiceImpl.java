package boot.deer.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import boot.deer.component.enumm.GlobalEnum;
import boot.deer.component.util.GlobalUtil;
import boot.deer.mapper.user.UserMapper;
import boot.deer.model.user.UserModel;
import boot.deer.service.system.SystemService;

/** 
 * @ClassName: SystemServiceImpl.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月11日 下午12:22:14 
 * @Description: 系统 Service
 */
@Service
public class SystemServiceImpl implements SystemService {

	private final UserMapper userMapper;

	@Autowired
	public SystemServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**
	 * 用户登陆
	 * @param userModel 用户对象
	 * @return 主页链接
	 */
	@Override
	public String login(UserModel userModel) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("USERNAME", userModel.getUsername());
		params.put("PASSWORD", userModel.getPassword());

		QueryWrapper<UserModel> queryWrapper = new QueryWrapper<UserModel>();
		queryWrapper.allEq(params);
		UserModel _userModel = userMapper.selectOne(queryWrapper);
		if (!GlobalUtil.isEmpty(_userModel)) {
			GlobalUtil.getHttpSession().setAttribute(GlobalEnum.SESSION_USER.getCode(), _userModel);
			return "/view/main";
		}
		throw new NullPointerException("用户或密码错误");

	}

	/**
	 * 获取用户信息
	 * @return 用户信息
	 */
	@Override
	public UserModel getUserInfo() throws Exception {
		Object object = GlobalUtil.getHttpSession().getAttribute(GlobalEnum.SESSION_USER.getCode());

		if (!GlobalUtil.isEmpty(object))
			return (UserModel) object;
		throw new NullPointerException("用为尚未登陆，用户信息为空，不能进行其他操作，请先登录");
	}

	/**
	 * 退出登录
	 */
	@Override
	public void logout() {
		GlobalUtil.getHttpSession().removeAttribute(GlobalEnum.SESSION_USER.getCode());
	}

}
