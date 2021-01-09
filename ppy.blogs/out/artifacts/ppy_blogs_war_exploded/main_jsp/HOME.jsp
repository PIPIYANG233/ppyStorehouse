<%@ page import="domain.Home" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2020/9/28
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>首页</title>

        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="../js/jquery-3.2.1.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="../js/bootstrap.min.js"></script>
        <!--[endif]-->
        <script src="../js/main/home.js"></script>
        <link href="../css/main/home.css" rel="stylesheet">

        <%
            Home home = (Home) session.getAttribute("home");
            if(null == home){
                response.sendRedirect("/CheckHome?labelId=0&&sortId=0");
            }
        %>
    </head>
    <body background="../img/山谷简约设计3440x1440带鱼屏风景壁纸_彼岸图网.jpg">
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
                    <a class="navbar-brand" href="/CheckHome?labelId=0&&sortId=0">首页</a>
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
        <!-- 左侧标签栏 -->
        <input type="text" style="display: none" value="${home.labelId}" id="left_label_color">
        <input type="text" style="display: none" value="${home.sortId}" id="sorts_color">
        <div class="row left_main_label">
            <div class="row left_label">
                <c:if test="${not empty home.labels}">
                    <c:forEach items="${home.labels}" var="label" varStatus="status">
                        <div class="row left_labels" id="label${status.index}" onclick="changeLabel(${status.index}, ${home.sortId})">
                                ${label}
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <!-- 中间推荐栏 -->
        <div class="row blog_center_recommend">
            <!-- 排序分类 -->
            <div class="row blog_center_recommend_blog_sort">
                <!-- 时间优先 -->
                <div class="row sorts" id="sort0" onclick="changeSort(${home.labelId}, 0)">
                    时间优先
                </div>
                <!-- 访客量优先 -->
                <div class="row sorts" id="sort1" onclick="changeSort(${home.labelId}, 1)">
                    访客量优先
                </div>
                <!-- 点赞优先 -->
                <div class="row sorts" id="sort2" onclick="changeSort(${home.labelId}, 2)">
                    点赞优先
                </div>
                <!-- 收藏优先 -->
                <div class="row sorts" id="sort3" onclick="changeSort(${home.labelId}, 3)">
                    收藏优先
                </div>
            </div>
            <!-- 中间推荐博客 -->
            <div class="row blog_center_recommend_blogs">
                <c:if test="${not empty home.centerRecommendBlogs}">
                    <c:forEach items="${home.centerRecommendBlogs}" var="centerRecommendBlog" varStatus="status">
                        <!-- 中间推荐具体博客 -->
                        <div class="row blog_center_recommend_blog">
                            <!-- 博客标题 -->
                            <div class="row centerRecommendBlog_title" onclick="readTheBlog(${status.index}, 1)">
                                ${centerRecommendBlog.blog_title}
                            </div>
                            <!-- 博客相关数据 -->
                            <div class="centerRecommendBlog_data">
                                <!-- 博客类型 -->
                                <!-- 原创 -->
                                <c:if test="${centerRecommendBlog.type == 1}">
                                    <div class="centerRecommendBlog_type centerRecommendBlog_accept_original">
                                        原创
                                    </div>
                                </c:if>
                                <c:if test="${centerRecommendBlog.type == 2}">
                                    <div class="centerRecommendBlog_type centerRecommendBlog_accept_reprint">
                                        转载
                                    </div>
                                </c:if>
                                <!-- 博客保存时间 -->
                                <div class="centerRecommendBlog_time">
                                    ${centerRecommendBlog.blog_pub_date}
                                </div>
                                <!-- 博客访客量 -->
                                <div class="centerRecommendBlog_scan">
                                    <img src="../img/articleReadEyes.png" id="scan_img">
                                     ${centerRecommendBlog.blog_scan_num}
                                </div>
                                <!-- 博客点赞量 -->
                                <div class="centerRecommendBlog_up">
                                    <img src="../img/tobarThumbUpactive.png" id="up_img">
                                    ${centerRecommendBlog.blog_up_num}
                                </div>
                                <!-- 博客收藏量 -->
                                <div class="centerRecommendBlog_collect">
                                    <img src="../img/取消收藏.png" id="collect_img">
                                    ${centerRecommendBlog.blog_collect_num}
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <!-- 右边推荐栏 -->
        <div class="row blog_right_recommend">
            <!-- 今日推荐 -->
            <div class="row today_recommend_title">
                今日推荐
            </div>
            <!-- 今日推荐博客 -->
            <div class="row today_recommend_blogs">
                <!-- 今日推荐具体博客 -->
                <c:if test="${not empty home.rightRecommendBlogs}">
                    <c:forEach items="${home.rightRecommendBlogs}" var="rightRecommendBlog" varStatus="status">
                        <div class="row today_recommend_blog">
                            <!-- 博客标题 -->
                            <div class="row rightRecommendBlog_title" onclick="readTheBlog(${status.index}, 2)">
                                    ${rightRecommendBlog.blog_title}
                            </div>
                            <!-- 博客相关数据 -->
                            <div class="rightRecommendBlog_data">
                                <!-- 博客类型 -->
                                <!-- 原创 -->
                                <c:if test="${rightRecommendBlog.type == 1}">
                                    <div class="rightRecommendBlog_type rightRecommendBlog_accept_original">
                                        原创
                                    </div>
                                </c:if>
                                <c:if test="${rightRecommendBlog.type == 2}">
                                    <div class="rightRecommendBlog_type rightRecommendBlog_accept_reprint">
                                        转载
                                    </div>
                                </c:if>
                                <!-- 博客访客量 -->
                                <div class="rightRecommendBlog_scan">
                                    <img src="../img/articleReadEyes.png" id="rightRecommendBlog_scan_img">
                                        ${rightRecommendBlog.blog_scan_num}
                                </div>
                                <!-- 博客点赞量 -->
                                <div class="rightRecommendBlog_up">
                                    <img src="../img/tobarThumbUpactive.png" id="rightRecommendBlog_up_img">
                                        ${rightRecommendBlog.blog_up_num}
                                </div>
                                <!-- 博客收藏量 -->
                                <div class="rightRecommendBlog_collect">
                                    <img src="../img/取消收藏.png" id="rightRecommendBlog_collect_img">
                                        ${rightRecommendBlog.blog_collect_num}
                                </div>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </body>
</html>