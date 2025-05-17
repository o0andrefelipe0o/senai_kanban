package br.com.kanban.kanban.repository;

import br.com.kanban.kanban.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    List<Postagem> findByUsuarioId(Long usuarioId);
}
