package domain;

import java.sql.Date;

public class User {
    private boolean follow;
    private int id;
    private String user_name;
    private String true_name;
    private String email;
    private String pwd;
    private Date birthday;
    private int sex;
    private String head_img;
    private int fans_num;
    private int follow_num;
    private int blogs_num;
    private int scan_num;
    private int up_num;
    private int collected_num;
    private int collect_num;
    private int column_num;
    private int rank;
    private String introduce;

    public boolean isFollow() { return follow; }

    public void setFollow(boolean follow) { this.follow = follow; }

    public int getUp_num() { return up_num; }

    public void setUp_num(int up_num) { this.up_num = up_num; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTrue_name() { return true_name; }

    public void setTrue_name(String true_name) { this.true_name = true_name; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getBirthday() { return birthday; }

    public void setBirthday(Date birthday) { this.birthday = birthday; }

    public int getSex() { return sex; }

    public void setSex(int sex) { this.sex = sex; }

    public String getHead_img() { return head_img; }

    public void setHead_img(String head_img) { this.head_img = head_img; }

    public int getFans_num() { return fans_num; }

    public void setFans_num(int fans_num) { this.fans_num = fans_num; }

    public int getFollow_num() { return follow_num; }

    public void setFollow_num(int follow_num) { this.follow_num = follow_num; }

    public int getBlogs_num() { return blogs_num; }

    public void setBlogs_num(int blogs_num) { this.blogs_num = blogs_num; }

    public int getScan_num() { return scan_num; }

    public void setScan_num(int scan_num) { this.scan_num = scan_num; }

    public int getCollected_num() { return collected_num; }

    public void setCollected_num(int collected_num) { this.collected_num = collected_num; }

    public int getCollect_num() { return collect_num; }

    public void setCollect_num(int collect_num) { this.collect_num = collect_num; }

    public int getColumn_num() { return column_num; }

    public void setColumn_num(int column_num) { this.column_num = column_num; }


    public int getRank() { return rank; }

    public void setRank(int rank) { this.rank = rank; }

    public String getIntroduce() { return introduce; }

    public void setIntroduce(String introduce) { this.introduce = introduce; }

    @Override
    public String toString() {
        return "User{" +
                "rank=" + rank +
                ", follow=" + follow +
                ", id=" + id +
                ", user_name='" + user_name + '\'' +
                ", true_name='" + true_name + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", head_img='" + head_img + '\'' +
                ", fans_num=" + fans_num +
                ", follow_num=" + follow_num +
                ", blogs_num=" + blogs_num +
                ", scan_num=" + scan_num +
                ", up_num=" + up_num +
                ", collected_num=" + collected_num +
                ", collect_num=" + collect_num +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
