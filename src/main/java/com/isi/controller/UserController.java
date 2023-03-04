package com.isi.controller;

import com.isi.entities.CvUserEntity;
import com.isi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @ModelAttribute("user")
    public CvUserEntity cvUserEntity() {
        return new CvUserEntity();
    }
    @GetMapping
    public String showRegistrationForm(ModelMap modelMap) {
        CvUserEntity cv = new CvUserEntity();
        modelMap.addAttribute("cv",cv);
        return "register";
    }
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") CvUserEntity cvEntity) {
        userService.save(cvEntity);
        return "redirect:/registration?success";
    }
}
