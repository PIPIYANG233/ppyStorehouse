$(function () {
    $(".search_blogs").slideDown("slow", "swing");
    $(".searchUsers").slideDown("slow", "swing");
    $(".recommendLabels").slideDown("slow", "swing");
    $(".sorts").hover(function () {
        $(this).addClass("changeBackground");
    },function () {
        $(this).removeClass("changeBackground");
    })
    $("#sort" + $('#sortId').val()).css("color", "red");
})

function regSearch() {
    var reg1 = /^\s*$/;
    var reg2 = /^[ ]{1,30}$/;
    var reg3 = /\uD83C[\uDF00-\uDFFF]|\uD83D[\uDC00-\uDE4F]/g;
    var reg4 = /^.{1,30}$/;
    searchContent = $("#searchContent");

    if (reg1.test(searchContent.val()) || reg2.test(searchContent.val())) {
        alert("搜索内容不能为空");
        return false;
    }
    if (reg3.test(searchContent.val())) {
        alert("搜索内容禁止使用emoji");
        return false;
    }

    if (reg4.test(searchContent.val())) {
        return true;
    }else{
        alert("内容过长")
        return false;
    }
}

function checkAuthor(userId) {
    var theUserId;
    if(userId == ""){
        theUserId = 1;
    }else{
        theUserId = userId;
    }
    $.post({
        url: "../CheckSearch",
        data:{
            isAuthor : true,
            start : 0,
            searchContent : $(".searchContent").val(),
            userId : theUserId
        },
        success: function () {
            window.location.reload()
        }
    })
}

function changeSort(sortId) {
    $.post({
        url: "../CheckSearch",
        data:{
            isAuthor : false,
            sortId : sortId,
            start : 0,
            searchContent : $(".searchContent").val()
        },
        success: function () {
            window.location.reload()
        }
    })
}

function checkLabel(index1, index2, sortId) {
    var label = $("#label" + index1 + "" + index2).val()
    $.post({
        url: "../CheckSearch",
        data:{
            isAuthor : false,
            start : 0,
            sortId: sortId,
            searchContent : label
        },
        success: function () {
            window.location.reload()
        }
    })
}

function checkRecommendLabel(index, sortId) {
    var recommendLabel = $("#recommendLabel" + index).text()
    $.post({
        url: "../CheckSearch",
        data:{
            isAuthor : false,
            start : 0,
            sortId: sortId,
            searchContent : recommendLabel
        },
        success: function () {
            window.location.reload()
        }
    })
}

function readTheBlog(index) {
    window.location.href = "/CheckSearchBlog?index=" + index;
}

function follow(isFollow, userId, index) {
    if(userId == ""){
        window.location.href="/main_jsp/LOGIN.jsp";
    }else{
        if(isFollow){
            $.ajax({
                url:"../FollowTheSearchUser",
                type:"POST",
                data:({
                    isFollow : isFollow,
                    userId : userId,
                    index : index
                }),
                success:function () {
                    window.location.reload();
                    alert("关注成功!");
                }
            })
        }else{
            $.ajax({
                url:"../FollowTheSearchUser",
                type:"POST",
                data:({
                    isFollow: isFollow,
                    userId : userId,
                    index : index
                }),
                success:function () {
                    window.location.reload();
                    alert("取关成功!");
                }
            })
        }
    }
}

function checkTheSearchUser(index){
    window.location.href = "/ToAuthorSpace?index=" + index + "&&flag=3";
}

function lastPage(userId, isAuthor, sortId, now) {
    var theUserId;
    if(userId == ""){
        theUserId = 1;
    }else{
        theUserId = userId;
    }
    $.post({
        url:"../CheckSearch",
        data:{
            userId : theUserId,
            isAuthor : isAuthor,
            sortId : sortId,
            start : (now - 1) * 8,
            searchContent : $(".searchContent").val()
        },
        success : function () {
            window.location.reload()
        }
    })
}

function goToPage(userId, isAuthor, sortId, pageindex){
    var theUserId;
    if(userId == ""){
        theUserId = 1;
    }else{
        theUserId = userId;
    }
    $.post({
        url:"../CheckSearch",
        data:{
            userId : theUserId,
            isAuthor : isAuthor,
            sortId : sortId,
            start : pageindex * 8,
            searchContent : $(".searchContent").val()
        },
        success : function () {
            window.location.reload()
        }
    })
}


function nextPage(userId, isAuthor, sortId, now) {
    var theUserId;
    if(userId == ""){
        theUserId = 1;
    }else{
        theUserId = userId;
    }
    $.post({
        url:"../CheckSearch",
        data:{
            userId : theUserId,
            isAuthor : isAuthor,
            sortId : sortId,
            start : (now + 1) * 8,
            searchContent : $(".searchContent").val()
        },
        success : function () {
            window.location.reload()
        }
    })
}