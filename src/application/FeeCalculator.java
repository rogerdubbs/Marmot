package application;

class FeeCalculator {
    private FeeCalculator next;

    void calculateFees(Auction auction) {
        if (next != null)
            next.calculateFees(auction);
    }

    FeeCalculator add(FeeCalculator feeCalculator) {
        feeCalculator.next = next;
        next = feeCalculator;
        return this;
    }
}
