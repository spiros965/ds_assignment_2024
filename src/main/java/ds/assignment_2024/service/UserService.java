package ds.assignment_2024.service;

import ds.assignment_2024.entities.User;
import ds.assignment_2024.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
@SessionScope
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private Integer currentUserId;
    private String currentUsername;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Update a user's role
    public User updateUserRole(Integer id, String role) {
        User user = getUserById(id);
        user.setRole(role);
        return userRepository.save(user);
    }

    // Delete a user
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public void setCurrentUser(Integer userId, String username) {
        this.currentUserId = userId;
        this.currentUsername = username;
    }

    public Integer getCurrentUserId() {
        return currentUserId;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void clear() {
        this.currentUserId = null;
        this.currentUsername = null;
    }

    public boolean isAdmin(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getRole().equals("ROLE_ADMIN");        }
        return false;
    }

    @Transactional
    public void registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        
        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}