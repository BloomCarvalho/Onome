package com.example.onome.models;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Names {

    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("localidade")
    @Expose
    private String localidade;

    public List<Res> getRes() {
        return res;
    }

    public void setRes(List<Res> res) {
        this.res = res;
    }

    @SerializedName("res")
    private List<Res> res;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}
