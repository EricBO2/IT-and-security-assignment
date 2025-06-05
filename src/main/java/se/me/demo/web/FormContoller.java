package se.me.demo.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.me.demo.model.AppUser;

@RestController
@RequestMapping("/register")
public class FormContoller {

    @GetMapping
    public @ResponseBody String showForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "form";
    }

    @PostMapping
    public @ResponseBody String handleForm(@Valid @ModelAttribute AppUser user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        // här ska cod skrivas som sparara user i data basen / callar på clasen som gör det

        return "home";
    }
}
