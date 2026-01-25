package ds.assignment_2024.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home(Model model) {

        model.addAttribute("title", "Home");
        return "index";
    }
}
