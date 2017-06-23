package application;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AuctionFeesTest {
    private String itemDescription = "Best non-car non-software item ever";
    private double startingPrice;
    private Auction auction;
    private User bidder;
    private User seller;
    private Date startTime;
    private Date endTime;

    @Before
    public void setUp() throws Exception {
        Users users = UsersTestHelper.createUsers();
        long currentTimeMillis = System.currentTimeMillis();
        startTime = new Date(currentTimeMillis + 1000000);
        endTime = new Date(currentTimeMillis + 2000000);
        seller = users.findByUserName(UsersTestHelper.USER_NAME_SELLER);
        startingPrice = 0.99;
        users.login(UsersTestHelper.USER_NAME_SELLER, UsersTestHelper.USER_PASSWORD);
        users.login(UsersTestHelper.USER_NAME, UsersTestHelper.USER_PASSWORD);
        users.login(UsersTestHelper.USER_NAME2, UsersTestHelper.USER_PASSWORD);
        bidder = users.findByUserName(UsersTestHelper.USER_NAME);
        bidder.login();
    }

    @Test
    public void transactionFeeIsCharged() {
        auction = new Auction(seller, itemDescription, Auction.Type.other, startingPrice, startTime, endTime);
        auction.onStart();
        auction.placeBid(bidder, 100);
        auction.onClose();
        assertEquals(2, auction.getTransactionFee(), 0.001);
    }

    @Test
    public void shippingFeeIsCharged() {
        auction = new Auction(seller, itemDescription, Auction.Type.other, startingPrice, startTime, endTime);
        auction.onStart();
        auction.placeBid(bidder, 100);
        auction.onClose();
        assertEquals(10, auction.getShippingFee(), 0.001);
    }

    @Test
    public void noShippingFeeForDownloadableSoftware() {
        auction = new Auction(seller, itemDescription, Auction.Type.downloadableSoftware, startingPrice, startTime, endTime);
        auction.onStart();
        auction.placeBid(bidder, 100);
        auction.onClose();
        assertEquals(0, auction.getShippingFee(), 0.001);
    }

    @Test
    public void bigShippingFeeChargedForCar() {
        auction = new Auction(seller, itemDescription, Auction.Type.car, startingPrice, startTime, endTime);
        auction.onStart();
        auction.placeBid(bidder, 100);
        auction.onClose();
        assertEquals(1000, auction.getShippingFee(), 0.001);
    }

    @Test
    public void luxuryTaxChargedForExpensiveCars() {
        auction = new Auction(seller, itemDescription, Auction.Type.car, startingPrice, startTime, endTime);
        auction.onStart();
        auction.placeBid(bidder, 100000);
        auction.onClose();
        assertEquals(4000, auction.getLuxuryTax(), 0.001);
    }
}
