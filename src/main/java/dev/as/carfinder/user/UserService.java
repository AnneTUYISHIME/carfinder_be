package dev.as.carfinder.user;

import dev.as.carfinder.exception.ResourceNotFound;
import dev.as.carfinder.user.dto.CreateUserRequest;
import dev.as.carfinder.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public User dtoToEntity(CreateUserRequest dto) {
        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setIsDeleted(false);
        return  user;
    }

    public UserResponseDto entityToDto(User user) {
        return  modelMapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto create(CreateUserRequest dto) {
        var createdUser = userRepo.save(dtoToEntity(dto));
        return entityToDto(createdUser);
    }

    public List<UserResponseDto> findAll() {
        return userRepo.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public UserResponseDto findById(Long id) {
        var user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User not found!"));
        return entityToDto(user);
    }

    public User findByEmail(String email) {
        return UserRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFound("User not found!"));
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }


}
