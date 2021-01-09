
function createColumn(userId) {
    columnName =  $(".name_input").val()
    columnIntroduce = $(".introduce_textarea").val()

    var reg1 = /^\s*$/;
    var nameReg2 = /^[ ]{1,8}$/;
    var introduceReg2 = /^[ ]{1,30}$/;
    var reg3 = /\uD83C[\uDF00-\uDFFF]|\uD83D[\uDC00-\uDE4F]/g;
    var nameReg4 = /^.{1,8}$/;
    var introduceReg4 = /^.{1,30}$/;
    if(reg1.test(columnName) || nameReg2.test(columnName) || reg1.test(columnIntroduce) || introduceReg2.test(columnIntroduce)){
        alert("专栏名称或介绍不能为空")
    }else{
        if(reg3.test(columnName) || reg3.test(columnIntroduce)){
            alert("专栏名称或介绍包含非法字符")
        }else{
            if(nameReg4.test(columnName) && introduceReg4.test(columnIntroduce)){
                $.post({
                    url : "../CreateColumn",
                    data : {
                        columnName : columnName,
                        columnIntroduce : columnIntroduce,
                        ownerId : userId,
                    },
                    success : function (result) {
                        if(result === 'true'){
                            alert("创建成功")
                            window.location.reload()
                        }else{
                            alert("专栏已存在,无法重复创建")
                        }
                    }
                })
            }else{
                alert("专栏名称或介绍不符合要求")
            }
        }
    }
}

function charge(columnId, columnName, columnIntroduce) {
    $.post({
        url : "../ManagerColumnBlogs",
        data : {
            flag : true,
            columnId : columnId,
            columnName : columnName,
            columnIntroduce : columnIntroduce
        },
        success : function () {
            window.location.href = "/user_jsp/USER_managerColumns.jsp"
        }
    })
}

function creditColumn(oldName,oldIntroduce,columnId, index, userId) {
    columnName =  $(".name_input"+index).val()
    columnIntroduce = $(".introduce_textarea"+index).val()

    if(oldName === columnName && oldIntroduce === columnIntroduce){
        alert("内容未变")
    }else{
        var reg1 = /^\s*$/;
        var nameReg2 = /^[ ]{1,8}$/;
        var introduceReg2 = /^[ ]{1,30}$/;
        var reg3 = /\uD83C[\uDF00-\uDFFF]|\uD83D[\uDC00-\uDE4F]/g;
        var nameReg4 = /^.{1,8}$/;
        var introduceReg4 = /^.{1,30}$/;
        if(reg1.test(columnName) || nameReg2.test(columnName) || reg1.test(columnIntroduce) || introduceReg2.test(columnIntroduce)){
            alert("专栏名称或介绍不能为空")
        }else{
            if(reg3.test(columnName) || reg3.test(columnIntroduce)){
                alert("专栏名称或介绍包含非法字符")
            }else{
                if(nameReg4.test(columnName) && introduceReg4.test(columnIntroduce)){
                    $.post({
                        url : "../UpdateColumn",
                        data : {
                            columnId : columnId,
                            columnName : columnName,
                            columnIntroduce : columnIntroduce,
                            index : index,
                            userId : userId
                        },
                        success : function (result) {
                            if(result === 'true'){
                                alert("编辑成功")
                                window.location.reload()
                            }else{
                                alert("专栏名重复")
                            }
                        }
                    })
                }else{
                    alert("专栏名称或介绍不符合要求")
                }
            }
        }
    }
}

function deleteColumn(columnId, index, userId){
    if(confirm("确认删除?")){
        $.post({
            url : "../DeleteColumn",
            data : {
                columnId : columnId,
                index : index,
                userId : userId
            },
            success : function () {
                alert("删除成功");
                window.location.reload()
            }
        })
    }
}
