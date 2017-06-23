package application;

class CarShippingFeeCalculator extends FeeCalculator {
    @Override
    void calculateFees(Auction auction) {
        if (auction.getType() == Auction.Type.car) {
            auction.addShippingFee(1000);
        }
        super.calculateFees(auction);
    }
}
