package br.com.kanban.kanban.controller;

import br.com.kanban.kanban.model.Usuario;
import br.com.kanban.kanban.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String home() {
        return "login"; // nome do arquivo HTML em templates (sem .html)
    }

    @PostMapping("/login")
    public String login(@RequestParam String usuario, @RequestParam String senha, HttpSession session) {
        Usuario usuarioEncontrado = usuarioRepository.findByUsuario(usuario);

        if (usuarioEncontrado != null && usuarioEncontrado.getSenha().equals(senha)) {
            session.setAttribute("usuario", usuarioEncontrado);
            return "redirect:/kanban";
        }

        return "redirect:/?erro=login";
    }
}

