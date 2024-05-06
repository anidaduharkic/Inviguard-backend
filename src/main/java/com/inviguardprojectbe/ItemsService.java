package com.inviguardprojectbe;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemsService {

    private long id = 0;

    private List<Items> items = new ArrayList<>();

    public ItemsService(){
        this.items.add(new Items(this.id++,"Laptop Computer", 50));
        this.items.add(new Items(this.id++,"Packing Boxes", 100));
        this.items.add(new Items(this.id++,"Office Chairs", 10));
    }

    public List<Items> getItemList(){
        return this.items;
    }

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
    }
}
