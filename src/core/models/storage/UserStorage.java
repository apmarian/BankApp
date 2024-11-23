/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class UserStorage extends Storage implements UserStorageInterface {

    private ArrayList<User> users;
    private static UserStorage userStorage;

    public void addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    private UserStorage() {
        this.users = new ArrayList<>();
    }

    public static UserStorage getInstance() {
        if (userStorage == null) {
            userStorage = new UserStorage();
        }
        return userStorage;
    }

    public ArrayList<User> userOrderbyId() {
        users.sort((obj1, obj2) -> (obj1.getId() - obj2.getId()));
        return users;
    }
}
