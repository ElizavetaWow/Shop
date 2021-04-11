package com.krylova.repository;


import com.krylova.entity.UserT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTRepository extends JpaRepository<UserT, Long> {
}
