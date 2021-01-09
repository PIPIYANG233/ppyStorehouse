package domain;

import java.util.List;

public class FollowPage extends Page{
    private List<User> follow; // 每一页的数据集合

    public List<User> getFollow() { return follow; }

    public void setFollow(List<User> follow) { this.follow = follow; }

    @Override
    public String toString() {
        return "FollowPage{" +
                "follow=" + follow +
                '}';
    }
}
