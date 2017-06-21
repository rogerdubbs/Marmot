package application;

class UsersTestHelper {
    static String firstName = "Elvis";
    static String lastName = "Costello";
    static String userEmail = "x@u.edu";
    static String userName = "imperialBedroom";
    static String password = "shrdlu";

    static User makeTestUser() {
        return User.create(firstName, lastName, userEmail, userName, password);
    }

    static Users createUsers() {
        Users users = new Users();
        User testSubject = makeTestUser();
        users.register(testSubject);
        return users;
    }
}
