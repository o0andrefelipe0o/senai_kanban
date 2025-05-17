package br.com.kanban.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.kanban.kanban.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca um usuário pelo nome de usuário (campo `usuario`)
    Usuario findByUsuario(String usuario);
}
