package domain;

import java.util.List;

public class FanPage extends Page{
    private List<User> fans; // 每一页的数据集合

    public List<User> getFans() { return fans; }

    public void setFans(List<User> fans) { this.fans = fans; }

    @Override
    public String toString() {
        return "FanPage{" +
                "fans=" + fans +
                '}';
    }
}
