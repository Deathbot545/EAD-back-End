package com.example.User_Service.Service;

import com.example.User_Service.Model.User;
import com.example.User_Service.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserfromId(int id){
        Optional<User> user= userRepository.findById(id);
        if(!user.isPresent()){
            return  null;
        }
        else {
            return user.get();
        }
    }

    public User registerUser(User user) {
        // Set timestamps for created_at and updated_at
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        user.setCreated_at(currentTimestamp);
        user.setUpdated_at(currentTimestamp);

        // Save the user to the database
        return userRepository.save(user);
    }

    public User authenticateUser(String email, String password) {
        // Updated method name to match repository method
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        return user.orElse(null);
    }

    public User updateUser(int id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
