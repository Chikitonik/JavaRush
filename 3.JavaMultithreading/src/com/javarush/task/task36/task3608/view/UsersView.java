package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

public class UsersView implements View {
    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {
        String displayUsers = modelData.isDisplayDeletedUserList() ? "All deleted users:" : "All users:";
        System.out.println(displayUsers);
        for (User user: modelData.getUsers()){
            System.out.println("\t" + user);
        }
        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

//    @Override
//    public void fireEventUserChanged(String name, long id, int level) {
//
//    }

//    @Override
//    public void fireEventUserDeleted(long id) {
//        throw new UnsupportedOperationException();
//    }

    public void fireEventShowAllUsers(){
        controller.onShowAllUsers();
    }
    public void fireEventShowDeletedUsers(){
        controller.onShowAllDeletedUsers();
    }
    public void fireEventOpenUserEditForm (long id) {
        controller.onOpenUserEditForm(id);
    }

}
