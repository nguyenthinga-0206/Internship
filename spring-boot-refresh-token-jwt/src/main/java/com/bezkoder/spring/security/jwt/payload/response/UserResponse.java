package com.bezkoder.spring.security.jwt.payload.response;

import com.bezkoder.spring.security.jwt.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phone;
    private Set<Role> role;
}
