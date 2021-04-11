package com.krylova.controller;

import com.krylova.entity.Role;
import com.krylova.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping("/api/roles")
    public ResponseEntity<?> create(@RequestBody Role role){
        roleService.create(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/roles")
    public ResponseEntity<List<Role>> findAll(){
        final List<Role> roleList = roleService.findAll();
        return roleList != null && !roleList.isEmpty()
                ? new ResponseEntity<>(roleList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/roles/{id}")
    public ResponseEntity<Optional<Role>> find(@PathVariable(name = "id") Long id){
        final Optional<Role> role = roleService.find(id);
        return role != null
                ? new ResponseEntity<>(role, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/api/roles/{id}")
    public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id, @RequestBody Role roleUpdate) {
        return roleService.find(id).map(role -> {
            role.setName(roleUpdate.getName());
            role.setUsers(roleUpdate.getUsers());
            roleService.update(role);
            return new ResponseEntity<>(role, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());

    }

    @DeleteMapping("/api/roles/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable(name = "id") Long id) {
        return roleService.find(id).map(role -> {
            roleService.delete(role);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }





}
