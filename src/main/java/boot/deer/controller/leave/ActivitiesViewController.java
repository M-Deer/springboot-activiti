package boot.deer.controller.leave;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import boot.deer.service.leave.ActivitiesViewService;

/** 
 * @ClassName: ActivitiesViewController.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月23日 下午3:14:38 
 * @Description: 查看当前流程进度节点的 Controller
 */
@Controller
@RequestMapping(value = "/activitiesView")
public class ActivitiesViewController {

	private final ActivitiesViewService activitiesViewService;

	@Autowired
	public ActivitiesViewController(ActivitiesViewService activitiesViewService) {
		this.activitiesViewService = activitiesViewService;
	}

	/**
	 * 根据请假单ID 查看流程图
	 * 
	 * @param leaveBillId 请假单ID
	 * @param response 响应体
	 */
	@GetMapping
	public void checkNowProcessActivitiesByLeaveBillId(@RequestParam(name = "leaveBillId") String leaveBillId,
			HttpServletResponse response) {
		try (InputStream stream = activitiesViewService.checkNowProcessActivitiesByLeaveBillId(leaveBillId);
				ServletOutputStream outputStream = response.getOutputStream()) {
			BufferedImage image = ImageIO.read(stream);
			ImageIO.write(image, "PNG", outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
