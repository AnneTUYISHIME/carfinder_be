package dev.as.carfinder.bodytype;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/body_types")
@RequiredArgsConstructor
public class BodyTypeController {
    private final BodyTypeService bodyTypeService;

    @SecurityRequirement(name = "auth")
    @PostMapping
    public BodyTypeDTO createBodyType(@RequestBody BodyTypeDTO dto) {
        return bodyTypeService.createBodyType(dto);
    }

    @SecurityRequirement(name = "auth")
    @GetMapping("/{id}")
    public BodyTypeDTO getBodyType(@PathVariable Long id) {
        return bodyTypeService.getBodyTypeById(id);
    }

    @GetMapping
    public List<BodyTypeDTO> getAllBodyTypes() {
        return bodyTypeService.getAllBodyTypes();
    }

    @SecurityRequirement(name = "auth")
    @PreAuthorize("@bodyTypeSecurity.isOwnerOfBodyType(#id)")
    @PutMapping("/{id}")
    public BodyTypeDTO updateBodyType(@PathVariable Long id, @RequestBody BodyTypeDTO dto) {
        return bodyTypeService.updateBodyType(id, dto);
    }

    @SecurityRequirement(name = "auth")
    @PreAuthorize("@bodyTypeSecurity.isOwnerOfBodyType(#id)")
    @PatchMapping("/{id}")
    public BodyTypeDTO patchBodyType(@PathVariable Long id, @RequestBody BodyTypeDTO dto) {
        return bodyTypeService.patchBodyType(id, dto);
    }

    @SecurityRequirement(name = "auth")
    @PreAuthorize("@bodyTypeSecurity.isOwnerOfBodyType(#id)")
    @DeleteMapping("/{id}")
    public void deleteBodyType(@PathVariable Long id) {
        bodyTypeService.deleteBodyType(id);
    }

    @SecurityRequirement(name = "auth")
    @GetMapping("/test-role")
    @PreAuthorize("hasRole('USER')")
    public String testUserRoleAccess() {
        return " Access granted for USER!";
    }


}
