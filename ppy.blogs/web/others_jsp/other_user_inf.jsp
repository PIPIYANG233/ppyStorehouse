<%@ page import="domain.User" %>
<%@ page import="service.tools.GetHeadPath" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2020/9/30
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <script src="../js/jquery-3.2.1.min.js"></script>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>用户个人中心</title>

        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="../js/jquery-3.2.1.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="../js/bootstrap.min.js"></script>
        <!--[endif]-->

        <script src="../js/user/User.js"></script>.
        <script src="../js/others/other_user_inf.js"></script>
        <link href="../css/user/User.css" rel="stylesheet">
        <link href="../css/others/other_user_inf.css" rel="stylesheet">
    </head>

    <body background="../img/ARC%20X%20Windows%2010%20Theme简约4k风景壁纸3840x2160_彼岸图网.jpg">
        <!-- 顶部 -->
        <nav class="navbar navbar-default" style="background: white; margin-top: -1.15%">
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
                        <li><a href="/ToCreateSpace">创作中心</a></li>
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
                        <li><a href="/main_jsp/LOGIN.jsp" id="login">登录</a></li>
                    </ul>
                </c:if>
                <c:if test="${not empty user}">
                    <ul class="nav navbar-nav navbar-right">
                        <img src=${user.head_img} alt="" class="img-circle" id = "right_head_img" onclick="javascrtpt:window.location.href='/user_jsp/USER_inf.jsp'">
                    </ul>
                </c:if>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
        <!-- 展开按钮 -->
        <button type="button" class="btn btn-primary" id="show_btn" onclick="show_()">》</button>
        <!-- 左边面板 -->
        <div class="row left">
            <div class="name row">
                <span id="name_span">@${author.user_name}</span>
            </div>

            <div class="list-group">
                <a href="/CheckMyself?flag=0" class="list-group-item" id="author_inf">个人资料</a>
                <a href="/CheckOtherUserBlogs?start=0&&flag=1" class="list-group-item" id="author_blogs">TA的博客</a>
            </div>

            <div class="row home_div">
                <button type="button" class="btn btn-danger" style="margin-top: 10px;width: 45%"
                        onclick="javascrtpt:window.location.href='../main_jsp/HOME.jsp'">首页
                </button>
            </div>
            <div class="row create_div">
                <button type="button" class="btn btn-info" style="margin-top: 10px;width: 60%"
                    onclick="javascrtpt:window.location.href='/CheckMyself?flag=1'">返回
                </button>
            </div>
            <!-- 注销登录 -->
            <div class="row">
                <button type="button" class="btn btn-primary" onclick="javascrtpt:window.location.href='../main_jsp/LOGIN.jsp'" style="margin-top: 10px">
                    注销登录
                </button>
            </div>

            <!-- 收起按钮 -->
            <div class="row hide_div">
                <button type="button" class="btn btn-success" id="hide_btn" style="margin-top: 10px" onclick="hide_()">《
                </button>
            </div>
        </div>
        <%-- 主体部 --%>
        <div class="main row">
            <%-- 主体部 --%>
            <div class="main_center row">
                <%-- 上部 --%>
                <div class="main_center_up row">
                    <img src=${author.head_img} alt="" class="img-circle" id="head_img">
                </div>
                <%-- 下部 --%>
                <div class="main_center_down row">
                    <%-- 左边 --%>
                    <div class="main_center_down_left row">
                        <!-- 昵称 -->
                        <h3><span class="label label-info">昵称</span></h3>
                        <p>${author.user_name}</p>
                        <!-- 姓名 -->
                        <h3><span class="label label-info">姓名</span></h3>
                        <p>${author.true_name}</p>
                        <!-- 邮箱 -->
                        <h3><span class="label label-info">邮箱</span></h3>
                        <p>${author.email}</p>
                        <!-- 生日 -->
                        <h3><span class="label label-info">生日</span></h3>
                        <p>${author.birthday}</p>
                        <!-- 性别 -->
                        <c:if test="${author.sex == 1}">
                            <h3><span class="label label-info">性别</span></h3>
                            <p>男</p>
                        </c:if>
                        <c:if test="${author.sex == 0}">
                            <h3><span class="label label-info">性别</span></h3>
                            <p>女</p>
                        </c:if>
                    </div>
                    <%-- 右边 --%>
                    <div class="main_center_down_right row">
                        <!-- 访客量 -->
                        <div>
                            <span class="num">
                                <img src="../img/articleReadEyes.png" class="data_img">${author.scan_num}
                            </span>
                        </div>
                        <!-- 点赞量 -->
                        <div>
                            <span class="num">
                                <img src="../img/tobarThumbUpactive.png" class="data_img">${author.up_num}
                            </span>
                        </div>
                        <!-- 收藏量 -->
                        <div>
                            <span class="num">
                                <img src="../img/取消收藏.png" class="data_img">${author.collected_num}
                            </span>
                        </div>

                        <div>
                            <a href="/CheckOtherUserBlogs?start=0&&flag=1">
                                <span id="num">
                                    TA的博客数
                                    ${author.blogs_num}
                                </span>
                            </a>
                        </div>

                        <div>
                           <span class="num">
                                    TA的粉丝数
                                    ${author.fans_num}
                           </span>
                        </div>

                        <div>
                            <span class="num">
                                    TA的关注数
                                    ${author.follow_num}
                                </span>
                        </div>

                        <div>
                            <span class="num">
                                TA的收藏量
                                ${author.collect_num}
                            </span>
                        </div>
                        <!-- 简介 -->
                        <h3><span class="label label-info">简介</span></h3>
                        <div class="row main_center_down_right_down">
                            ${author.introduce}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>