package application;

class User {
    private final String firstName;
    private final String lastName;
    private final String userEmail;
    private final String userName;
    private final String password;
    private boolean loggedIn;

    private User(String firstName, String lastName, String userEmail, String userName, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
    }

    static User create(String firstName, String lastName, String userEmail, String userName, String password) {
        return new User(firstName, lastName, userEmail, userName, password);
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    String getUserEmail() {
        return userEmail;
    }

    String getUserName() {
        return userName;
    }

    String getPassword() {
        return password;
    }

    boolean isLoggedIn() {
        return loggedIn;
    }

    void login() {
        loggedIn = true;
    }

    void logout() {
        loggedIn = false;
    }
}
