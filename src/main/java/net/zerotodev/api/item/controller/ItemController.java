package net.zerotodev.api.item.controller;

import lombok.RequiredArgsConstructor;
import net.zerotodev.api.item.domain.Item;
import net.zerotodev.api.item.service.ItemService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ItemController{
    private final ItemService itemService;

    public List<Item> findAll() {
        return itemService.findAll();
    }

    public Optional<Item> findById(long id) {
        return itemService.findById(id);
    }

    public boolean existsById(long id) {
        return itemService.existsById(id);
    }

    public int count() {
        return itemService.count();
    }

    public void save(Item entity) {
        itemService.save(entity);
    }

    public void deleteById(long id) {
        itemService.deleteById(id);
    }
}
