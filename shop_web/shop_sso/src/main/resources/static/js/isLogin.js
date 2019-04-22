$(function () {
    $.ajax({
        url: "http://localhost:8084/user/login_token",
        success: function (data) {
            if (data == 0) {
                // var loginNode = $("<a></a>").attr("href", "http://localhost:8084/user/toLogin?returnUrl="+location.href).html("登录");
                // var registerNode = $("<a></a>").attr("href", "http://localhost:8084/user/toRegister").html("注册");
                // $("#is_login").append($("<div></div>")).append(loginNode).append(registerNode);
                $("#is_login").append($("<a href='javascript:login()'>登录</a> |"+"<a href='http://localhost:8084/user/toRegister'>注册</a>"))
            } else {
                // console.log(data.nickname);
                $("#is_login").html(data.nickname + ",欢迎您<a href='http://localhost:8084/user/logout'>注销</a>");
            }
        },
        dataType: "jsonp",
        jsonpCallback: "login_token"
    })
});

//进行登录
function login() {
    var returnUrl = location.href;
    //编码url
    returnUrl = encodeURI(returnUrl);
    location.href = "http://localhost:8084/user/toLogin?returnUrl=" + returnUrl;
}
