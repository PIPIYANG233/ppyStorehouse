package dao.page;

import dao.blogData.GetBlogDataImp;
import dao.userData.GetUserData;
import dao.userData.GetUserDataImp;
import domain.*;
import service.userOption.UserOptionImp;

import java.util.List;

public class GetPageDataImp implements GetPageData{

    // 获取默认的blogPage
    @Override
    public BlogPage getBlogPage(int now, int start, int pages, int num, int authorId, String sql) {
        BlogPage blogPage = new BlogPage();
        blogPage.setNow(now);
        blogPage.setStart(start);
        blogPage.setPages(pages);
        blogPage.setNum(num);
        List<Blog> blogs = new GetBlogDataImp().getBlogs(authorId, sql);
        blogPage.setBlogs(blogs);
        blogPage.setYears(new GetUserDataImp().getYears(authorId));
        blogPage.setYear(0);
        blogPage.setMonth(0);
        blogPage.setColumns(new GetUserDataImp().getColumnNames(authorId));
        blogPage.setColumn("");
        blogPage.setType(0);
        blogPage.setMainContent("");
        return blogPage;
    }
    // 获取筛选后的blogPage
    @Override
    public BlogPage getBlogPage(boolean isFromBlog, BlogPage blogPage, int authorId, String sql) {
        // 重新获取 博客集合
        List<Blog> blogs = new GetBlogDataImp().getBlogs(authorId, sql);
        blogPage.setBlogs(blogs);
        if(isFromBlog){ // 是从博客页面
            blogPage.setYears(new GetUserDataImp().getYears(authorId));
            blogPage.setColumns(new GetUserDataImp().getColumnNames(authorId));
        }
        return blogPage;
    }

    @Override
    public FanPage getFanPage(int now, int start, int pages, int num, int userId, String sql) {
        FanPage fanPage = new FanPage();
        fanPage.setNow(now);
        fanPage.setStart(start);
        fanPage.setPages(pages);
        fanPage.setNum(num);
        List<User> fans = new GetUserDataImp().getUsers(userId, true, sql);
        fanPage.setFans(fans);
        return fanPage;
    }

    @Override
    public FollowPage getFollowPage(int now, int start, int pages, int num, int userId, String sql) {
        FollowPage followPage = new FollowPage();
        followPage.setNow(now);
        followPage.setStart(start);
        followPage.setPages(pages);
        followPage.setNum(num);
        List<User> follow = new GetUserDataImp().getUsers(userId, false, sql);
        followPage.setFollow(follow);
        return followPage;
    }

    @Override
    public CollectPage getCollectPage(int now, int start, int pages, int num, int userId, String sql) {
        CollectPage collectPage = new CollectPage();
        collectPage.setNow(now);
        collectPage.setStart(start);
        collectPage.setPages(pages);
        collectPage.setNum(num);
        List<Blog> collect = new GetBlogDataImp().getBlogs(userId, sql);
        collectPage.setCollect(collect);
        return collectPage;
    }

    @Override
    public ColumnPage getColumnPage(int now, int start, int pages, int num, int userId) {
        ColumnPage columnPage = new ColumnPage();
        columnPage.setNow(now);
        columnPage.setStart(start);
        columnPage.setPages(pages);
        columnPage.setNum(num);
        columnPage.setColumns(new GetUserDataImp().getColumns(start, userId));
        return columnPage;
    }

    @Override
    public ColumnManagerPage getColumnManagerPage(int now, int start, int pages, int num, int columnId, String sql) {
        ColumnManagerPage columnManagerPage = new ColumnManagerPage();
        columnManagerPage.setNow(now);
        columnManagerPage.setStart(start);
        columnManagerPage.setPages(pages);
        columnManagerPage.setNum(num);
        columnManagerPage.setColumnId(columnId);
        columnManagerPage.setColumnBlogs(new GetBlogDataImp().getBlogs(columnId, sql));
        return columnManagerPage;
    }

    @Override
    public ColumnManagerPage getColumnManagerPage(ColumnManagerPage columnManagerPage, int columnId, String sql) {
        // 重新获取 博客集合
        List<Blog> columnBlogs = new GetBlogDataImp().getBlogs(columnId, sql);
        columnManagerPage.setColumnBlogs(columnBlogs);
        return columnManagerPage;
    }

    @Override
    public int getNum(Object object, String sql, int parametersNum) {
        GetUserData getUserData = new GetUserDataImp();
        int num = getUserData.getNum(object, sql, parametersNum);
        return num;
    }

    @Override
    public int getPages(int num, int size) {
        int pages = 0;
        if(num % size == 0) {
            pages += num / size;
        }else{
            pages += num / size + 1;
        }
        return pages;
    }
}