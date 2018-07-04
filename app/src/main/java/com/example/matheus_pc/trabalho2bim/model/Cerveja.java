package com.example.matheus_pc.trabalho2bim.model;

/**
 * Created by Matheus-PC on 03/07/2018.
 */

public class Cerveja {


    private long id;
    private String nome;
    private int tipo;
    private String local;
    private float preco;
    private String localizacao;

    public Cerveja(long id, String nome, int tipo, String local, float preco, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.local = local;
        this.preco = preco;
        this.localizacao = localizacao;
    }
    public Cerveja(String nome, int tipo, String local, float preco, String localizacao) {
        this.nome = nome;
        this.tipo = tipo;
        this.local = local;
        this.preco = preco;
        this.localizacao = localizacao;
    }


    public static String getNomeTipo(int tipo){
        if (tipo == 1){
            return "Latinha";
        } else if (tipo == 2){
            return "Lata";
        } else if (tipo == 3){
            return "Latão";
        } else if (tipo == 4){
            return "Long Neck";
        } else if (tipo == 5){
            return "Garrafa";
        } else if (tipo == 6){
            return "Litrao";
        } else if (tipo == 7){
            return "Barril";
        }
        return "" ;
    }
    public String getNome() {
        return nome;
    }

    public int getTipo() {
        return tipo;
    }

    public String getLocal() {
        return local;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getPreco() {
        return preco;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
