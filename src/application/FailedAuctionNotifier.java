package application;

import services.PostOffice;

public class FailedAuctionNotifier implements AuctionNotifier {
    @Override
    public void notify(Auction auction) {
        User seller = auction.getSeller();
        String itemDescription = auction.getItemDescription();
        PostOffice.getInstance().sendEMail(seller.getUserEmail(), String.format("Sorry, your auction for %s did not have any bidders.", itemDescription));

    }
}
