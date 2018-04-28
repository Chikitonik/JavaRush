package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.List;

public class MainModel implements Model{
    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();
    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setDisplayDeletedUserList(false);
//        List<User> listUserLevel = userService.getUsersBetweenLevels(1,100);
        List<User> listUserLevel = getAllUsers();//add for live
        modelData.setUsers(listUserLevel);
    }
    public void loadDeletedUsers() {
        modelData.setDisplayDeletedUserList(true);
        List<User> users = userService.getAllDeletedUsers();
        modelData.setUsers(users);
    }
    public void loadUserById (long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }
    public void deleteUserById(long id){
        User user = userService.deleteUser(id);
        loadUsers();
        modelData.setActiveUser(user);
    }
    private List<User> getAllUsers() {
        return userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1,100));
    }
    public void changeUserData(String name, long id, int level){
        User user = userService.createOrUpdateUser(name, id, level);
        loadUsers();
    }
}
