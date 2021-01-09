$(function () {
    $(".managerBlogs").slideDown("slow", "swing");
    $(".managerUsers").slideDown("slow", "swing");
    $(".managerLabels").show("slow", "swing");

    $(".sorts").hover(function () {
        $(this).addClass("changeBackground");
    },function () {
        $(this).removeClass("changeBackground");
    })
    $("#sort" + $('#sortId').val()).css("color", "red");
})

function managerBlogs(sortId) {
    window.location.href = "/CheckManager?sortId=" + sortId + "&&start=0";
}

function checkBlog(index){
    window.location.href = "/CheckBlog?index=" + index;
}

function pass(index) {
    if(confirm("确认通过?")){
        $.post({
            url:"../PassBlog",
            data:{
                index : index,
            },
            success : function () {
                window.location.reload()
                alert("完成")
            }
        })
    }
}

function fail(index) {
    if(confirm("确认不通过?")){
        $.post({
            url:"../FailBlog",
            data:{
                index : index,
            },
            success : function () {
                window.location.reload()
                alert("完成")
            }
        })
    }
}

function recommend(sortId, index, recommendNum) {
    if(confirm("确认推荐?")){
        if(recommendNum == 8){
            alert("最多推荐八篇~")
        }else{
            $.post({
                url:"../RecommendBlog",
                data:{
                    sortId : sortId,
                    index : index,
                },
                success : function () {
                    window.location.reload()
                    alert("完成")
                }
            })
        }
    }
}

function deleteRecommend(index) {
    if(confirm("确认取消推荐?")){
        $.post({
            url:"../DeleteRecommendBlog",
            data:{
                index : index,
            },
            success : function () {
                window.location.reload()
                alert("完成")
            }
        })
    }
}

function managerUsers(rank) {
    window.location.href = "/CheckManager?sortId=1&&start=0&&rank=" + rank;
}

function checkUser(userId) {
    window.location.href = "/CheckUser?userId=" + userId;
}

function deleteUser(index) {
    if(confirm("确认删除？")){
        if(confirm("请再次确认!")){
            $.post({
                url : "../DeleteUser",
                data : {
                    index : index,
                },
                success : function () {
                    window.location.reload()
                    alert("删除成功!")
                }
            })
        }
    }
}

function setManager(index) {
    if(confirm("确认设置为管理员?")){
        $.post({
            url : "../SetManager",
            data : {
                index : index,
            },
            success : function () {
                window.location.reload()
                alert("设置成功!")
            }
        })
    }
}

function dropManager(index) {
    if(confirm("确认取消管理员?")){
        $.post({
            url : "../DropManager",
            data : {
                index : index,
            },
            success : function () {
                window.location.reload()
                alert("取消成功!")
            }
        })
    }
}

function changeLabel(index) {
    if($("#label" + index).val() === ($("#" + index + "label").val())){
        alert("内容未变")
    }else{
        var reg = /^[\u4e00-\u9fa5_a-zA-Z0-9]{1,12}$/;
        if (reg.test($("#" + index + "label").val())){
            $.post({
                url : "../UpdateLabel",
                data : {
                    index : index + 1,
                    newLabel : $("#" + index + "label").val()
                },
                success : function () {
                    window.location.reload()
                    alert("修改成功!")
                }
            })
        }else{
            alert("为中英文,长度不超过12")
        }
    }
}

function lastPage(sortId, now, rank) {
    $.post({
        url:"../CheckManager",
        data:{
            sortId : sortId,
            start : (now - 1) * 8,
            rank : rank
        },
        success : function () {
            window.location.reload()
        }
    })
}

function goToPage(sortId, pageindex, rank){
    $.post({
        url:"../CheckManager",
        data:{
            sortId : sortId,
            start : pageindex * 8,
            rank : rank
        },
        success : function () {
            window.location.reload()
        }
    })
}


function nextPage(sortId, now, rank) {
    $.post({
        url:"../CheckManager",
        data:{
            sortId : sortId,
            start : (now + 1) * 8,
            rank : rank
        },
        success : function () {
            window.location.reload()
        }
    })
}