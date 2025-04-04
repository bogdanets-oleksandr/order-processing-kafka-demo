package org.bogdanets.oleksandr.orderprocessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {


    private final KafkaTemplate<String, Order> kafkaTemplate;
    private final ItemRepository itemRepository;

    @Autowired
    public OrderController(KafkaTemplate<String, Order> kafkaTemplate, ItemRepository itemRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.itemRepository = itemRepository;
    }

    private static final String TOPIC = "orders";

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        Map<String, Integer> itemsOrdered = order.getItemsOrdered();
        List<Item> items = itemRepository.findAllBySkuIn(itemsOrdered.keySet());
        for (Item item : items) {
            if (item.getQuantity() < itemsOrdered.get(item.getSku())) {
                return ResponseEntity.badRequest().body("Not enough items in order");
            }
        }
        kafkaTemplate.send(TOPIC, order);
        return ResponseEntity.ok().body("Order placed successfully");
    }
}
