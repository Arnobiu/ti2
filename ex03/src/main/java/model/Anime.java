package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Anime implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String descricao;
    private int eps;
    private LocalDate inicio;
    private LocalDate fim;

    public Anime() {
        id = -1;
        descricao = "Novo Anime";
        eps = 0;
        inicio = LocalDate.now();
        fim = LocalDate.now().plusMonths(6);
    }

    public Anime(int id, String descricao, int eps, LocalDate inicio, LocalDate fim) {
        setId(id);
        setDescricao(descricao);
        setEps(eps);
        setInicio(inicio);
        setFim(fim);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEps() {
        return eps;
    }

    public void setEps(int eps) {
        this.eps = eps;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    @Override
    public String toString() {
        return "Anime [id=" + id + ", descricao=" + descricao + ", eps=" + eps + ", inicio=" + inicio + ", fim=" + fim
                + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Anime anime = (Anime) obj;
        return id == anime.id;
    }
}
