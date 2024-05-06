package com.inviguardprojectbe;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("items")
@RestController

public class ItemsController {


    private final ItemsService items;

    public ItemsController(ItemsService items){
        this.items = items;
    }

    @GetMapping("list")
    public List<Items> getItems() {
        return this.items.getItemList();
    }

    @GetMapping("{id}")
    public Items getItem(@PathVariable Integer id){
     return this.items.getItem(id);
    }

    @DeleteMapping("{id}")
    public void deleteItem(@PathVariable Long id){
        this.items.deleteItem(id);
    }

    @PostMapping()
    public Items createItem(@RequestBody Items item){
        return this.items.createItem(item);
    }

    @PutMapping("{id}")
    public Items updateItem(@PathVariable Long id, @RequestBody Items item){
        return this.items.updateItem(id, item);
    }
}
