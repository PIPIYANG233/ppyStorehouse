package domain;

import java.util.List;

public class Manager {
    private List<User> users;
    private List<Blog> blogs;
    private List<String> labels;
    private int sortId;
    private int now; // 当前是第几页
    private int start; // 每一页开始位置
    private int pages; // 总页数
    private int num; // 博客或博主总数;

    private int recommendNum; // 推荐博客数量

    public List<User> getUsers() { return users; }

    public void setUsers(List<User> users) { this.users = users; }

    public List<Blog> getBlogs() { return blogs; }

    public void setBlogs(List<Blog> blogs) { this.blogs = blogs; }

    public List<String> getLabels() { return labels; }

    public void setLabels(List<String> labels) { this.labels = labels; }

    public int getSortId() { return sortId; }

    public void setSortId(int sortId) { this.sortId = sortId; }

    public int getNow() { return now; }

    public void setNow(int now) { this.now = now; }

    public int getStart() { return start; }

    public void setStart(int start) { this.start = start; }

    public int getPages() { return pages; }

    public void setPages(int pages) { this.pages = pages; }

    public int getNum() { return num; }

    public void setNum(int num) { this.num = num; }

    public int getRecommendNum() { return recommendNum; }

    public void setRecommendNum(int recommendNum) { this.recommendNum = recommendNum; }

    @Override
    public String toString() {
        return "Manager{" +
                "users=" + users +
                ", blogs=" + blogs +
                ", labels=" + labels +
                ", sortId=" + sortId +
                ", now=" + now +
                ", start=" + start +
                ", pages=" + pages +
                ", num=" + num +
                ", recommendNum=" + recommendNum +
                '}';
    }
}
