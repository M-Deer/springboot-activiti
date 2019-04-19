$(function() {
	// 初始化表格数据
	initTableCompleteTask();
	
	// 按钮提交
	$('#btn_success').on('click',(event)=>{
		completeTask(event.currentTarget);
	});
	$('#btn_rollback').on('click',(event)=>{
		completeTask(event.currentTarget);
	});
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
 * 获取请假单
 * 
 * @param taskId
 *            任务id
 */
function getLeaveBillInfo(taskId){
	$.ajax({
		type: 'PUT',
		url: BASIS_URL + '/completeTask?taskId='+taskId,
		dataType: 'JSON',
		contentType: 'application/json;charset=UTF-8',
		success: (responseData) => {
			// 获取数据信息
			let dataInfo = responseData.object.dataInfo;
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
	// 获取按钮的值（决定 ：任务ID）
	let param = $(elem).val();
	
	$.ajax({
		type: 'PUT',
		url: BASIS_URL + '/completeTask?param='+param,
		dataType: 'JSON',
		contentType: 'application/json;charset=UTF-8',
		success: (responseData) => {
			
		}
	});
}