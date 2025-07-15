package com.sandeep.QuizApp.contoller;

import com.sandeep.QuizApp.dao.UserRepository;
import com.sandeep.QuizApp.entity.AppUser;
import com.sandeep.QuizApp.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @PostMapping("/add-user")
//    public ResponseEntity<AppUser> addUser(@RequestBody AppUser user) {
//        user.setRole(Role.ROLE_USER);
//        return ResponseEntity.ok(userRepository.save(user));
//    }
    @PostMapping("/add-user")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser user) {
        user.setRole(Role.ROLE_USER); // set default role
        System.out.println("User saved: " + user.getEmail());
        return ResponseEntity.ok(userRepository.save(user));
    }

//    @PostMapping("/add-admin")
//    public ResponseEntity<AppUser> addAdmin(@RequestBody AppUser user) {
//        user.setRole(Role.ROLE_ADMIN);
//        return ResponseEntity.ok(userRepository.save(user));
//    }
    @PostMapping("/add-admin")
    public ResponseEntity<AppUser> addAdmin(@RequestBody AppUser user) {
        user.setRole(Role.ROLE_ADMIN);
        AppUser savedAdmin = userRepository.save(user);
        System.out.println("Admin added: " + savedAdmin.getEmail());
        return ResponseEntity.ok(savedAdmin);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
