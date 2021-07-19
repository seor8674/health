package com.example.health.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.health.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByNickname(String nickname);

    @Query("select u from User u join fetch u.videoItemList where u.nickname=:nickname")
    public User findByNicknamefetch(@Param("nickname") String nickname);


}
