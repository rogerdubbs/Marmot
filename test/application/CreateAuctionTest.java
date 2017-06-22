package application;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateAuctionTest {
    private Users users;
    @Before
    public void setUp() throws Exception {
        users = UsersTestHelper.createUsers();
    }

    @Test
    public void testMakeSeller() {
        User user = users.findByUserName(UsersTestHelper.USER_NAME);
        user.setSeller();
        assertEquals(true, user.isSeller());
    }
}
