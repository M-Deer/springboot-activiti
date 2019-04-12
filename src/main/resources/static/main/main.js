$(function(){
	getUserInfo();
})

// 用户信息
let $userInfo;

/**
 * 获取用户信息
 */
function getUserInfo(){
	$.ajax({
        type:'GET',
        url:BASIS_URL+'/system/uerInfo',  
        dataType:'JSON',
		success:(responseData)=>{
			if(responseData.status === '1')
				$userInfo = responseData.data
			else{
				alert(responseData.message);
				window.location.href=BASIS_URL+'/view/index';
			}
		}
	});
}