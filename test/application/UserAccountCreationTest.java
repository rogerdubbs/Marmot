package application;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserAccountCreationTest {
    String firstName = "Elvis";
    String lastName = "Costello";
    String userEmail = "x@u.edu";
    String userName = "imperialBedroom";
    String password = "shrdlu";

    @Test
    public void testCreateUser()
    {
        User testsubject = new User (firstName, lastName, userEmail, userName, password);
    }
}
