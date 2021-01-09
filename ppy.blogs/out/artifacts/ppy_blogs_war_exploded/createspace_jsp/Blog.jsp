<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2020/10/24
  Time: 17:42
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
        <title>博客</title>

        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="../js/jquery-3.2.1.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="../js/bootstrap.min.js"></script>
        <!--[endif]-->

        <link href="../css/createspace/blog.css" rel="stylesheet">
        <script src="../js/createspace/blog.js"></script>
    </head>

    <body background="/img/塞尔达传说%20简约极简主义4k壁纸_彼岸图网.jpg">
        <div class="row father">
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
            <!-- 左边 -->
            <div class="row left_main">
                <div class="row left_author_data" style="display: none">
                    <!-- 作者信息栏 -->
                    <div class="row left_author_inf">
                        <!-- 作者头像 -->
                        <div class="row author_head">
                            <!-- 作者是自己 -->
                            <c:if test="${user.id == author.id}">
                                <img src="${checkBlog.author.head_img}" alt="" class="img-circle" id="author_head_img" onclick="toAuthorSpace(1)">
                            </c:if>
                            <!-- 作者不是自己 -->
                            <c:if test="${user.id != author.id}">
                                <img src="${checkBlog.author.head_img}" alt="" class="img-circle" id="author_head_img" onclick="toAuthorSpace(0)">
                            </c:if>
                        </div>
                        <!-- 作者名字 -->
                        <div class="row author_name">
                            <div class="row author_name_div">
                                ${checkBlog.author.user_name}
                            </div>
                        </div>
                        <!-- 作者数据 -->
                        <div class="row author_data" id="author_data_div">
                            <table class="table">
                                <tr class="author_data_tr">
                                    <td class="default">粉丝</td>
                                    <td class="default">博客</td>
                                    <td class="default">访客</td>
                                    <td class="default">获赞</td>
                                    <td class="default">收藏</td>
                                </tr>

                                <tr>
                                    <td class="default">${checkBlog.author.fans_num}</td>
                                    <td class="default">${checkBlog.author.blogs_num}</td>
                                    <td class="default">${checkBlog.author.scan_num}</td>
                                    <td class="default">${checkBlog.author.up_num}</td>
                                    <td class="default">${checkBlog.author.collected_num}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="row left_comments">
                    <!-- 输入评论 -->
                    <div class="row comment_div">
                        <!-- 评论输入框 -->
                        <c:if test="${empty user}">
                            <textarea id="no_comment_textarea" placeholder="请登陆后评论" disabled="disabled"></textarea>
                        </c:if>
                        <c:if test="${not empty user}">
                            <textarea id="yes_comment_textarea" autofocus;></textarea>
                        </c:if>
                        <!-- 评论发表按钮 -->
                        <button type="button" class="btn btn-info" id="comment_bnt" onclick="publishComment('${user.id}', '${checkBlog.blog.id}')">发表</button>
                    </div>
                    <!-- 获取历史评论 -->
                    <div class="row past_comment_div" id="past_comment_div">
                        <c:if test="${not empty checkBlog.comments}">
                            <c:forEach items="${checkBlog.comments}" var="comment" varStatus="status1">
                                <!-- 主评论 -->
                                <div class="row comments">
                                    <!-- 评论者头像 -->
                                    <img src="${comment.commentUser.head_img}" alt="" class="img-circle theComment_head">
                                    <div class="row theComment_content" id="theComment_content${status1.index}">
                                        <!-- 评论者名字 -->                  <!-- 评论内容 -->
                                        ${comment.commentUser.user_name}
                                        <br>
                                        <font style="color: gray">"${comment.comment_content}"</font>
                                    </div>
                                    <!-- 评论下面回复 -->
                                    <c:if test="${not empty comment.requests}">
                                        <c:forEach items="${comment.requests}" var="request" varStatus="status2">
                                            <div class="row requests" id="request${status1.index}${status2.index}">
                                                <!-- 回复者头像 -->
                                                <img src="${request.requestUser.head_img}" alt="" class="img-circle theRequest">
                                                <!-- 回复者名字 -->
                                                    ${request.requestUser.user_name}
                                                <div class="row request_option_btn_div">
                                                    <a class="request_option_btn" onclick="requestRequest('${comment.id}','${request.req_user_id}','${user.id}','${status1.index}','${status2.index}')">回复</a>
                                                    <c:if test="${user.rank >= 1 || user.id == request.req_user_id || user.id == author.id}">
                                                        <a class="request_option_btn" onclick="deleteRequest('${status1.index}', '${status2.index}')">删除</a>
                                                    </c:if>
                                                </div>
                                                <br>
                                                 <font style="color: lightblue">回复</font>
                                                <!-- 回复对象名字 -->
                                                    ${request.commentUser.user_name}
                                                <br>
                                                <!-- 回复内容 -->
                                                <font style="color: gray">"${request.req_content}"</font>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                    <hr>
                                </div>
                                <div class="row comment_request_btn_div">
                                    <a class="comment_option_btn" id="request_btn${status1.index}" onclick="requestComment('${comment.id}','${comment.user_id}','${user.id}','${status1.index}')">回复</a>
                                    <c:if test="${user.rank >= 1 || user.id == comment.user_id}">
                                        <a class="comment_option_btn" onclick="deleteComment('${status1.index}')">删除</a>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
            <!-- 中间 -->
            <div class="row center_" id="center_">
                <!-- 中间标题 -->
                <div class="row center_title">
                    <h1>${checkBlog.blog.blog_title}</h1>
                </div>
                <!-- 中间博客信息 -->
                <div class="row center_top">
                    <!-- 类别 -->
                    <div class="row center_top_img">
                        <!-- 文章为原创 -->
                        <c:if test="${checkBlog.blog.type == 1}">
                            <img src="../img/original.png" id = "type_img">
                        </c:if>
                        <!-- 文章为转载 -->
                        <c:if test="${checkBlog.blog.type == 2}">
                            <img src="../img/reprint.png" id = "type_img">
                        </c:if>
                    </div>
                    <!-- 作者 -->
                    <div class="row center_top_author">
                        <span id="author">${checkBlog.author.user_name}</span>
                    </div>
                    <!-- 发布时间 -->
                    <div class="row center_top_time">
                        <span id="time">${checkBlog.blog.blog_pub_date}</span>
                    </div>
                    <!-- 访客量图标 -->
                    <div class="row center_top_scan_img">
                        <img src="../img/articleReadEyes.png" id="scan_img">
                    </div>
                    <!-- 访客量 -->
                    <div class="row center_top_scan">
                        <span id="scan">${checkBlog.blog.blog_scan_num}</span>
                    </div>
                    <!-- 为本人编辑博客 -->
                    <c:if test="${checkBlog.author.id == user.id}">
                        <div class="row center_top_credit">
                            <span id="credit"><a href="/CreditBlogFromBlog">编辑</a></span>
                        </div>
                    </c:if>
                    <!-- 不为本人 -->
                    <c:if test="${checkBlog.author.id != user.id && checkBlog.blog.status==4}">
                        <!-- 点赞 -->
                        <div class="row center_top_up">
                            <span id="up_span">
                                <!-- 用户未点赞此博客 -->
                                <c:if test="${!checkBlog.up}">
                                    <!-- 用户已登录 -->
                                    <c:if test="${not empty user}">
                                        <img src="../img/tobarThumbUp.png" id="upBtn" onclick="up()">
                                        <input type="text" style="display: none" value="0" id="isUp">
                                    </c:if>
                                    <!-- 用户未登录 -->
                                    <c:if test="${empty user}">
                                        <img src="../img/tobarThumbUp.png" id="upBtn" onclick="login()">
                                    </c:if>
                                </c:if>
                                <!-- 用户已点赞此博客 -->
                                <c:if test="${checkBlog.up}">
                                    <!-- 用户已登录 -->
                                    <c:if test="${not empty user}">
                                        <img src="../img/tobarThumbUpactive.png" id="upBtn" onclick="up()">
                                        <input type="text" style="display: none" value="1" id="isUp">
                                    </c:if>
                                    <!-- 用户未登录 -->
                                    <c:if test="${empty user}">
                                        <img src="../img/tobarThumbUpactive.png" id="upBtn" onclick="login()">
                                    </c:if>
                                </c:if>
                                <!-- 点赞数量大于0则显示 -->
                                <c:if test="${checkBlog.blog.blog_up_num > 0}">
                                    ${checkBlog.blog.blog_up_num}
                                </c:if>
                            </span>
                        </div>
                        <!-- 收藏 -->
                        <div class="row center_top_collect">
                            <span id="collect_span">
                                <!-- 用户未收藏此博客 -->
                                <c:if test="${!checkBlog.collect}">
                                    <!-- 用户已登录 -->
                                    <c:if test="${not empty user}">
                                        <img src="../img/收藏.png" id="collectBtn" onclick="collect()">
                                        <input type="text" style="display: none" value="0" id="isCollect">
                                    </c:if>
                                    <!-- 用户未登录 -->
                                    <c:if test="${empty user}">
                                        <img src="../img/收藏.png" id="collectBtn" onclick="login()">
                                    </c:if>
                                </c:if>
                                <!-- 用户已收藏此博客 -->
                                <c:if test="${checkBlog.collect}">
                                    <!-- 用户已登录 -->
                                    <c:if test="${not empty user}">
                                        <img src="../img/取消收藏.png" id="collectBtn" onclick="collect()">
                                        <input type="text" style="display: none" value="1" id="isCollect">
                                    </c:if>
                                    <!-- 用户未登录 -->
                                    <c:if test="${empty user}">
                                        <img src="../img/取消收藏.png" id="collectBtn" onclick="login()">
                                    </c:if>
                                </c:if>
                                <!-- 收藏数量大于0则显示 -->
                                <c:if test="${checkBlog.blog.blog_collect_num > 0}">
                                    ${checkBlog.blog.blog_collect_num}
                                </c:if>
                            </span>
                        </div>
                    </c:if>
                    <!-- 判断是否有标签专栏 -->
                    <div class="row center_top_sort">
                        <!-- 判断是否有标签 -->
                        <c:if test="${not empty checkBlog.blog.labels}">
                            <span id="sort1">文章标签:</span>
                        </c:if>
                        <!-- 列出所有标签 -->
                        <c:forEach items="${checkBlog.blog.labels}" var="label">
                            <div style="cursor: pointer" class="row sorts" onclick="searchLabels('${label}')">${label}</div>
                        </c:forEach>

                        <!-- 中间博客分类专栏 -->
                        <c:if test="${not empty checkBlog.blog.columns}">
                            <span id="sort2">分类专栏:</span>
                        </c:if>
                        <!-- 列出专栏名称 -->
                        <c:forEach items="${checkBlog.blog.columns}" var="column">
                            <div style="cursor: pointer" class="row sorts" onclick="checkColumns('${column}','${author.id}','${user.id}')">${column}</div>
                        </c:forEach>
                    </div>
                </div>
                <!-- 博客内容 -->
                <div class="row center_content" id="center_content">
                    ${checkBlog.htmlCode}
                </div>
                <!-- 关注博主 -->
                <!-- 用户不为作者 -->
                <c:if test="${not empty user}">
                    <c:if test="${checkBlog.author.id != user.id}">
                        <div class="row isFollowDiv">
                        <span id="followSpan">
                            <!-- 已关注 -->
                            <c:if test="${checkBlog.follow}">
                            <!-- 用户已登录 -->
                                <c:if test="${not empty user}">
                                    <button type="button" id="followBtn" class="btn btn-default" onclick="follow()">取消关注
                                    <input type="text" style="display: none" value="1" id="isFollow">
                                </c:if>
                                        <!-- 用户未登录 -->
                                <c:if test="${empty user}">
                                    <button type="button" id="followBtn" class="btn btn-default" onclick="login()">取消关注
                                </c:if>
                            </c:if>
                                        <!-- 未关注 -->
                            <c:if test="${!checkBlog.follow}">
                                 <button type="button" id="followBtn" class="btn btn-danger" onclick="follow()">关注博主
                                 <input type="text" style="display: none" value="0" id="isFollow">
                            </c:if>
                        </span>
                        </div>
                    </c:if>
                </c:if>
            </div>
            <!-- 右边 -->
            <div class="row right" style="display: none">
                <!-- 博主博客推荐 -->
                <div class="row recommend_title">
                    博主博客推荐
                </div>
                <div class="row recommend_blogs">
                    <c:forEach items="${checkBlog.recommendBlogs}" var="recommendBlog" varStatus="status">
                        <div class="row blogs">
                            <!-- 博客标题 -->
                            <div class="row blog_title_">
                                <a href="/CheckRecommendBlog?index=${status.index}">${recommendBlog.blog_title}</a>
                            </div>
                            <!-- 博客相关数据 -->
                            <div class="blog_data_">
                                <!-- 博客保存时间 -->
                                <div class="blog_time_">
                                    ${recommendBlog.blog_pub_date}
                                </div>
                                <!-- 博客访客量 -->
                                <div class="blog_scan_">
                                    <img src="../img/articleReadEyes.png" id="scan_img_">
                                    ${recommendBlog.blog_scan_num}
                                </div>
                                <!-- 博客点赞量 -->
                                <div class="blog_up_">
                                    <img src="../img/tobarThumbUpactive.png" id="up_img_">
                                    ${recommendBlog.blog_up_num}
                                </div>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
