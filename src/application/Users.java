package application;

import java.util.HashMap;

class Users {
    private HashMap<String,User> registeredUsers = new HashMap<String,User>();

    void register(User user) {
        registeredUsers.put(user.getUserName(), user);
    }

    User findByUserName(String userName) {
        return registeredUsers.get(userName);
    }
}
