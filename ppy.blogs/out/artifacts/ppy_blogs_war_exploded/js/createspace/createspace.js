
var testEditor;
$(function() {
    testEditor = editormd("test-editormd", {
        width   : "90%",
        height  : 640,
        syncScrolling : "single",
        path    : "/editor.md-master/lib/",
        //这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
        saveHTMLToTextarea : true,
        codeFold : true,
        taskList: true,
        tocm: true,
        tex: true,

        flowChart: true,
        sequenceDiagram: true,
    });
});

var num = 0;
window.onload = function () {
    $("#test-editormd").hide("fast", "swing");
    $("#test-editormd").show("slow", "swing");

    var reg1 = /^\s*$/;
    var reg2 = /^[ ]{1,30}$/;
    var reg3 = /\uD83C[\uDF00-\uDFFF]|\uD83D[\uDC00-\uDE4F]/g;
    var reg4 = /^.{1,30}$/;
    var blog_title = document.getElementById("blog_title");
    var markdown = document.getElementById("test-editormd-markdown");
    document.getElementById("publish_form").onsubmit = function () {
        if(reg1.test(blog_title.value) || reg2.test(blog_title.value)){
            alert("标题不能为空");
            return false;
        }
        if (reg3.test(blog_title.value)){
            alert("标题禁止使用emoji");
            return false;
        }

        if(reg1.test(markdown.value) || reg2.test(markdown.value)){
            alert("内容不能为空");
            return false;
        }
        if (reg3.test(markdown.value)){
            alert("内容禁止使用emoji");
            return false;
        }

        if(reg4.test(blog_title.value) && reg4.test(markdown.value)){
            return true;
        }
    }

    document.getElementById("publish").onclick = function () {
        document.getElementById("publish_form").action = "/CheckSaveBlog?status=2";
    }

    document.getElementById("save").onclick = function () {
        document.getElementById("publish_form").action = "/CheckSaveBlog?status=1";
    }

    document.getElementById("add_labels").onclick = function () {
        var reg1 = /^\s*$/;
        var reg2 = /^[ ]{1,8}$/;
        var reg3 = /\uD83C[\uDF00-\uDFFF]|\uD83D[\uDC00-\uDE4F]/g;
        var reg4 = /^.{1,8}$/;
        var add_label_input = document.getElementById("add_label_input");

        if(reg1.test(add_label_input.value) || reg2.test(add_label_input.value)){

            alert("标签不能为空");
        }else{
            if (reg3.test(add_label_input.value)){
                alert("使用了非法字符");
            }else{
                if(reg4.test(add_label_input.value)){
                    var the_blog_label1_show = document.getElementById("the_blog_label1_show")
                    if(the_blog_label1_show.childElementCount >= 5){
                        alert("最多添加5个标签");
                    }else{
                        var labelContent = add_label_input.value;//输入框内容

                        var flag = true;
                        var labels = document.getElementsByClassName("labels_span")
                        var labelNum = labels.length
                        for(var i = 0; i < labelNum; i++){
                            if(labels[i].firstChild.nodeValue === labelContent){
                                alert("请勿添加重复标签")
                                flag = false;
                                break;
                            }
                        }
                        // 假如没重复
                        if(flag){
                            var labels_div = document.createElement("div");
                            labels_div.className = "the_blog_label1_show_div";

                            var labels_span = document.createElement("span");
                            labels_span.className = "labels_span";

                            var labels_text = document.createTextNode(labelContent);
                            add_label_input.value="";

                            var labels_span_a = document.createElement("a");
                            labels_span_a.setAttribute("href", "javascript:void(0);");
                            labels_span_a.setAttribute("onclick", "deleteAttributes(this)");

                            var labels_span_a_text = document.createTextNode("×");

                            labels_span_a.appendChild(labels_span_a_text);
                            labels_span.appendChild(labels_text);
                            labels_span.appendChild(labels_span_a);
                            labels_div.appendChild(labels_span);
                            the_blog_label1_show.appendChild(labels_div);

                            var hideInput = document.createElement("input");
                            hideInput.setAttribute("type", "text");
                            hideInput.setAttribute("name", "blogLabels");
                            hideInput.setAttribute("value", labelContent);
                            hideInput.setAttribute("style", "display:none");
                            labels_div.appendChild(hideInput);
                        }
                    }
                }else{
                    alert("标签长度不能超过8");
                }
            }
        }
    }

    document.getElementById("add_columns").onclick = function () {
        var the_blog_label2_show = document.getElementById("the_blog_label2_show")
        if(the_blog_label2_show.childElementCount >= 3){
            alert("最多添加3个专栏");
        }else{
            var columnContent = $("#add_column_selected option:selected").val();//选择框内容
            if(columnContent != undefined){
                var flag = true;
                var columns = document.getElementsByClassName("columns_span")
                var columnNum = columns.length
                for(var i = 0; i < columnNum; i++){
                    if(columns[i].firstChild.nodeValue === columnContent){
                        alert("请勿添加重复专栏")
                        flag = false;
                        break;
                    }
                }
                // 假如没重复
                if(flag){
                    var columns_div = document.createElement("div");
                    columns_div.className = "the_blog_label2_show_div";

                    var columns_span = document.createElement("span");
                    columns_span.className = "columns_span";

                    var columns_text = document.createTextNode(columnContent);

                    var columns_span_a = document.createElement("a");
                    columns_span_a.setAttribute("href", "javascript:void(0);");
                    columns_span_a.setAttribute("onclick", "deleteAttributes(this)");

                    var columns_span_a_text = document.createTextNode("×");

                    columns_span_a.appendChild(columns_span_a_text);
                    columns_span.appendChild(columns_text);
                    columns_span.appendChild(columns_span_a);
                    columns_div.appendChild(columns_span);
                    the_blog_label2_show.appendChild(columns_div);

                    var hideInput = document.createElement("input");
                    hideInput.setAttribute("type", "text");
                    hideInput.setAttribute("name", "blogColumns");
                    hideInput.setAttribute("value", columnContent);
                    hideInput.setAttribute("style", "display:none");
                    columns_div.appendChild(hideInput);
                }
            }else{
                alert("无分类专栏")
            }
        }
    }
}

function deleteAttributes(attributes_span_a){
    var div1 = attributes_span_a.parentNode.parentNode.parentNode;
    var div2 = attributes_span_a.parentNode.parentNode;
    div1.removeChild(div2);
}