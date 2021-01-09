
$(function () {
    $("#user_name_change").val($("#user_name").text())
    $("#true_name_change").val($("#true_name").text())
    $("#birthday_change").val($("#birthday").text())
    $(".change_inf_textarea").val($(".main_center_down_right_down").text())

    var head_form = document.getElementById("head_form")
    var img_file = $("#img_file");
    head_form.onsubmit = function(){
        return img_file.val() != "";
    }

    img_file.change(function() {
        var file = this.files[0];
        // 确认选择的文件是图片
        if(file.type.indexOf("image") == 0) {
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function() {
                // 图片base64化
                var newUrl = this.result;
                $("#head_img_change").prop("src", newUrl);
            };
        }
    });
})

function show_main_right() {
    $(".main_right").slideDown("slow");
    $("#head_img_change").prop("src", $("#head_img").attr("src"));
}

function hide_main_right() {
    $(".main_right").slideUp("slow");
}

function change(userId) {
    if($("#flag").val() === '2'){
        // 修改密码
        var reg2 = /^[a-zA-Z0-9]{6,12}$/;
        oldPassword = $("#oldPassword").val()
        newPassword = $("#newPassword").val()
        if(reg2.test(oldPassword) && reg2.test(newPassword)){
            $.post({
                url : "../UpdateUserPassword",
                data : {
                    userId : userId,
                    oldPassword : oldPassword,
                    newPassword : newPassword
                },
                success : function (result) {
                    if(result === 'true'){
                        alert("更新密码成功")
                        window.location.reload();
                    }else{
                        alert("旧密码不正确")
                    }
                }
            })
        }else{
            alert("密码格式有误")
        }
    }else{
        sex_change = '';
        $("input[type=radio]:checked").each(function () {
            sex_change += $(this).val();
        })

        user_name_change = $("#user_name_change").val();
        true_name_change = $("#true_name_change").val()
        birthday_change = $("#birthday_change").val()
        change_inf_textarea = $(".change_inf_textarea").val()

        if(user_name_change===$("#user_name").text()&&true_name_change===$("#true_name").text()&&
            birthday_change===$("#birthday").text()&&sex_change===$("#sex").text()&&change_inf_textarea===$(".main_center_down_right_down").text()){
            alert("资料未变");
        }else{
            var reg1 = /^[\u4e00-\u9fa5_a-zA-Z0-9]{1,10}$/;
            var reg2 = /^\s*$/;
            var reg3 = /^[ ]{1,60}$/;
            var reg4 = /\uD83C[\uDF00-\uDFFF]|\uD83D[\uDC00-\uDE4F]/g;
            var reg5 = /^.{1,60}$/;

            if(reg1.test(user_name_change) && reg1.test(true_name_change) &&
                !reg2.test(change_inf_textarea) && !reg3.test(change_inf_textarea) && !reg4.test(change_inf_textarea) && reg5.test(change_inf_textarea)){

                if(sex_change === '男'){
                    sex_change = '1';
                }else{
                    sex_change = '0';
                }
                $.post({
                    url : "../ChangeUserInf",
                    data : {
                        user_name : user_name_change,
                        true_name : true_name_change,
                        birthday : birthday_change,
                        sex : sex_change,
                        introduce : change_inf_textarea
                    },
                    success : function () {
                        window.location.reload();
                        alert("修改成功!")
                    }
                })
            }else{
                alert("内容格式有误,修改失败")
            }
        }
    }
}

function wantChangePassword() {
    $("#flag").val('2')
    $(".inf").prop("disabled",1)
    $("#oldPassword").removeAttr("disabled")
    $("#newPassword").removeAttr("disabled")
}

function notChangePassword() {
    $("#flag").val('1')
    $(".inf").removeAttr("disabled")
    $("#oldPassword").prop("disabled",1)
    $("#newPassword").prop("disabled",1)
}
