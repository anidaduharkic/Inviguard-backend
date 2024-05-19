package com.inviguardprojectbe;

import com.inviguardprojectbe.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemsService {

   private ItemRepository itemRepository;

    public ItemsService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public List<Items> getItemList(){
        return this.itemRepository.findAll();
    }


/*
   public Items getItem(long id){
        for(Items item : this.items){
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }

   public void deleteItem(long id){
        List<Items> item = this.items;
        for(int i = 0; i < item.size(); i++){
            Items items1 = item.get(i);
            if(items1.getId() == id){
               item.remove(i);
               return;
            }
        }
    }

    public Items createItem(Items items) {
        items.setId(this.id++);
        this.items.add(items);
        return items;
    }

    public Items updateItem(Long id, Items item) {
        Items current = this.getItem(id);
        if (current == null) {
            return null;
        }
        current.setItemName(item.getItemName());
        current.setLeftInStock(item.getLeftInStock());
        return current;
    } */


}
