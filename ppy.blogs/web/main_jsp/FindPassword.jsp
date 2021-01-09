<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2020/9/28
  Time: 17:05
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
        <title>找回密码</title>

        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="../js/jquery-3.2.1.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="../js/bootstrap.min.js"></script>
        <!--[endif]-->
        <link href="../css/main/findPassword.css" rel="stylesheet">
        <script src="../js/main/findPassword.js"></script>

        <%
            request.getSession().removeAttribute("checkCode");
            String error = (String)request.getSession().getAttribute("error");
            if(null != error){
        %>
        <script>
            alert("<%=error%>")
        </script>
        <%
                request.getSession().removeAttribute("error");
            }
        %>
    </head>
    <body background="/img/lol英雄联盟%20take%20over%202020世界大赛4k游戏壁纸_彼岸图网.jpg">
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
    <div class="main row">
        <span class="tit" style="font-size: 38px;">找回密码</span>
        <form id="register" method="post" action="/FindPassword">
            <!-- 中间块 -->
            <div class="center row">
                <!-- email -->
                <div class="form-group has-success has-feedback row" style="margin-top: 20px">
                    <label for="email">邮箱</label>
                    <input type="email" class="form-control" id="email" name="email"
                           aria-describedby="inputSuccess2Status" placeholder="请输入邮箱">
                    <span></span>
                </div>

                <!-- 密码框 -->
                <div class="form-group has-success has-feedback row" style="margin-top: 20px">
                    <label for="pwd">密码</label>
                    <input type="password" class="form-control" id="pwd" name="pwd" aria-describedby="inputSuccess2Status"
                           placeholder="请输入密码">
                    <span><img src="" id="pwd_span"></span>
                </div>

                <!-- 确认密码框 -->
                <div class="form-group has-success has-feedback row" style="margin-top: 20px">
                    <label for="pwd_true">确认密码</label>
                    <input type="password" class="form-control" id="pwd_true" name="pwd_true"
                           aria-describedby="inputSuccess2Status" placeholder="请确认密码">
                    <span><img src="" id="pwd_true_span"></span>
                </div>

                <!-- 验证码框 -->
                <div id="验证码_div" class="form-group has-success has-feedback row" style="margin-top: 20px">
                    <label for="验证码">验证码</label>
                    <input type="text" class="form-control" id="验证码" name="验证码" aria-describedby="inputSuccess2Status">
                    <button type="button" onclick="getCheckCode()" class="btn btn-link" style="height: 50px; margin-left: 10px">获取验证码</button>
                    <input type="text" value="1" id="CheckCode" style="display: none">
                </div>

                <!-- 回到登录页面 -->
                <div class="row">
                    <button type="button" class="btn btn-primary" onclick="javascrtpt:window.location.href='../main_jsp/LOGIN.jsp'">
                        登录
                    </button>
                </div>

                <!-- 注册按钮 -->
                <div class="row" id="login_row">
                    <input type="submit" class="btn btn-warning" id="register_btn" value="更新密码">
                </div>

                <!-- 回到注册页面 -->
                <div class="row">
                    <button type="button" class="btn btn-danger" onclick="javascrtpt:window.location.href='../main_jsp/REGISTER.jsp'">注册</button>
                </div>
            </div>
        </form>
    </div>
    </body>
</html>
