package com.example.health.repository;

import com.example.health.entity.User;
import com.example.health.entity.Video;
import com.example.health.entity.VideoItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VideoRepository videoRepository;
    @Autowired
    VideoItemRepository videoItemRepository;



}