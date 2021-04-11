package com.krylova.controller;

import com.krylova.entity.UserT;
import com.krylova.service.UserTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserTController {

    private final UserTService userTService;

    @Autowired
    public UserTController(UserTService userTService){
        this.userTService = userTService;
    }

    @PostMapping("/api/userTs")
    public ResponseEntity<?> create(@RequestBody UserT userT){
        userTService.create(userT);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/userTs")
    public ResponseEntity<List<UserT>> findAll(){
        final List<UserT> userTList = userTService.findAll();
        return userTList != null && !userTList.isEmpty()
                ? new ResponseEntity<>(userTList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/userTs/{id}")
    public ResponseEntity<Optional<UserT>> find(@PathVariable(name = "id") Long id){
        final Optional<UserT> userT = userTService.find(id);
        return userT != null
                ? new ResponseEntity<>(userT, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/api/userTs/{id}")
    public ResponseEntity<?> updateUserT(@PathVariable(name = "id") Long id, @RequestBody UserT userTUpdate) {
        return userTService.find(id).map(userT -> {
            userT.setEmail(userTUpdate.getEmail());
            userT.setPassword(userTUpdate.getPassword());
            userT.setIsActive(userTUpdate.getIsActive());
            userT.setRole(userTUpdate.getRole());
            userT.setUserProfile(userTUpdate.getUserProfile());
            userTService.update(userT);
            return new ResponseEntity<>(userT, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());

    }

    @DeleteMapping("/api/userTs/{id}")
    public ResponseEntity<?> deleteUserT(@PathVariable(name = "id") Long id) {
        return userTService.find(id).map(userT -> {
            userTService.delete(userT);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }





}
