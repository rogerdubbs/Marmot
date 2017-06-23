package application;

class FeeCalculatorFactory {
    static FeeCalculator make() {
        return new CarShippingFeeCalculator();
    }
}
