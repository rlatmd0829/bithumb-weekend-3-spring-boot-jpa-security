package net.zerotodev.api.item.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import net.zerotodev.api.item.domain.Item;
import net.zerotodev.api.item.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowCredentials = "false") // cors 이슈 처리, 전부허용해줌 , 로그인 된 상태 allowCredentials 이게 뭐지
@Api(tags = "items") // swagger 문서작업용
@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController{
    private final ItemService itemService;

    @GetMapping("/connect")
    public String connect(){
        return "success";
    }

    public List<Item> findAll() {
        return itemService.findAll();
    }

    @GetMapping
    public Item findById(@RequestParam("itemBrand") String itemBrand,
                         @RequestParam("itemName") String itemName,
                         @RequestParam("itemColor") String itemColor) {
        return new Item(itemBrand, itemName, itemColor);
        //return itemService.findById(id);
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
