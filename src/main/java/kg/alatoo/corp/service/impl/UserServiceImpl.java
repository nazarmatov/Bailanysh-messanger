package kg.alatoo.corp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kg.alatoo.corp.entity.User;
import kg.alatoo.corp.exception.UserNotFoundException;
import kg.alatoo.corp.dto.UserDto;
import kg.alatoo.corp.mapper.UserMapper;
import kg.alatoo.corp.repository.UserRepository;
import kg.alatoo.corp.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with '"+id+"' not found"));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with '"+id+"' not found"));
        existingUser.setUserName(userDto.getUserName());
        existingUser.setUserPassword(userDto.getUserPassword());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with '"+id+"' not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        if (userRepository.existsById(id)) {
            return true;
        }else{
            return false;
        }
    }

}   