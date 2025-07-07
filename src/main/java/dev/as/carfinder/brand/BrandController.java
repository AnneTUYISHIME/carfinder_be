package dev.as.carfinder.brand;

import dev.as.carfinder.brand.BrandDTO;
import dev.as.carfinder.brand.BrandService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {


    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @SecurityRequirement(name = "auth")
    @PostMapping
    public BrandDTO createBrand(@RequestBody BrandDTO dto) {
        return brandService.createBrand(dto);
    }


    @SecurityRequirement(name = "auth")
    @GetMapping("/{id}")
    public BrandDTO getBrand(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }

    @GetMapping
    public List<BrandDTO> getAllBrands() {
        return brandService.getAllBrands();
    }

    @SecurityRequirement(name = "auth")
    @PreAuthorize("@brandSecurity.isOwnerOfBrand(#id)")
    @PutMapping("/{id}")
    public BrandDTO updateBrand(@PathVariable Long id, @RequestBody BrandDTO dto) {
        return brandService.updateBrand(id, dto);
    }

    @SecurityRequirement(name = "auth")
    @PreAuthorize("@brandSecurity.isOwnerOfBrand(#id)")
    @PatchMapping("/{id}")
    public BrandDTO patchBrand(@PathVariable Long id, @RequestBody BrandDTO dto) {
        return brandService.patchBrand(id, dto);
    }


    @SecurityRequirement(name = "auth")
    @PreAuthorize("@brandSecurity.isOwnerOfBrand(#id)")
    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }
}
