package com.example.health.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class VideoItem {

    @Id
    @GeneratedValue
    @Column(name = "videoitem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;

    public void setUser(User user) {
        this.user = user;
    }

    public VideoItem(Video video) {
        this.video = video;
    }
}
