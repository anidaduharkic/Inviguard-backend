package com.inviguardprojectbe.services;

import com.inviguardprojectbe.models.entities.Item;
import com.inviguardprojectbe.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemsService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemsService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItemList() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItem(long id) {
        return itemRepository.findById(id);
    }

    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> updateItem(Long id, Item item) {
        return itemRepository.findById(id).map(existingItem -> {
            existingItem.setItemName(item.getItemName());
            existingItem.setLeftInStock(item.getLeftInStock());
            return itemRepository.save(existingItem);
        });
    }

    public List<Item> getLowStockItems() {
       return itemRepository.findByLeftInStockLessThan(5);
   }

    public boolean isItemAvailable(Long itemId, Integer quantity) {
        Optional<Item> item = itemRepository.findById(itemId);
        return item.isPresent() && item.get().getLeftInStock() >= quantity;
    }

    public void decreaseItemStock(Long itemId, Integer quantity) {
        itemRepository.findById(itemId).ifPresent(item -> {
            item.setLeftInStock(item.getLeftInStock() - quantity);
            itemRepository.save(item);
        });
    }
}

