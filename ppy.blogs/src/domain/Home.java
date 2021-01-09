package domain;

import java.util.List;

public class Home {
    private List<String> labels;
    private List<Blog> centerRecommendBlogs;
    private List<Blog> rightRecommendBlogs;
    private int labelId;
    private int sortId;

    public List<String> getLabels() { return labels; }

    public void setLabels(List<String> labels) { this.labels = labels; }

    public List<Blog> getCenterRecommendBlogs() { return centerRecommendBlogs; }

    public void setCenterRecommendBlogs(List<Blog> centerRecommendBlogs) { this.centerRecommendBlogs = centerRecommendBlogs; }

    public List<Blog> getRightRecommendBlogs() { return rightRecommendBlogs; }

    public void setRightRecommendBlogs(List<Blog> rightRecommendBlogs) { this.rightRecommendBlogs = rightRecommendBlogs; }

    public int getLabelId() { return labelId; }

    public void setLabelId(int labelId) { this.labelId = labelId; }

    public int getSortId() { return sortId; }

    public void setSortId(int sortId) { this.sortId = sortId; }

    @Override
    public String toString() {
        return "Home{" +
                "labels=" + labels +
                ", centerRecommendBlogs=" + centerRecommendBlogs +
                ", rightRecommendBlogs=" + rightRecommendBlogs +
                ", labelId=" + labelId +
                ", sortId=" + sortId +
                '}';
    }
}
