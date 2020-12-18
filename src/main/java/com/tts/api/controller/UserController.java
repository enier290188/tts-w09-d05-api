package com.tts.api.controller;

import com.tts.api.entity.UserEntity;
import com.tts.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<UserEntity> getUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    /*
     * @GetMapping("") public List<UserEntity> getUsers(@RequestParam(value = "state", required = false) String state) {
     * if (state != null) {
     * return (List<UserEntity>) userRepository.findByState(state);
     * }
     * return (List<UserEntity>) userRepository.findAll();
     * }
     */

    @PostMapping("")
    public void createUser(@RequestBody UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable(value = "id") Long id) {
        return userRepository.findById(id);
    }

    @PutMapping("/{id}")
    public void updateUserById(@PathVariable(value = "id") Long id, @RequestBody UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @DeleteMapping("/{id}")
    public void createUser(@PathVariable(value = "id") Long id) {
        userRepository.deleteById(id);
    }
}
