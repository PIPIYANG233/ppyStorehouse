window.onload = function () {
    $(".left_author_data").show("slow", "swing");
    // $(".center").slideDown("slow", "swing");
    $(".right").show("slow", "swing");

    var imgs = $("img");
    var imgNum = imgs.length;
    var center_contentWidth = $("#center_").width();
    for(var i = 0; i < imgNum; i++){
        if(imgs[i].width > center_contentWidth){
            imgs[i].width = center_contentWidth * 4/5;
        }
    }
}

function up() {
    var isUp = $("#isUp").val();
    if(isUp == "0"){
        $("#upBtn").prop("src", "../img/tobarThumbUpactive.png");
        $("#isUp").prop("value","1");
        $.ajax({
            url: "../UpTheBlog",
            type: "POST",
            data:{isUp : "1"},
            success:function () {
                $("#up_span").load(location.href + " #up_span")
            }
        })
    }else{
        $("#upBtn").prop("src", "../img/tobarThumbUp.png");
        $("#isUp").prop("value","0");
        $.ajax({
            url: "../UpTheBlog",
            type: "POST",
            data:{isUp : "0"},
            success:function () {
                $("#up_span").load(location.href + " #up_span")
            }
        })
    }
}

function follow() {
    var isFollow = document.getElementById("isFollow");
    if(isFollow.value == "0"){
        document.getElementById("isFollow").value = "1";
        $.ajax({
            url: "../FollowTheAuthor",
            type: "POST",
            data:{isFollow : "1"},
            success:function () {
                window.location.reload()
                alert("关注成功");
            }
        })
    }else{
        document.getElementById("isFollow").value = "0";
        $.ajax({
            url: "../FollowTheAuthor",
            type: "POST",
            data:{isFollow : "0"},
            success:function () {
                window.location.reload()
                alert("取关成功");
            }
        })
    }
}

function collect() {
    var isCollect = document.getElementById("isCollect");
    if(isCollect.value == "0"){
        $("#collectBtn").prop("src", "../img/取消收藏.png");
        document.getElementById("isCollect").value = "1";
        $.ajax({
            url: "../CollectTheBlog",
            type: "POST",
            data:{isCollect : "1"},
            success:function () {
                $("#collect_span").load(location.href + " #collect_span")
            }
        })
    }else{
        $("#collectBtn").prop("src", "../img/收藏.png");
        document.getElementById("isCollect").value = "0";
        $.ajax({
            url: "../CollectTheBlog",
            type: "POST",
            data:{isCollect : "0"},
            success:function () {
                $("#collect_span").load(location.href + " #collect_span")
            }
        })
    }
}

function toAuthorSpace(flag) {
    window.location.href = "/CheckMyself?flag=" + flag;
}

function login() {
    window.location.href = "/main_jsp/LOGIN.jsp";
}

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

function publishComment(userId, blogId) {
    if(userId === ""){
        window.location.href = "/main_jsp/LOGIN.jsp";
    }else{
        var reg1 = /^\s*$/;
        var reg2 = /^[ ]{1,50}$/;
        var reg3 = /\uD83C[\uDF00-\uDFFF]|\uD83D[\uDC00-\uDE4F]/g;
        var reg4 = /^.{1,50}$/;
        comment = $("#yes_comment_textarea").val()
        if(reg1.test(comment) || reg2.test(comment)){
            alert("内容不能为空");
        }else{
            if (reg3.test(comment)){
                alert("使用了非法字符");
            }else{
                if(reg4.test(comment)){
                    $("#yes_comment_textarea").val("")
                    $.post({
                        url : "../PublishComment",
                        data : {
                            userId : userId,
                            content : comment,
                            blogId : blogId,
                        },
                        success : function () {
                            $("#past_comment_div").load(location.href + " #past_comment_div")
                        }
                    })
                }else{
                    alert("限制长度为 50 当前长度为 " + comment.length)
                }
            }
        }
    }
}

function deleteComment(commentIndex) {
    if(confirm("确认删除？")){
        $.post({
            url : "../DeleteComment",
            data : {
                commentIndex : commentIndex
            },
            success : function () {
                $("#past_comment_div").load(location.href + " #past_comment_div")
            }
        })
    }
}

function requestComment(commentId, commentUserId, userId, commentIndex) {
    if(userId === ""){
        window.location.href = "/main_jsp/LOGIN.jsp";
    }else{
        var theComment_content = document.getElementById("theComment_content" + commentIndex);
        if(theComment_content.childElementCount <= 2){
            var comment_request_div = document.createElement("div");
            comment_request_div.className = "row comment_request_div";
            // 回复输入框
            var comment_request_textarea = document.createElement("textarea");
            comment_request_textarea.className = "comment_request_textarea";
            // 回复按钮
            var comment_request_btn = document.createElement("button");
            comment_request_btn.className = "btn btn-primary comment_request_btn";
            comment_request_btn.innerText = "回复";
            //取消按钮
            var comment_cancel_btn = document.createElement("button");
            comment_cancel_btn.className = "btn btn-default comment_cancel_btn";
            comment_cancel_btn.innerText = "取消";
            // 将字节点添加进去
            comment_request_div.appendChild(comment_request_textarea);
            comment_request_div.appendChild(comment_request_btn);
            comment_request_div.appendChild(comment_cancel_btn);
            theComment_content.appendChild(comment_request_div);
            // 确认回复
            comment_request_btn.onclick = function () {
                var reg1 = /^\s*$/;
                var reg2 = /^[ ]{1,50}$/;
                var reg3 = /\uD83C[\uDF00-\uDFFF]|\uD83D[\uDC00-\uDE4F]/g;
                var reg4 = /^.{1,50}$/;
                var reqContent = comment_request_textarea.value;
                if(reg1.test(reqContent) || reg2.test(reqContent)){
                    alert("内容不能为空");
                }else{
                    if (reg3.test(reqContent)){
                        alert("使用了非法字符");
                    }else{
                        if(reg4.test(reqContent)){
                            $.post({
                                url : "../PublishRequest",
                                data : {
                                    commentId : commentId,
                                    content : reqContent,
                                    commentUserId : commentUserId,
                                    userId : userId,
                                    commentIndex : commentIndex,
                                },
                                success : function () {
                                    $("#past_comment_div").load(location.href + " #past_comment_div")
                                }
                            })
                        }else{
                            alert("限制长度为 50 当前长度为 " + reqContent.length  + " 或有非法字符")
                        }
                    }
                }
            }
            // 取消回复
            comment_cancel_btn.onclick = function () {
                this.parentNode.remove();
            }
        }
    }
}

function requestRequest(commentId, commentUserId, userId, commentIndex, requestIndex) {
    if(userId === ""){
        window.location.href = "/main_jsp/LOGIN.jsp";
    }else{
        var request = document.getElementById("request" + commentIndex + requestIndex);
        if(request.childElementCount <= 6){
            var comment_request_div = document.createElement("div");
            comment_request_div.className = "row comment_request_div";
            // 回复输入框
            var comment_request_textarea = document.createElement("textarea");
            comment_request_textarea.className = "comment_request_textarea";
            // 回复按钮
            var comment_request_btn = document.createElement("button");
            comment_request_btn.className = "btn btn-primary comment_request_btn";
            comment_request_btn.innerText = "回复";
            //取消按钮
            var comment_cancel_btn = document.createElement("button");
            comment_cancel_btn.className = "btn btn-default comment_cancel_btn";
            comment_cancel_btn.innerText = "取消";
            // 将字节点添加进去
            comment_request_div.appendChild(comment_request_textarea);
            comment_request_div.appendChild(comment_request_btn);
            comment_request_div.appendChild(comment_cancel_btn);
            request.appendChild(comment_request_div);
            // 确认回复
            comment_request_btn.onclick = function () {
                var reg1 = /^\s*$/;
                var reg2 = /^[ ]{1,50}$/;
                var reg3 = /\uD83C[\uDF00-\uDFFF]|\uD83D[\uDC00-\uDE4F]/g;
                var reg4 = /^.{1,50}$/;
                var reqContent = comment_request_textarea.value;
                if(reg1.test(reqContent) || reg2.test(reqContent)){
                    alert("内容不能为空");
                }else{
                    if (reg3.test(reqContent)){
                        alert("使用了非法字符");
                    }else{
                        if(reg4.test(reqContent)){
                            $.post({
                                url : "../PublishRequest",
                                data : {
                                    commentId : commentId,
                                    content : reqContent,
                                    commentUserId : commentUserId,
                                    userId : userId,
                                    commentIndex : commentIndex,
                                },
                                success : function () {
                                    $("#past_comment_div").load(location.href + " #past_comment_div")
                                }
                            })
                        }else{
                            alert("限制长度为 50 当前长度为 " + reqContent.length + " 或有非法字符")
                        }
                    }
                }
            }
            // 取消回复
            comment_cancel_btn.onclick = function () {
                this.parentNode.remove();
            }
        }
    }
}

function deleteRequest(commentIndex, requestIndex) {
    if(confirm("确认删除？")){
        $.post({
            url : "../DeleteRequest",
            data : {
                commentIndex : commentIndex,
                requestIndex: requestIndex
            },
            success : function () {
                $("#past_comment_div").load(location.href + " #past_comment_div")
            }
        })
    }
}

function searchLabels(labelName) {
    $.post({
        url: "../CheckSearch",
        data:{
            isAuthor : false,
            start : 0,
            sortId: 1,
            searchContent : labelName
        },
        success: function () {
            window.location.href = "/main_jsp/Search.jsp"
        }
    })
}

function checkColumns(columnName, authorId, userId) {
    if(authorId === userId){
        $.post({
            url: "../CheckUserBlogs",
            data:{
                start : 0,
                flag : 4,
                columnName : columnName
            },
            success: function () {
                window.location.href="/user_jsp/USER_blogs.jsp"
            }
        })
    }else{
        $.post({
            url: "../CheckOtherUserBlogs",
            data:{
                start : 0,
                flag : 4,
                columnName : columnName
            },
            success: function () {
                window.location.href="/others_jsp/other_user_blogs.jsp"
            }
        })
    }
}