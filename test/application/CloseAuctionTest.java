package application;

import org.junit.Before;
import org.junit.Test;
import services.PostOffice;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CloseAuctionTest {

    private Users users;
    private User seller;
    private long currentTimeMillis;
    private Date startTime;
    private Date endTime;
    private String itemDescription;
    private Auction auction;
    private User bidder;

    @Before
    public void setUp() throws Exception {
        users = UsersTestHelper.createUsers();
        seller = users.findByUserName(UsersTestHelper.USER_NAME_SELLER);
        bidder = users.findByUserName(UsersTestHelper.USER_NAME);
        currentTimeMillis = System.currentTimeMillis();
        startTime = new Date(currentTimeMillis + 10000);
        endTime = new Date(currentTimeMillis + 20000);
        itemDescription = "Nobody wants this";
        seller.login();
        auction = new Auction(seller, itemDescription, 0.99, startTime, endTime);
        auction.onStart();
        seller.logout();  // Seller should not have to be logged in to close an auction.
    }

    @Test
    public void sellerNotifiedWhenAuctionClosesWithoutBidders() {
        auction.onClose();

        assertEquals(true, PostOffice.getInstance().doesLogContain(seller.getUserEmail(), "Sorry, your auction for " +
                itemDescription +
                " did not have any bidders."));
    }

    @Test
    public void sellerNotifiedWhenAuctionClosesWithWinner() {
        bidder.login();
        auction.placeBid(bidder, 2.00);
        bidder.logout();

        auction.onClose();
        String allEmailsForSeller = PostOffice.getInstance().findEmail(seller.getUserEmail(), "");
        assertEquals(allEmailsForSeller, true, PostOffice.getInstance().doesLogContain(seller.getUserEmail(), "Your " +
                itemDescription +
                " auction sold to bidder " + bidder.getUserEmail() + " for " + "$2.00."));
    }
}
