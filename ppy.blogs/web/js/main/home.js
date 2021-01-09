$(function () {
    $(".left_label").slideDown("slow", "swing");
    $(".blog_right_recommend").slideDown("slow", "swing");
    $(".blog_center_recommend_blogs").slideDown("slow", "swing");

    $(".left_labels").hover(function () {
        $(this).addClass("changeBackground");
    },function () {
        $(this).removeClass("changeBackground");
    })
    $("#label" + $('#left_label_color').val()).css("color", "red");

    $(".sorts").hover(function () {
        $(this).addClass("changeBackground");
    },function () {
        $(this).removeClass("changeBackground");
    })
    $("#sort" + $('#sorts_color').val()).css("color", "red");
})

function changeLabel(labelIndex, sortIndex) {
    window.location.href="/CheckHome?labelId=" + labelIndex + "&&sortId=" + sortIndex;
}

function changeSort(labelIndex, sortIndex) {
    window.location.href="/CheckHome?labelId=" + labelIndex + "&&sortId=" + sortIndex;
}

function readTheBlog(index, flag) {
    window.location.href = "/CheckHomeBlog?index=" + index + "&&flag=" + flag;
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