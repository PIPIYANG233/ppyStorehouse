package domain;

public class Page {
    private int now; // 当前是第几页
    private int start; // 每一页开始位置
    private int pages; // 总页数
    private int num; // 总数;


    public int getNow() { return now; }

    public void setNow(int now) { this.now = now; }

    public int getStart() { return start; }

    public void setStart(int start) { this.start = start; }

    public int getPages() { return pages; }

    public void setPages(int pages) { this.pages = pages; }

    public int getNum() { return num; }

    public void setNum(int num) { this.num = num; }

    @Override
    public String toString() {
        return "Page{" +
                "now=" + now +
                ", start=" + start +
                ", pages=" + pages +
                ", num=" + num +
                '}';
    }
}
