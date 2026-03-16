package com.atharv.SolarShare.service;

import com.atharv.SolarShare.model.UserInfo;
import com.atharv.SolarShare.repo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserInfoRepository userInfoRepo;

    public UserInfo getProfile(String username) {
        UserInfo info = userInfoRepo.findByUserUsername(username);
        return info;
    }
}
