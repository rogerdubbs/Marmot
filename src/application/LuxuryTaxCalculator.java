package application;

class LuxuryTaxCalculator extends FeeCalculator {
    @Override
    void calculateFees(Auction auction) {
        switch (auction.getType()) {
            case car:
                if (auction.getHighBid() > 50000)
                    auction.addLuxuryTax(0.04 * auction.getHighBid());
        }
        super.calculateFees(auction);
    }
}
