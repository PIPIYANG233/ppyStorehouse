package service.tools;

import domain.Search;
import service.searchOption.SearchOption;
import service.searchOption.SearchOptionImp;

public class GetSearch {
    public Search getSearch(String searchContent, int start, int userId){
        // 获取搜索
        SearchOption searchOption = new SearchOptionImp();
        Search search = new Search();
        search.setAuthor(true);
        // 搜索博主集合
        search.setSearchUsers(searchOption.getSearchUsers(searchContent, start, userId));
        // 推荐标签集合
        search.setRecommendLabels(searchOption.getRecommendLabels(searchContent));
        search.setNow(start / 8);
        search.setStart(start);
        int num = searchOption.getSearchNum(true, "%" + searchContent + "%", 1);
        search.setNum(num);
        search.setPages(searchOption.getPages(num, 8));
        search.setSearchContent(searchContent);
        return search;
    }

    public Search getSearch(int sortId, String searchContent, int start){
        // 获取搜索
        SearchOption searchOption = new SearchOptionImp();
        Search search = new Search();
        search.setAuthor(false);
        search.setSortId(sortId);
        // 搜索博客集合
        search.setSearchBlogs(searchOption.getSearchBlogs(sortId, searchContent, start));
        // 推荐标签集合
        search.setRecommendLabels(searchOption.getRecommendLabels(searchContent));
        search.setNow(start / 8);
        search.setStart(start);
        int num = searchOption.getSearchNum(false, "%" + searchContent + "%", 2);
        search.setNum(num);
        search.setPages(searchOption.getPages(num, 8));
        search.setSearchContent(searchContent);
        return search;
    }
}