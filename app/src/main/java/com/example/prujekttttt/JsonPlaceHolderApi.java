package com.example.prujekttttt;

import retrofit2.Call;

import java.util.List;

import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("db.json")
    public Call<List<Tresc>> getTresci();
}
