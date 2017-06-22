package application;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AuctionBiddingTest {
    private Users users;
    private double startingPrice;
    private Auction auction;
    private User bidder;
    private User seller;
    private User bidder2;

    @Before
    public void setUp() throws Exception {
        users = UsersTestHelper.createUsers();
        String itemDescription = "Best non-car non-software item ever";
        long currentTimeMillis = System.currentTimeMillis();
        Date startTime = new Date(currentTimeMillis + 1000000);
        Date endTime = new Date(currentTimeMillis + 2000000);
        seller = users.findByUserName(UsersTestHelper.USER_NAME_SELLER);
        startingPrice = 0.99;
        users.login(UsersTestHelper.USER_NAME_SELLER, UsersTestHelper.USER_PASSWORD);
        users.login(UsersTestHelper.USER_NAME, UsersTestHelper.USER_PASSWORD);
        users.login(UsersTestHelper.USER_NAME2, UsersTestHelper.USER_PASSWORD);
        auction = new Auction(seller, itemDescription, startingPrice, startTime, endTime);
        bidder = users.findByUserName(UsersTestHelper.USER_NAME);
        bidder2 = users.findByUserName(UsersTestHelper.USER_NAME2);
    }

    @Test(expected = AuctionNotStartedException.class)
    public void cannotBidIfAuctionNotStarted() {
        auction.placeBid(bidder, startingPrice);
    }

    @Test(expected = IllegalBidderException.class)
    public void cannotBidOnOwnAuction() {
        auction.onStart();
        auction.placeBid(seller, startingPrice);
    }

    @Test(expected = NotLoggedInException.class)
    public void cannotBidIfNotLoggedIn() {
        bidder.logout();
        auction.onStart();
        auction.placeBid(bidder, startingPrice);
    }

    @Test(expected = BidTooLowException.class)
    public void initialBidRejectedIfBelowStartingPrice() {
        auction.placeBid(bidder, startingPrice - 0.01);
    }

    @Test
    public void BidderIsHighBidderIfStartingBidAtStartingPrice() {
        auction.onStart();
        auction.placeBid(bidder, startingPrice);
        assertEquals(bidder, auction.getHighBidder());
    }

    @Test
    public void BidAmountIsStartingPriceIfFirstBidAtStartingPrice() {
        auction.onStart();
        auction.placeBid(bidder, startingPrice);
        assertEquals(startingPrice, auction.getHighBid(), 0.001);
    }

    @Test
    public void lowerBidDoesNotBecomeHighBid() {
        auction.onStart();
        auction.placeBid(bidder, startingPrice + 0.10);
        assertEquals(false, auction.placeBid(bidder2, startingPrice));
        assertEquals(startingPrice + 0.10, auction.getHighBid(), 0.001);
    }

    @Test
    public void canOutbidOldHighBid() {
        auction.onStart();
        auction.placeBid(bidder, startingPrice + 0.10);
        assertEquals(true, auction.placeBid(bidder2, startingPrice + 0.20));
        assertEquals(startingPrice + 0.20, auction.getHighBid(), 0.001);
        assertEquals(bidder2, auction.getHighBidder());
    }
}
