package boot.activiti.note.manage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import boot.deer.SpringbootActivitiApplicationTests;

/** 
 * @ClassName: ManageProcessVariableNote.java 
 * @Author: Mr_Deer
 * @Date: 2019年3月28日 下午7:27:18 
 * @Description: 管理流程流程变量
 * <p>
 * 所有的模糊查询都需要自己在参数上手动加上%： %param%
 * <p>
 * 流程变量就是再整个工作流中很重要的角色
 * 就是再流程中，我们集合实际的业务逻辑中会存在并且携带的变量
 * 例如“请假的天数？请假的理由等等”
 * 流程变量的作用域值对应这个自身的工作实例（例如：张三的流程变量只对张三自己的流程实例有有效；李四的流程变量只对李四自己的流程实例有有效；）
 * 他们之间是互不影响的，同时，流程实例结束完成后，流程变量会同步存放再历史表中
 * 其属性机制就是 Map：key -- value
 * <p>
 * 对于基本数据类型都是支持，对于应用数据类型需要实现序列化
 * <p>
 * 对应的数据表：
 * ACT_RU_VARIABLE
 * ACT_HI_VARINST
 * </p>
 *
 * <p>
 * setVariable && setVariableLocal
 * setVariable：在存放变量的时候不会存入 task_id；同时如果变量的 key 是一样的话，那么后面的值就会覆盖掉之前的值
 * setVariableLocal：在存放变量的时候会将 task_id 一起存入；如果 key 一样的情况，不会覆盖，会出想两个值，以 task_id 进行唯一区别
 * </p>
 */
public class ManageProcessVariableNote extends SpringbootActivitiApplicationTests {
	// 注入 RuntimeService
	@Autowired
	private RuntimeService runtimeService;
	// 注入 TaskService
	@Autowired
	private TaskService taskService;

	/**
	 * 启动流程
	 * 这里我们启动流程的同时就给出参数
	 */
	@Test
	public void startProcess() {
		String instanceKey = "firstProcess";
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("days", 5); // 请假天数
		paramMap.put("description", "约会"); // 请假理由
		paramMap.put("time", new Date()); // 请假的时间

		runtimeService.startProcessInstanceByKey(instanceKey, paramMap);

		System.out.println("流程启动成功");
	}

	/**
	 * 设置流程变量（RuntimeService）：
	 * 有可能再流程的过程中回对变量进行修改或者重设
	 * 流程变量本身就是一个 Map ，对应数据库中也是同样的机制
	 * 只要 key 一样那么 value 就会被覆盖
	 *
	 * <p>
	 * 设置流程变量有两种方式（RuntimeService）：
	 * 他们的共同点使用的都是 “执行实例（ACT_RU_EXECUTION） ID”
	 * 第一种就是直接给出需要设置的 key，value即可
	 * 第二种就是再使用 Map 的方式再次放入
	 * </p>
	 */
	@Test
	public void setVariableRuntimeService() {
		// 第一种设置方式
		String executionId = "10001";
//        runtimeService.setVariable(executionId, "days", 3);

		// 第二种设置方式
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("days", 7); // 请假天数
		/**
		 * 这里我们存入一个对象，如果想存入对象，那么该对象必须实现序列化接口，同时必须让该类声明固定的序列化ID，如果序列化ID不固定，那么对象属性修改就无法存取，会报错
		 * 存入数据库之后，不会直接和其他数据类型一样存到“ACT_RU_VARIABLE”表中，
		 * 他会给出一个“BYTEARRAY_ID_”
		 * 这也就是说，改对象序列化之后会存在“ACT_GE_BYTEARRAY”表中
		 * “ACT_GE_BYTEARRAY”每次都会生成两个用户对象：一个对应运行时、一个对应历史记录
		 */
		paramMap.put("user", new User(1, "deer"));
		runtimeService.setVariables(executionId, paramMap);

		System.out.println("流程变量设置完成");
	}

	/**
	 * 设置流程变量（TaskService）：
	 * 如果再审批的过程中，需要携带或加入或修改流程变量当然也是可以的
	 * 那么审批对应的就是任务
	 * 我们可以通过“TaskService” 去设置
	 *
	 * <p>
	 * 设置流程变量有两种方式（TaskService）：
	 * 他们的共同点使用的都是 “执行实例（ACT_RU_EXECUTION） ID”
	 * 第一种就是直接给出需要设置的 key，value即可
	 * 第二种就是再使用 Map 的方式再次放入
	 * </p>
	 */
	@Test
	public void setVariableTaskService() {
		// 任务 ID
		String taskId = "10008";
		/**
		 * 第一种设置方式
		 * 通过“任务 ID”去进行设置，添加等等
		 */
		taskService.setVariable(taskId, "days", 2);

//        // 第二种设置方式
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("days", 7); // 请假天数
//        runtimeService.setVariables(taskId, paramMap);

		System.out.println("流程变量设置完成");

	}

	/**
	 * 获取流程变量
	 * 在获取流程变量的时候
	 * 我们需要先知道获取哪一个流程的变量，所以我们需要“执行实例（ACT_RU_EXECUTION） ID”
	 * 然后和 Map 一样，通过 key 直接可以获取 value
	 */
	@Test
	public void getVariable() {
		String executionId = "10001"; // 执行实例 ID
		String paramKeyOne = "days"; // 获取请假天数
		String paramKeyTwo = "time"; // 获取请假时间

		// 获取出来的是 Object 可以直接强转
		Integer days = (Integer) runtimeService.getVariable(executionId, paramKeyOne);
		Date time = (Date) runtimeService.getVariable(executionId, paramKeyTwo);
		System.out.println("请假天数：" + days);
		System.out.println("请假时间：" + time.toString());

		// 获取存入的对象
		String paramKeyThree = "user"; // 用户对象
		// 对于对象同样是强转
		User user = (User) runtimeService.getVariable(executionId, paramKeyThree);
		System.out.println("用户对象：" + user.toString());
	}
}
