package com.taskforge.Task_Forge.Service;

import com.taskforge.Task_Forge.Model.User;
import com.taskforge.Task_Forge.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id){
        return userRepository.findById(id);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }
}
