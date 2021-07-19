package com.example.health.controller;

import com.example.health.Dto.YoutubeDto;
import com.example.health.Service.UserService;
import com.example.health.Service.YoutubeService;
import com.example.health.config.auth.PrincipalDetails;
import com.example.health.entity.User;
import com.example.health.entity.Video;
import com.example.health.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.w3c.dom.Node;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final YoutubeService youtubeService;
    private final UserService userService;
    @GetMapping("/")
    public String login(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        try{
            model.addAttribute("name",userDetails.getUser().getName());
            model.addAttribute("check",true);
        }
        catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        return "index";
    }
    @GetMapping("/exe/shoulder")
    public String shoulder(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        try{
            model.addAttribute("name",userDetails.getUser().getName());
            model.addAttribute("check",true);
        }
        catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        YoutubeDto[] getshoulder = youtubeService.getshoulder();
        model.addAttribute("list",getshoulder);
        return "shoulder";
    }
    @GetMapping("/exe/idu")
    public String idu(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        try{
            model.addAttribute("name",userDetails.getUser().getName());
            model.addAttribute("check",true);
        }
        catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        YoutubeDto[] getidu = youtubeService.getidu();
        model.addAttribute("list",getidu);
        return "idu";
    }
    @GetMapping("/exe/samdu")
    public String samdu(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        try{
            model.addAttribute("name",userDetails.getUser().getName());
            model.addAttribute("check",true);
        }
        catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        YoutubeDto[] getsamdu = youtubeService.getsamdu();
        model.addAttribute("list",getsamdu);
        return "samdu";
    }
    @GetMapping("/exe/chest")
    public String chest(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        try{
            model.addAttribute("name",userDetails.getUser().getName());
            model.addAttribute("check",true);
        }
        catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        YoutubeDto[] getchest = youtubeService.getchest();
        model.addAttribute("list",getchest);
        return "chest";
    }
    @GetMapping("/exe/leg")
    public String leg(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        try{
            model.addAttribute("name",userDetails.getUser().getName());
            model.addAttribute("check",true);
        }
        catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        YoutubeDto[] getleg = youtubeService.getleg();
        model.addAttribute("list",getleg);
        return "leg";
    }
    @GetMapping("/exe/back")
    public String back(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        try{
            model.addAttribute("name",userDetails.getUser().getName());
            model.addAttribute("check",true);
        }
        catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        YoutubeDto[] getback = youtubeService.getback();
        model.addAttribute("list",getback);
        return "back";
    }
    @GetMapping("/exe/bock")
    public String bock(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        try{
            model.addAttribute("name",userDetails.getUser().getName());
            model.addAttribute("check",true);
        }
        catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        YoutubeDto[] getbock = youtubeService.getbock();
        model.addAttribute("list",getbock);
        return "bock";
    }
    @GetMapping("/exe/running")
    public String running(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        try{
            model.addAttribute("name",userDetails.getUser().getName());
            model.addAttribute("check",true);
        }
        catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        YoutubeDto[] getrunning = youtubeService.getrunning();
        model.addAttribute("list",getrunning);
        return "running";
    }
    @GetMapping("/exe/stretch")
    public String stretch(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        try{
            model.addAttribute("name",userDetails.getUser().getName());
            model.addAttribute("check",true);
        }
        catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        YoutubeDto[] getstretch = youtubeService.getstretch();
        model.addAttribute("list",getstretch);
        return "stretch";
    }
    @PostMapping("/myvideo")
    public String myvideo(@AuthenticationPrincipal PrincipalDetails userDetails,String id){
        User user = userDetails.getUser();
        userService.myvideo(user,id);
        return "redirect:/";
    }
    @GetMapping("/myvideopage")
    public String myvideopage(@AuthenticationPrincipal PrincipalDetails userDetails,Model model){
        try{
            model.addAttribute("name",userDetails.getUser().getName());
            model.addAttribute("check",true);
        }
        catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        User user = userDetails.getUser();
        YoutubeDto[] transform = userService.transform(user);
        model.addAttribute("list",transform);
        return "mypage";
    }
    @PostMapping("/delete")
    public String delete(@AuthenticationPrincipal PrincipalDetails userDetails,String id) {
        userService.delete(userDetails.getUser(),id);
        return "redirect:/myvideopage";
    }

}
