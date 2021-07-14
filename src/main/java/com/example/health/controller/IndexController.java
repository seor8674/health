package com.example.health.controller;

import com.example.health.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
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
}
