package com.example.health.config.oauth;

import com.example.health.config.auth.PrincipalDetails;
import com.example.health.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.health.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
            OAuth2User oAuth2User=super.loadUser(userRequest);
            User user;
            String nickname;
            String password=bCryptPasswordEncoder.encode("이환준");
            String email;
            String name;
            User byuser;
            if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
                nickname="google_"+oAuth2User.getAttribute("sub");
                email=oAuth2User.getAttribute("email");
                name=oAuth2User.getAttribute("name");
            }
            else{
                Map response = (Map) oAuth2User.getAttributes().get("response");
                nickname="naver_"+response.get("id");
                email=(String)response.get("email");
                name=(String)response.get("name");
            }
            byuser = userRepository.findByNickname(nickname);

        if(byuser==null){
            user=new User(name,nickname,password,email);
            userRepository.save(user);
        }
        else{
            user=byuser;
        }
        return new PrincipalDetails(user,oAuth2User.getAttributes());
    }

}
