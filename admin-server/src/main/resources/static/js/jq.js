$.ajaxSetup({
	cache : false,
	error : function(xhr, textStatus, errorThrown) {
		var msg = xhr.responseText;
		var response = JSON.parse(msg);
		var code = response.code;
		var message = response.message;
		if (code == 400) {
			layer.msg(message);
		} else if (code == 401) {
			layer.msg('未登录');
		} else if (code == 403) {
			console.log("未授权:" + message);
			layer.msg('未授权');
		} else if (code == 500) {
			layer.msg('系统错误：' + message);
		}
	}
});
//删除注册账号同时删除牧民信息
function buttonDel(data, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='删除' onclick='del(\"" + data +"\")'><i class='layui-icon'>&#xe640;</i></button>");
	return btn.prop("outerHTML");
}
//删除注册账号同时删除专家信息
function buttonDelExpert(data, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='删除' onclick='delExpert(\"" + data +"\")'><i class='layui-icon'>&#xe640;</i></button>");
	return btn.prop("outerHTML");
}
//删除注册账号同时删除药店信息
function buttonDelDrugstore(data, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='删除' onclick='delDrugstore(\"" + data +"\")'><i class='layui-icon'>&#xe640;</i></button>");
	return btn.prop("outerHTML");
}
//专家审核通过按钮
function buttonExpertPass(data, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='审核通过' onclick='expertPass(\"" + data +"\")'>审核通过</button>");
	return btn.prop("outerHTML");
}
//专家审核失败按钮
function buttonExpertFail(data, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='审核失败' onclick='expertFail(\"" + data +"\")'>审核失败</button>");
	return btn.prop("outerHTML");
}
//药店审核通过按钮
function buttonDrugstorePass(data, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='审核通过' onclick='drugstorePass(\"" + data +"\")'>审核通过</button>");
	return btn.prop("outerHTML");
}
//药店审核失败按钮
function buttonDrugstoreFail(data, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='审核失败' onclick='drugstoreFail(\"" + data +"\")'>审核失败</button>");
	return btn.prop("outerHTML");
}

//公共添加信息按钮
function buttonCreate(href, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='添加个人信息' onclick='window.location=\"" + href +"\"'><i class='layui-icon'>&#xe608;</i></button>");
	return btn.prop("outerHTML");
}
//公共修改信息按钮
function buttonEdit(href, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='编辑' onclick='window.location=\"" + href +"\"'><i class='layui-icon'>&#xe642;</i></button>");
	return btn.prop("outerHTML");
}
//公共学习管理视频文章预览按钮
function buttonVeiw(href, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='预览详情' onclick='window.location=\"" + href +"\"'><i class='layui-icon'>&#xe6b2;</i></button>");
	return btn.prop("outerHTML");
}
//公共 学习管理 审核通过按钮
function buttonPass(data, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='审核通过' onclick='pass(\"" + data +"\")'>审核通过</button>");
	return btn.prop("outerHTML");
}
//公共学习管理 审核失败按钮
function buttonFail(data, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='审核失败' onclick='fail(\"" + data +"\")'>审核失败</button>");
	return btn.prop("outerHTML");
}
//详情带审核
function showView(data, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='预览详情' onclick='show(\"" + data +"\")'><i class='layui-icon'>&#xe6b2;</i></button>");
	return btn.prop("outerHTML");
}
function showView1(data, permission, pers){
	if ($.inArray(permission, pers) < 0) {
		return "";
	}
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='预览详情' onclick='show1(\"" + data +"\")'><i class='layui-icon'>&#xe6b2;</i></button>");
	return btn.prop("outerHTML");
}
//视频通讯测试
function buttonVideo(data){
	
	var btn = $("<button class='layui-btn layui-btn-mini' title='视频通讯' onclick='videoSee(\"" + data +"\")'><i class='layui-icon'>&#xe642;</i></button>");
	return btn.prop("outerHTML");
}

function deleteCurrentTab(){
	var lay_id = $(parent.document).find("ul.layui-tab-title").children("li.layui-this").attr("lay-id");
	parent.active.tabDelete(lay_id);
}
