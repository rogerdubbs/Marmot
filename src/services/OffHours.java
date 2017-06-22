package services;

public final class OffHours implements Hours {

    private OffHours() {
    }

    public static OffHours getInstance() {
        return new OffHours();
    }

    public boolean isOffHours() {
        return Math.random() * 10.0 >= 5.0;
    }
}
