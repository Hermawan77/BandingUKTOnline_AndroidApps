package com.example.tugasbesar;

public class Tanggal {
    String idtanggal;
    String tanggal1;
    String tanggal2;

    public Tanggal(){

    }

    public Tanggal(String idtanggal, String tanggal1, String tanggal2) {
        this.idtanggal = idtanggal;
        this.tanggal1 = tanggal1;
        this.tanggal2 = tanggal2;
    }
    public String getIdtanggal(){
        return idtanggal;
    }
    public String getTanggal1(){
        return tanggal1;
    }
    public String getTanggal2(){
        return tanggal2;
    }
}
