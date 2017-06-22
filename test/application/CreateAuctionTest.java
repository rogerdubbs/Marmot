package application;

import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;

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

    @Test
    public void canCreateAuction() {
        // User must be logged in an a seller.
        // Start Time > Now.
        // End Time > Start Time
        // Seller cannot bid on their own auction
        User user = users.findByUserName(UsersTestHelper.USER_NAME);
        users.login(UsersTestHelper.USER_NAME,UsersTestHelper.USER_PASSWORD);
        String itemDescription = "Best non-car non-software item ever";
        long currentTimeMillis = System.currentTimeMillis();
        Date startTime = new Date(currentTimeMillis + 1000000);
        Date endTime = new Date(currentTimeMillis + 2000000);
        double startingPrice = 0.99;
        Auction auction = new Auction(user, itemDescription, startingPrice, startTime, endTime);
        assertEquals(user,auction.getSeller());
    }
}
