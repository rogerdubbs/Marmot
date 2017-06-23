package application;

class UsersTestHelper {
    static final String NOSUCHUSER = "nosuchuser";
    static final String DOESNTMATTER = "doesntmatter";
    static final String FIRST_NAME = "Elvis";
    static final String LAST_NAME = "Costello";
    static final String USER_EMAIL = "x@u.edu";
    static final String USER_NAME_SELLER = "seller";
    static final String USER_NAME = "imperialBedroom";
    static final String USER_NAME2 = "imperialBedroom2";
    static final String USER_PASSWORD = "shrdlu";

    static User makeTestUser() {
        return User.create(FIRST_NAME, LAST_NAME, USER_EMAIL, USER_NAME, USER_PASSWORD);
    }

    private static User makeTestUser2() {
        return User.create(FIRST_NAME, LAST_NAME, "user2@nowhere.com", USER_NAME2, USER_PASSWORD);
    }

    private static User makeSeller() {
        User user = User.create(FIRST_NAME, LAST_NAME, "seller@nowhere.com", USER_NAME_SELLER, USER_PASSWORD);
        user.setSeller();
        return user;
    }

    static Users createUsers() {
        Users users = new Users();
        users.register(makeTestUser());
        users.register(makeTestUser2());
        users.register(makeSeller());
        return users;
    }
}
