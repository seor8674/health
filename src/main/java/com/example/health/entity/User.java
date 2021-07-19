package com.example.health.entity;

import com.example.health.Dto.YoutubeDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String nickname;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user")
    List<VideoItem> videoItemList=new ArrayList<>();


    public User(String name, String nickname, String password, String email) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }
    public void addvideoitem(VideoItem videoItem){
        this.videoItemList.add(videoItem);
        videoItem.setUser(this);
    }

}
