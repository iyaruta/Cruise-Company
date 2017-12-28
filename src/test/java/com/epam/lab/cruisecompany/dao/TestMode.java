package com.epam.lab.cruisecompany.dao;

public class TestMode {

    public static void enable() {
        ConnectionPool.setTestMode(true);
    }

}