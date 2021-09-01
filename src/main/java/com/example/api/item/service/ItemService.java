package com.example.api.item.service;

import com.example.api.item.domain.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ItemService {
    List<Item> findAll();
    Optional<Item> findById(long id);
    boolean existsById(long id);
    int count();
    void save(Item entity);
    void deleteById(long id);

}
