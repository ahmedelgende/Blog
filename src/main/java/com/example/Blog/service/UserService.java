package com.example.Blog.service;

import com.example.Blog.Dto.UserDto;
import com.example.Blog.entity.Post;
import com.example.Blog.entity.User;
import com.example.Blog.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /*
    Map List of users -> list of user's DTO
     */
    public List<UserDto> getUsersList() {
        List<UserDto> userDtoList = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();
        for(User u: userRepository.findAll()) {
            UserDto userDto = modelMapper.map(u, UserDto.class);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public UserDto getUserById(Integer userId) {
        return userRepository.findById(userId).map(u -> {
            UserDto userDto = new UserDto(u.getUserId(), u.getName(), u.getAge(), u.getPosts());
            return userDto;
        }).orElse(null);
    }

    public void saveUser(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);

        userRepository.saveAndFlush(user);
        userDto.setUserId(user.getUserId());
    }

    public void updateUser(UserDto userDto) {
        User user = new User(userDto.getUserId(), userDto.getName(), userDto.getAge(), userDto.getPosts());
        userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        UserDto userDto = getUserById(userId);

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);
        userRepository.delete(user);
    }

}
