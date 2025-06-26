package dev.as.carfinder.user;


import dev.as.carfinder.user.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO dto);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO dto);
    UserDTO patchUser(Long id, UserDTO dto);

    void deleteUser(Long id);
}
