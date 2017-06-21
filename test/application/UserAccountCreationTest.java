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
    public void testCreateUser()
    {
        Users users = new Users();
        User testsubject = User.register(firstName, lastName, userEmail, userName, password);
        assertEquals(firstName, testsubject.getFirstName());
        assertEquals(lastName, testsubject.getLastName());
        assertEquals(userEmail, testsubject.getUserEmail());
        assertEquals(userName, testsubject.getUserName());
        assertEquals(password, testsubject.getPassword());
    }

}
