package domain;

public class Request {
    private int id;
    private int comment_id;
    private String req_content;
    private int comment_user_id;
    private int req_user_id;
    private User requestUser;
    private User commentUser;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getComment_id() { return comment_id; }

    public void setComment_id(int comment_id) { this.comment_id = comment_id; }

    public String getReq_content() { return req_content; }

    public void setReq_content(String req_content) { this.req_content = req_content; }

    public int getComment_user_id() { return comment_user_id; }

    public void setComment_user_id(int comment_user_id) { this.comment_user_id = comment_user_id; }

    public int getReq_user_id() { return req_user_id; }

    public void setReq_user_id(int req_user_id) { this.req_user_id = req_user_id; }

    public User getRequestUser() { return requestUser; }

    public void setRequestUser(User requestUser) { this.requestUser = requestUser; }

    public User getCommentUser() { return commentUser; }

    public void setCommentUser(User commentUser) { this.commentUser = commentUser; }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", comment_id=" + comment_id +
                ", req_content='" + req_content + '\'' +
                ", comment_user_id=" + comment_user_id +
                ", req_user_id=" + req_user_id +
                ", requestUser=" + requestUser +
                ", commentUser=" + commentUser +
                '}';
    }
}
