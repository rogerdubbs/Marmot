package application;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CreateAuctionTest {
    private Users users;
    private String itemDescription;
    private long currentTimeMillis;
    private Date startTime;
    private Date endTime;
    private User user;
    private double startingPrice;

    @Before
    public void setUp() throws Exception {
        users = UsersTestHelper.createUsers();
        itemDescription = "Best non-car non-software item ever";
        currentTimeMillis = System.currentTimeMillis();
        startTime = new Date(currentTimeMillis + 1000000);
        endTime = new Date(currentTimeMillis + 2000000);
        user = users.findByUserName(UsersTestHelper.USER_NAME);
        startingPrice = 0.99;
    }

    @Test
    public void testMakeSeller() {
        user.setSeller();
        assertEquals(true, user.isSeller());
    }

    @Test
    public void canCreateAuction() {
        // Start Time > Now.
        // End Time > Start Time
        // Seller cannot bid on their own auction
        users.login(UsersTestHelper.USER_NAME, UsersTestHelper.USER_PASSWORD);
        user.setSeller();
        Auction auction = new Auction(user, itemDescription, startingPrice, startTime, endTime);
        assertEquals(user, auction.getSeller());
        assertEquals(startTime, auction.getStartTime());
        assertEquals(endTime, auction.getEndTime());
        assertEquals(itemDescription, auction.getItemDescription());
        assertEquals(startingPrice, auction.getStartingPrice(), 0.005);

    }

    @Test(expected = NotSellerException.class)
    public void cannotCreateAuctionIfNotSeller() {
        users.login(UsersTestHelper.USER_NAME, UsersTestHelper.USER_PASSWORD);
        new Auction(user, itemDescription, startingPrice, startTime, endTime);
    }

    @Test(expected = NotLoggedInException.class)
    public void cannotcreateAuctionIfNotLoggedIn() {
        new Auction(user, itemDescription, startingPrice, startTime, endTime);
    }

    @Test(expected = IllegalAuctionException.class)
    public void cannotcreateAuctionIfEndTimeLessThanStartTime() {
        users.login(UsersTestHelper.USER_NAME, UsersTestHelper.USER_PASSWORD);
        user.setSeller();
        endTime = new Date(currentTimeMillis);
        new Auction(user, itemDescription, startingPrice, startTime, endTime);
    }

    @Test(expected = IllegalAuctionException.class)
    public void cannotCreateAuctionIfStartTimeLessThanNow() {
        users.login(UsersTestHelper.USER_NAME, UsersTestHelper.USER_PASSWORD);
        user.setSeller();
        startTime = new Date(currentTimeMillis-50);
        new Auction(user, itemDescription, startingPrice, startTime, endTime);
    }
    @Test
    public void auctionIsStarted(){
        users.login(UsersTestHelper.USER_NAME, UsersTestHelper.USER_PASSWORD);
        user.setSeller();
        Auction auction = new Auction(user, itemDescription, startingPrice, startTime, endTime);
        auction.onStart();
        assertEquals(Auction.State.active, auction.getState());
    }
}
