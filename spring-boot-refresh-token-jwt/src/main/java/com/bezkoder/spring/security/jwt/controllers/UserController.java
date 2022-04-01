package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.models.User;
import com.bezkoder.spring.security.jwt.payload.request.ChangePasswordRequest;
import com.bezkoder.spring.security.jwt.payload.request.ProfileRequest;
import com.bezkoder.spring.security.jwt.payload.response.UserResponse;
import com.bezkoder.spring.security.jwt.repository.UserRepository;
import com.bezkoder.spring.security.jwt.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> listUser(){
        List<UserResponse> userList = userService.listUser();
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        boolean isSuccessful = this.userService.changePassword(changePasswordRequest);

        if (isSuccessful) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<Boolean> editProfile(@RequestBody ProfileRequest profileRequest) {
        Optional<User> userOptional = userRepository.findByEmail(profileRequest.getEmail());
        if (userOptional.isPresent()) {
            userOptional.get().setFirstname(profileRequest.getFirstname());
            userOptional.get().setLastname(profileRequest.getLastname());
            userOptional.get().setAddress(profileRequest.getAddress());
            userOptional.get().setPhone(profileRequest.getPhone());
            this.userRepository.save(userOptional.get());
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
