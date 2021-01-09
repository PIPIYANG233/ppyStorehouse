package domain;

import java.util.List;

public class BlogPage  extends Page{
    private List<Blog> blogs; // 每一页的数据集合
    private List<Integer> years; // 博客年份集合
    private int year; // 当前年份
    private int month; // 月份选择
    private List<String> columns; // 博主所有分类专栏
    private String column; // 当前具体分类专栏
    private int type; // 是否原创
    private String mainContent; // 主要关键字

    public List<Blog> getBlogs() { return blogs; }

    public void setBlogs(List<Blog> list) { this.blogs = list; }

    public List<Integer> getYears() { return years; }

    public void setYears(List<Integer> years) { this.years = years; }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    public int getMonth() { return month; }

    public void setMonth(int month) { this.month = month; }

    public List<String> getColumns() { return columns; }

    public void setColumns(List<String> columns) { this.columns = columns; }

    public String getColumn() { return column; }

    public void setColumn(String column) { this.column = column; }

    public int getType() { return type; }

    public void setType(int type) { this.type = type; }

    public String getMainContent() { return mainContent; }

    public void setMainContent(String mainContent) { this.mainContent = mainContent; }

    @Override
    public String toString() {
        return "BlogPage{" +
                "blogs=" + blogs +
                ", years=" + years +
                ", year=" + year +
                ", month=" + month +
                ", columns=" + columns +
                ", column='" + column + '\'' +
                ", type=" + type +
                ", mainContent='" + mainContent + '\'' +
                '}';
    }
}
