package application;

import java.util.HashMap;
import java.util.Objects;

class Users {
    private HashMap<String, User> registeredUsers = new HashMap<>();

    void register(User user) {
        if (findByUserName(user.getUserName()) != null)
            throw new DuplicateUserException();
        registeredUsers.put(user.getUserName(), user);
    }

    User findByUserName(String userName) {
        return registeredUsers.get(userName);
    }

    boolean login(String username, String password) {
        User user = findByUserName(username);
        if (user != null && Objects.equals(user.getPassword(), password)) {
            user.login();
            return true;
        } else return false;
    }

    void logout(String username) {
        User user = findByUserName(username);
        user.logout();
    }
}
