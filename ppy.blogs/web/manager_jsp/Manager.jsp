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
        <title>管理员</title>

        <!-- Bootstrap -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="../js/jquery-3.2.1.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="../js/bootstrap.min.js"></script>
        <!--[endif]-->
        <script src="../js/manager/manager.js"></script>
        <link href="../css/manager/manager.css" rel="stylesheet">
    </head>
    <body background="../img/火箭.jpg">
        <!-- 顶部 -->
        <nav class="navbar navbar-default" style="background: white; width: 100%">
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
        <input type="text" value="${manager.sortId}" style="display: none" id="sortId">
        <div class="row manager">
            <!-- 排序分类 -->
            <div class="row manager_sort">
                <!-- 管理用户 -->
                <div class="row sorts" id="sort1" onclick="managerUsers(${user.rank})">
                    用户
                </div>
                <!-- 管理已审核博客 -->
                <div class="row sorts" id="sort2" onclick="managerBlogs(2)">
                    已过审博客
                </div>
                <!-- 管理未过审博客 -->
                <div class="row sorts" id="sort3" onclick="managerBlogs(3)">
                    未过审博客
                </div>
                <!-- 管理未审核博客 -->
                <div class="row sorts" id="sort4" onclick="managerBlogs(4)">
                    未审核博客
                </div>
                <!-- 管理首页推荐博客 -->
                <div class="row sorts" id="sort5" onclick="managerBlogs(5)">
                    每日推荐
                </div>
                <!-- 管理首页标签 -->
                <div class="row sorts" id="sort6" onclick="managerBlogs(6)">
                    首页标签
                </div>
            </div>
            <!-- 管理博客 -->
            <c:if test="${not empty manager.blogs}">
                <div class="row managerBlogs">
                    <c:forEach items="${manager.blogs}" var="managerBlog" varStatus="status">
                        <!-- 管理具体博客 -->
                        <div class="row managerBlog">
                            <!-- 博客标题 -->
                            <div class="row managerBlog_title" style="cursor: pointer" onclick="checkBlog(${status.index})">
                                    ${managerBlog.blog_title}
                            </div>
                            <!-- 博客相关数据 -->
                            <div class="managerBlog_data">
                                <!-- 博客类型 -->
                                <!-- 原创 -->
                                <c:if test="${managerBlog.type == 1}">
                                    <div class="managerBlog_type managerBlog_accept_original">
                                        原创
                                    </div>
                                </c:if>
                                <!-- 转载 -->
                                <c:if test="${managerBlog.type == 2}">
                                    <div class="managerBlog_type managerBlog_accept_reprint">
                                        转载
                                    </div>
                                </c:if>
                                <!-- 博客状态 -->
                                <!-- 审核未通过 -->
                                <c:if test="${managerBlog.status == 3}">
                                    <div class="managerBlog_type managerBlog_notAccept">
                                        审核不通过!
                                    </div>
                                </c:if>
                                <!-- 待审核 -->
                                <c:if test="${managerBlog.status == 2}">
                                    <div class="managerBlog_type managerBlog_wait">
                                        待审核...
                                    </div>
                                </c:if>
                            </div>
                            <!-- 博客相关操作 -->
                            <div class="row managerBlog_option">
                                <!-- 审核通过 -->
                                <c:if test="${managerBlog.status != 4}">
                                    <div class="row passed">
                                        <button type="button" class="btn btn-success" id="passed_btn" onclick="pass(${status.index})">
                                            通过
                                        </button>
                                    </div>
                                </c:if>
                                <!-- 审核不通过 -->
                                <c:if test="${managerBlog.status != 3}">
                                    <div class="row notPassed">
                                        <button type="button" class="btn btn-danger" id="notPassed_btn" onclick="fail(${status.index})">
                                            不通过
                                        </button>
                                    </div>
                                </c:if>
                                <!-- 推荐 -->
                                <c:if test="${manager.sortId < 5 && !managerBlog.recommend}">
                                    <div class="row recommend">
                                        <button type="button" class="btn btn-primary" id="recommend_btn" onclick="recommend(${manager.sortId},${status.index},${manager.recommendNum})">
                                            推荐
                                        </button>
                                    </div>
                                </c:if>
                                <!-- 取消推荐 -->
                                <c:if test="${manager.sortId == 5}">
                                    <div class="row notRecommend">
                                        <button type="button" class="btn btn-default" id="notRecommend_btn" onclick="deleteRecommend(${status.index})">
                                            取消推荐
                                        </button>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <!-- 管理博主 -->
            <c:if test="${not empty manager.users}">
                <div class="row managerUsers">
                    <c:forEach items="${manager.users}" var="managerUser" varStatus="status">
                        <!-- 管理具体博主 -->
                        <div class="row managerUser">
                            <!-- 博主头像 -->
                            <div class="row managerUser_head">
                                <img src="${managerUser.head_img}" alt="" class="img-circle" id="managerUser_head_img" onclick="checkUser(${managerUser.id})">
                            </div>
                            <!-- 博主数据 -->
                            <div class="row managerUser_data">
                                <!-- 博主名字 -->
                                <div class="row managerUser_name_div">
                                    <div class="row managerUser_name">
                                            ${managerUser.user_name}
                                    </div>
                                </div>
                                <!-- 博主是否被关注 -->
                                <div class="row managerUser_follow">
                                    <!-- 主管 -->
                                    <c:if test="${user.rank == 2}">
                                        <!-- 删除用户 -->
                                        <button type="button" class="btn btn-warning" id="managerUser_delete_button" onclick="deleteUser(${status.index})">
                                            删除该用户
                                        </button>
                                        <!-- 设置管理员 -->
                                        <c:if test="${managerUser.rank == 0}">
                                            <button type="button" class="btn btn-info" id="managerUser_set_button" onclick="setManager(${status.index})">
                                                设为管理员
                                            </button>
                                        </c:if>
                                        <!-- 取消管理员 -->
                                        <c:if test="${managerUser.rank == 1}">
                                            <button type="button" class="btn btn-default" id="managerUser_set_button" onclick="dropManager(${status.index})">
                                                取消管理员
                                            </button>
                                        </c:if>
                                    </c:if>
                                    <!-- 普通管理员 -->
                                    <c:if test="${user.rank == 1}">
                                        <!-- 删除用户 -->
                                        <button type="button" class="btn btn-warning" id="managerUser_delete_button" onclick="deleteUser(${status.index})">
                                            删除该用户
                                        </button>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <!-- 管理标签 -->
            <c:if test="${not empty manager.labels}">
                <div class="row managerLabels">
                    <c:forEach items="${manager.labels}" var="managerLabel" varStatus="status">
                        <input type="text" style="display: none" id="label${status.index}" value="${managerLabel}">
                        <!-- 管理具体标签 -->
                        <!-- 输入框 -->
                        <div class="row managerLabel_input">
                            <span class="label label-info">${status.index + 1}</span>
                            <input type="text" class="form-control" id="${status.index}label" value="${managerLabel}">
                        </div>
                        <!-- 按钮 -->
                        <div class="row managerLabel_btn">
                            <button type="button" class="btn btn-info" id="managerLabel_btn" onclick="changeLabel(${status.index})">修改</button>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <!-- 分页 -->
            <c:if test="${manager.sortId < 5}">
                <div class="row page">
                    <c:if test="${manager.pages > 0}">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <!-- 为首页 -->
                                <c:if test="${manager.start / 8 == 0}">
                                    <li class="disabled">
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <!-- 不为首页 -->
                                <c:if test="${manager.start / 8 != 0}">
                                    <li>
                                        <a style="cursor:pointer" onclick="lastPage(${manager.sortId}, ${manager.now}, ${user.rank})" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <!-- 获取页码 -->
                                <!-- 前半部分有多余页 -->
                                <c:if test="${manager.now - 2 > 1}">
                                    <!-- 为选中页 -->
                                    <c:if test="${0 == (manager.now)}">
                                        <li class="active">
                                            <span>${1} <span class="sr-only">(current)</span></span>
                                        </li>
                                    </c:if>
                                    <!-- 不为选中页 -->
                                    <c:if test="${0 != (manager.now)}">
                                        <li><a style="cursor: pointer" onclick="goToPage(${manager.sortId}, 0, ${user.rank})">${1}</a></li>
                                    </c:if>
                                    <li><a>...</a></li>
                                    <c:forEach begin="${manager.now - 2}" end="${manager.now}" var="i" step="1">
                                        <!-- 为选中页 -->
                                        <c:if test="${(i) == (manager.now)}">
                                            <li class="active">
                                                <span>${i+1} <span class="sr-only">(current)</span></span>
                                            </li>
                                        </c:if>
                                        <!-- 不为选中页 -->
                                        <c:if test="${(i) != (manager.now)}">
                                            <li><a style="cursor: pointer" onclick="goToPage(${manager.sortId}, ${i}, ${user.rank})">${i+1}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <!-- 前半部分无多余页 -->
                                <c:if test="${manager.now - 2 <= 1}">
                                    <c:forEach begin="0" end="${manager.now}" var="i" step="1">
                                        <!-- 为选中页 -->
                                        <c:if test="${(i) == (manager.now)}">
                                            <li class="active">
                                                <span>${i+1} <span class="sr-only">(current)</span></span>
                                            </li>
                                        </c:if>
                                        <!-- 不为选中页 -->
                                        <c:if test="${(i) != (manager.now)}">
                                            <li><a style="cursor: pointer" onclick="goToPage(${manager.sortId}, ${i}, ${user.rank})">${i+1}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <!-- 后半部分有多余页 -->
                                <c:if test="${(manager.now + 2) < (manager.pages - 2)}">
                                    <c:forEach begin="${manager.now + 1}" end="${manager.now + 2}" var="i" step="1">
                                        <!-- 为选中页 -->
                                        <c:if test="${(i) == (manager.now)}">
                                            <li class="active">
                                                <span>${i+1} <span class="sr-only">(current)</span></span>
                                            </li>
                                        </c:if>
                                        <!-- 不为选中页 -->
                                        <c:if test="${(i) != (manager.now)}">
                                            <li><a style="cursor: pointer" onclick="goToPage(${manager.sortId}, ${i}, ${user.rank})">${i+1}</a></li>
                                        </c:if>
                                    </c:forEach>
                                    <li><a>...</a></li>
                                    <!-- 为选中页 -->
                                    <c:if test="${manager.pages - 1 == (manager.now)}">
                                        <li class="active">
                                            <span>${manager.pages} <span class="sr-only">(current)</span></span>
                                        </li>
                                    </c:if>
                                    <!-- 不为选中页 -->
                                    <c:if test="${manager.pages - 1 != (manager.now)}">
                                        <li><a style="cursor: pointer" onclick="goToPage(${manager.sortId}, ${manager.pages - 1}, ${user.rank})">${manager.pages}</a></li>
                                    </c:if>
                                </c:if>
                                <!-- 后半部分无多余页 -->
                                <c:if test="${(manager.now + 2) >= (manager.pages - 2)}">
                                    <c:forEach begin="${manager.now + 1}" end="${manager.pages - 1}" var="i" step="1">
                                        <!-- 为选中页 -->
                                        <c:if test="${(i) == (manager.now)}">
                                            <li class="active">
                                                <span>${i+1} <span class="sr-only">(current)</span></span>
                                            </li>
                                        </c:if>
                                        <!-- 不为选中页 -->
                                        <c:if test="${(i) != (manager.now)}">
                                            <li><a style="cursor: pointer" onclick="goToPage(${manager.sortId}, ${i}, ${user.rank})">${i+1}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <!-- 不为尾页 -->
                                <c:if test="${manager.now != manager.pages - 1}">
                                    <li>
                                        <a style="cursor: pointer" onclick="nextPage(${manager.sortId}, ${manager.now}, ${user.rank})" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <!-- 为尾页 -->
                                <c:if test="${manager.now == manager.pages - 1}">
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
            </c:if>
        </div>
    </body>
</html>