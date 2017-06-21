package application;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserAccountCreationTest {
    private String firstName = "Elvis";
    private String lastName = "Costello";
    private String userEmail = "x@u.edu";
    private String userName = "imperialBedroom";
    private String password = "shrdlu";

    @Test
    public void testCreateUser() {
        Users users = new Users();
        User testSubject = User.create(firstName, lastName, userEmail, userName, password);
        users.register(testSubject);
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
        Users users = new Users();
        User testSubject = User.create(firstName, lastName, userEmail, userName, password);
        users.register(testSubject);
        users.register(testSubject);
    }

    @Test
    public void testNullResultWhenNoUserIsRegistered() {
        Users users = new Users();
        User foundUser = users.findByUserName(userName);
        assertEquals(null, foundUser);

    }

}
