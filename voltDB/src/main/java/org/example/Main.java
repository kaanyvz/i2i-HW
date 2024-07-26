package org.example;

import java.io.IOException;
import java.sql.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        VoltOps voltOps = new VoltOps();
//        voltOps.createTable();
        Subscriber subscriber1 = new Subscriber(
                1,
                "Kaan",
                "Yavuz",
                "905335556677",
                1,
                new Date()
        );

        Subscriber subscriber2 = new Subscriber(
                2,
                "Ali",
                "Ahmet",
                "905553331122",
                2,
                new Date()
        );

        Subscriber subscriber3 = new Subscriber(
                3,
                "Ayşe",
                "Fatma",
                "905443332211",
                3,
                new Date()
        );
        Subscriber subscriber4 = new Subscriber(
                4,
                "Mehmet",
                "Mustafa",
                "905316661177",
                4,
                new Date()
        );

        Subscriber subscriber5 = new Subscriber(
                5,
                "Hakan",
                "Ali",
                "905324441256",
                5,
                new Date()
        );
//        voltOps.insert(subscriber1);
//        voltOps.insert(subscriber2);
//        voltOps.insert(subscriber3);
//        voltOps.insert(subscriber5);
        voltOps.selectSubscribers();


    }
}