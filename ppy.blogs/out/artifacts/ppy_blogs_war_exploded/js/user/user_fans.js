function follow(index, isFollow) {
    if(isFollow == 0){
        $.ajax({
            url:"../FollowTheFan",
            type:"POST",
            data:({
                isFollow: "1",
                index: index
            }),
            success:function () {
                window.location.reload();
                alert("关注成功!");
            }
        })
    }else{
        $.ajax({
            url:"../FollowTheFan",
            type:"POST",
            data:({
                isFollow: "0",
                index: index
            }),
            success:function () {
                window.location.reload();
                alert("取关成功!");
            }
        })
    }
}

function checkTheFan(index){
    window.location.href = "/ToAuthorSpace?index=" + index + "&&flag=2";
}

