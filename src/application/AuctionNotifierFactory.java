package application;

class AuctionNotifierFactory {
    static AuctionNotifier make(Auction auction) {
        AuctionNotifier notifier;
        if (auction.getHighBid() > 0) {
            notifier = new SuccessfulAuctionNotifier();
        } else {
            notifier = new FailedAuctionNotifier();
        }
        return notifier;
    }
}
