package com.example.taskmanager.pojo;

public class Tarefas {

    String titulo;
    String descricao;
    Integer id;

    public Tarefas() {
    }

    public Tarefas(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
