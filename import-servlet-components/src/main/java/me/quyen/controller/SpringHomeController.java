package me.quyen.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/spring")
@RequiredArgsConstructor
public class SpringHomeController {
    final HttpServletRequest request;
    @GetMapping({"/","/home"})
    @ResponseBody
    public String home(){
        StringBuffer requestURL = request.getRequestURL();
        return String.format("<h2 style=\"color:green;text-align:center;\">Request from Spring-Controller with " +
                        "<i>URL:%s</i></h2>",requestURL);

    }
}
