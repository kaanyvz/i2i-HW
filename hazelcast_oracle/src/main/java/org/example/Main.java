package org.example;

public class Main {
    public static void main(String[] args) {
        OracleOps oracleOps = new OracleOps();
        HazelcastOps hazelcastOps = new HazelcastOps();

        int[] counts = {20000, 100000};

        for (int count : counts) {
            System.out.println("Benchmarking for " + count + " numbers:");

            long oracleInsertTime = oracleOps.insertRandomNumbers(count);
            System.out.println("Oracle Insert time: " + oracleInsertTime + " ms");
            long oracleSelectTime = oracleOps.selectRandomNumbers();
            System.out.println("Oracle Select time: " + oracleSelectTime + " ms");

            long hazelcastPutTime = hazelcastOps.putRandomNumbers(count);
            System.out.println("Hazelcast Put time: " + hazelcastPutTime + " ms");
            long hazelcastGetTime = hazelcastOps.getRandomNumbers(count);
            System.out.println("Hazelcast Get time: " + hazelcastGetTime + " ms");

            System.out.println();
        }

        hazelcastOps.shutdown();
    }
}