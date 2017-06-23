package application;

import org.junit.Test;
import services.PostOffice;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CloseAuctionTest {
    @Test
    public void auctionClosedNoBidders() {
        Users users = UsersTestHelper.createUsers();
        User seller = users.findByUserName(UsersTestHelper.USER_NAME_SELLER);
        seller.login();
        long currentTimeMillis = System.currentTimeMillis();
        Date startTime = new Date(currentTimeMillis + 10000);
        Date endTime = new Date(currentTimeMillis + 20000);
        String itemDescription = "Nobody wants this";
        Auction auction = new Auction(seller, itemDescription, 0.99, startTime, endTime);
        auction.onStart();
        seller.logout();  // Seller should not have to be logged in to close an auction.

        auction.onClose();
        //check seller was notified
        assertEquals(true, PostOffice.getInstance().doesLogContain(seller.getUserEmail(), "Sorry, your auction for " +
                itemDescription +
                " did not have any bidders."));
    }

    @Test
    public void auctionClosedWithBidders() {
        Users users = UsersTestHelper.createUsers();
        User bidder = users.findByUserName(UsersTestHelper.USER_NAME);
        User seller = users.findByUserName(UsersTestHelper.USER_NAME_SELLER);
        seller.login();
        long currentTimeMillis = System.currentTimeMillis();
        Date startTime = new Date(currentTimeMillis + 10000);
        Date endTime = new Date(currentTimeMillis + 20000);
        String itemDescription = "Nobody wants this";
        Auction auction = new Auction(seller, itemDescription, 0.99, startTime, endTime);
        auction.onStart();
        seller.logout();  // Seller should not have to be logged in to close an auction.

        bidder.login();
        auction.placeBid(bidder, 2.00);
        bidder.logout();

        auction.onClose();
        String allEmailsForSeller = PostOffice.getInstance().findEmail(seller.getUserEmail(), "");
        //check seller was notified
        assertEquals(allEmailsForSeller, true, PostOffice.getInstance().doesLogContain(seller.getUserEmail(), "Your " +
                itemDescription +
                " auction sold to bidder " + bidder.getUserEmail() + " for " + "$2.00."));
    }
}
