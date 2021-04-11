package com.krylova.service;

import com.krylova.entity.UserT;
import com.krylova.repository.UserTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTService {

    @Autowired
    private UserTRepository userTRepository;

    public void create(UserT userT){
        userTRepository.save(userT);
    }

    public void update(UserT userT) { userTRepository.save(userT); }

    public void delete(UserT userT) { userTRepository.delete(userT); }

    public List<UserT> findAll(){
        return userTRepository.findAll();
    }

    public Optional<UserT> find(Long id){
        return userTRepository.findById(id);
    }

}
