package com.software.eventmanagement.user;

import com.software.eventmanagement.entities.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public User verifyUser(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findById(loginRequest.getUsername());
        if(user.isPresent()) {
            User found = user.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(loginRequest.getPassword(), found.getPassword()))
                return found;
        }
        return null;
    }
}
