package application;

class TransactionFeeCalculator extends FeeCalculator {
    @Override
    void calculateFees(Auction auction) {
        auction.addTransactionFee(0.02 * auction.getHighBid());

        super.calculateFees(auction);
    }
}
