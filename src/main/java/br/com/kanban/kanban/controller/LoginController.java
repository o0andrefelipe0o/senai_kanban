package br.com.kanban.kanban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String home() {
        return "login"; // nome do arquivo HTML em templates (sem .html)
    }
}
