
function lastPage(now){
    $.post({
        url:"../CheckOtherUserBlogs",
        data : {
            flag : 3,
            start : (now - 1) * 3
        },
        success : function () {
            window.location.reload();
        }
    })
}

function goToThePage(pageIndex){
    $.post({
        url:"../CheckOtherUserBlogs",
        data : {
            flag : 3,
            start : pageIndex * 3
        },
        success : function () {
            window.location.reload();
        }
    })
}

function nextPage(now){
    $.post({
        url:"../CheckOtherUserBlogs",
        data : {
            flag : 3,
            start : (now + 1) * 3
        },
        success : function () {
            window.location.reload();
        }
    })
}

function searchMyBlogs() {
    year = $("#year_select option:selected").val();
    if(null == year){
        year = 0;
    }
    month = $("#month_select option:selected").val();
    if(null == month){
        month = 0;
        alert(month)
    }
    column = "" + $("#column_select option:selected").val();
    type = $("#type_select option:selected").val();
    mainContent = $(".main_content_input").val();

    var reg1 = /^\s*$/;
    var reg2 = /^[ ]{1,8}$/;
    var reg3 = /\uD83C[\uDF00-\uDFFF]|\uD83D[\uDC00-\uDE4F]/g;
    var reg4 = /^.{0,8}$/;
    if(reg1.test(mainContent) || reg2.test(mainContent)){
        mainContent="";
    }
    if(reg3.test(mainContent)){
        alert("禁止使用非法字符")
    }else{
        if(!reg4.test(mainContent)){
            alert("长度限制为30")
        }else{
            $.post({
                url:"../CheckOtherUserBlogs",
                data : {
                    flag : 2,
                    year : year,
                    month : month,
                    column : column,
                    type : type,
                    mainContent: mainContent,
                    start : 0
                },
                success : function () {
                    window.location.reload();
                }
            })
        }
    }
}