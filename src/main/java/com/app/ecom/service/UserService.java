package com.app.ecom.service;


import com.app.ecom.model.User;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

   // private List<User> userList=new ArrayList<>();
    private final UserRepository userRepository;
    private Long nextId=1L;
    public List<User> fetchAllUsers()
    {
        return userRepository.findAll();
    }
    public void addUser(User user)
    {
       // user.setId(nextId++);
        //userList.add(user);
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

}
