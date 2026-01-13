package com.social.media.kgram.service;

import com.social.media.kgram.models.PlatformProfile;
import com.social.media.kgram.models.PlatformUser;
import com.social.media.kgram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<PlatformUser> getAllUsers() {
        return userRepository.findAll();
    }

    public PlatformUser addNewUser(PlatformUser platformUser) {
        if (platformUser.getPlatformProfile() != null) {
            platformUser.setPlatformProfile(platformUser.getPlatformProfile());
        }
        return userRepository.save(platformUser);
    }

    public String deleteUser(Long userId) {
        PlatformUser platformUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));
        userRepository.delete(platformUser);
        return "User Deleted Successfully";
    }
}
