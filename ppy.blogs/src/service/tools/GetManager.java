package service.tools;

import domain.Manager;
import service.managerOption.ManagerOption;
import service.managerOption.ManagerOptionImp;

public class GetManager {
    public Manager getManager(int sortId, int start, int rank){
        // 获取管理数据
        ManagerOption managerOption = new ManagerOptionImp();
        Manager manager = new Manager();
        manager.setUsers(managerOption.getManagerUsers(start, rank));
        manager.setNow(start / 8);
        manager.setSortId(sortId);
        manager.setStart(start);
        int num = managerOption.getManagerNum(1, rank, 1);
        manager.setNum(num);
        manager.setPages(managerOption.getPages(num, 8));
        return manager;
    }

    public Manager getManager(int sortId, int start){
        // 获取管理数据
        ManagerOption managerOption = new ManagerOptionImp();
        Manager manager = new Manager();
        if(sortId == 2){
            manager.setBlogs(managerOption.getManagerBlogs(start, 4));
            manager.setNow(start / 8);
            manager.setSortId(sortId);
            manager.setStart(start);
            int num = managerOption.getManagerNum(2,4, 1);
            int recommendNum = managerOption.getManagerNum(3,null, 0);
            manager.setNum(num);
            manager.setRecommendNum(recommendNum);
            manager.setPages(managerOption.getPages(num, 8));
        }else if(sortId == 3){
            manager.setBlogs(managerOption.getManagerBlogs(start, 3));
            manager.setNow(start / 8);
            manager.setSortId(sortId);
            manager.setStart(start);
            int num = managerOption.getManagerNum(2, 3, 1);
            int recommendNum = managerOption.getManagerNum(3, null, 0);
            manager.setNum(num);
            manager.setRecommendNum(recommendNum);
            manager.setPages(managerOption.getPages(num, 8));
        }else if(sortId == 4){
            manager.setBlogs(managerOption.getManagerBlogs(start, 2));
            manager.setNow(start / 8);
            manager.setSortId(sortId);
            manager.setStart(start);
            int num = managerOption.getManagerNum(2, 2, 1);
            int recommendNum = managerOption.getManagerNum(3, null, 0);
            manager.setNum(num);
            manager.setRecommendNum(recommendNum);
            manager.setPages(managerOption.getPages(num, 8));
        }
        return manager;
    }

    public Manager getManager(int sortId){
        // 获取管理数据
        ManagerOption managerOption = new ManagerOptionImp();
        Manager manager = new Manager();
        if(sortId == 5){
            manager.setBlogs(managerOption.getRecommendBlogs());
            manager.setSortId(sortId);
            int recommendNum = managerOption.getManagerNum(3, null, 0);
            manager.setRecommendNum(recommendNum);
        }else{
            manager.setLabels(managerOption.getLabels());
            manager.setSortId(sortId);
        }
        return manager;
    }
}