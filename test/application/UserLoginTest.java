package application;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertTrue(users.login(username, password));
    }

    @Test
    public void cannotLoginWithUserNameThatDoesNotExist() {
        String username = UsersTestHelper.NOSUCHUSER;
        String password = UsersTestHelper.DOESNTMATTER;
        assertFalse(users.login(username, password));
    }
}
