package service.tools;

import domain.Home;
import service.homeOption.HomeOption;
import service.homeOption.HomeOptionImp;

public class GetHome {
    public Home getHome(int labelId, int sortId){
        HomeOption homeOption = new HomeOptionImp();
        Home home = new Home();
        home.setLabels(homeOption.getHomeLabels());
        home.setCenterRecommendBlogs(homeOption.getCenterRecommendBlogs(labelId, sortId));
        home.setRightRecommendBlogs(homeOption.getRightRecommendBlogs());
        home.setLabelId(labelId);
        home.setSortId(sortId);
        return home;
    }
}