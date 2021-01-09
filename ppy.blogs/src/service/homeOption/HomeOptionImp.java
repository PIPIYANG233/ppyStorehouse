package service.homeOption;

import dao.homeData.GetHomeDataImp;
import domain.Blog;
import java.util.List;

public class HomeOptionImp implements HomeOption {
    @Override
    public List<String> getHomeLabels() {
        return new GetHomeDataImp().getHomeLabels();
    }

    @Override
    public List<Blog> getCenterRecommendBlogs(int labelId, int sortId) {
        return new GetHomeDataImp().getCenterRecommendBlogs(labelId, sortId);
    }

    @Override
    public List<Blog> getRightRecommendBlogs() {
        return new GetHomeDataImp().getRightRecommendBlogs();
    }
}
