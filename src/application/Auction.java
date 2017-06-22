package application;

import java.util.Date;

class Auction {
    private User seller;
    private final String itemDescription;
    private final double startingPrice;
    private final Date startTime;
    private final Date endTime;

    Auction(User seller, String itemDescription, double startingPrice, Date startTime, Date endTime) {
        if (!seller.isLoggedIn()) throw new NotSellerException();

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
}
