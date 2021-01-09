function downTheFollow(index) {
    var res = confirm("确认取消关注?");
    if(res){
        $.ajax({
            url:"../DownTheFollow",
            type:"POST",
            data:({
                index: index
            }),
            success:function () {
                window.location.reload()
                alert("取关成功!");
            }
        })
    }
}

function checkTheFollow(index){
    window.location.href = "/ToAuthorSpace?index=" + index + "&&flag=1";
}