package com.zeta_servlet.model;


public class Alternativa {
    int id;
    String alternativa;
    int id_atividade;

    public Alternativa(int id, String alternativa, int id_atividade) {
        this.id = id;
        this.alternativa = alternativa;
        this.id_atividade = id_atividade;
    }

    public int getId() {
        return id;
    }

    public String getAlternativa() {
        return alternativa;
    }

    public int getId_atividade() {
        return id_atividade;
    }

    public void setId_atividade(int id_atividade) {
        this.id_atividade = id_atividade;
    }

    public String toString() {
        return "Alternativa{" +
                "id=" + id +
                ", alternativa='" + alternativa + '\'' +
                ", id_atividade=" + id_atividade;
    }
}
