package br.com.kanban.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.kanban.kanban.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Pode adicionar métodos de busca personalizados aqui no futuro
}
