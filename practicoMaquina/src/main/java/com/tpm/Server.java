package com.tpm;
public class Server {
    private boolean ocupado;

    //Cola de entidades.
    private Cola cola;
    private Entidad entity;
    private double inicioOcio;
    private double ociomin;
    private double ociomax;
    private double ocioTotal;
    private double durabilidad;
    private int id;
    
    
    public Server(Cola cola, int id, double durabilidad) {
        this.durabilidad=durabilidad;
        this.cola = cola;
        this.id=id;
    }


    public void setCola(Cola cola) {
        this.cola = cola;
    }

    public void setEntity(Entidad entity) {
        this.entity = entity;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean estaOcupado() {
        return this.ocupado;
    }

    public Cola getCola() {
        return cola;
    }
    public int getID() {
        return id;
    }

    public Entidad getEntity() {
        return entity;
    }

    public double getInicioOcio() {
        return this.inicioOcio;
    }

    public void setInicioOcio(double inicioOcio) {
        this.inicioOcio = inicioOcio;
    }
    
    public void setOcioTotal(double ocio){
        if(this.ociomin==0){
            this.ociomin=ocio;
        }
        if(this.ociomin>ocio){
            if(ocio>0){
            this.ociomin=ocio;}
        }
        else if(this.ociomax<ocio){
            this.ociomax=ocio;
        }
        this.ocioTotal += ocio;
    }
    
    public double getOcioTotal(){
        return this.ocioTotal;
    }
    
    public double getOcioMin(){
        return this.ociomin;
    }
    
    public double getOcioMax(){
        return this.ociomax;
    }

    public double getDurabiliad(){
        return this.durabilidad;
    }

    public void decrDura(double decremento){
        this.durabilidad -= decremento;
    }

    public void aumDura(double incremento){
        this.durabilidad += incremento;
    }
    
}
