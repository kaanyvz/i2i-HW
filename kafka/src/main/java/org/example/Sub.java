package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class Sub {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OperationDeserializer.class.getName());

        KafkaConsumer<String, Operation> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("operation"));

        while(true){
            ConsumerRecords<String, Operation> records = consumer.poll(100);
            for (ConsumerRecord<String, Operation> record : records){
                Operation operation = record.value();
                int operand = operation.operand();
                String op = operation.operation();
                System.out.println(op);
                switch (op) {
                    case "fib":
                        System.out.println(isFibonacci(operand) ? operand + " is a Fibonacci number." : operand + " is not a Fibonacci number.");
                        break;
                    case "prime":
                        System.out.println(isPrime(operand) ? operand + " is a prime number." : operand + " is not a prime number.");
                        break;
                    case "fibPrime":
                        boolean isFib = isFibonacci(operand);
                        boolean isPrime = isPrime(operand);
                        if (isFib && isPrime) {
                            System.out.println(operand + " is both a Fibonacci and a prime number.");
                        } else {
                            System.out.println(operand + " is not both a Fibonacci and a prime number.");
                        }
                        break;
                    default:
                        System.out.println("Error: Invalid operation.");
                }
            }
        }
    }
    private static boolean isFibonacci(int n) {
        int a = 0, b = 1, c;
        if (n == 0 || n == 1) return true;
        while (b < n) {
            c = a + b;
            a = b;
            b = c;
        }
        return b == n;
    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }


}
