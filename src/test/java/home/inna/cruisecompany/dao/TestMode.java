package home.inna.cruisecompany.dao;

public class TestMode {

    public static void enable() {
        ConnectionPool.setTestMode(true);
    }

}