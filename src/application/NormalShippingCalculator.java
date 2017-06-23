package application;

class NormalShippingCalculator extends FeeCalculator {
    @Override
    void calculateFees(Auction auction) {
        switch (auction.getType()) {
            case other:
                auction.addShippingFee(10);
        }
        super.calculateFees(auction);
    }
}
