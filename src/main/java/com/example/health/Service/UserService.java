package com.example.health.Service;

import com.example.health.Dto.YoutubeDto;
import com.example.health.entity.User;
import com.example.health.entity.Video;
import com.example.health.entity.VideoItem;
import com.example.health.repository.UserRepository;
import com.example.health.repository.VideoItemRepository;
import com.example.health.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.LazyInitializationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private final VideoItemRepository videoItemRepository;

    public void myvideo(User user,String videoname){
        Video byName = videoRepository.findByName(videoname);
        VideoItem videoItem = new VideoItem(byName);
        videoItem.setUser(user);
        videoItemRepository.save(videoItem);
        User byNicknamefetch = userRepository.findByNicknamefetch(user.getNickname());
        byNicknamefetch.addvideoitem(videoItem);
    }
    public YoutubeDto[] transform(User user){
        User byNicknamefetch = userRepository.findByNicknamefetch(user.getNickname());
        try{
            List<VideoItem> videoItemList = byNicknamefetch.getVideoItemList();
            YoutubeDto[] ar=new YoutubeDto[videoItemList.size()];
            for(int i=0;i<videoItemList.size();i++){
                ar[i]=new YoutubeDto(videoItemList.get(i).getVideo().getTitle(),videoItemList.get(i).getVideo().getName());
            }
            return ar;
        }
        catch (NullPointerException e){
            return null;
        }


    }
    public void delete(User user,String videoname){
        User byNicknamefetch = userRepository.findByNicknamefetch(user.getNickname());
        List<VideoItem> videoItemList = byNicknamefetch.getVideoItemList();
        for(int i=0;i<videoItemList.size();i++){
            if(videoItemList.get(i).getVideo().getName().equals(videoname)){
                videoItemRepository.deleteById(videoItemList.get(i).getId());
                videoItemList.remove(i);

                return;
            }
        }
    }
}
