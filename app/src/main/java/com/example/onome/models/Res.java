package com.example.onome.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Res {

    @SerializedName("periodo")
    private String periodo;

    @SerializedName("frequencia")
    private int frequencia;

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }
}
