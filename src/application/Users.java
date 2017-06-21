package application;

import java.util.HashMap;

class Users {
    private HashMap<String,User> registeredUsers = new HashMap<>();

    void register(User user) {
        if (findByUserName(user.getUserName()) != null)
            throw new DuplicateUserException();
        registeredUsers.put(user.getUserName(), user);
    }

    User findByUserName(String userName) {
        return registeredUsers.get(userName);
    }
}
