package com.zancheema.pos.service.inventory.brand;

import com.zancheema.pos.service.inventory.brand.dto.BrandCreationPayload;
import com.zancheema.pos.service.inventory.brand.dto.BrandInfo;
import com.zancheema.pos.service.inventory.brand.dto.BrandPatch;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<BrandInfo> getBrands();

    Optional<BrandInfo> addBrand(BrandCreationPayload payload);

    Optional<BrandInfo> updateBrand(BrandPatch patch);

    void deleteBrand(long brandId);
}
