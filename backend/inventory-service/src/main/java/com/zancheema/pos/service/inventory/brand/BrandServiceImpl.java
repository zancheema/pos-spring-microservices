package com.zancheema.pos.service.inventory.brand;

import com.zancheema.pos.service.inventory.brand.dto.BrandCreationPayload;
import com.zancheema.pos.service.inventory.brand.dto.BrandInfo;
import com.zancheema.pos.service.inventory.brand.dto.BrandPatch;
import com.zancheema.pos.service.inventory.util.StreamUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    @Override
    public List<BrandInfo> getBrands() {
        return StreamUtil.stream(brandRepository.findAll())
                .map(brand -> new BrandInfo(brand.getId(), brand.getName(), brand.isActive()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BrandInfo> addBrand(BrandCreationPayload payload) {
        boolean alreadyExists = brandRepository.existsByNameIgnoreCase(payload.getName());
        if (alreadyExists) {
            return Optional.empty();
        }
        Brand brand = new Brand(0L, payload.getName(), true);
        Brand savedBrand = brandRepository.save(brand);
        BrandInfo info = new BrandInfo(savedBrand.getId(), savedBrand.getName(), savedBrand.isActive());
        return Optional.of(info);
    }

    @Override
    public Optional<BrandInfo> updateBrand(BrandPatch patch) {
        Optional<Brand> optionalBrand = brandRepository.findById(patch.getId());
        if (optionalBrand.isEmpty()) {
            return Optional.empty();
        }
        Brand brand = optionalBrand.get();
        if (StringUtils.hasLength(patch.getName())) {
            brand.setName(patch.getName());
        }
        if (patch.getIsActive() != null) {
            brand.setActive(patch.getIsActive());
        }
        Brand updatedBrand = brandRepository.save(brand);
        BrandInfo info = new BrandInfo(updatedBrand.getId(), updatedBrand.getName(), updatedBrand.isActive());
        return Optional.of(info);
    }

    @Override
    public void deleteBrand(long brandId) {
        if (brandRepository.existsById(brandId)) {
            brandRepository.deleteById(brandId);
        }
    }
}
