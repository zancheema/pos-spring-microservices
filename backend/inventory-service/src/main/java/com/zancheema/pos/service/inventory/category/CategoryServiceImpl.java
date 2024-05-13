package com.zancheema.pos.service.inventory.category;

import com.zancheema.pos.service.inventory.category.dto.CategoryCreationPayload;
import com.zancheema.pos.service.inventory.category.dto.CategoryDistribution;
import com.zancheema.pos.service.inventory.category.dto.CategoryInfo;
import com.zancheema.pos.service.inventory.category.dto.CategoryPatch;
import com.zancheema.pos.service.inventory.util.StreamUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
//    private final InvoiceItemRepository invoiceItemRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
//        this.invoiceItemRepository = invoiceItemRepository;
    }

    @Override
    public List<CategoryInfo> getCategories() {
        return StreamUtil.stream(categoryRepository.findAll())
                .map(category -> new CategoryInfo(category.getId(), category.getName(), category.isActive()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDistribution> getCategoryDistributions() {
//        Map<String, Integer> distributionMap = StreamUtil.stream(invoiceItemRepository.findAll())
//                .collect(Collectors.groupingBy(
//                        item -> item.getStock().getItem().getCategory().getName(),
//                        Collectors.summingInt(InvoiceItem::getQuantity)
//                ));
//        return distributionMap.entrySet()
//                .stream()
//                .map(entry -> new CategoryDistribution(entry.getKey(), entry.getValue()))
//                .toList();
        return new ArrayList<>();
    }

    @Override
    public Optional<CategoryInfo> addCategory(CategoryCreationPayload payload) {
        boolean alreadyExists = categoryRepository.existsByNameIgnoreCase(payload.getName());
        if (alreadyExists) {
            return Optional.empty();
        }
        Category category = new Category(0L, payload.getName(), true);
        Category savedCategory = categoryRepository.save(category);
        CategoryInfo categoryInfo = new CategoryInfo(savedCategory.getId(), savedCategory.getName(), savedCategory.isActive());
        return Optional.of(categoryInfo);
    }

    @Override
    public Optional<CategoryInfo> updateCategory(CategoryPatch patch) {
        Optional<Category> optionalCategory = categoryRepository.findById(patch.getId());
        if (optionalCategory.isEmpty()) {
            return Optional.empty();
        }
        Category category = optionalCategory.get();
        if (StringUtils.hasLength(patch.getName())) {
            category.setName(patch.getName());
        }
        if (patch.getIsActive() != null) {
            category.setActive(patch.getIsActive());
        }
        Category updatedCategory = categoryRepository.save(category);
        CategoryInfo categoryInfo = new CategoryInfo(updatedCategory.getId(), updatedCategory.getName(), updatedCategory.isActive());
        return Optional.of(categoryInfo);
    }

    @Override
    public void deleteCategory(long categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
        }
    }
}
