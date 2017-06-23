package application;

import java.util.Date;

import static application.Auction.Type.other;

class Auction {
    private final String itemDescription;
    private final double startingPrice;
    private final Date startTime;
    private final Date endTime;
    private User seller;
    private State state = State.notStarted;
    private User highBidder;
    private double highBid;
    private Type type;

    Auction(User seller, String itemDescription, Type type, double startingPrice, Date startTime, Date endTime) {
        if (!seller.isLoggedIn()) throw new NotLoggedInException();
        if (!seller.isSeller()) throw new NotSellerException();
        if (startTime.after(endTime)) throw new IllegalAuctionException();
        if (startTime.before(new Date(System.currentTimeMillis()))) throw new IllegalAuctionException();

        this.type = type;
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

    boolean placeBid(User bidder, double bidPrice) {
        if (!bidder.isLoggedIn()) {
            throw new NotLoggedInException();
        }
        if (bidder == seller) {
            throw new IllegalBidderException();
        }
        if (bidPrice < startingPrice) {
            throw new BidTooLowException();
        }
        if (state != State.active) {
            throw new AuctionNotStartedException();
        }
        if (bidPrice > highBid) {
            highBidder = bidder;
            highBid = bidPrice;
            return true;
        }
        return false;
    }

    User getHighBidder() {
        return highBidder;
    }

    double getHighBid() {
        return highBid;
    }

    void onClose() {
        AuctionNotifierFactory factory = AuctionNotifierFactory.getInstance();
        AuctionNotifier notifier = factory.make(this);
        notifier.notify(this);
    }

    double getTransactionFee() {
        return 0.02 * getHighBid();
    }

    double getShippingFee() {
        return type == other ? 10 : 0;
    }

    public enum State {notStarted, active}

    public enum Type {
        downloadableSoftware, other
    }
}
