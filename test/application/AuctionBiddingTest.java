package application;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AuctionBiddingTest {
    private Users users;
    private double startingPrice;
    private Auction auction;

    @Before
    public void setUp() throws Exception {
        users = UsersTestHelper.createUsers();
        String itemDescription = "Best non-car non-software item ever";
        long currentTimeMillis = System.currentTimeMillis();
        Date startTime = new Date(currentTimeMillis + 1000000);
        Date endTime = new Date(currentTimeMillis + 2000000);
        User seller = users.findByUserName(UsersTestHelper.USER_NAME_SELLER);
        startingPrice = 0.99;
        users.login(UsersTestHelper.USER_NAME_SELLER, UsersTestHelper.USER_PASSWORD);
        users.login(UsersTestHelper.USER_NAME, UsersTestHelper.USER_PASSWORD);
        users.login(UsersTestHelper.USER_NAME2, UsersTestHelper.USER_PASSWORD);
        auction = new Auction(seller, itemDescription, startingPrice, startTime, endTime);
    }

    @Test(expected = AuctionNotStartedException.class)
    public void cannotBidIfAuctionNotStarted() {
        User bidder = users.findByUserName(UsersTestHelper.USER_NAME);
        auction.placeBid(bidder, startingPrice);
    }

    @Test(expected = IllegalBidderException.class)
    public void cannotBidOnOwnAuction() {
        User bidder = users.findByUserName(UsersTestHelper.USER_NAME_SELLER);
        auction.onStart();
        auction.placeBid(bidder, startingPrice);
    }

    @Test(expected = NotLoggedInException.class)
    public void cannotBidIfNotLoggedIn() {
        User bidder = users.findByUserName(UsersTestHelper.USER_NAME);
        bidder.logout();
        auction.onStart();
        auction.placeBid(bidder, startingPrice);
    }

    @Test(expected = BidTooLowException.class)
    public void initialBidRejectedIfBelowStartingPrice() {
        User bidder = users.findByUserName(UsersTestHelper.USER_NAME);
        auction.placeBid(bidder, startingPrice - 0.01);
    }

    @Test
    public void BidderIsHighBidderIfStartingBidAtStartingPrice() {
        User bidder = users.findByUserName(UsersTestHelper.USER_NAME);
        auction.onStart();
        auction.placeBid(bidder, startingPrice);
        assertEquals(bidder, auction.getHighBidder());
    }

    @Test
    public void BidAmountIsStartingPriceIfFirstBidAtStartingPrice() {
        User bidder = users.findByUserName(UsersTestHelper.USER_NAME);
        auction.onStart();
        auction.placeBid(bidder, startingPrice);
        assertEquals(startingPrice, auction.getHighBid(), 0.001);
    }

    @Test
    public void lowerBidDoesNotBecomeHighBid() {
        User bidder = users.findByUserName(UsersTestHelper.USER_NAME);
        User bidder2 = users.findByUserName(UsersTestHelper.USER_NAME2);
        auction.onStart();
        auction.placeBid(bidder, startingPrice + 0.10);
        assertEquals(false, auction.placeBid(bidder2, startingPrice));
        assertEquals(startingPrice + 0.10, auction.getHighBid(), 0.001);
    }

    @Test
    public void canOutbidOldHighBid() {
        User bidder = users.findByUserName(UsersTestHelper.USER_NAME);
        User bidder2 = users.findByUserName(UsersTestHelper.USER_NAME2);
        auction.onStart();
        auction.placeBid(bidder, startingPrice + 0.10);
        assertEquals(true, auction.placeBid(bidder2, startingPrice + 0.20));
        assertEquals(startingPrice + 0.20, auction.getHighBid(), 0.001);
        assertEquals(bidder2, auction.getHighBidder());
    }
}
