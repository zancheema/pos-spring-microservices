package com.zancheema.pos.service.inventory.item;

import com.zancheema.pos.service.inventory.brand.Brand;
import com.zancheema.pos.service.inventory.brand.BrandRepository;
import com.zancheema.pos.service.inventory.category.Category;
import com.zancheema.pos.service.inventory.category.CategoryRepository;
import com.zancheema.pos.service.inventory.item.dto.*;
import com.zancheema.pos.service.inventory.util.StreamUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
//    private final InvoiceItemRepository invoiceItemRepository;

    public ItemServiceImpl(ItemRepository itemRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
//        this.invoiceItemRepository = invoiceItemRepository;
    }

    @Override
    public List<ItemInfo> getItems() {
        return StreamUtil.stream(itemRepository.findAll())
                .map(item -> new ItemInfo(
                        item.getItemCode().toString(),
                        item.getName(),
                        item.getPurchasePrice(),
                        item.getRetailPrice(),
                        item.getCategory().getName(),
                        item.getBrand().getName(),
                        item.isActive()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Sale> getsSales() {
//        Stream<InvoiceItem> invoiceItemStream = StreamUtil.stream(invoiceItemRepository.findAll());
//        Map<String, Integer> salesMap = invoiceItemStream.collect(Collectors.groupingBy(
//                item -> item.getInvoice().getCreatedAt().toLocalDate().toString(),
//                Collectors.summingInt(InvoiceItem::getQuantity)
//        ));
//        return salesMap.entrySet()
//                .stream()
//                .map(entry -> new Sale(entry.getKey(), entry.getValue()))
//                .toList();
        return new ArrayList<>();
    }

    @Override
    public List<MostBoughtItem> getMostBoughtItems() {
//        final int maxSize = 3;
//        Map<String, Integer> mostBoughtItemsMap = StreamUtil.stream(invoiceItemRepository.findAll())
//                .collect(Collectors.groupingBy(
//                        item -> item.getStock().getItem().getName(),
//                        Collectors.summingInt(InvoiceItem::getQuantity)
//                ));
//        return mostBoughtItemsMap.entrySet()
//                .stream()
//                .map(entry -> new MostBoughtItem(entry.getKey(), entry.getValue()))
//                .toList();
        return new ArrayList<>();
    }

    @Override
    public Optional<ItemInfo> addItem(ItemCreationPayload payload) {
        Optional<Brand> optionalBrand = brandRepository.findById(payload.getBrand());
        Optional<Category> optionalCategory = categoryRepository.findById(payload.getCategory());
        if (optionalBrand.isEmpty() || optionalCategory.isEmpty()) {
            return Optional.empty();
        }
        Brand brand = optionalBrand.get();
        Category category = optionalCategory.get();

        Item item = new Item(
                null,
                payload.getName(),
                payload.getPurchasePrice(),
                payload.getRetailPrice(),
                brand, category,
                true
        );
        Item savedItem = itemRepository.save(item);
        ItemInfo itemInfo = new ItemInfo(
                savedItem.getItemCode().toString(),
                savedItem.getName(),
                savedItem.getPurchasePrice(),
                savedItem.getRetailPrice(),
                savedItem.getCategory().getName(),
                savedItem.getBrand().getName(),
                savedItem.isActive()
        );
        return Optional.of(itemInfo);
    }

    @Override
    public Optional<ItemInfo> updateItem(ItemPatch patch) {
        UUID itemCode = UUID.fromString(patch.getItemCode());
        Optional<Item> optionalItem = itemRepository.findById(itemCode);
        if (optionalItem.isEmpty()) {
            return Optional.empty();
        }
        Item item = optionalItem.get();
        if (StringUtils.hasLength(patch.getName())) {
            item.setName(patch.getName());
        }
        if (patch.getPurchasePrice() != null) {
            item.setPurchasePrice(patch.getPurchasePrice());
        }
        if (patch.getRetailPrice() != null) {
            item.setRetailPrice(patch.getRetailPrice());
        }
        if (patch.getBrand() != null && brandRepository.existsById(patch.getBrand())) {
            Brand brand = brandRepository.findById(patch.getBrand()).get();
            item.setBrand(brand);
        }
        if (patch.getCategory() != null && categoryRepository.existsById(patch.getCategory())) {
            Category category = categoryRepository.findById(patch.getCategory()).get();
            item.setCategory(category);
        }
        if (patch.getIsActive() != null ) {
            item.setActive(patch.getIsActive());
        }

        Item updatedItem = itemRepository.save(item);
        ItemInfo itemInfo = new ItemInfo(
                updatedItem.getItemCode().toString(),
                updatedItem.getName(),
                updatedItem.getPurchasePrice(),
                updatedItem.getRetailPrice(),
                updatedItem.getCategory().getName(),
                updatedItem.getBrand().getName(),
                updatedItem.isActive()
        );
        return Optional.of(itemInfo);
    }

    @Override
    public void deleteItem(String itemCode) {
        UUID itemCodeUUID = UUID.fromString(itemCode);
        if (itemRepository.existsById(itemCodeUUID)) {
            itemRepository.deleteById(itemCodeUUID);
        }
    }
}
