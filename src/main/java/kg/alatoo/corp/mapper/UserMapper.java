package kg.alatoo.corp.mapper;

import kg.alatoo.corp.dto.UserDto;
import kg.alatoo.corp.entity.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user){
        if (user == null) {
            return null;
        }
        return new UserDto(
            user.getId(),
            user.getUserName(),
            user.getUserPassword()
        );
    }
    public User toEntity(UserDto dto){
        if (dto == null) {
            return null;
        }
        return new User(
            dto.getId(),
            dto.getUserName(),
            dto.getUserPassword()
        );
    }
}