<%@ page import="domain.Search" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>搜索</title>

        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="../js/jquery-3.2.1.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="../js/bootstrap.min.js"></script>
        <!--[endif]-->
        <script src="../js/main/search.js"></script>
        <link href="../css/main/search.css" rel="stylesheet">
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
                            <!-- 防止翻页或改变分类时影响搜索 -->
                            <input type="text" style="display:none" class="searchContent" value="${search.searchContent}">
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
        <!-- 搜索结果 -->
        <input type="text" style="display: none" value="${search.sortId}" id="sortId">
        <div class="row search_content">
            <!-- 排序分类 -->
            <div class="row search_content_blog_sort">
                <!-- 搜博主 -->
                <div class="row sorts" id="sort0" onclick="checkAuthor('${user.id}')">
                    搜索博主
                </div>
                <!-- 时间优先 -->
                <div class="row sorts" id="sort1" onclick="changeSort(1)">
                    时间优先
                </div>
                <!-- 访客量优先 -->
                <div class="row sorts" id="sort2" onclick="changeSort(2)">
                    访客量优先
                </div>
                <!-- 点赞优先 -->
                <div class="row sorts" id="sort3" onclick="changeSort(3)">
                    点赞优先
                </div>
                <!-- 收藏优先 -->
                <div class="row sorts" id="sort4" onclick="changeSort(4)">
                    收藏优先
                </div>
            </div>
            <!-- 搜索博客 -->
            <c:if test="${!search.author && not empty search.searchBlogs}">
                <div class="row search_blogs">
                    <c:forEach items="${search.searchBlogs}" var="searchBlog" varStatus="status1">
                        <!-- 搜索具体博客 -->
                        <div class="row searchBlog">
                            <!-- 博客标题 -->
                            <div class="row searchBlog_title" onclick="readTheBlog(${status1.index})">
                                    ${searchBlog.blog_title}
                            </div>
                            <!-- 博客相关数据 -->
                            <div class="searchBlog_data">
                                <!-- 博客类型 -->
                                <!-- 原创 -->
                                <c:if test="${searchBlog.type == 1}">
                                    <div class="searchBlog_type searchBlog_accept_original">
                                        原创
                                    </div>
                                </c:if>
                                <c:if test="${searchBlog.type == 2}">
                                    <div class="searchBlog_type searchBlog_accept_reprint">
                                        转载
                                    </div>
                                </c:if>
                                <!-- 博客保存时间 -->
                                <div class="searchBlog_time">
                                        ${searchBlog.blog_pub_date}
                                </div>
                                <!-- 博客访客量 -->
                                <div class="searchBlog_scan">
                                    <img src="../img/articleReadEyes.png" id="scan_img">
                                        ${searchBlog.blog_scan_num}
                                </div>
                                <!-- 博客点赞量 -->
                                <div class="searchBlog_up">
                                    <img src="../img/tobarThumbUpactive.png" id="up_img">
                                        ${searchBlog.blog_up_num}
                                </div>
                                <!-- 博客收藏量 -->
                                <div class="searchBlog_collect">
                                    <img src="../img/取消收藏.png" id="collect_img">
                                        ${searchBlog.blog_collect_num}
                                </div>
                                <!-- 博客标签 -->
                                <div class="searchBlog_labels">
                                    <c:if test="${not empty searchBlog.labels}">
                                        <c:forEach items="${searchBlog.labels}" var="label" varStatus="status2">
                                            <button type="button" class="btn btn-link labels" id="label_button" onclick="checkLabel(${status1.index}, ${status2.index}, ${search.sortId})">
                                                <span>${label}</span>
                                                <input type="text" style="display: none" id="label${status1.index}${status2.index}" value="${label}">
                                            </button>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <!-- 搜索博主 -->
            <c:if test="${search.author && not empty search.searchUsers}">
                <div class="row searchUsers">
                    <c:forEach items="${search.searchUsers}" var="searchUser" varStatus="status">
                        <!-- 搜索具体博主 -->
                        <div class="row searchUser">
                            <!-- 博主头像 -->
                            <div class="row searchUser_head">
                                <img src="${searchUser.head_img}" alt="" class="img-circle" id="searchUser_head_img" onclick="checkTheSearchUser(${status.index})">
                            </div>
                            <!-- 博主数据 -->
                            <div class="row searchUser_data">
                                <!-- 博主名字 -->
                                <div class="row searchUser_name_div">
                                    <div class="row searchUser_name">
                                        ${searchUser.user_name}
                                    </div>
                                </div>
                                <!-- 博主是否被关注 -->
                                <div class="row searchUser_follow">
                                    <!-- 未被关注 -->
                                    <c:if test="${!searchUser.follow}">
                                        <button type="button" class="btn btn-danger" id="searchUser_follow_button" onclick="follow(true, '${user.id}', ${status.index})">关注博主</button>
                                    </c:if>
                                    <!-- 被关注 -->
                                    <c:if test="${searchUser.follow}">
                                        <button type="button" class="btn btn-default" id="searchUser_follow_button" onclick="follow(false, '${user.id}', ${status.index})">取消关注</button>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <!-- 分页 -->
            <div class="row page">
                <c:if test="${search.pages > 0}">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <!-- 为首页 -->
                            <c:if test="${search.start / 8 == 0}">
                                <li class="disabled">
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <!-- 不为首页 -->
                            <c:if test="${search.start / 8 != 0}">
                                <li>
                                    <a style="cursor:pointer" onclick="lastPage('${user.id}', ${search.author}, ${search.sortId}, ${search.now})" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <!-- 获取页码 -->
                            <!-- 前半部分有多余页 -->
                            <c:if test="${search.now - 2 > 1}">
                                <!-- 为选中页 -->
                                <c:if test="${0 == (search.now)}">
                                    <li class="active">
                                        <span>${1} <span class="sr-only">(current)</span></span>
                                    </li>
                                </c:if>
                                <!-- 不为选中页 -->
                                <c:if test="${0 != (search.now)}">
                                    <li><a style="cursor: pointer" onclick="goToPage('${user.id}', ${search.author}, ${search.sortId}, 0)">${1}</a></li>
                                </c:if>
                                <li><a>...</a></li>
                                <c:forEach begin="${search.now - 2}" end="${search.now}" var="i" step="1">
                                    <!-- 为选中页 -->
                                    <c:if test="${(i) == (search.now)}">
                                        <li class="active">
                                            <span>${i+1} <span class="sr-only">(current)</span></span>
                                        </li>
                                    </c:if>
                                    <!-- 不为选中页 -->
                                    <c:if test="${(i) != (search.now)}">
                                        <li><a style="cursor: pointer" onclick="goToPage('${user.id}', ${search.author}, ${search.sortId}, ${i})">${i+1}</a></li>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <!-- 前半部分无多余页 -->
                            <c:if test="${search.now - 2 <= 1}">
                                <c:forEach begin="0" end="${search.now}" var="i" step="1">
                                    <!-- 为选中页 -->
                                    <c:if test="${(i) == (search.now)}">
                                        <li class="active">
                                            <span>${i+1} <span class="sr-only">(current)</span></span>
                                        </li>
                                    </c:if>
                                    <!-- 不为选中页 -->
                                    <c:if test="${(i) != (search.now)}">
                                        <li><a style="cursor: pointer" onclick="goToPage('${user.id}', ${search.author}, ${search.sortId}, ${i})">${i+1}</a></li>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <!-- 后半部分有多余页 -->
                            <c:if test="${(search.now + 2) < (search.pages - 2)}">
                                <c:forEach begin="${search.now + 1}" end="${search.now + 2}" var="i" step="1">
                                    <!-- 为选中页 -->
                                    <c:if test="${(i) == (search.now)}">
                                        <li class="active">
                                            <span>${i+1} <span class="sr-only">(current)</span></span>
                                        </li>
                                    </c:if>
                                    <!-- 不为选中页 -->
                                    <c:if test="${(i) != (search.now)}">
                                        <li><a style="cursor: pointer" onclick="goToPage('${user.id}', ${search.author}, ${search.sortId}, ${i})">${i+1}</a></li>
                                    </c:if>
                                </c:forEach>
                                <li><a>...</a></li>
                                <!-- 为选中页 -->
                                <c:if test="${search.pages - 1 == (search.now)}">
                                    <li class="active">
                                        <span>${search.pages} <span class="sr-only">(current)</span></span>
                                    </li>
                                </c:if>
                                <!-- 不为选中页 -->
                                <c:if test="${search.pages - 1 != (search.now)}">
                                    <li><a style="cursor: pointer" onclick="goToPage('${user.id}', ${search.author}, ${search.sortId}, ${search.pages - 1})">${search.pages}</a></li>
                                </c:if>
                            </c:if>
                            <!-- 后半部分无多余页 -->
                            <c:if test="${(search.now + 2) >= (search.pages - 2)}">
                                <c:forEach begin="${search.now + 1}" end="${search.pages - 1}" var="i" step="1">
                                    <!-- 为选中页 -->
                                    <c:if test="${(i) == (search.now)}">
                                        <li class="active">
                                            <span>${i+1} <span class="sr-only">(current)</span></span>
                                        </li>
                                    </c:if>
                                    <!-- 不为选中页 -->
                                    <c:if test="${(i) != (search.now)}">
                                        <li><a style="cursor: pointer" onclick="goToPage('${user.id}', ${search.author}, ${search.sortId}, ${i})">${i+1}</a></li>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <!-- 不为尾页 -->
                            <c:if test="${search.now != search.pages - 1}">
                                <li>
                                    <a style="cursor: pointer" onclick="nextPage('${user.id}', ${search.author}, ${search.sortId}, ${search.now})" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <!-- 为尾页 -->
                            <c:if test="${search.now == search.pages - 1}">
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
        <!-- 相关标签推荐 -->
        <div class="row recommendLabels">
            <!-- 标签推荐 -->
            <div class="row recommendLabel_title">
                相关标签推荐
            </div>
            <c:if test="${not empty search.recommendLabels}">
                <c:forEach items="${search.recommendLabels}" var="recommendLabel" varStatus="status">
                    <div class="row recommendLabel">
                        <a style="cursor: pointer" id="recommendLabel${status.index}" onclick="checkRecommendLabel(${status.index}, ${search.sortId})">${recommendLabel}</a>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </body>
</html>