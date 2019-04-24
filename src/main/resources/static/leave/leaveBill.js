$(function() {
	initTableLeaveBill();

	formValidation();
	
	// 清除数据、样式
	$('#modal_leave_info').on('hidden.bs.modal', function (e) {
		let $formInfo = $('#form_leave_info')[0];
		$formInfo.reset();
		$formInfo.classList.remove('was-validated');
	})
});

/**
 * 初始化表格数据
 */
function initTableLeaveBill() {
	let $table = $('#table_leave_bill');
	$table.bootstrapTable({
		locale : 'zh-CN',
		height : 550,
		url : BASIS_URL + '/billLeave/getItemsByPage',
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
		} , {
			field : 'status',
			title : '当前状态',
			formatter:(value, row, index)=>{
				let color;
				let html;
				if(value==='-1'){
					color = '#969696';
					html = '放弃';
				}else if(value==='0'){
					color = '#EE0000';
					html = '驳回';
				}else if(value==='1'){
					color = '#20B2AA';
					html = '审批中';
				}else if(value==='2'){
					color = '#43CD80';
					html = '审批完成';
				}
				return '<span style="color:'+color+'">'+html+'</span>';
			}
		} ,{
			field : 'operate',
			title : '操作',
			formatter:(value, row, index)=>{
				let html ='';
				if(row.status==='1' || row.status==='0'){
					html =  '<button class="btn btn-outline-primary btn-sm" onclick=checkNowProcessActivities('+row.id+')>查看进度</button>';
				}
				return html;
			}
		}]
	});
}

/**
 * 表单验证 进判断非空
 */
function formValidation() {
	// 获取表单
	let $formInfo = $('#form_leave_info');
	Array.prototype.filter.call($formInfo, function($formInfo) {
		// 这里传入事件驱动的按钮（虽然是ID 获取，但依旧需要给一个 [0]，否则报错）
		$('#btn_submit')[0].addEventListener('click', function(event) {
			if ($formInfo.checkValidity() === false) {
				// 失败则爆出相对应的信息
				event.preventDefault();
				event.stopPropagation();
				$formInfo.classList.add('was-validated');
			} else {
				// 成功则序列化数据，准备提交
				let jsonObj = $('#form_leave_info').serializeJSON();
				$.ajax({
					type: 'POST',
					url: BASIS_URL + '/billLeave',
					dataType: 'JSON',
					contentType: 'application/json;charset=UTF-8',
					data: JSON.stringify(jsonObj),
					success: (responseData) => {
						alert(responseData.message);
						$('#table_leave_bill').bootstrapTable('refresh');
						$('#modal_leave_info').modal('hide')
					}
				});
				
			}
		}, false);
	});
	
}

/**
 * 查看当前流程图执行节点
 * 
 * @param id
 *            当前流程 id
 */
function checkNowProcessActivities(id){
	$('#modal_activities_view').modal('show')
	$('#img_activities_view').attr('src',BASIS_URL+'/activitiesView?leaveBillId='+id); 
}