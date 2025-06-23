package dev.as.carfinder.user;

import dev.as.carfinder.user.UserDTO;
import dev.as.carfinder.user.UserRepository;
import dev.as.carfinder.user.User;
import dev.as.carfinder.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = new User();
        mapDtoToEntity(dto, user);
        userRepository.save(user);
        return mapEntityToDto(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return mapEntityToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        mapDtoToEntity(dto, user);
        userRepository.save(user);
        return mapEntityToDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private void mapDtoToEntity(UserDTO dto, User user) {
        user.setFname(dto.getFname());
        user.setLname(dto.getLname());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setRole(Role.valueOf(dto.getRole()));
    }

    private UserDTO mapEntityToDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFname(user.getFname());
        dto.setLname(user.getLname());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole().name());
        return dto;
    }
}