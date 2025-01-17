package ds.assignment_2024.service;

import ds.assignment_2024.entities.User;
import ds.assignment_2024.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor-based dependency injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Update a user's role
    public User updateUserRole(Long id, String role) {
        User user = getUserById(id);
        user.setRole(role);
        return userRepository.save(user);
    }

    // Delete a user
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}