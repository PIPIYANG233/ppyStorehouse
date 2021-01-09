package domain;

public class Column {
    private boolean isSelected;
    private String column_name;
    private String introduce;
    private int columnNum;
    private int ownerId;
    private int id;

    public boolean isSelected() { return isSelected; }

    public void setSelected(boolean selected) { isSelected = selected; }

    public String getColumn_name() { return column_name; }

    public void setColumn_name(String column_name) { this.column_name = column_name; }

    public String getIntroduce() { return introduce; }

    public void setIntroduce(String introduce) { this.introduce = introduce; }

    public int getColumnNum() { return columnNum; }

    public void setColumnNum(int columnNum) { this.columnNum = columnNum; }

    public int getOwnerId() { return ownerId; }

    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "Column{" +
                "isSelected=" + isSelected +
                ", column_name='" + column_name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", columnNum=" + columnNum +
                ", ownerId=" + ownerId +
                ", id=" + id +
                '}';
    }
}
