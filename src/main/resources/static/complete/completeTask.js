$(function() {
	// 初始化表格数据
	initTableCompleteTask();
	
	// 办理任务的相关按钮
	$('#btn_success').on('click',(event)=>{
		completeTask(event.currentTarget);
	});
	$('#btn_rollback').on('click',(event)=>{
		completeTask(event.currentTarget);
	});
	
	// 清除数据、样式
	$('#modal_task_info').on('hidden.bs.modal', function (e) {
		let $formInfo = $('#form_task_info')[0];
		$formInfo.reset();
	})
});

/**
 * 初始化表格数据
 */
function initTableCompleteTask() {
	let $table = $('#task_table');
	
	$table.bootstrapTable({
		locale : 'zh-CN',
		height : 550,
		url : BASIS_URL + '/completeTask/getItemsByPage',
		pagination : 'true',
		sidePagination : 'server',
		pageNumber : 1,
		pageSize : 10,
		totalField : 'total',
		dataField : 'records',
		queryParamsType : '',
		columns : [ {
			field : 'id',
			title : 'ID',
			visible : false,
		}, {
			field : 'name',
			title : '任务名称'
		}, {
			field : 'assignee',
			title : '办理人'
		},  {
			field : 'createTime',
			title : '创建时间'
		},{
			field : 'operate',
			title : '操作',
			formatter:(value, row, index)=>{
				let html = '<button class="btn btn-outline-primary btn-sm" onclick=getLeaveBillInfo('+row.id+')>任务办理</button>';
				return html;
			}
		}]
	});
}

/**
 * 初始化表格数据
 * 
 * @param taskId
 *            任务ID
 */
function initTableCommentHistory(taskId) {
	let $table = $('#task_comment_history');
	
	$table.bootstrapTable({
		locale : 'zh-CN',
		height : 201,
		url : BASIS_URL + '/completeTask/getCommentsByTaskId?taskId='+taskId,
		pagination : 'false',
		columns : [{
			field : 'message',
			title : '批注信息'
		}, {
			field : 'userId',
			title : '批注人'
		},  {
			field : 'time',
			title : '批注时间'
		}]
	});
}

/**
 * 获取请假单
 * 
 * @param taskId
 *            任务id
 */
function getLeaveBillInfo(taskId){
	$.ajax({
		type: 'GET',
		url: BASIS_URL + '/completeTask/getItemById?taskId='+taskId,
		dataType: 'JSON',
		contentType: 'application/json;charset=UTF-8',
		success: (responseData) => {
			// 获取数据信息
			let dataInfo = responseData.object.dataInfo;
			$('#input_leave_bill_id').val(dataInfo.id);
			$('#input_task_title').val(dataInfo.title);
			$('#input_leave_content').val(dataInfo.content);
			$('#input_leave_days').val(dataInfo.days);
			$('#input_leave_time').val(dataInfo.leaveTime);
			
			// 按钮信息
			let btnInfo = responseData.object.btnInfo;
			$('#btn_success').html(btnInfo[0]);
			$('#btn_success').val(btnInfo[0]+':'+taskId);
			$('#btn_rollback').html(btnInfo[1]);
			$('#btn_rollback').val(btnInfo[1]+':'+taskId);
			
			// 初始化批注信息表格
			initTableCommentHistory(taskId);
			
			$('#modal_task_info').modal('show')
		}
	});
}

/**
 * 办理任务
 * 
 * @param elem
 *            按钮元素
 */
function completeTask(elem){
	let paramMap = {};
	// 获取按钮的值（结果 ：任务ID）
	let paramArray = $(elem).val().split(":");
	// 分割后开始分装不同的参数
	let outcome = paramArray[0];
	let taskId = paramArray[1];
	// 参数对象
	paramMap.outcome = outcome;
	paramMap.taskId = taskId;
	paramMap.leaveBillId = $('#input_leave_bill_id').val();
	paramMap.comment = $('#textarea_comment').val();
	
	$.ajax({
		type: 'POST',
		url: BASIS_URL + '/completeTask',
		dataType: 'JSON',
		contentType: 'application/json;charset=UTF-8',
		data:JSON.stringify(paramMap),
		success: (responseData) => {
			// 办理信息
			alert(responseData.message);
			// 刷新表格，关闭模态框
			$('#task_table').bootstrapTable('refresh');
			$('#modal_task_info').modal('hide')
		}
	});
}