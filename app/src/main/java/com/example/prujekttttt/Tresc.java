package com.example.prujekttttt;

import com.google.gson.annotations.SerializedName;

public class Tresc {
    @SerializedName("id")
    public String id;
    @SerializedName("imie")
    public String imie;
    @SerializedName("nazwisko")
    public String nazwisko;
    @SerializedName("email")
    public String email;
    @SerializedName("rola")
    public String rola;

    public Tresc(String id, String imie, String nazwisko, String email, String rola) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.rola = rola;
    }
}
