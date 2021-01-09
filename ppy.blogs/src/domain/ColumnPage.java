package domain;

import java.util.List;

public class ColumnPage extends Page{
    private List<Column> columns; // 每一页的分类集合

    public List<Column> getColumns() { return columns; }

    public void setColumns(List<Column> columns) { this.columns = columns; }

    @Override
    public String toString() {
        return "ColumnPage{" +
                "columns=" + columns +
                '}';
    }
}
