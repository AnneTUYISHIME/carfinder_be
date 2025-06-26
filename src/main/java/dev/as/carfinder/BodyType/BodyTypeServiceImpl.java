package dev.as.carfinder.bodytype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BodyTypeServiceImpl implements BodyTypeService {

    private final BodyTypeRepository bodyTypeRepository;

    @Override
    public BodyTypeDTO createBodyType(BodyTypeDTO dto) {
        BodyType bt = new BodyType();
        bt.setName(dto.getName());
        bt.setImage(dto.getImage());
        bodyTypeRepository.save(bt);
        return mapToDto(bt);
    }

    @Override
    public BodyTypeDTO getBodyTypeById(Long id) {
        BodyType bt = bodyTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Body Type not found"));
        return mapToDto(bt);
    }

    @Override
    public List<BodyTypeDTO> getAllBodyTypes() {
        return bodyTypeRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public BodyTypeDTO updateBodyType(Long id, BodyTypeDTO dto) {
        BodyType bt = bodyTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Body Type not found"));
        bt.setName(dto.getName());
        bt.setImage(dto.getImage());
        bodyTypeRepository.save(bt);
        return mapToDto(bt);
    }

    @Override
    public BodyTypeDTO patchBodyType(Long id, BodyTypeDTO dto) {
        BodyType bt = bodyTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Body Type not found"));

        if (dto.getName() != null) bt.setName(dto.getName());
        if (dto.getImage() != null) bt.setImage(dto.getImage());

        BodyType saved = bodyTypeRepository.save(bt);
        return mapToDto(saved);
    }


    @Override
    public void deleteBodyType(Long id) {
        bodyTypeRepository.deleteById(id);
    }

    private BodyTypeDTO mapToDto(BodyType bt) {
        BodyTypeDTO dto = new BodyTypeDTO();
        dto.setId(bt.getId());
        dto.setName(bt.getName());
        dto.setImage(bt.getImage());
        return dto;
    }

}