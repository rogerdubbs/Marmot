package application;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserAccountCreationTest {
    private String firstName = "Elvis";
    private String lastName = "Costello";
    private String userEmail = "x@u.edu";
    private String userName = "imperialBedroom";
    private String password = "shrdlu";
    private Users users;
    private User testSubject;

    @Before
    public void setUp() throws Exception {
        users = new Users();
        testSubject = User.create(firstName, lastName, userEmail, userName, password);
        users.register(testSubject);
    }

    @Test
    public void testCreateUser() {
        User foundUser = users.findByUserName(userName);
        assertEquals(testSubject, foundUser);
        assertEquals(firstName, testSubject.getFirstName());
        assertEquals(lastName, testSubject.getLastName());
        assertEquals(userEmail, testSubject.getUserEmail());
        assertEquals(userName, testSubject.getUserName());
        assertEquals(password, testSubject.getPassword());
    }

    @Test(expected = DuplicateUserException.class)
    public void cannotCreateAccountWithSameUserName() {
        users.register(testSubject);
    }

    @Test
    public void testNullResultWhenNoUserIsRegistered() {
        User foundUser = users.findByUserName("notFound");
        assertEquals(null, foundUser);

    }

}
