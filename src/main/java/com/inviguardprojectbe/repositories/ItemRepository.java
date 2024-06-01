package com.inviguardprojectbe.repositories;
import com.inviguardprojectbe.Classes.Items;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Items, Long> {

   // List<Items> findAll(String name);

}
