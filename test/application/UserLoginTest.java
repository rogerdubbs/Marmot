package application;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserLoginTest {

    private Users users;

    @Before
    public void setUp() throws Exception {
        users = UsersTestHelper.createUsers();
    }

    @Test
    public void canLoginWithRegisteredUserAndCorrectPassword() {
        String username = UsersTestHelper.USER_NAME;
        String password = UsersTestHelper.USER_PASSWORD;
        assertEquals(true, users.login(username, password));
        User user = users.findByUserName(username);
        assertEquals(true, user.isLoggedIn());
    }

    @Test
    public void cannotLoginWithIncorrectPassword() {
        String username = UsersTestHelper.USER_NAME;
        String password = UsersTestHelper.DOESNTMATTER;
        assertEquals(false, users.login(username, password));
        User user = users.findByUserName(username);
        assertEquals(false, user.isLoggedIn());
    }

    @Test
    public void cannotLoginWithUserNameThatDoesNotExist() {
        String username = UsersTestHelper.NOSUCHUSER;
        String password = UsersTestHelper.DOESNTMATTER;
        assertEquals(false, users.login(username, password));
    }
}
