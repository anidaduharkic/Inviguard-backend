package com.inviguardprojectbe.Controllers;

import com.inviguardprojectbe.Classes.Items;
import com.inviguardprojectbe.Services.ItemsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("items")
@RestController

public class ItemsController {


    private final ItemsService itemsService;

    public ItemsController(ItemsService items){
        this.itemsService = items;
    }

    @GetMapping("list")
    public List<Items> getItems() {
        return this.itemsService.getItemList();
    }

   /* @GetMapping("{id}")
    public Items getItem(@PathVariable Integer id){
     return this.items.getItem(id);
    }

    @DeleteMapping("{id}")
    public void deleteItem(@PathVariable Long id){
        this.items.deleteItem(id);
    } */

    @PostMapping()
    public Items createItem(@RequestBody Items item){
        return this.itemsService.createItem(item);
    }

  /*  @PutMapping("{id}")
    public Items updateItem(@PathVariable Long id, @RequestBody Items item){
        return this.items.updateItem(id, item);
    } */
}
