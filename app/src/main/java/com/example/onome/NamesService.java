package com.example.onome;

import com.example.onome.models.Names;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface NamesService {

    public static final String BASE_URL = "https://servicodados.ibge.gov.br/api/v2/censos/";

    @GET("nomes/{nome}")
    @Headers({"Accept: application/json"})
    Call<List<Names>> listNames(@Path("nome") String nome);

    //Bloom

}
