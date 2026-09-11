package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for User-related endpoints.
 *
 * @RestController = @Controller + @ResponseBody
 * All return values are automatically serialized to JSON.
 *
 * Node.js (Express) equivalent:
 *   const router = express.Router();
 *   router.get('/users', userService.getAll);
 *   router.post('/users', userService.create);
 */
@RestController
@RequestMapping("/users")
public class UserController {

    // Constructor injection (preferred over @Autowired field injection)
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /users — returns all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET /users/{id} — returns a single user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // POST /users — creates a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // PUT /users/{id} — updates an existing user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    // DELETE /users/{id} — deletes a user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
