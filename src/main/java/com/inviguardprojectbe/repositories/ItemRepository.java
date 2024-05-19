package com.inviguardprojectbe.repositories;
import com.inviguardprojectbe.Items;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Items, Long> {

    List<Items> findAllByName(String name);

}
