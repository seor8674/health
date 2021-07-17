package com.example.health.Service;

import com.example.health.entity.User;
import com.example.health.entity.Video;
import com.example.health.repository.UserRepository;
import com.example.health.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;

    public void myvideo(String userid,String videoid){
        User user = userRepository.findByUserid(userid);
        Video byVideonum = videoRepository.findByVideoid(videoid);
        user.addvideo(byVideonum);
        System.out.println(user.getVideos().size());
    }
}
