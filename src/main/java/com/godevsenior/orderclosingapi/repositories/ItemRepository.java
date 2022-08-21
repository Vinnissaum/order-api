package com.godevsenior.orderclosingapi.repositories;

import com.godevsenior.orderclosingapi.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
