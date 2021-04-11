package com.krylova.service;

import com.krylova.entity.UserProfile;
import com.krylova.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    public void create(UserProfile userProfile){
        userProfileRepository.save(userProfile);
    }

    public void update(UserProfile userProfile) { userProfileRepository.save(userProfile); }

    public void delete(UserProfile userProfile) { userProfileRepository.delete(userProfile); }

    public List<UserProfile> findAll(){
        return userProfileRepository.findAll();
    }

    public Optional<UserProfile> find(Long id){
        return userProfileRepository.findById(id);
    }
}
