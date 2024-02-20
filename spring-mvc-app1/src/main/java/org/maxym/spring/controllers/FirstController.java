package org.maxym.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String  name,
                            @RequestParam(value = "surname", required = false) String  surname,
                            Model model) {

        model.addAttribute("message", "Hello, " + name + " " + surname);

        return "first/hello";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam("a") int a,
                             @RequestParam("b") int b,
                             @RequestParam("action") String action,
                             Model model) {

        String result =  switch (action) {
            case "multiplication" -> String.valueOf(a * b);
            case "addition" -> String.valueOf(a + b);
            case "subtraction" -> String.valueOf(a - b);
            case "division" -> String.valueOf(a / (double) b);
            default -> "Incorrect action";
        };

        model.addAttribute("result", result);

        return "first/calculator";
    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "first/goodbye";
    }
}
