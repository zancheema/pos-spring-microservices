package com.zancheema.pos.service.inventory.category;

import com.zancheema.pos.service.inventory.category.dto.CategoryCreationPayload;
import com.zancheema.pos.service.inventory.category.dto.CategoryDistribution;
import com.zancheema.pos.service.inventory.category.dto.CategoryInfo;
import com.zancheema.pos.service.inventory.category.dto.CategoryPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryInfo> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("distribution")
    public List<CategoryDistribution> getCategoryDistribution() {
        return categoryService.getCategoryDistributions();
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryInfo> addCategory(@RequestBody CategoryCreationPayload payload) {
        return ResponseEntity.of(categoryService.addCategory(payload));
    }

    @PatchMapping("/update")
    public ResponseEntity<CategoryInfo> updateCategory(@RequestBody CategoryPatch patch) {
        return ResponseEntity.of(categoryService.updateCategory(patch));
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
