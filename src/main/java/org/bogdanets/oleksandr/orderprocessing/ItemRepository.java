package org.bogdanets.oleksandr.orderprocessing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ItemRepository extends JpaRepository<Item, String> {
    List<Item> findAllBySkuIn(Set<String> skus);
}
