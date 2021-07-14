package com.example.health.repository;


import com.example.health.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUserid(String Userid);
}
