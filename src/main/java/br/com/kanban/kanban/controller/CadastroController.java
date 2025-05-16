package br.com.kanban.kanban.controller;

import br.com.kanban.kanban.model.Usuario;
import br.com.kanban.kanban.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro"; // nome do arquivo HTML em templates (sem .html)
    }

    @PostMapping("/salvar-cadastro")
    public String salvarCadastro(@ModelAttribute Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/"; // Redireciona para login após cadastro
    }
}
