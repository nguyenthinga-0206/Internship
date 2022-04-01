package com.bezkoder.spring.security.jwt.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class ProfileRequest {
    private String email;

    @NotBlank
    @Size(min = 3, max = 20)
    private String firstname;

    @NotBlank
    @Size(min = 3, max = 20)
    private String lastname;

    private String address;

    @NotNull
    @Pattern(regexp ="(^$|[0][0-9]{9}$)")
    private String phone;
}
