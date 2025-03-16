package com.taskforge.Task_Forge.Service;

import com.taskforge.Task_Forge.Exceptions.UserNotFoundException;
import com.taskforge.Task_Forge.Model.User;
import com.taskforge.Task_Forge.Repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(UUID id){
        if(id == null){
            throw new IllegalArgumentException("User ID cannot be null");
        }
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return user;
    }

    public User createUser(User user){
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        //Consider adding validation for email format here.
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(UUID id){
        if(id == null){
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User not found with id" + id);
        }
        userRepository.deleteById(id);
    }

    public User updateUser(User user){
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return userRepository.save(user);
    }
}
