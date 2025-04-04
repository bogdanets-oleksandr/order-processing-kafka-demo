package org.bogdanets.oleksandr.orderprocessing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class OrderSerializer implements Serializer<Order> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, Order order) {
        try {
            return objectMapper.writeValueAsBytes(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
