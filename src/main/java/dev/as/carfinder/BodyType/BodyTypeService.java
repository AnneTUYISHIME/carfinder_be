package dev.as.carfinder.bodytype;

import java.util.List;

public interface BodyTypeService {
    BodyTypeDTO createBodyType(BodyTypeDTO dto);
    BodyTypeDTO getBodyTypeById(Long id);
    List<BodyTypeDTO> getAllBodyTypes();
    BodyTypeDTO updateBodyType(Long id, BodyTypeDTO dto);
    BodyTypeDTO patchBodyType(Long id, BodyTypeDTO dto);

    void deleteBodyType(Long id);
}
