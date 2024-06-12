package com.inviguardprojectbe.repositories;

import com.inviguardprojectbe.models.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

   //Optional<Item> findById(Long id);
   List<Item> findByLeftInStockLessThan(int i);
}
