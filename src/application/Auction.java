package application;

import java.util.Date;

class Auction {
    private User seller;
    private final String itemDescription;
    private final double startingPrice;
    private final Date startTime;
    private final Date endTime;
    private State state = State.notStarted;

    Auction(User seller, String itemDescription, double startingPrice, Date startTime, Date endTime) {
        if (!seller.isLoggedIn()) throw new NotLoggedInException();
        if (!seller.isSeller()) throw new NotSellerException();
        if (startTime.after(endTime)) throw new IllegalAuctionException();
        if (startTime.before(new Date(System.currentTimeMillis()))) throw new IllegalAuctionException();

        this.seller = seller;
        this.itemDescription = itemDescription;
        this.startingPrice = startingPrice;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    User getSeller() {
        return seller;
    }

    Date getStartTime() {
        return startTime;
    }

    Date getEndTime() {
        return endTime;
    }

    String getItemDescription() {
        return itemDescription;
    }

    double getStartingPrice() {
        return startingPrice;
    }

    void onStart() {
        state = State.active;
    }

    State getState() {
        return state;
    }

    void placeBid(User bidder, double bidPrice) {
        if(bidPrice < startingPrice) {
            throw new BidTooLowException();
        }
        throw new AuctionNotStartedException();
    }

    public enum State {notStarted, active}
}
