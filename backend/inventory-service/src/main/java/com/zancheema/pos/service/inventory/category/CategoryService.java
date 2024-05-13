package com.zancheema.pos.service.inventory.category;

import com.zancheema.pos.service.inventory.category.dto.CategoryCreationPayload;
import com.zancheema.pos.service.inventory.category.dto.CategoryDistribution;
import com.zancheema.pos.service.inventory.category.dto.CategoryInfo;
import com.zancheema.pos.service.inventory.category.dto.CategoryPatch;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryInfo> getCategories();

    List<CategoryDistribution> getCategoryDistributions();

    Optional<CategoryInfo> addCategory(CategoryCreationPayload payload);

    Optional<CategoryInfo> updateCategory(CategoryPatch patch);

    void deleteCategory(long categoryId);
}
