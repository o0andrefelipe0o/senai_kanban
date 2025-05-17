package br.com.kanban.kanban.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String texto;
    private boolean aFazer;
    private boolean fazendo;
    private boolean feito;

    private LocalDateTime data = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isaFazer() {
        return aFazer;
    }

    public void setaFazer(boolean aFazer) {
        this.aFazer = aFazer;
    }

    public boolean isFazendo() {
        return fazendo;
    }

    public void setFazendo(boolean fazendo) {
        this.fazendo = fazendo;
    }

    public boolean isFeito() {
        return feito;
    }

    public void setFeito(boolean feito) {
        this.feito = feito;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
