package com.etapa2;
import java.util.List;

// Get busy livin' or get busy diyin'
// Los grandes artistas copian, los genios roban.

public class Estadisticas {
    
    private List<Server> servers;
    
    private int tiempoSim;
    
    private int ocioMax;
    private int ocioMin;
    private int ocioAcum;

    private double transMax;
    private double transMin;
    private double transAcum;
    private int transCant;
    private double transMed;

    private double esperaMax;
    private double esperaMin;
    private double esperaMed;
    private double esperaAcum;

    private int cantArribo;
    private int cantAterrizado;

    private int tamColaMax;
    private int tamColaMin;

    public Estadisticas(List<Server> servers, int tiempoSim) {
        this.tiempoSim = tiempoSim;
        this.servers =  servers;           
    }
    
    
    public String calcOcio() { 
        String stringOcio = "Ocios:\n\t";
        int i = 0;
        double porc;
        for(Server s: this.servers){
            porc = ((double) s.getOcioTotal() / (double) this.tiempoSim) * 100;
            //porc = ((double)(s.getOcioTotal() / this.tiempoSim))*100 ;
           
            //porc = ((s.getOcioTotal()/this.tiempoSim)*100);
            
            stringOcio += "Server " + i + " \n Tiempo total: " + s.getOcioTotal() + "\n Tiempo minimo: "+s.getOcioMin()+"\n Tiempo maximo: "+s.getOcioMax()+"\n Porcentaje de ocio: "+porc+"%";
        }
        return stringOcio;
    }
    
    public void setEspera(double espera) {
        if(this.esperaMin==0){
            this.esperaMin=espera;}
        if (espera > this.esperaMax) {
            this.esperaMax = espera;
        } else if (espera < this.esperaMin) {
            if(espera!=0){
            this.esperaMin = espera;}
        }

        this.esperaAcum += espera;

    }

    public void setTrans(double trans) {
        if(this.transMin==0){
            this.transMin=trans;
        }
        if (trans > this.transMax) {
            this.transMax = trans;
        } else if (trans < this.transMin) {
            if(trans>0){
            this.transMin = trans;}
        }

        this.transCant++;
        this.transAcum += trans;
        //this.transMed=this.transAcum/this.transCant;
    }

    public void setTamCola(int tam) {
        if(this.tamColaMin==0){
            this.tamColaMin=tam;
        }
        if (tam > this.tamColaMax) {
            this.tamColaMax = tam;
        } else if (tam < this.tamColaMin) {
            if(tam>0){
            this.tamColaMin = tam;}
        }
    }

    public void sumarArribo() {
        this.cantArribo++;
    }

    public void sumarAterrizado() {
        this.cantAterrizado++;
    }
    
    public void mostrarEstadisticas(){
        System.out.println("Cantidad de Aeronaves que arribaron: " + this.cantArribo);
        System.out.println("Cantidad de Aeronaves que aterrizaron: " + this.cantAterrizado);
        System.out.println("-----------Transito-----------------");
        System.out.println("Tiempo Total: " + this.transAcum);
        System.out.println("Tiempo Medio: " + this.transAcum/this.transCant);
        System.out.println("Tiempo Maximo: " + this.transMax);
        System.out.println("Tiempo Minimo: " + this.transMin);
        System.out.println("-----------Espera-----------------");
        this.esperaMed=((double)this.esperaAcum / (double)this.cantAterrizado);
        System.out.println("Tiempo Total: " + this.esperaAcum);
        System.out.println("Tiempo Medio: " + (this.esperaMed));
        System.out.println("Tiempo Maximo: " + this.esperaMax);
        System.out.println("Tiempo Minimo: " + this.esperaMin);
        System.out.println(this.calcOcio());
        //System.out.println("Porcentaje de Ocio "  + (this.ocioAcum / this.tiempoSim)* 100 + "%");
        
        System.out.println("Tamaño cola Maximo: " + this.tamColaMax);
        System.out.println("Tamaño cola Minimo: " + this.tamColaMin);
        
        
    }
}
