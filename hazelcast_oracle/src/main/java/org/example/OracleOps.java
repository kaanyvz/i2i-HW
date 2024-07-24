package org.example;

import java.sql.*;
import java.util.Random;

public class OracleOps {
    private String dbUrl = "jdbc:oracle:thin:@//localhost:1521/TESTYVZ.kaan.com";
    private String username = "C##KAAN";
    private String password = "password";

    //insert ops. for oracle db.
    public long insertRandomNumbers(int count) {
        long startTime = System.nanoTime();
        try (Connection connection = DriverManager.getConnection(dbUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO RANDOM_NUMBERS(NUMBER_VALUE) VALUES(?)")) {

            Random random = new Random();
            for (int i = 0; i < count; i++) {
                int randomNumber = random.nextInt(count);
                preparedStatement.setString(1, String.valueOf(randomNumber));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    //select ops. for oracle db
    public long selectRandomNumbers() {
        long startTime = System.nanoTime();
        try (Connection connection = DriverManager.getConnection(dbUrl, username, password);
             Statement statement = connection.createStatement()) {

            String sql = "SELECT NUMBER_VALUE FROM RANDOM_NUMBERS";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }
}