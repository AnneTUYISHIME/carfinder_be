package dev.as.carfinder.brand;

import dev.as.carfinder.brand.BrandDTO;
import dev.as.carfinder.brand.BrandRepository;
import dev.as.carfinder.brand.Brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public BrandDTO createBrand(BrandDTO dto) {
        Brand brand = new Brand();
        brand.setName(dto.getName());
        brandRepository.save(brand);
        return mapToDto(brand);
    }

    @Override
    public BrandDTO getBrandById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found"));
        return mapToDto(brand);
    }

    @Override
    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public BrandDTO updateBrand(Long id, BrandDTO dto) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found"));
        brand.setName(dto.getName());
        brandRepository.save(brand);
        return mapToDto(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    private BrandDTO mapToDto(Brand brand) {
        BrandDTO dto = new BrandDTO();
        dto.setId(brand.getId());
        dto.setName(brand.getName());
        return dto;
    }
}
