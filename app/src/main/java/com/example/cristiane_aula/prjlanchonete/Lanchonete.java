  package com.example.cristiane_aula.prjlanchonete;

/**
 * Created by Cristiane-Aula on 20/02/2017.
 */
import java.io.Serializable;

public class Lanchonete implements Serializable{
    private static final long serialVersionUID = 1L;
    private String lanche, bebida;
    private String catchup, mostarda, picles, alface, gelo;

    public String getLanche() {
        return lanche;
    }

    public void setLanche(String lanche) {
        this.lanche = lanche;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getCatchup() {
        return catchup;
    }

    public void setCatchup(String catchup) {
        this.catchup = catchup;
    }

    public String getMostarda() {
        return mostarda;
    }

    public void setMostarda(String mostarda) {
        this.mostarda = mostarda;
    }

    public String getPicles() {
        return picles;
    }

    public void setPicles(String picles) {
        this.picles = picles;
    }

    public String getAlface() {
        return alface;
    }

    public void setAlface(String alface) {
        this.alface = alface;
    }

    public String getGelo() {
        return gelo;
    }

    public void setGelo(String gelo) {
        this.gelo = gelo;
    }

    public Lanchonete(String lanche, String bebida, String catchup, String mostarda, String picles, String alface, String gelo) {
        // TODO Auto-generated constructor stub
        this.alface=alface;
        this.bebida=bebida;
        this.catchup=catchup;
        this.gelo=gelo;
        this.lanche=lanche;
        this.mostarda=mostarda;
        this.picles=picles;
    }

    public String simNaoAlface(){
        return alface.equals("S") ? "sim": "não";
    }

    public String simNaoMostarda(){
        return mostarda.equals("S") ? "sim": "não";
    }

    public String simNaoCatchup(){
        return catchup.equals("S") ? "sim": "não";
    }

    public String simNaoPicles(){
        return picles.equals("S") ? "sim": "não";
    }

    public String simNaoGelo(){
        return gelo.equals("S") ? "gelada": "sem gelo";
    }


    public String retornaDados()
    {
        return "Lanche: "+lanche+"\nAlface: "+simNaoAlface()+"\nPicles: "+simNaoPicles()+
                "\nCatchup: "+simNaoCatchup()+"\nMostarda: "+simNaoMostarda()+
                "\nBebida: "+getBebida()+" "+simNaoGelo();
    }

}
