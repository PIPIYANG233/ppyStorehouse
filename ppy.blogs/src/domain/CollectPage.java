package domain;

import java.util.List;

public class CollectPage extends Page{
    private List<Blog> collect; // 每一页的数据集合

    public List<Blog> getCollect() { return collect; }

    public void setCollect(List<Blog> collect) { this.collect = collect; }

    @Override
    public String toString() {
        return "CollectPage{" +
                "collect=" + collect +
                '}';
    }
}

