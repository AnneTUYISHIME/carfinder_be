package dev.as.carfinder.brand;

import dev.as.carfinder.brand.BrandDTO;
import java.util.List;

public interface BrandService {
    BrandDTO createBrand(BrandDTO dto);
    BrandDTO getBrandById(Long id);
    List<BrandDTO> getAllBrands();
    BrandDTO updateBrand(Long id, BrandDTO dto);
    BrandDTO patchBrand(Long id, BrandDTO dto);

    void deleteBrand(Long id);
}
