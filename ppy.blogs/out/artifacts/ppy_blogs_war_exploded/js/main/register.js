
    window.onload = function () {
        var pic = document.getElementById("pic");
        pic.onclick = function () {
            var date = new Date().getTime();
            pic.src = "/checkCode?" + date;
    }

        var flag1 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false;
        var reg1 = /^[\u4e00-\u9fa5_a-zA-Z0-9]{1,10}$/;
        var reg2 = /^[a-zA-Z0-9]{6,12}$/;
        <!-- 判断昵称 -->
        var name = document.getElementById("name");
        name.onblur = function () {
            if (reg1.test(name.value)) {
                var name_span = document.getElementById("name_span");
                name_span.src = "/img/yes.png"
                name_span.style.width = 20 + "px";
                name_span.style.height = 30 + "px";
                name_span.style.marginLeft = 5 + "px";
                flag1 = true;
        } else {
                var name_span = document.getElementById("name_span");
                name_span.src = "/img/no.png"
                name_span.style.width = 20 + "px";
                name_span.style.height = 30 + "px";
                name_span.style.marginLeft = 5 + "px";
                flag1 = false;
        }
    }
        <!-- 判断密码 -->
        var pwd = document.getElementById("pwd");
        pwd.onblur = function () {
            if (reg2.test(pwd.value)) {
                var pwd_span = document.getElementById("pwd_span");
                pwd_span.src = "/img/yes.png"
                pwd_span.style.width = 20 + "px";
                pwd_span.style.height = 30 + "px";
                pwd_span.style.marginLeft = 5 + "px";
                flag3 = true;
        } else {
                var pwd_span = document.getElementById("pwd_span");
                pwd_span.src = "/img/no.png"
                pwd_span.style.width = 20 + "px";
                pwd_span.style.height = 30 + "px";
                pwd_span.style.marginLeft = 5 + "px";
                flag3 = false
        }
    }
        <!-- 判断确认密码 -->
        var pwd_true = document.getElementById("pwd_true");
        pwd_true.onblur = function () {
            if (reg2.test(pwd_true.value) && pwd_true.value == pwd.value) {
                var pwd_true_span = document.getElementById("pwd_true_span");
                pwd_true_span.src = "/img/yes.png"
                pwd_true_span.style.width = 20 + "px";
                pwd_true_span.style.height = 30 + "px";
                pwd_true_span.style.marginLeft = 5 + "px";
                flag4 = true;
        } else {
                var pwd_true_span = document.getElementById("pwd_true_span");
                pwd_true_span.src = "/img/no.png"
                pwd_true_span.style.width = 20 + "px";
                pwd_true_span.style.height = 30 + "px";
                pwd_true_span.style.marginLeft = 5 + "px";
                flag4 = false;
        }
    }

        <!-- 判断验证码 -->
        var 验证码 = document.getElementById("验证码");
        验证码.onblur = function () {
            var reg3 = /^[a-zA-Z0-9]{1,}$/;
            if (reg3.test(验证码.value)) {
                flag6 = true;
        } else {
                flag6 = false
        }
    }
        var email = document.getElementById("email");
        document.getElementById("register").onsubmit = function () {
            if (null != email.value && email.value != "") {
                flag5 = true;
        }
            return (flag1 && flag3 && flag4 && flag5 && flag6);
    }
}