package application;

class FeeCalculatorFactory {
    static FeeCalculator make() {
        return new CarShippingFeeCalculator()
                .add(new LuxuryTaxCalculator())
                .add(new NormalShippingCalculator())
                .add(new TransactionFeeCalculator());
    }
}
