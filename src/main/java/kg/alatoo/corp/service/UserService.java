package kg.alatoo.corp.service;
import java.util.List;

import kg.alatoo.corp.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(long id, UserDto userDto);
    void deleteUser(long id);
}
