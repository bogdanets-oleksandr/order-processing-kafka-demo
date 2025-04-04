package org.bogdanets.oleksandr.orderprocessing;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue
    private long id;

    private String recipient;

    private BigDecimal amount;

    @ElementCollection
    private Map<String, Integer> itemsOrdered = new HashMap<>();
}
