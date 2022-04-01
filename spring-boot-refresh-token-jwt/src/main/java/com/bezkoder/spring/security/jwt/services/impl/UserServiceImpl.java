package com.bezkoder.spring.security.jwt.services.impl;

import com.bezkoder.spring.security.jwt.models.User;
import com.bezkoder.spring.security.jwt.payload.request.ChangePasswordRequest;
import com.bezkoder.spring.security.jwt.payload.response.UserResponse;
import com.bezkoder.spring.security.jwt.repository.UserRepository;
import com.bezkoder.spring.security.jwt.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public boolean changePassword(ChangePasswordRequest changePasswordRequest) {
        Optional<User> userOptional = userRepository.findByEmail(changePasswordRequest.getEmail());
        return userOptional.map(user -> {
            if (encoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
                user.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));
                userRepository.save(user);

                return true;
            }
            return false;
        }).orElse(false);
    }

    @Override
    public List<UserResponse> listUser(){
        return userRepository.findByDeletedFlagFalse()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private UserResponse convertToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        userResponse.setEmail(user.getEmail());
        userResponse.setAddress(user.getAddress());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRoles());

        return  userResponse;
    }
}
