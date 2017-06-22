package application;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserAccountCreationTest {
    private Users users;

    @Before
    public void setUp() throws Exception {
        users = UsersTestHelper.createUsers();
    }

    @Test
    public void testCreateUser() {
        User foundUser = users.findByUserName(UsersTestHelper.USER_NAME);
        assertEquals(UsersTestHelper.FIRST_NAME, foundUser.getFirstName());
        assertEquals(UsersTestHelper.LAST_NAME, foundUser.getLastName());
        assertEquals(UsersTestHelper.USER_EMAIL, foundUser.getUserEmail());
        assertEquals(UsersTestHelper.USER_NAME, foundUser.getUserName());
        assertEquals(UsersTestHelper.USER_PASSWORD, foundUser.getPassword());
    }

    @Test(expected = DuplicateUserException.class)
    public void cannotCreateAccountWithSameUserName() {
        users.register(UsersTestHelper.makeTestUser());
    }

    @Test
    public void testNullResultWhenNoUserIsRegistered() {
        User foundUser = users.findByUserName("notFound");
        assertEquals(null, foundUser);
    }

}
