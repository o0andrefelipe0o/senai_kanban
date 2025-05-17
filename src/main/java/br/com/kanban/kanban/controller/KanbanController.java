package br.com.kanban.kanban.controller;

import br.com.kanban.kanban.model.Postagem;
import br.com.kanban.kanban.model.Usuario;
import br.com.kanban.kanban.repository.PostagemRepository;
import br.com.kanban.kanban.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class KanbanController {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/kanban")
    public String kanban(HttpSession session, Model model) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogado == null) {
            return "redirect:/?erro=login";
        }

        List<Postagem> tarefas = postagemRepository.findByUsuarioId(usuarioLogado.getId());
        model.addAttribute("tarefas", tarefas);
        return "kanban"; // nome do arquivo .html
    }

    @PostMapping("/kanban/tarefa")
    public String criarTarefa(@RequestParam String titulo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        Postagem postagem = new Postagem();
        postagem.setTitulo(titulo);
        postagem.setTexto("");
        postagem.setaFazer(true);
        postagem.setUsuario(usuario);

        postagemRepository.save(postagem);

        return "redirect:/kanban";
    }

    @PostMapping("/kanban/mover/{id}")
    @ResponseBody
    public void moverTarefa(@PathVariable Long id, @RequestParam String para, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        postagemRepository.findById(id).ifPresent(p -> {
            if (!p.getUsuario().getId().equals(usuario.getId())) return;

            p.setaFazer(false);
            p.setFazendo(false);
            p.setFeito(false);

            switch (para) {
                case "todo" -> p.setaFazer(true);
                case "in-progress" -> p.setFazendo(true);
                case "done" -> p.setFeito(true);
            }

            postagemRepository.save(p);
        });
    }

    @PostMapping("/kanban/editar/{id}")
    @ResponseBody
    public void editarTarefa(@PathVariable Long id, @RequestParam String titulo) {
        postagemRepository.findById(id).ifPresent(p -> {
            p.setTitulo(titulo);
            postagemRepository.save(p);
        });
    }

    @PostMapping("/kanban/excluir/{id}")
    @ResponseBody
    public void excluirTarefa(@PathVariable Long id) {
        postagemRepository.deleteById(id);
    }
}
