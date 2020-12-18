package com.tts.api.controller;

import com.tts.api.entity.UserEntity;
import com.tts.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserEntity userEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userRepository.save(userEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
