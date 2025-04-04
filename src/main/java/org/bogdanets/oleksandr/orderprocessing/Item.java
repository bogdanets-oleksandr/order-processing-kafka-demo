package org.bogdanets.oleksandr.orderprocessing;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter @Setter
public class Item {

    @Id
    private String sku;

    private String name;
    private int quantity;

}
