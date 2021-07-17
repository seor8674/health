package com.example.health.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.health.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUserid(String Userid);
}
