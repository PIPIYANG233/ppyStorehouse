
window.onload = function () {
    var pic = document.getElementById("pic");
    pic.onclick = function () {
        var date = new Date().getTime();
        pic.src = "/checkCode?" + date;
    }
    var flag1 = false, flag2 = false, flag3 = false
    var reg = /^[a-zA-Z0-9]{5,12}$/;
    var name = document.getElementById("name");
    <!-- 判断账号 -->
    var id = document.getElementById("id");
    id.onblur = function () {
        if(reg.test(id.value)){
            var id_span = document.getElementById("id_span");
            id_span.src = "/img/yes.png"
            id_span.style.width = 20 + "px";
            id_span.style.height = 30 + "px";
            id_span.style.marginLeft = 5 + "px";
            flag1 = true;
        }else{
            var id_span = document.getElementById("id_span");
            id_span.src = "/img/no.png"
            id_span.style.width = 20 + "px";
            id_span.style.height = 30 + "px";
            id_span.style.marginLeft = 5 + "px";
            flag1 = false;
        }
    }
    <!-- 判断密码 -->
    var pwd = document.getElementById("pwd");
    pwd.onblur = function () {
        if(reg.test(pwd.value)){
            var pwd_span = document.getElementById("pwd_span");
            pwd_span.src = "/img/yes.png"
            pwd_span.style.width = 20 + "px";
            pwd_span.style.height = 30 + "px";
            pwd_span.style.marginLeft = 5 + "px";
            flag2 = true;
        }else{
            var pwd_span = document.getElementById("pwd_span");
            pwd_span.src = "/img/no.png"
            pwd_span.style.width = 20 + "px";
            pwd_span.style.height = 30 + "px";
            pwd_span.style.marginLeft = 5 + "px";
            flag2 = false
        }
    }
    <!-- 判断密码 -->
    var 验证码 = document.getElementById("验证码");
    验证码.onblur = function () {
        var reg1 = /^[a-zA-Z0-9]{1,}$/;
        if(reg1.test(验证码.value)){
            flag3 = true;
        }else{
            flag3 = false
        }
    }

    var login_form = document.getElementById("login_form");
    login_form.onsubmit = function () {
            return (flag1 && flag2 && flag3);
    }
}