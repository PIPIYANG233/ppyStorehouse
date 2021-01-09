package domain;

import java.sql.Timestamp;
import java.util.List;

public class Blog {
    private int id;
    private int blog_author_id;
    private String blog_title;
    private int blog_scan_num;
    private int blog_up_num;
    private Timestamp blog_pub_date;
    private int status;
    private List<String> labels;
    private List<String> columns;
    private int type;
    private int blog_collect_num;
    private boolean isRecommend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlog_author_id() {
        return blog_author_id;
    }

    public void setBlog_author_id(int blog_author_id) {
        this.blog_author_id = blog_author_id;
    }

    public String getBlog_title() {
        return blog_title;
    }

    public void setBlog_title(String blog_title) {
        this.blog_title = blog_title;
    }

    public int getBlog_scan_num() {
        return blog_scan_num;
    }

    public void setBlog_scan_num(int blog_scan_num) {
        this.blog_scan_num = blog_scan_num;
    }

    public int getBlog_up_num() {
        return blog_up_num;
    }

    public void setBlog_up_num(int blog_up_num) {
        this.blog_up_num = blog_up_num;
    }

    public Timestamp getBlog_pub_date() {
        return blog_pub_date;
    }

    public void setBlog_pub_date(Timestamp blog_pub_date) { this.blog_pub_date = blog_pub_date; }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getLabels() { return labels; }

    public void setLabels(List<String> labels) { this.labels = labels; }

    public List<String> getColumns() { return columns; }

    public void setColumns(List<String> columns) { this.columns = columns; }

    public int getType() { return type; }

    public void setType(int type) { this.type = type; }

    public int getBlog_collect_num() { return blog_collect_num; }

    public void setBlog_collect_num(int blog_collect_num) { this.blog_collect_num = blog_collect_num; }

    public boolean isRecommend() { return isRecommend; }

    public void setRecommend(boolean recommend) { isRecommend = recommend; }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", blog_author_id=" + blog_author_id +
                ", blog_title='" + blog_title + '\'' +
                ", blog_scan_num=" + blog_scan_num +
                ", blog_up_num=" + blog_up_num +
                ", blog_pub_date=" + blog_pub_date +
                ", status=" + status +
                ", labels=" + labels +
                ", columns=" + columns +
                ", type=" + type +
                ", blog_collect_num=" + blog_collect_num +
                ", isRecommend=" + isRecommend +
                '}';
    }
}
