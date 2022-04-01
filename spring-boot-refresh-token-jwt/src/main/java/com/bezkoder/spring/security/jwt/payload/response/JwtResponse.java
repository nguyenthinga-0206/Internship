package com.bezkoder.spring.security.jwt.payload.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
	private String address;
    private List<String> roles;

    public JwtResponse(String accessToken, String refreshToken, String firstname, String lastname, String email, String address, String phone,
            List<String> roles) {
        this.token = accessToken;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
		this.phone = phone;
        this.roles = roles;
        this.refreshToken = refreshToken;
    }

}
