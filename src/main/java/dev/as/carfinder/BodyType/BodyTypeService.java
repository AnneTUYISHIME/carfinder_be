package dev.as.carfinder.BodyType;

import dev.as.carfinder.BodyType.BodyTypeDTO;
import java.util.List;

public interface BodyTypeService {
    BodyTypeDTO createBodyType(BodyTypeDTO dto);
    BodyTypeDTO getBodyTypeById(Long id);
    List<BodyTypeDTO> getAllBodyTypes();
    BodyTypeDTO updateBodyType(Long id, BodyTypeDTO dto);
    void deleteBodyType(Long id);
}
