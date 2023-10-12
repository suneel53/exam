/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.inventory.controller;

import com.example.inventory.model.Item;
import com.example.inventory.service.ItemJpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ItemController{

    @Autowired
    private ItemJpaService is;

    @GetMapping("/items")
    public ArrayList<Item> getItems(){
        return is.getItems();
    }

    @GetMapping("/items/{itemId}")
    public Item getItemById(@PathVariable int itemId){
        return is.getItemById(itemId);
    }

    @PostMapping("/items")
    public Item addItem(@RequestBody Item item){
        return is.addItem(item);
    }

    

    @DeleteMapping("/items/{itemId}")
    public void deleteItem(@PathVariable int itemId){
        is.deleteItem(itemId);
    }

    @PutMapping("/items/{itemId}")
    public Item updateItem(@PathVariable int itemId, @RequestBody Item item){
        return is.updateItem(itemId, item);
    }
}