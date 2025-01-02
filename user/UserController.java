package com.software.eventmanagement.user;

import com.software.eventmanagement.cookies.CookieController;
import com.software.eventmanagement.entities.LoginRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    //tested successfully
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user, HttpServletResponse response) {
        userService.save(user);
        CookieController.setUserCookie(response, user.getUsername());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //tested successfully
    @PostMapping("/verifyUser")
    public ResponseEntity<?> verifyUser(@RequestBody LoginRequest request, HttpServletResponse response) {
        if (userService.verifyUser(request) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Invalid username or password\"}");
        CookieController.setUserCookie(response, request.getUsername());
        System.out.println("login successful");
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Validation successful\"}");
    }
}