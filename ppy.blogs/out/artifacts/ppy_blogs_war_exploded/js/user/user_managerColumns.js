
function removeBlog(index, blogId, columnId) {
    if(confirm("确认移除?")){
       $.post({
           url : "../RemoveBlogFromColumn",
           data : {
               index : index,
               blogId : blogId,
               columnId : columnId
           },
           success : function () {
                alert("移除成功")
               window.location.reload()
           }
       })
    }
}

function lastPage(now){
    $.post({
        url:"../ManagerColumnBlogs",
        data : {
            flag : false,
            start : (now - 1) * 3
        },
        success : function () {
            window.location.reload();
        }
    })
}

function goToThePage(pageIndex){
    $.post({
        url:"../ManagerColumnBlogs",
        data : {
            flag : false,
            start : pageIndex * 3
        },
        success : function () {
            window.location.reload();
        }
    })
}

function nextPage(now){
    $.post({
        url:"../ManagerColumnBlogs",
        data : {
            flag : false,
            start : (now + 1) * 3
        },
        success : function () {
            window.location.reload();
        }
    })
}