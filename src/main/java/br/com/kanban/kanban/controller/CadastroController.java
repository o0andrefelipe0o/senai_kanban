package br.com.kanban.kanban.controller;

import br.com.kanban.kanban.dto.UsuarioDTO;
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
        return "cadastro";
    }

    @PostMapping("/salvar-cadastro")
    public String salvarCadastro(@ModelAttribute UsuarioDTO usuarioDTO) {

        // 1. Verifica se as senhas coincidem
        if (!usuarioDTO.getSenha().equals(usuarioDTO.getConfirmarSenha())) {
            return "redirect:/cadastro?erro=senhas-diferentes";
        }

        // 2. Verifica se o nome de usuário já está cadastrado
        if (usuarioRepository.findByUsuario(usuarioDTO.getUsuario()) != null) {
            return "redirect:/cadastro?erro=usuario-existente";
        }

        // 3. Cria o objeto Usuario com os dados do DTO
        Usuario usuario = new Usuario();
        usuario.setUsuario(usuarioDTO.getUsuario());
        usuario.setSenha(usuarioDTO.getSenha()); // Recomendado: criptografar a senha

        usuarioRepository.save(usuario);
        return "redirect:/";
    }
}
