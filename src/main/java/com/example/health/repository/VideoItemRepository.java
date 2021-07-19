package com.example.health.repository;

import com.example.health.entity.VideoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoItemRepository extends JpaRepository<VideoItem,Long> {
}
