package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstController {

    @GetMapping("/hell")
    public String sayHello(){
        return "hello_world";
    }
}
