// 通一的注销功能
function logout() {
    if(confirm("是否确定注销？")){
        // 1-去后端删除session信息
        jQuery.ajax({
            url: "user/logout",
            type: "POST",
            data: {},
            success: function (res){}
        })
        // 2-跳转到登录页面
        location.href = "login.html";
    }
}