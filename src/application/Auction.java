package application;

import services.PostOffice;

import java.util.Date;

class Auction {
    private final String itemDescription;
    private final double startingPrice;
    private final Date startTime;
    private final Date endTime;
    private final String sellerNoBidsMessageFormat = "Your %s auction sold to bidder %s for $%.2f.";
    private final String sellerSuccessfulAuctionMessageFormat = "Sorry, your auction for %s did not have any bidders.";
    private final String buyerSuccessfulAuctionMessageFormat = "Congratulations!  You won an auction for a %s from %s for $%.2f.";
    private User seller;
    private State state = State.notStarted;
    private User highBidder;
    private double highBid;

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
        if (highBid > 0)
            PostOffice.getInstance().sendEMail(seller.getUserEmail(), String.format(sellerNoBidsMessageFormat,
                    itemDescription, getHighBidder().getUserEmail(), highBid));
        else
            PostOffice.getInstance().sendEMail(seller.getUserEmail(), String.format(sellerSuccessfulAuctionMessageFormat, itemDescription));

        if (highBidder != null)
            PostOffice.getInstance().sendEMail(highBidder.getUserEmail(), String.format(buyerSuccessfulAuctionMessageFormat, itemDescription, seller.getUserEmail(), highBid));

    }

    public enum State {notStarted, active}
}
