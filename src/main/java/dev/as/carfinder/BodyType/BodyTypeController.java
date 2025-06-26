package dev.as.carfinder.bodytype;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/body-types")
@RequiredArgsConstructor
public class BodyTypeController {
    private final BodyTypeService bodyTypeService;

    @PostMapping
    public BodyTypeDTO createBodyType(@RequestBody BodyTypeDTO dto) {
        return bodyTypeService.createBodyType(dto);
    }

    @GetMapping("/{id}")
    public BodyTypeDTO getBodyType(@PathVariable Long id) {
        return bodyTypeService.getBodyTypeById(id);
    }

    @GetMapping
    public List<BodyTypeDTO> getAllBodyTypes() {
        return bodyTypeService.getAllBodyTypes();
    }

    @PutMapping("/{id}")
    public BodyTypeDTO updateBodyType(@PathVariable Long id, @RequestBody BodyTypeDTO dto) {
        return bodyTypeService.updateBodyType(id, dto);
    }

    @PatchMapping("/{id}")
    public BodyTypeDTO patchBodyType(@PathVariable Long id, @RequestBody BodyTypeDTO dto) {
        return bodyTypeService.patchBodyType(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBodyType(@PathVariable Long id) {
        bodyTypeService.deleteBodyType(id);
    }

}
