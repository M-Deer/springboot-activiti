package boot.deer.controller.leave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import boot.deer.component.util.ResponseJsonResult;
import boot.deer.model.bill.LeaveBillModel;
import boot.deer.service.leave.LeaveBillService;

/** 
 * @ClassName: LeaveBillController.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月12日 上午10:31:20 
 * @Description: 请假单管理 Controller
 */
@RestController
@RequestMapping(value = "/billLeave")
public class LeaveBillController {

	private final LeaveBillService leaveBillService;

	@Autowired
	public LeaveBillController(LeaveBillService leaveBillService) {
		this.leaveBillService = leaveBillService;
	}

	/**
	 * 分页查询
	 * 
	 * @param current 当前页码
	 * @param size 显示数量
	 * @return Json 结果
	 */
	@GetMapping(value = "/getItemsByPage")
	public IPage<LeaveBillModel> getItemsByPage(@RequestParam(name = "pageNumber") Integer current,
			@RequestParam(name = "pageSize") Integer size) {
		IPage<LeaveBillModel> resultIPage = leaveBillService.getItemsByPage(current, size);

		return resultIPage;
	}

	/**
	 * 添加数据/启动流程
	 * 
	 * @param leaveBillModel 请假实体类
	 * @return Json 结果
	 */
	@PostMapping
	public ResponseJsonResult insertItem(@RequestBody LeaveBillModel leaveBillModel) {
		try {
			leaveBillService.insertItem(leaveBillModel);
			return ResponseJsonResult.successResult("启动流程成功");
		} catch (Exception e) {
			return ResponseJsonResult.unsuccessResult("启动流程失败");
		}
	}
}
