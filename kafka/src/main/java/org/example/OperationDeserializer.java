package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class OperationDeserializer implements Deserializer<Operation> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Operation deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Operation.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing Operation", e);
        }
    }

    @Override
    public void close() {
    }
}