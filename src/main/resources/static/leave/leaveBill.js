$(function() {
	initTableLeaveBill();

	formValidation();
});

/**
 * 初始化表格数据
 */
function initTableLeaveBill() {
	let $table = $('#table_leave_bill');
	$table.bootstrapTable({
		locale : 'zh-CN',
		height : 550,
		url : BASIS_URL + '/billLeave',
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
			field : 'user.username',
			title : '请假用户'
		}, {
			field : 'title',
			title : '请假标题'
		}, {
			field : 'content',
			title : '请假内容'
		}, {
			field : 'leaveTime',
			title : '请假时间'
		} ]
	});
}

/**
 * 表单验证
 */
function formValidation() {
	let $formInfo = $('#form_leave_info');
	Array.prototype.filter.call($formInfo, function($formInfo) {
		$('#btn_submit')[0].addEventListener('click', function(event) {
			if ($formInfo.checkValidity() === false) {
				event.preventDefault();
				event.stopPropagation();
			} else {
				let jsonObj = $('#form_leave_info').serializeJSON();
				$.ajax({
			        type:'POST',
			        url:BASIS_URL+'/billLeave',  
			        dataType:'JSON',
					contentType:'application/json;charset=UTF-8',
					data:JSON.stringify(jsonObj),
					success:(responseData)=>{
						if(responseData.status === '1')
							$userInfo = responseData.data
						else{
							alert(responseData.message);
						}
						let $table = $('#table_leave_bill');
						$table.bootstrapTable('refresh');
					}
				});
			}
			$formInfo.classList.add('was-validated');
		}, false);
	});
}