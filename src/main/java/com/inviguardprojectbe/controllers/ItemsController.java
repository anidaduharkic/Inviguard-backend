package com.inviguardprojectbe.controllers;

import com.inviguardprojectbe.models.entities.Item;
import com.inviguardprojectbe.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/items")
@RestController
public class ItemsController {

    private final ItemsService itemsService;

    @Autowired
    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping("list")
    public List<Item> getItems() {
        return itemsService.getItemList();
    }

    @GetMapping("{id}")
    public Optional<Item> getItem(@PathVariable Long id) {
        return itemsService.getItem(id);
    }

    @DeleteMapping("{id}")
    public void deleteItem(@PathVariable Long id) {
        itemsService.deleteItem(id);
    }

    @PostMapping()
    public Item createItem(@RequestBody Item item) {
        return itemsService.createItem(item);
    }

    @PutMapping("{id}")
    public Optional<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemsService.updateItem(id, item);
    }

    @GetMapping("low-stock")
    public List<Item> getLowStockItems() {
        return itemsService.getLowStockItems();
    }

}
