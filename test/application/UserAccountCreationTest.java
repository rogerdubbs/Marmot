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
        User foundUser = users.findByUserName(UsersTestHelper.userName);
        assertEquals(UsersTestHelper.firstName, foundUser.getFirstName());
        assertEquals(UsersTestHelper.lastName, foundUser.getLastName());
        assertEquals(UsersTestHelper.userEmail, foundUser.getUserEmail());
        assertEquals(UsersTestHelper.userName, foundUser.getUserName());
        assertEquals(UsersTestHelper.password, foundUser.getPassword());
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
