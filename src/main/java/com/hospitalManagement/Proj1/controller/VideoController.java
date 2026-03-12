package com.hospitalManagement.Proj1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VideoController {

    @GetMapping("/video-call")
    public String videoCall(@RequestParam("room") String room, Model model) {
        model.addAttribute("room", room);
        return "video-call";
    }

}
