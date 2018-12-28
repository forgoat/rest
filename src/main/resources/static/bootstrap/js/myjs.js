//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

function getToken() {
    $.ajax({
        type: 'HEAD', // 获取头信息，type=HEAD即可
        url : window.location.href,
        complete: function( xhr,data ){
            return xhr.getResponseHeader("token");
        }
    });
}

function redict(url){
    var token=getToken();
    $.ajax({
        beforeSend: function(request) {
            request.setRequestHeader("token", token),
                request.setRequestHeader("Accept","application/json; charset=utf-8")
        },
        success:function (data,textStatus,request) {
            window.location.href=url;
        }
    });

}

function getroot(){
    var url=window.location.href;
    var role=sessionStorage.getItem("role");
    if(role=='Student')
        if (url.search(/istudent/)==-1)
        {
            alert("您无权限访问此页面,请重新登录！");
            window.location.href="../login.html";
        }
    if(role=='Teacher')
        if (url.search(/iteacher/)==-1)
        {
            alert("您无权限访问此页面，请重新登录！");
            window.location.href="../login.html";
        }
}
