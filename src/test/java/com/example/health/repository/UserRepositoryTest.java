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

    @Test
    public void test1(){

        User user = new User("이환준","seor75","1234","123@123");
        User user2 = new User("김렁쟈","seor7545","1234","123@123");
        Video video = new Video("video1","title1");
        Video video2 = new Video("video2","title2");
        userRepository.save(user2);
        userRepository.save(user);
        videoRepository.save(video);
        videoRepository.save(video2);
        User byNickname = userRepository.findByNickname(user.getNickname());
        byNickname.addvideoitem(new VideoItem(video));
        System.out.println(byNickname.getVideoItemList().size());


    }
    @Test
    public void test2(){
        User user = new User("이환준","seor75","1234","123@123");
        User user2 = new User("김렁쟈","seor7545","1234","123@123");
        Video video = new Video("video1","title1");
        Video video2 = new Video("video2","title2");
        userRepository.save(user2);
        userRepository.save(user);
        videoRepository.save(video);
        videoRepository.save(video2);
        VideoItem videoItem = new VideoItem(videoRepository.findByName(video.getName()));
        user.addvideoitem(videoItem);
        videoItemRepository.save(videoItem);
        User byNicknamefetch = userRepository.findByNicknamefetch(user.getNickname());
        System.out.println(byNicknamefetch.getVideoItemList().size());
    }

}