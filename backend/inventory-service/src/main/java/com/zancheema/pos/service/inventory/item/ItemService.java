package com.zancheema.pos.service.inventory.item;

import com.zancheema.pos.service.inventory.item.dto.*;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<ItemInfo> getItems();

    List<Sale> getsSales();

    List<MostBoughtItem> getMostBoughtItems();

    Optional<ItemInfo> addItem(ItemCreationPayload payload);

    Optional<ItemInfo> updateItem(ItemPatch patch);

    void deleteItem(String itemCode);
}
