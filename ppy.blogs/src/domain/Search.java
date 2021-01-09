package domain;

import java.util.List;

public class Search {
    private boolean isAuthor;
    private int sortId;
    private List<Blog> searchBlogs;
    private List<User> searchUsers;
    private List<String> recommendLabels;
    private int now; // 当前是第几页
    private int start; // 每一页开始位置
    private int pages; // 总页数
    private int num; // 博客或博主总数;
    private String searchContent; // 搜索的内容

    public boolean isAuthor() { return isAuthor; }

    public void setAuthor(boolean author) { isAuthor = author; }

    public int getSortId() { return sortId; }

    public void setSortId(int sortId) { this.sortId = sortId; }

    public List<Blog> getSearchBlogs() { return searchBlogs; }

    public void setSearchBlogs(List<Blog> searchBlogs) { this.searchBlogs = searchBlogs; }

    public List<User> getSearchUsers() { return searchUsers; }

    public void setSearchUsers(List<User> searchUsers) { this.searchUsers = searchUsers; }

    public List<String> getRecommendLabels() { return recommendLabels; }

    public void setRecommendLabels(List<String> recommendLabels) { this.recommendLabels = recommendLabels; }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) { this.pages = pages; }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSearchContent() { return searchContent; }

    public void setSearchContent(String searchContent) { this.searchContent = searchContent; }

    @Override
    public String toString() {
        return "Search{" +
                "isAuthor=" + isAuthor +
                ", sortId=" + sortId +
                ", searchBlogs=" + searchBlogs +
                ", searchUsers=" + searchUsers +
                ", recommendLabels=" + recommendLabels +
                ", now=" + now +
                ", start=" + start +
                ", pages=" + pages +
                ", num=" + num +
                ", searchContent='" + searchContent + '\'' +
                '}';
    }
}
