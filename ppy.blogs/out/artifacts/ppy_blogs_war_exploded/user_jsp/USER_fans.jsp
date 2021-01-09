
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

        <script src="../js/user/User.js"></script>
        <script src="../js/user/user_fans.js"></script>
        <link href="../css/user/User.css" rel="stylesheet">
        <link href="../css/user/user_fans.css" rel="stylesheet">
    </head>

    <body background="../img/ARC%20X%20Windows%2010%20Theme简约4k风景壁纸3840x2160_彼岸图网.jpg">
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
        <!-- 展开按钮 -->
        <button type="button" class="btn btn-primary" id="show_btn" onclick="show_()">》</button>
        <!-- 左边面板 -->
        <div class="row left" style="display: none">
            <div class="name row">
                <span id="name_span">@${user.user_name}</span>
            </div>

            <div class="list-group">
                <a href="/CheckMyself?flag=1" class="list-group-item" id="user_inf">个人资料</a>
                <a href="/CheckUserBlogs?start=0&&flag=1" class="list-group-item" id="user_blogs">我的博客</a>
                <a href="/CheckUserFollow?start=0" class="list-group-item" id="user_att">我的关注</a>
                <a href="/CheckUserFans?start=0" class="list-group-item" id="user_fans">我的粉丝</a>
                <a href="/CheckUserCollect?start=0" class="list-group-item" id="user_collect">我的收藏</a>
                <a href="/CheckUserColumns?userId=${user.id}&&start=0" class="list-group-item" id="user_columns">我的分类专栏</a>
            </div>
            <!-- 注销登录 -->
            <div class="row">
                <button type="button" class="btn btn-primary" onclick="javascrtpt:window.location.href='../main_jsp/LOGIN.jsp'">
                    注销登录
                </button>
            </div>

            <div class="row hide_div">
                <button type="button" class="btn btn-success" id="hide_btn" style="margin-top: 10px" onclick="hide_()">《
                </button>
            </div>
        </div>

        <div class="main row">
            <%-- 主体部 --%>
            <div class="main_center row">
                <!-- 标题 -->
                <div class="row fans_title">
                    我的粉丝
                </div>
                <!-- 粉丝栏 -->
                <div class="row show_fans">
                    <c:if test="${not empty fanPage.fans}">
                        <c:forEach items="${fanPage.fans}" var="fan" varStatus="status">
                            <!-- 具体粉丝 -->
                            <div class="row fans">
                                <!-- 头像 -->
                                <div class="row fan_head">
                                    <img src="${fan.head_img}" id="fan_head_img" class="img-circle" onclick="checkTheFan(${status.index})">
                                </div>
                                <!-- 粉丝数据 -->
                                <div class="row fan_data">
                                    <!-- 粉丝名字 -->
                                    <div class="row fan_name_div">
                                        <div class="row fan_name">
                                            ${fan.user_name}
                                        </div>
                                    </div>
                                    <!-- 粉丝是否被关注 -->
                                    <div class="row fan_follow">
                                        <!-- 未被关注 -->
                                        <c:if test="${!fan.follow}">
                                            <button type="button" class="btn btn-danger" id="fan_follow_button" onclick="follow(${status.index}, 0)">关注博主</button>
                                        </c:if>
                                        <!-- 被关注 -->
                                        <c:if test="${fan.follow}">
                                            <button type="button" class="btn btn-default" id="fan_follow_button" onclick="follow(${status.index}, 1)">取消关注</button>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <hr>
                        </c:forEach>
                    </c:if>
                </div>
                <!-- 分页 -->
                <div class="row page">
                    <c:if test="${fanPage.pages > 0}">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <!-- 为首页 -->
                                <c:if test="${fanPage.start / 4 == 0}">
                                    <li class="disabled">
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <!-- 不为首页 -->
                                <c:if test="${fanPage.start / 4 != 0}">
                                    <li>
                                        <a href="/CheckUserFans?start=${(fanPage.now - 1) * 4}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <!-- 获取页码 -->
                                <!-- 前半部分有多余页 -->
                                <c:if test="${fanPage.now - 2 > 1}">
                                    <!-- 为选中页 -->
                                    <c:if test="${0 == (fanPage.now)}">
                                        <li class="active">
                                            <span>${1} <span class="sr-only">(current)</span></span>
                                        </li>
                                    </c:if>
                                    <!-- 不为选中页 -->
                                    <c:if test="${0 != (fanPage.now)}">
                                        <li><a href="/CheckUserFans?start=0">${1}</a></li>
                                    </c:if>
                                    <li><a>...</a></li>
                                    <c:forEach begin="${fanPage.now - 2}" end="${fanPage.now}" var="i" step="1">
                                        <!-- 为选中页 -->
                                        <c:if test="${(i) == (fanPage.now)}">
                                            <li class="active">
                                                <span>${i+1} <span class="sr-only">(current)</span></span>
                                            </li>
                                        </c:if>
                                        <!-- 不为选中页 -->
                                        <c:if test="${(i) != (fanPage.now)}">
                                            <li><a href="/CheckUserFans?start=${i * 4}">${i+1}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <!-- 前半部分无多余页 -->
                                <c:if test="${fanPage.now - 2 <= 1}">
                                    <c:forEach begin="0" end="${fanPage.now}" var="i" step="1">
                                        <!-- 为选中页 -->
                                        <c:if test="${(i) == (fanPage.now)}">
                                            <li class="active">
                                                <span>${i+1} <span class="sr-only">(current)</span></span>
                                            </li>
                                        </c:if>
                                        <!-- 不为选中页 -->
                                        <c:if test="${(i) != (fanPage.now)}">
                                            <li><a href="/CheckUserFans?start=${i * 4}">${i+1}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <!-- 后半部分有多余页 -->
                                <c:if test="${(fanPage.now + 2) < (fanPage.pages - 2)}">
                                    <c:forEach begin="${fanPage.now + 1}" end="${fanPage.now + 2}" var="i" step="1">
                                        <!-- 为选中页 -->
                                        <c:if test="${(i) == (fanPage.now)}">
                                            <li class="active">
                                                <span>${i+1} <span class="sr-only">(current)</span></span>
                                            </li>
                                        </c:if>
                                        <!-- 不为选中页 -->
                                        <c:if test="${(i) != (fanPage.now)}">
                                            <li><a href="/CheckUserFans?start=${i * 4}">${i+1}</a></li>
                                        </c:if>
                                    </c:forEach>
                                    <li><a>...</a></li>
                                    <!-- 为选中页 -->
                                    <c:if test="${fanPage.pages - 1 == (fanPage.now)}">
                                        <li class="active">
                                            <span>${fanPage.pages} <span class="sr-only">(current)</span></span>
                                        </li>
                                    </c:if>
                                    <!-- 不为选中页 -->
                                    <c:if test="${fanPage.pages - 1 != (fanPage.now)}">
                                        <li><a href="/CheckUserFans?start=${(fanPage.pages - 1) * 4}">${fanPage.pages}</a></li>
                                    </c:if>
                                </c:if>
                                <!-- 后半部分无多余页 -->
                                <c:if test="${(fanPage.now + 2) >= (fanPage.pages - 2)}">
                                    <c:forEach begin="${fanPage.now + 1}" end="${fanPage.pages - 1}" var="i" step="1">
                                        <!-- 为选中页 -->
                                        <c:if test="${(i) == (fanPage.now)}">
                                            <li class="active">
                                                <span>${i+1} <span class="sr-only">(current)</span></span>
                                            </li>
                                        </c:if>
                                        <!-- 不为选中页 -->
                                        <c:if test="${(i) != (fanPage.now)}">
                                            <li><a href="/CheckUserFans?start=${i * 4}">${i+1}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <!-- 不为尾页 -->
                                <c:if test="${fanPage.now != fanPage.pages - 1}">
                                    <li>
                                        <a href="/CheckUserFans?start=${(fanPage.now + 1) * 4}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <!-- 为尾页 -->
                                <c:if test="${fanPage.now == fanPage.pages - 1}">
                                    <li class="disabled">
                                        <a href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
