$(function(){
	// 获取用户信息
	getUserInfo();
	
	// 退出登录
	$('#a_logout').on('click',()=>{
		logout();
	});
})

/**
 * 获取用户信息
 */
function getUserInfo(){
	$.ajax({
        type:'GET',
        url:BASIS_URL+'/system/uerInfo',  
        dataType:'JSON',
		success:(responseData)=>{
			if(responseData.status === '1'){				
				let userInfo = responseData.object;
				if(userInfo.username==='userA'){
					$('#li_leave_bill').css('display','block');
				}
			}
			else{
				alert(responseData.message);
				window.location.href=BASIS_URL+'/view/index';
			}
		}
	});
}

/**
 * 退出登录
 */
function logout(){
	$.ajax({
        type:'GET',
        url:BASIS_URL+'/system/logout',  
		success:()=>{
			window.location.href=BASIS_URL+'/view/index';
		}
	});
}