package com.example.health.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class YoutubeDto {
    private String title;
    private String videoid;

    public YoutubeDto(String title, String videoid) {
        this.title = title;
        this.videoid = videoid;
    }
}
