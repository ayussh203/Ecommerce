package com.app.ecom.service;


import com.app.ecom.model.*;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<UserResponse> fetchAllUsers(){
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }
    public void addUser(UserRequest request)
    {
        User user=modelMapper.map(request,User.class);
        userRepository.save(user);

    }
    public Optional<User> fetchUser(Long Id)
    {
       return userRepository.findById(Id);
    }
    public boolean updateUser(Long id,User updatedUser)
    {
        return userRepository.findById(id)
                .map(u->{u.setFirstName(updatedUser.getFirstName());u.setLastName(updatedUser.getLastName());
               userRepository.save(u);
                return true;
                }).orElse(false);
    }
    private void updateUserFromRequest(User user, UserRequest userRequest) {
        modelMapper.map(userRequest, user);
    }

    private UserResponse mapToUserResponse(User user){
        UserResponse response = modelMapper.map(user, UserResponse.class);
        response.setId(String.valueOf(user.getId()));
        return response;
    }

}
