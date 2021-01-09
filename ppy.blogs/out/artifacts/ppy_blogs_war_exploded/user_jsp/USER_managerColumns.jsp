<%@ page contentType="text/html;charset=UTF-8"%>
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
        <script src="../js/user/user_managerColumns.js"></script>
        <link href="../css/user/User.css" rel="stylesheet">
        <link href="../css/user/user_managerColumns.css" rel="stylesheet">

    </head>

    <body background="../img/ARC%20X%20Windows%2010%20Theme简约4k风景壁纸3840x2160_彼岸图网.jpg">
        <nav class="navbar navbar-default" style="background: white;">
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
                <!-- 标题与介绍 -->
                <div class="row column_title_introduce">
                    <!-- 标题 -->
                    ${columnManagerPage.columnName}
                    <!-- 介绍 -->
                    <div class="row column_introduce">${columnManagerPage.columnIntroduce}</div>
                </div>
                <!-- 专栏博客栏 -->
                <div class="row show_blogs">
                    <c:if test="${not empty columnManagerPage.columnBlogs}">
                        <c:forEach items="${columnManagerPage.columnBlogs}" var="columnBlog" varStatus="status">
                            <!-- 具体专栏博客 -->
                            <div class="row blogs">
                                <!-- 博客标题 -->
                                <div class="row blog_title">
                                        ${columnBlog.blog_title}
                                </div>
                                <!-- 博客相关操作 -->
                                <div class="row blog_option">
                                    <table class="table blog_option_table">
                                        <tr class="blog_option_tr">
                                            <td class="default"><a href="/CheckTheBlog?index=${status.index}&&flag=4">|查看|</a></td>
                                            <td class="default"><a href="/CheckTheBlog?index=${status.index}&&flag=5">|编辑|</a></td>
                                            <td class="default" id="delete"><a onclick="removeBlog('${status.index}', '${columnBlog.id}', '${columnManagerPage.columnId}')">|移除|</a></td>
                                            <input type="text" style="display: none" value="${status.index}" id="blog_index">
                                        </tr>
                                    </table>
                                </div>
                                <!-- 博客相关数据 -->
                                <div class="blog_data">
                                    <!-- 博客类型 -->
                                    <!-- 原创 -->
                                    <c:if test="${columnBlog.status == 4}">
                                        <c:if test="${columnBlog.type == 1}">
                                            <div class="blog_type blog_accept_original">
                                                原创
                                            </div>
                                        </c:if>
                                        <c:if test="${columnBlog.type == 2}">
                                            <div class="blog_type blog_accept_reprint">
                                                转载
                                            </div>
                                        </c:if>
                                    </c:if>
                                    <!-- 审核未通过 -->
                                    <c:if test="${columnBlog.status == 3}">
                                        <div class="blog_type blog_notAccept">
                                            审核未通过!
                                        </div>
                                    </c:if>
                                    <!-- 待审核 -->
                                    <c:if test="${columnBlog.status == 2}">
                                        <div class="blog_type blog_wait">
                                            待审核...
                                        </div>
                                    </c:if>
                                    <!-- 草稿 -->
                                    <c:if test="${columnBlog.status == 1}">
                                        <div class="blog_type blog_waitPublish">
                                            草稿(未发布)
                                        </div>
                                    </c:if>
                                    <!-- 博客保存时间 -->
                                    <div class="blog_time">
                                        <c:if test="${columnBlog.status == 4}">
                                            ${columnBlog.blog_pub_date}
                                        </c:if>
                                    </div>
                                    <!-- 博客访客量 -->
                                    <div class="blog_scan">
                                        <img src="../img/articleReadEyes.png" id="scan_img">
                                            ${columnBlog.blog_scan_num}
                                    </div>
                                    <!-- 博客点赞量 -->
                                    <div class="blog_up">
                                        <img src="../img/tobarThumbUpactive.png" id="up_img">
                                            ${columnBlog.blog_up_num}
                                    </div>
                                    <!-- 博客收藏量 -->
                                    <div class="blog_collect">
                                        <img src="../img/取消收藏.png" id="collect_img">
                                            ${columnBlog.blog_collect_num}
                                    </div>
                                </div>
                            </div>
                            <hr>
                        </c:forEach>
                    </c:if>
                </div>
                <!-- 分页 -->
                <div class="row page">
                    <c:if test="${columnManagerPage.pages > 0}">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <!-- 为首页 -->
                                <c:if test="${columnManagerPage.start / 3 == 0}">
                                    <li class="disabled">
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <!-- 不为首页 -->
                                <c:if test="${columnManagerPage.start / 3 != 0}">
                                    <li>
                                        <a style="cursor:pointer" onclick="lastPage(${columnManagerPage.now})" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <!-- 获取页码 -->
                                <!-- 前半部分有多余页 -->
                                <c:if test="${columnManagerPage.now - 2 > 1}">
                                    <!-- 为选中页 -->
                                    <c:if test="${0 == (columnManagerPage.now)}">
                                        <li class="active">
                                            <span>${1} <span class="sr-only">(current)</span></span>
                                        </li>
                                    </c:if>
                                    <!-- 不为选中页 -->
                                    <c:if test="${0 != (columnManagerPage.now)}">
                                        <li><a style="cursor:pointer" onclick="goToThePage(0)">${1}</a></li>
                                    </c:if>
                                    <li><a>...</a></li>
                                    <c:forEach begin="${columnManagerPage.now - 2}" end="${columnManagerPage.now}" var="i" step="1">
                                        <!-- 为选中页 -->
                                        <c:if test="${(i) == (columnManagerPage.now)}">
                                            <li class="active">
                                                <span>${i+1} <span class="sr-only">(current)</span></span>
                                            </li>
                                        </c:if>
                                        <!-- 不为选中页 -->
                                        <c:if test="${(i) != (columnManagerPage.now)}">
                                            <li><a style="cursor:pointer" onclick="goToThePage(${i})">${i+1}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <!-- 前半部分无多余页 -->
                                <c:if test="${columnManagerPage.now - 2 <= 1}">
                                    <c:forEach begin="0" end="${columnManagerPage.now}" var="i" step="1">
                                        <!-- 为选中页 -->
                                        <c:if test="${(i) == (columnManagerPage.now)}">
                                            <li class="active">
                                                <span>${i+1} <span class="sr-only">(current)</span></span>
                                            </li>
                                        </c:if>
                                        <!-- 不为选中页 -->
                                        <c:if test="${(i) != (columnManagerPage.now)}">
                                            <li><a style="cursor:pointer" onclick="goToThePage(${i})">${i+1}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </c:if>-
                                <!-- 后半部分有多余页 -->
                                <c:if test="${(columnManagerPage.now + 2) < (columnManagerPage.pages - 2)}">
                                    <c:forEach begin="${columnManagerPage.now + 1}" end="${columnManagerPage.now + 2}" var="i" step="1">
                                        <!-- 为选中页 -->
                                        <c:if test="${(i) == (columnManagerPage.now)}">
                                            <li class="active">
                                                <span>${i+1} <span class="sr-only">(current)</span></span>
                                            </li>
                                        </c:if>
                                        <!-- 不为选中页 -->
                                        <c:if test="${(i) != (columnManagerPage.now)}">
                                            <li><a style="cursor:pointer" onclick="goToThePage(${i})">${i+1}</a></li>
                                        </c:if>
                                    </c:forEach>
                                    <li><a>...</a></li>
                                    <!-- 为选中页 -->
                                    <c:if test="${columnManagerPage.pages - 1 == (columnManagerPage.now)}">
                                        <li class="active">
                                            <span>${columnManagerPage.pages} <span class="sr-only">(current)</span></span>
                                        </li>
                                    </c:if>
                                    <!-- 不为选中页 -->
                                    <c:if test="${columnManagerPage.pages - 1 != (columnManagerPage.now)}">
                                        <li><a style="cursor:pointer" onclick="goToThePage(${columnManagerPage.pages - 1})">${columnManagerPage.pages}</a></li>
                                    </c:if>
                                </c:if>
                                <!-- 后半部分无多余页 -->
                                <c:if test="${(columnManagerPage.now + 2) >= (columnManagerPage.pages - 2)}">
                                    <c:forEach begin="${columnManagerPage.now + 1}" end="${columnManagerPage.pages - 1}" var="i" step="1">
                                        <!-- 为选中页 -->
                                        <c:if test="${(i) == (columnManagerPage.now)}">
                                            <li class="active">
                                                <span>${i+1} <span class="sr-only">(current)</span></span>
                                            </li>
                                        </c:if>
                                        <!-- 不为选中页 -->
                                        <c:if test="${(i) != (columnManagerPage.now)}">
                                            <li><a style="cursor:pointer" onclick="goToThePage(${i})">${i+1}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <!-- 不为尾页 -->
                                <c:if test="${columnManagerPage.now != columnManagerPage.pages - 1}">
                                    <li>
                                        <a style="cursor:pointer" onclick="nextPage(${columnManagerPage.now})" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <!-- 为尾页 -->
                                <c:if test="${columnManagerPage.now == columnManagerPage.pages - 1}">
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
