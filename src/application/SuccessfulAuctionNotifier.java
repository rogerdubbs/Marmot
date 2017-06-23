package application;

import services.PostOffice;

class SuccessfulAuctionNotifier implements AuctionNotifier {
    private final String sellerSuccessfulAuctionMessageFormat = "Your %s auction sold to bidder %s for $%.2f.";
    private final String buyerSuccessfulAuctionMessageFormat = "Congratulations!  You won an auction for a %s from %s for $%.2f.";


    @Override
    public void notify(Auction auction) {
        User seller = auction.getSeller();
        User highBidder = auction.getHighBidder();
        double highBid = auction.getHighBid();
        String itemDescription = auction.getItemDescription();

        PostOffice.getInstance().sendEMail(seller.getUserEmail(), String.format(sellerSuccessfulAuctionMessageFormat,
                itemDescription, highBidder.getUserEmail(), highBid));
        PostOffice.getInstance().sendEMail(highBidder.getUserEmail(), String.format(buyerSuccessfulAuctionMessageFormat, itemDescription, seller.getUserEmail(), highBid));


    }
}
