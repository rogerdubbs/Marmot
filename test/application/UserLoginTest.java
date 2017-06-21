package application;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserLoginTest {
    @Test
    public void test() {
        String username = "imperialBedroom";
        String password = "shrdlu";
        assertTrue(Users.login(username, password));
    }
}
