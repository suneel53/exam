/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * 
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.inventory.model.Item;
import com.example.inventory.repository.ItemRepository;
import com.example.inventory.repository.ItemJpaRepository;

@Service
public class ItemJpaService implements ItemRepository {

    @Autowired
    private ItemJpaRepository ijr;

    @Override
    public ArrayList<Item> getItems() {
        List<Item> itemCollection = ijr.findAll();
        ArrayList<Item> items = new ArrayList<>(itemCollection);

        return items;
    }

    @Override
    public Item getItemById(int itemId) {
        try {
            Item item = ijr.findById(itemId).get();
            return item;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Item addItem(Item item) {
        ijr.save(item);
        return item;
    }

    @Override
    public Item updateItem(int itemId, Item item) {
        try {
            Item newItem = ijr.findById(itemId).get();
            if (item.getItemName() != null) {
                newItem.setItemName(item.getItemName());
            }
            if (item.getQuantity() != 0) {
                newItem.setQuantity(item.getQuantity());
            }
            if(item.getSupplier() !=null){
                newItem.setSupplier(item.getSupplier());
            }
            ijr.save(newItem);
            return newItem;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteItem(int itemId) {
        try {
            ijr.deleteById(itemId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}