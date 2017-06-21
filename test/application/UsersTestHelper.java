package application;

class UsersTestHelper {
    static final String NOSUCHUSER = "nosuchuser";
    static final String DOESNTMATTER = "doesntmatter";
    static final String FIRST_NAME = "Elvis";
    static final String LAST_NAME = "Costello";
    static final String USER_EMAIL = "x@u.edu";
    static final String USER_NAME = "imperialBedroom";
    static final String USER_PASSWORD = "shrdlu";

    static User makeTestUser() {
        return User.create(FIRST_NAME, LAST_NAME, USER_EMAIL, USER_NAME, USER_PASSWORD);
    }

    static Users createUsers() {
        Users users = new Users();
        User testSubject = makeTestUser();
        users.register(testSubject);
        return users;
    }
}
