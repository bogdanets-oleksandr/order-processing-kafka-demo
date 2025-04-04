package org.bogdanets.oleksandr.orderprocessing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class OrderDeserializer implements Deserializer<Order> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Order deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Order.class);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing Order", e);
        }
    }
}
