package com.poly.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/")
    public String goTohomePage() {
        return "redirect:/produits/user/all";
    }
    @GetMapping("/erreur")
    public String goToerreurPage(){
        return "erreurPage";
    }

}
