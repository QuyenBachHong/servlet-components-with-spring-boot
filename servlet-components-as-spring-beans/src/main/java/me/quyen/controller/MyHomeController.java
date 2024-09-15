package me.quyen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/spring")
public class MyHomeController {

    @GetMapping({"/","/home"})
    @ResponseBody
    public String home(){
        return "<h1 style=\"color:blue;text-align:center;\">Request from Spring-Controller</h1>";
    }
}
