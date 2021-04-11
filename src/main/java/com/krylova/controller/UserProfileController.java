package com.krylova.controller;

import com.krylova.entity.UserProfile;
import com.krylova.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    @PostMapping("/api/userProfiles")
    public ResponseEntity<?> create(@RequestBody UserProfile userProfile){
        userProfileService.create(userProfile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/userProfiles")
    public ResponseEntity<List<UserProfile>> findAll(){
        final List<UserProfile> userProfileList = userProfileService.findAll();
        return userProfileList != null && !userProfileList.isEmpty()
                ? new ResponseEntity<>(userProfileList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/userProfiles/{id}")
    public ResponseEntity<Optional<UserProfile>> find(@PathVariable(name = "id") Long id){
        final Optional<UserProfile> userProfile = userProfileService.find(id);
        return userProfile != null
                ? new ResponseEntity<>(userProfile, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/api/userProfiles/{id}")
    public ResponseEntity<?> updateUserProfile(@PathVariable(name = "id") Long id, @RequestBody UserProfile userProfileUpdate) {
        return userProfileService.find(id).map(userProfile -> {
            userProfile.setFirstName(userProfileUpdate.getFirstName());
            userProfile.setLastName(userProfileUpdate.getLastName());
            userProfile.setMiddleName(userProfileUpdate.getMiddleName());
            userProfile.setBirthday(userProfileUpdate.getBirthday());
            userProfile.setCity(userProfileUpdate.getCity());
            userProfile.setStreet(userProfileUpdate.getStreet());
            userProfile.setPostalCode(userProfileUpdate.getPostalCode());
            userProfile.setBuilding(userProfileUpdate.getBuilding());
            userProfile.setFlat(userProfileUpdate.getFlat());
            userProfile.setUserT(userProfileUpdate.getUserT());
            userProfileService.update(userProfile);
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());

    }

    @DeleteMapping("/api/userProfiles/{id}")
    public ResponseEntity<?> deleteUserProfile(@PathVariable(name = "id") Long id) {
        return userProfileService.find(id).map(userProfile -> {
            userProfileService.delete(userProfile);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }


}
