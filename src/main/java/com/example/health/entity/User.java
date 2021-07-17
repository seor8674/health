package com.example.health.entity;

import com.example.health.Dto.YoutubeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String userid;
    private String password;
    private String email;




    @OneToMany(mappedBy = "user")
    List<Video> videos=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Board> boardList=new ArrayList<>();

    public User(String name, String userid, String password, String email) {
        this.name = name;
        this.userid = userid;
        this.password = password;
        this.email = email;
    }

    public void addvideo(Video video){
        videos.add(video);
    }
}
