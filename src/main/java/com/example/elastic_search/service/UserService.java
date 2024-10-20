package com.example.elastic_search.service;
import com.example.elastic_search.model.User;
import com.example.elastic_search.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);  // Convert Iterable to List
        return users;
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }


    public Optional<User> updateUserById(String id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            // Update the fields
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setAge(updatedUser.getAge());

            // Save the updated user to the repository
            userRepository.save(existingUser);
            return Optional.of(existingUser);
        } else {
            return Optional.empty(); // User not found
        }
    }


    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
}
