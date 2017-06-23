package application;

class AuctionNotifierFactory {
    private static AuctionNotifierFactory instance = new AuctionNotifierFactory();

    static AuctionNotifierFactory getInstance() {
        return instance;
    }

    AuctionNotifier make(Auction auction) {
        AuctionNotifier notifier;
        if (auction.getHighBid() > 0) {
            notifier = new SuccessfulAuctionNotifier();
        } else {
            notifier = new FailedAuctionNotifier();
        }
        return notifier;
    }
}
