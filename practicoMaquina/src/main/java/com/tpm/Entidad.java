package com.tpm;
public class Entidad {
    private int id;
    private Server pista;
    private double clockDeArribo;
    
    public Entidad(int id){
        this.id=id;
    }

    public int getId(){
        return id;
    }

    public void setPista(Server pista) {
        this.pista = pista;
    }

    public Server getPista() {
        return pista;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setClockDeArribo(double tiempo){
     this.clockDeArribo=tiempo;
    }
    
    public double getClockDeArribo(){
        return this.clockDeArribo;
    }
    
}
