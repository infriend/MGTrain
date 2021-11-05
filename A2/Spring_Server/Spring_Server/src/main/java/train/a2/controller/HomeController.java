package train.a2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String HomePage(){
        return "login";
    }

    @RequestMapping("/regpage")
    public String registerpage(){
        return "register";
    }
}
