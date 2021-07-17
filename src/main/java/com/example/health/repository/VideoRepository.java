package com.example.health.repository;


import com.example.health.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VideoRepository extends JpaRepository<Video,Long> {

    public Video findByVideoid(String s);
}
