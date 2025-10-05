package com.zeta_servlet.model;

public class Flash_card {
    private int id;
    private String flash_card;
    private int id_aula;

    public Flash_card(int id, String flash_card, int id_aula) {
        this.id = id;
        this.flash_card = flash_card;
        this.id_aula = id_aula;
    }

    public int getId() {
        return id;
    }

    public String getFlash_card() {
        return flash_card;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }


    @Override
    public String toString() {
        return "Flash_card{" +
                "id=" + id +
                ", flash_card='" + flash_card + '\'' +
                ", id_aula=" + id_aula +
                '}';
    }
}
