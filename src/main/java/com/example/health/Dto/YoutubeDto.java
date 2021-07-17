package com.example.health.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class YoutubeDto {
    private String title;
    private String thumbnailpath;
    private String videoid;

    public YoutubeDto(String title, String thumbnailpath, String videoid) {
        this.title = title;
        this.thumbnailpath = thumbnailpath;
        this.videoid = videoid;
    }
}
