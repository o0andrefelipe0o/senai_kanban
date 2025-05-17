package br.com.kanban.kanban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KanbanController {

    @GetMapping("/kanban")
    public String kanban() {
        return "kanban"; // Mostra o arquivo kanban.html que está na pasta templates
    }
}
