package com.bezkoder.spring.security.jwt.services;

import com.bezkoder.spring.security.jwt.payload.request.ChangePasswordRequest;
import com.bezkoder.spring.security.jwt.payload.response.UserResponse;

import java.util.List;

public interface IUserService {
    boolean changePassword(ChangePasswordRequest changePasswordRequest);

    List<UserResponse> listUser();
}
