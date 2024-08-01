package org.example;

import org.apache.ignite.client.ClientConnectionException;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ignite {

    private static final Logger logger = LoggerFactory.getLogger(Ignite.class);

    public static void main(String[] args) {
        IgniteConfiguration igniteCfg = new IgniteConfiguration();
        igniteCfg.setWorkDirectory("C:\\Users\\kaan_\\i2i\\ignite");
        String jdbcURL = "jdbc:ignite:thin://127.0.0.1";

        try {
            Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
            Connection connection = DriverManager.getConnection(jdbcURL);

                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM SUBSCRIBER");

                while (rs.next()){
                    int subscId = rs.getInt("SUBSC_ID");
                    String subscName = rs.getString("SUBSC_NAME");
                    String subscLName = rs.getString("SUBSC_SURNAME");

                    logger.info("{} {} {}", subscId, subscName, subscLName);
                }

        }catch(ClientConnectionException | ClassNotFoundException ce){
            System.out.println(ce.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}