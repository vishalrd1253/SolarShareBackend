package com.atharv.SolarShare.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String city;
    private String userType;
}
