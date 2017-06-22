package application;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class AuctionBiddingTest {
    private Users users;
    private String itemDescription;
    private long currentTimeMillis;
    private Date startTime;
    private Date endTime;
    private User user;
    private double startingPrice;
    private Auction auction;

    @Before
    public void setUp() throws Exception {
        users = UsersTestHelper.createUsers();
        itemDescription = "Best non-car non-software item ever";
        currentTimeMillis = System.currentTimeMillis();
        startTime = new Date(currentTimeMillis + 1000000);
        endTime = new Date(currentTimeMillis + 2000000);
        user = users.findByUserName(UsersTestHelper.USER_NAME);
        startingPrice = 0.99;
        users.login(UsersTestHelper.USER_NAME, UsersTestHelper.USER_PASSWORD);
        user.setSeller();
        auction = new Auction(user, itemDescription, startingPrice, startTime, endTime);
    }

    @Test(expected = AuctionNotStartedException.class)
    public void cannotBidIfAuctionNotStarted() {
        User bidder = users.findByUserName(UsersTestHelper.USER_NAME);
        auction.placeBid(bidder, startingPrice);
    }

    @Test(expected = BidTooLowException.class)
    public void initialBidRejectedIfBelowStartingPrice() {
        User bidder = users.findByUserName(UsersTestHelper.USER_NAME);
        auction.placeBid(bidder, startingPrice-0.01);
    }

    // If it is the first bid, >= starting price
    // If it is 2nd+ bid, > current high bid
    // Bidder has to be logged in
    // Bidder can't be the seller
}
