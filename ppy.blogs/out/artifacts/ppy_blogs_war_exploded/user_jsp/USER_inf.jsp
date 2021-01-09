<%@ page import="domain.User" %>
<%@ page import="service.tools.GetHeadPath" %>
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
        <script src="../js/user/user_inf.js"></script>
        <link href="../css/user/User.css" rel="stylesheet">
        <link href="../css/user/user_inf.css" rel="stylesheet">

        <%
            String message = (String)session.getAttribute("message");
            if (null != message){
        %>
        <script type="text/javascript" language="javascript">
            alert("<%=message%>");
        </script>
        <%
                session.removeAttribute("message");
            }
        %>

        <%
            User user = (User)session.getAttribute("user");
            String path = GetHeadPath.getHeadPath(user.getId());
            session.setAttribute("head_path", path);
        %>
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
                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#myModal">
                        修改个人资料
                    </button>
                    <!-- 修改资料框 -->
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">个人资料修改</h4>
                                </div>
                                <div class="modal-body change_inf_modal">
                                    <!-- 昵称 -->
                                    <h3><span class="label label-info">昵称</span></h3>
                                    <input type="text" id="user_name_change" class="form-control change_inf_input inf" placeholder="中文、英文、数字、下划线 长度为10">
                                    <span><img src="" id="reg_img_user_name"></span>
                                    <!-- 姓名 -->
                                    <h3><span class="label label-info">姓名</span></h3>
                                    <input type="text" id="true_name_change" class="form-control change_inf_input inf" placeholder="中文、英文、数字、下划线 长度为20">
                                    <span><img src="" id="reg_img_true_name"></span>
                                    <!-- 生日 -->
                                    <h3><span class="label label-info">生日</span></h3>
                                    <input type="date" id="birthday_change" class="form-control change_inf_input inf">
                                    <!-- 性别 -->
                                    <h3><span class="label label-info">性别</span></h3>
                                    <div class="radio-inline inf">
                                        <label>
                                            <input type="radio" name="sex" id="optionsRadios1" value="男" class="inf" <c:if test="${user.sex == 1}">checked</c:if>>男
                                        </label>
                                    </div>
                                    <div class="radio-inline inf">
                                        <label>
                                            <input type="radio" name="sex" id="optionsRadios2" value="女" class="inf" <c:if test="${user.sex == 0}">checked</c:if>>女
                                        </label>
                                    </div>
                                    <!-- 简介 -->
                                    <h3><span class="label label-info">简介</span></h3>
                                    <textarea autofocus class="change_inf_textarea inf" placeholder="最多60字"></textarea>
                                    <span><img src="" id="reg_img_introduce"></span>
                                    <!-- 旧密码 -->
                                    <h3><span class="label label-info">旧密码</span></h3>
                                    <input type="text" id="oldPassword" class="form-control change_inf_input" placeholder="输入旧密码" disabled>
                                    <!-- 新密码 -->
                                    <h3><span class="label label-info">新密码</span></h3>
                                    <input type="text" id="newPassword" class="form-control change_inf_input" placeholder="输入新密码" disabled>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-link" onclick="notChangePassword()">修改资料</button>
                                    <button type="button" class="btn btn-link" onclick="wantChangePassword()">修改密码</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    <button type="button" class="btn btn-primary" id="change" onclick="change(${user.id})">确认修改</button>
                                </div>
                                <input type="text" value="1" id="flag" style="display: none">
                            </div>
                        </div>
                    </div>
                    <img src=${head_path} alt="" class="img-circle" id="head_img" onclick="show_main_right()">
                </div>
                <%-- 下部 --%>
                <div class="main_center_down row">
                    <%-- 左边 --%>
                    <div class="main_center_down_left row">
                        <!-- 昵称 -->
                        <h3><span class="label label-info">昵称</span></h3>
                        <p id="user_name">${user.user_name}</p>
                        <!-- 姓名 -->
                        <h3><span class="label label-info">姓名</span></h3>
                        <p id="true_name">${user.true_name}</p>
                        <!-- 邮箱 -->
                        <h3><span class="label label-info">邮箱</span></h3>
                        <p>${user.email}</p>
                        <!-- 生日 -->
                        <h3><span class="label label-info">生日</span></h3>
                        <p id="birthday">${user.birthday}</p>
                        <!-- 性别 -->
                        <h3><span class="label label-info">性别</span></h3>
                        <c:if test="${user.sex == 1}">
                            <p id="sex">男</p>
                        </c:if>
                        <c:if test="${user.sex == 0}">
                            <p id="sex">女</p>
                        </c:if>
                    </div>
                    <%-- 右边 --%>
                    <div class="main_center_down_right row">
                        <!-- 访客量 -->
                        <div>
                            <span class="num">
                                <img src="../img/articleReadEyes.png" class="data_img">${user.scan_num}
                            </span>
                        </div>
                        <!-- 点赞量 -->
                        <div>
                            <span class="num">
                                <img src="../img/tobarThumbUpactive.png" class="data_img">${user.up_num}
                            </span>
                        </div>
                        <!-- 收藏量 -->
                        <div>
                            <span class="num">
                                <img src="../img/取消收藏.png" class="data_img">${user.collected_num}
                            </span>
                        </div>
                        <!-- 粉丝 -->
                        <div>
                            <a href="/CheckUserFans?start=0">
                                <span class="num">
                                    粉丝数
                                    ${user.fans_num}
                                </span>
                            </a>
                        </div>
                        <!-- 关注 -->
                        <div>
                            <a href="/CheckUserFollow?start=0">
                                <span class="num">
                                    关注数
                                    ${user.follow_num}
                                </span>
                            </a>
                        </div>
                        <!-- 博客 -->
                        <div>
                            <a href="/CheckUserBlogs?start=0&&flag=1">
                                <span class="num">
                                    博客数
                                    ${user.blogs_num}
                                </span>
                            </a>
                        </div>
                        <!-- 收藏 -->
                        <div>
                            <a href="/CheckUserCollect?start=0">
                                <span class="num">
                                    收藏数
                                    ${user.collect_num}
                                </span>
                            </a>
                        </div>
                        <!-- 分类专栏 -->
                        <div>
                            <a href="/CheckUserColumns?userId=${user.id}&&start=0">
                                <span class="num">
                                    分类专栏数
                                    ${user.column_num}
                                </span>
                            </a>
                        </div>
                        <!-- 简介 -->
                        <h3><span class="label label-info">简介</span></h3>
                        <div class="row main_center_down_right_down">${user.introduce}</div>
                    </div>
                </div>
            </div>
            <!-- 更换头像 -->
            <div class="main_right row">
                <h2 id="h">更换头像</h2>

                <label for="img_file" id = "head_label">
                    <img src="" alt="" class="img-circle" id="head_img_change">
                </label>

                <form action="/CheckUpdate" method="post" enctype="multipart/form-data" id="head_form">
                    <input type="file" accept="image/*" name="fileImg" placeholder="" id = "img_file">

                    <div class="main_right_button row">
                        <button type="submit" class="btn btn-success" id="yes" onclick="hide_main_right()">确认</button>
                        <button type="button" class="btn btn-danger" id = "no" onclick="hide_main_right()">取消</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>