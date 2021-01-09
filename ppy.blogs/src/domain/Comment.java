package domain;

import java.util.List;

public class Comment {
    private int id;
    private int user_id;
    private int blog_id;
    private String comment_content;
    private User commentUser;
    private List<Request> requests;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }

    public int getBlog_id() { return blog_id; }

    public void setBlog_id(int blog_id) { this.blog_id = blog_id; }

    public User getCommentUser() { return commentUser; }

    public void setCommentUser(User commentUser) { this.commentUser = commentUser; }

    public String getComment_content() { return comment_content; }

    public void setComment_content(String comment_content) { this.comment_content = comment_content; }

    public List<Request> getRequests() { return requests; }

    public void setRequests(List<Request> requests) { this.requests = requests; }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", blog_id=" + blog_id +
                ", comment_content='" + comment_content + '\'' +
                ", commentUser=" + commentUser +
                ", requests=" + requests +
                '}';
    }
}
