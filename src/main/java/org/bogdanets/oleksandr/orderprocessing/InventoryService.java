package org.bogdanets.oleksandr.orderprocessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryService {

    @Autowired
    ItemRepository itemRepository;

    @KafkaListener(topics = "orders", groupId = "inventory-group")
    public void fulfillOrder(Order order) {
        System.out.println("Order is being fulfilled");
        List<Item> items = itemRepository.findAllBySkuIn(order.getItemsOrdered().keySet());
        items.forEach(item -> {
            item.setQuantity(item.getQuantity() - order.getItemsOrdered().get(item.getSku()));
        });
        itemRepository.saveAll(items);
    }
}
