package dev.as.carfinder.BodyType;

import dev.as.carfinder.BodyType.BodyTypeDTO;
import dev.as.carfinder.BodyType.BodyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bodytypes")
public class BodyTypeController {

    @Autowired
    private BodyTypeService bodyTypeService;

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
