function downTheCollect(index) {
    var res = confirm("确认取消收藏?");
    if(res){
        $.ajax({
            url:"../CollectTheBlog",
            type:"POST",
            data:({
                index: index,
                isCollect : 2
            }),
            success:function () {
                window.location.reload()
                alert("取消收藏成功!");
            }
        })
    }
}

function checkTheCollect(index){
    window.location.href = "/CheckTheBlog?index=" + index + "&&flag=3";
}