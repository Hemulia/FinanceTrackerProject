package com.example.FinanceTracker.Controller;

import com.example.FinanceTracker.Model.User;
import com.example.FinanceTracker.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> getAllUsers() {
        logger.info("Getting all the users");

        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        logger.info("Received a request to create a user with username: {}", user.getUsername());

        try {
            // User creation logic
            // Hash the user's password before saving it
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            logger.info("User created successfully: {}", user.getUsername());
            return userService.createUser(user);
        } catch (Exception e) {
            logger.error("Error creating user: {}", e.getMessage());
            throw e; // You may want to handle exceptions better in a production environment
        }
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        // Hash the updated password, if provided
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}