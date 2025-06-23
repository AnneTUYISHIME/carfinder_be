package dev.as.carfinder.brand;

import dev.as.carfinder.brand.BrandDTO;
import dev.as.carfinder.brand.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping
    public BrandDTO createBrand(@RequestBody BrandDTO dto) {
        return brandService.createBrand(dto);
    }

    @GetMapping("/{id}")
    public BrandDTO getBrand(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }

    @GetMapping
    public List<BrandDTO> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PutMapping("/{id}")
    public BrandDTO updateBrand(@PathVariable Long id, @RequestBody BrandDTO dto) {
        return brandService.updateBrand(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }
}
