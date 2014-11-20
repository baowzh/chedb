/**
 * 用户登录
 */
var switchdiv = function(type) {
	if (type == 1) {
		$("#carmaintain").hide();
		$("#carbrands").show();
	} else {
		$("#carmaintain").show();
		$("#carbrands").hide();
	}

};
