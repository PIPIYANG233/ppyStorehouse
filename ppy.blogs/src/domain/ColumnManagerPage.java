package domain;

import java.util.List;

public class ColumnManagerPage extends Page{
    private int columnId; // 分类id
    private String columnName; // 分类名
    private String columnIntroduce; // 分类介绍
    private List<Blog> columnBlogs; // 每一页的分类集合

    public int getColumnId() { return columnId; }

    public void setColumnId(int columnId) { this.columnId = columnId; }

    public String getColumnName() { return columnName; }

    public void setColumnName(String columnName) { this.columnName = columnName; }

    public String getColumnIntroduce() { return columnIntroduce; }

    public void setColumnIntroduce(String columnIntroduce) { this.columnIntroduce = columnIntroduce; }

    public List<Blog> getColumnBlogs() { return columnBlogs; }

    public void setColumnBlogs(List<Blog> columnBlogs) { this.columnBlogs = columnBlogs; }

    @Override
    public String toString() {
        return "ColumnManagerPage{" +
                "columnId=" + columnId +
                ", columnName='" + columnName + '\'' +
                ", columnIntroduce='" + columnIntroduce + '\'' +
                ", columnBlogs=" + columnBlogs +
                '}';
    }
}
