package com.zancheema.pos.service.inventory.brand;

import com.zancheema.pos.service.inventory.brand.dto.BrandCreationPayload;
import com.zancheema.pos.service.inventory.brand.dto.BrandInfo;
import com.zancheema.pos.service.inventory.brand.dto.BrandPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<BrandInfo> getBrands() {
        return brandService.getBrands();
    }

    @PostMapping("/add")
    public ResponseEntity<BrandInfo> addBrand(@RequestBody BrandCreationPayload payload) {
        return ResponseEntity.of(brandService.addBrand(payload));
    }

    @PatchMapping("/update")
    public ResponseEntity<BrandInfo> updateBrand(@RequestBody BrandPatch patch) {
        return ResponseEntity.of(brandService.updateBrand(patch));
    }

    @DeleteMapping("/delete/{brandId}")
    public ResponseEntity<?> deleteBrand(@PathVariable("brandId") long brandId) {
        brandService.deleteBrand(brandId);
        return ResponseEntity.noContent().build();
    }
}
