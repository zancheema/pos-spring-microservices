package com.zancheema.pos.service.inventory.item;

import com.zancheema.pos.service.inventory.item.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemInfo> getItems() {
        return itemService.getItems();
    }

    @GetMapping("sales")
    public List<Sale> geSales() {
        return itemService.getsSales();
    }

    @GetMapping("most-bought")
    public List<MostBoughtItem> getMostBoughtItems() {
        return itemService.getMostBoughtItems();
    }

    @PostMapping("/add")
    public ResponseEntity<ItemInfo> addItem(@RequestBody @Valid ItemCreationPayload payload) {
        return ResponseEntity.of(itemService.addItem(payload));
    }

    @PatchMapping("/update")
    public ResponseEntity<ItemInfo> updateItem(@RequestBody @Valid ItemPatch patch) {
        return ResponseEntity.of(itemService.updateItem(patch));
    }

    @DeleteMapping("/delete/{item_code}")
    public ResponseEntity<?> deleteItem(@PathVariable("item_code") String itemCode) {
        itemService.deleteItem(itemCode);
        return ResponseEntity.noContent().build();
    }
}
