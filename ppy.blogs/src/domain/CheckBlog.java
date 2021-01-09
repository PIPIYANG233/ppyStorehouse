package domain;

import java.util.List;

public class CheckBlog {
    private Blog blog;
    private User author;
    private String htmlCode;
    private String markdown;
    private List<Blog> recommendBlogs;
    private boolean isUp;
    private boolean isFollow;
    private boolean isCollect;
    private List<Comment> comments;
    private List<String> columnNames;

    public Blog getBlog() { return blog; }

    public void setBlog(Blog blog) { this.blog = blog; }

    public User getAuthor() { return author; }

    public void setAuthor(User author) { this.author = author; }

    public String getHtmlCode() { return htmlCode; }

    public void setHtmlCode(String htmlCode) { this.htmlCode = htmlCode; }

    public String getMarkdown() { return markdown; }

    public void setMarkdown(String markdown) { this.markdown = markdown; }

    public List<Blog> getRecommendBlogs() { return recommendBlogs; }

    public void setRecommendBlogs(List<Blog> recommendBlogs) { this.recommendBlogs = recommendBlogs; }

    public boolean isUp() { return isUp; }

    public void setUp(boolean up) { isUp = up; }

    public boolean isFollow() { return isFollow; }

    public void setFollow(boolean follow) { isFollow = follow; }

    public boolean isCollect() { return isCollect; }

    public void setCollect(boolean collect) { isCollect = collect; }

    public List<Comment> getComments() { return comments; }

    public void setComments(List<Comment> comments) { this.comments = comments; }

    public List<String> getColumnNames() { return columnNames; }

    public void setColumnNames(List<String> columnNames) { this.columnNames = columnNames; }

    @Override
    public String toString() {
        return "CheckBlog{" +
                "blog=" + blog +
                ", author=" + author +
                ", htmlCode='" + htmlCode + '\'' +
                ", markdown='" + markdown + '\'' +
                ", recommendBlogs=" + recommendBlogs +
                ", isUp=" + isUp +
                ", isFollow=" + isFollow +
                ", isCollect=" + isCollect +
                ", comments=" + comments +
                ", columnNames=" + columnNames +
                '}';
    }
}