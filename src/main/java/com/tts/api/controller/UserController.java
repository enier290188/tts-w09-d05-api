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
    public ResponseEntity<List<UserEntity>> getUsers(@RequestParam(value = "state", required = false) String state) {
        if (state != null) {
            return new ResponseEntity<>((List<UserEntity>) userRepository.findByState(state), HttpStatus.OK);
        }
        return new ResponseEntity<>((List<UserEntity>) userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserEntity userEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userRepository.save(userEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable(value = "id") Long id) {
        Optional<UserEntity> userEntityData = userRepository.findById(id);
        if (userEntityData.isPresent()) {
            return new ResponseEntity<>(userEntityData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserById(@PathVariable(value = "id") Long id, @RequestBody UserEntity userEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<UserEntity> userEntityData = userRepository.findById(id);
        if (userEntityData.isPresent()) {
            UserEntity _userEntity = userEntityData.get();
            _userEntity.setFirstName(userEntity.getFirstName());
            _userEntity.setLastName(userEntity.getLastName());
            _userEntity.setState(userEntity.getState());
            userRepository.save(_userEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) {
        Optional<UserEntity> userEntityData = userRepository.findById(id);
        if (userEntityData.isPresent()) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/all")
    public void all() {
    }
}
