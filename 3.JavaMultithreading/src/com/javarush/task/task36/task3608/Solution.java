package com.javarush.task.task36.task3608;

import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.MainModel;
import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.view.EditUserView;
import com.javarush.task.task36.task3608.view.UsersView;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
//        Model model = new MainModel();
//        UsersView usersView = new UsersView();
//        EditUserView editUserView = new EditUserView();
//        Controller controller = new Controller();
//
//        usersView.setController(controller);
//        editUserView.setController(controller);
//        controller.setModel(model);
//        controller.setUsersView(usersView);
//        controller.setEditUserView(editUserView);
//
//        usersView.fireEventShowAllUsers();
//        usersView.fireEventOpenUserEditForm(126L);
//        editUserView.fireEventUserDeleted(124L);
//        editUserView.fireEventUserChanged("Paltus",126L,3);
//        usersView.fireEventShowDeletedUsers();

        Direction_my direction_my = Direction_my.UP;
        System.out.println(Arrays.toString(Direction_my.values()));
        System.out.println(direction_my.opposite());
}

    enum Direction_my {
        UP,
        DOWN,
        LEFT,
        RIGHT;
        public Direction_my opposite() {
            return this == UP ? DOWN : UP;
        }
    }
}