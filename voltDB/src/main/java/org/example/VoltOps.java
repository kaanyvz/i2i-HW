package org.example;

import org.voltdb.VoltTable;
import org.voltdb.client.*;

import java.io.IOException;
import java.sql.*;

public class VoltOps {
    private static final String INSERT_QUERY =
            "INSERT INTO SUBSCRIBER(SUBSC_ID, SUBSC_NAME, SUBSC_SURNAME, MSISDN, TARIFF_ID, START_DATE) VALUES (?,?,?,?,?,?)";

    String url = "jdbc:voltdb://localhost:32769";
    private final Client client;

    public VoltOps() throws IOException {
        ClientConfig config = new ClientConfig();
        this.client = ClientFactory.createClient(config);
        this.client.createConnection("localhost:32769");
    }

    //just runs one time at startt.
    public void createTable(){
        String query = """
                CREATE TABLE SUBSCRIBER(
                SUBSC_ID INTEGER,
                SUBSC_NAME VARCHAR(100),
                SUBSC_SURNAME VARCHAR(100),
                MSISDN VARCHAR(100),
                TARIFF_ID  INTEGER,
                START_DATE TIMESTAMP,
                );
                """;
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()){

            statement.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert (Subscriber subscriber) {

        try (Connection connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)){

            preparedStatement.setInt(1, subscriber.getSubscId());
            preparedStatement.setString(2, subscriber.getSubscName());
            preparedStatement.setString(3, subscriber.getSubscSurname());
            preparedStatement.setString(4, subscriber.getMsisdn());
            preparedStatement.setInt(5, subscriber.getTariffId());
            preparedStatement.setTimestamp(6, new java.sql.Timestamp(subscriber.getStartDate().getTime()));
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void selectSubscribers() {
        try {
            ClientResponse response = client.callProcedure("SubscriberProcedure");
                VoltTable resultTable = response.getResults()[0];
                while (resultTable.advanceRow()) {
                    System.out.println("ID: " + resultTable.getLong("SUBSC_ID") +
                            ", Name: " + resultTable.getString("SUBSC_NAME") +
                            ", Surname: " + resultTable.getString("SUBSC_SURNAME") +
                            ", MSISDN: " + resultTable.getString("MSISDN") +
                            ", Tariff ID: " + resultTable.getLong("TARIFF_ID") +
                            ", Start Date: " + resultTable.getTimestampAsSqlTimestamp("START_DATE"));
                }
        } catch (IOException | ProcCallException e) {
            e.printStackTrace();
        }
    }
}
