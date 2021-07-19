package com.example.health.entity;

import com.example.health.repository.VideoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoTest {

    @Autowired
    VideoRepository videoRepository;

    @Test
    public void test1(){
        Video qwe = new Video("qwe","rqr");
        Video qwe2 = new Video("qwerqwed","qrqqq");
        System.out.println("qwe2 = " + qwe2.getName());
        System.out.println("qwe2 = " + qwe2.getId());
        System.out.println("qwe = " + qwe.getName());
        System.out.println("qwe = " + qwe.getId());
        Video save = videoRepository.save(qwe);
        Video save1 = videoRepository.save(qwe2);
        Video byVideoid = videoRepository.findByName(qwe.getName());
        Video byVideoid1 = videoRepository.findByName(qwe2.getName());
        org.assertj.core.api.Assertions.assertThat(qwe.getName()).isEqualTo(byVideoid.getName());
        org.assertj.core.api.Assertions.assertThat(qwe2.getName()).isEqualTo(byVideoid1.getName());
    }
    @Test
    public void test2(){
        Video qwe = videoRepository.findByName("qwe");
        org.assertj.core.api.Assertions.assertThat(qwe).isNull();
    }

}