<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2020/9/30
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>创作中心</title>

        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="../js/jquery-3.2.1.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="../js/bootstrap.min.js"></script>
        <!--[endif]-->
        <link href="../css/createspace/createspace.css" rel="stylesheet">
        <script src="../js/createspace/createspace.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/editor.md-master/css/editormd.css" />
        <script src="${pageContext.request.contextPath}/editor.md-master/examples/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/editor.md-master/editormd.js"></script>

    </head>

    <body style="width: 98%; margin: auto" background="/img/森林%20夜晚%20¹%20月亮%20唯美意境4k壁纸3840x2160_彼岸图网.jpg">
        <!-- 顶部 -->
        <nav class="navbar navbar-default" style="background: white">
            <div class="container-fluid" id = "top">
                <!-- 首页 -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/CheckHome?labelId=0&&sortId=1">首页</a>
                </div>

                <!-- 创作中心 搜索框 搜索按钮 登陆注册按钮 -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <!-- 为管理员 -->
                    <c:if test="${user.rank >= 1}">
                        <ul class="nav navbar-nav" style="width: 13%">
                            <li><a href="/ToCreateSpace?userId=${user.id}">创作中心</a></li>
                        </ul>
                        <ul class="nav navbar-nav" style="width: 9.5%" >
                            <li>
                                <a href="/CheckManager?sortId=1&&start=0&&rank=${user.rank}">管理员</a>
                            </li>
                        </ul>
                    </c:if>
                    <!-- 不为管理员 -->
                    <c:if test="${user.rank == 0 || empty user.rank}">
                        <ul class="nav navbar-nav" style="width: 22.5%">
                            <li><a href="/ToCreateSpace?userId=${user.id}">创作中心</a></li>
                        </ul>
                    </c:if>
                    <form class="navbar-form navbar-left" style="width: 60%" method="post" action="/CheckSearch?idAuthor=false&&start=0&&sortId=1" onsubmit="return regSearch();">
                        <div class="form-group" style="width: 75%">
                            <input type="text" class="form-control" id = "searchContent" name="searchContent" placeholder="请开始搜索" value="${search.searchContent}" style="width: 100%">
                        </div>
                        <button type="submit" class="btn btn-default" id = "search">搜索</button>
                    </form>
                    <c:if test="${empty user}">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="LOGIN.jsp" id="login">登录</a></li>
                        </ul>
                    </c:if>
                    <c:if test="${not empty user}">
                        <ul class="nav navbar-nav navbar-right">
                            <img src=${user.head_img} alt="" class="img-circle" id = "right_head_img" onclick="javascrtpt:window.location.href='/CheckMyself?flag=1'">
                        </ul>
                    </c:if>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <form action="" method="post" id = "publish_form" enctype=multipart/form-data">
            <!-- 标题输入框 -->
            <div class="row" id = "title_div">
                <input type="text" class="form-control" placeholder="请输入标题" name="blog_title" id = "blog_title" value="${checkBlog.blog.blog_title}">
                <!-- 回到首页 -->
                <div class="row">
                    <button type="button" class="btn btn-info" onclick="javascrtpt:window.location.href='/main_jsp/HOME.jsp'" id="back_home">返回</button>
                </div>
            </div>

            <!-- 编辑器 -->
            <div id="test-editormd">
                <textarea  style="display: none" class="editormd-markdown-textarea" id="test-editormd-markdown" name="test-editormd-markdown">${checkBlog.markdown}</textarea>
                <textarea  style="display: none" class="editormd-html-textarea" id="test-editormd-htmlCode" name="test-editormd-htmlCode"></textarea>
            </div>

            <div class="row">
                <!-- 发布/保存按钮 -->
                <div class="row" id = "button_div">
                    <button type="button" class="btn btn-success" id = "publish" data-toggle="modal" data-target="#myModal">发布文章</button>
                    <button type="submit" class="btn btn-default" id = "save">保存草稿</button>
                </div>
                <!-- 隐藏数据 -->
                <div class="row" style="display: none">
                    <input type="text" style="display: none" id="blog_author_id" name="blog_author_id" value="${user.id}">`
                </div>
                <!-- 模态框 -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">发布文章</h4>
                            </div>
                            <!-- 确认提交表单 -->
                            <div class="modal-body">
                                <!-- 文章标签 -->
                                <div class="row the_blog_label1">
                                    <div class="row the_blog_label1_title">
                                        文章标签
                                    </div>
                                    <!-- 博客标签手动添加 -->
                                    <div class="row the_blog_label1_check" id="the_blog_label1_check">
                                        <button type="button" class="btn btn-default add_labels" id="add_labels">＋添加标签</button>
                                        <input type="text" class="form-control add_label_input" id="add_label_input" placeholder="添加你的标签">
                                    </div>
                                    <!-- 显示添加的标签 -->
                                    <div class="row the_blog_label1_show" id="the_blog_label1_show">
                                        <c:if test="${not empty checkBlog.blog.labels}">
                                            <c:forEach items="${checkBlog.blog.labels}" var="label">
                                                <div class="row the_blog_label1_show_div">
                                                        <span class="row labels_span">${label}<a href="javascript:void(0);" onclick="deleteAttributes(this);">×</a></span>
                                                    <input style="display: none" type="text" name="blogLabels" value="${label}">
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </div>
                                <!-- 分类专栏 -->
                                <div class="row the_blog_label2">
                                    <div class="row the_blog_label2_title">
                                        分类专栏
                                    </div>
                                    <!-- 博客分类专栏选项 -->
                                    <div class="row the_blog_label2_check" id="the_blog_label2_check">
                                        <button type="button" class="btn btn-default add_columns" id="add_columns">＋分类专栏</button>
                                        <select class="form-control add_column_selected" id="add_column_selected">
                                            <c:forEach items="${checkBlog.columnNames}" var="columnName">
                                                <option value="${columnName}">${columnName}</option>
                                                <span>${columnName}</span>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <!-- 博客已在的分类专栏 -->
                                    <div class="row the_blog_label2_show" id="the_blog_label2_show">
                                        <c:if test="${not empty checkBlog.blog.columns}">
                                            <c:forEach items="${checkBlog.blog.columns}" var="column">
                                                <div class="row the_blog_label2_show_div">
                                                        <span class="row columns_span">${column}<a href="javascript:void(0);" onclick="deleteAttributes(this);">×</a></span>
                                                    <input style="display: none" type="text" name="blogColumns" value="${column}">
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </div>
                                <!-- 文章类型 -->
                                <div class="row the_blog_label3">
                                    <div class="row the_blog_label3_title">
                                        文章类型
                                    </div>
                                    <!-- 博客类型选项 -->
                                    <!-- 为首次编辑博客 -->
                                    <c:if test="${empty checkBlog.blog.type}">
                                        <div class = "the_blog_label3_check">
                                            <label class="radio-inline">
                                                <input type="radio" name="blog_type" value="1" checked> 原创
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="blog_type" id="reprint" value="2"> 转载
                                            </label>
                                        </div>
                                    </c:if>
                                    <!-- 为修改博客 -->
                                    <c:if test="${not empty checkBlog.blog.type}">
                                        <c:if test="${checkBlog.blog.type == 1}">
                                            <div class = "the_blog_label3_check">
                                                <label class="radio-inline">
                                                    <input type="radio" name="blog_type" value="1" checked> 原创
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="blog_type" value="2"> 转载
                                                </label>
                                            </div>
                                        </c:if>
                                        <c:if test="${checkBlog.blog.type == 2}">
                                            <div class = "the_blog_label3_check">
                                                <label class="radio-inline">
                                                    <input type="radio" name="blog_type" value="1"> 原创
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="blog_type" value="2" checked> 转载
                                                </label>
                                            </div>
                                        </c:if>
                                    </c:if>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="submit" class="btn btn-primary">确定</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>