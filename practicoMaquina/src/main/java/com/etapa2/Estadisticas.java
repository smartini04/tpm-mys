package com.etapa2;
import java.util.List;

// Get busy livin' or get busy diyin'
// Los grandes artistas copian, los genios roban.

public class Estadisticas {

    
    private List<Server> servers;
    
    private int tiempoSim;
    
    // Ocio
    private double ocioMax;
    private double ocioMin;
    private double ocioAcum;

    // Transito
    private double transMax;
    private double transMin;
    private double transAcum;
    private int     transCant;
    private double transMed;

    // Espera
    private double esperaMax;
    private double esperaMin;
    private double esperaMed;
    private double esperaAcum;

    // Arribados
    private int cantArribo;

    // Aterrizados
    private int cantAterrizado;

    // Cola o colas?
    private int tamColaMax;
    private int tamColaMin;



    public Estadisticas(List<Server> servers, int tiempoSim) {
        this.tiempoSim = tiempoSim;
        this.servers =  servers;
    }
    
    
    public String calcOcio() { 
        String stringOcio = "Ocios:\n";
        
        double porc;
        for(Server s: this.getServers()){
            porc = ((double) s.getOcioTotal() / (double) this.getTiempoSim()) * 100;
            //porc = ((double)(s.getOcioTotal() / this.tiempoSim))*100 ;
        
            //porc = ((s.getOcioTotal()/this.tiempoSim)*100);
            
            stringOcio += "Server " + s.getID() + 
                          " \n Tiempo total: " + s.getOcioTotal() + 
                          "\n Tiempo minimo: " + s.getOcioMin()+
                          "\n Tiempo maximo: " + s.getOcioMax()+
                          "\n Porcentaje de ocio: "+ porc +"%"+ "\n";
            
        }
        return stringOcio;
    }
    
    public void setEspera(double espera) {
        if(this.getEsperaMin()==0){
            this.esperaMin=espera;}
        if (espera > this.getEsperaMax()) {
            this.esperaMax = espera;
        } else if (espera < this.getEsperaMin()) {
            if(espera!=0){
            this.esperaMin = espera;}
        }

        this.esperaAcum += espera;

    }

    public void setTrans(double trans) {
        if(this.getTransMin()==0){
            this.transMin=trans;
        }
        if (trans > this.getTransMax()) {
            this.transMax = trans;
        } else if (trans < this.getTransMin()) {
            if(trans>0){
            this.transMin = trans;}
        }

        this.transCant++;
        this.transAcum += trans;
    }

    public void setTamCola(int tam) {
        if(this.getTamColaMin()==0){
            this.tamColaMin=tam;
        }
        if (tam > this.getTamColaMax()) {
            this.tamColaMax = tam;
        } else if (tam < this.getTamColaMin()) {
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

    public void durabilidadFinal(){
        for (Server server : this.getServers()) {
            System.out.println("Durabiliad final de la pista " + 
                                server.getID() + 
                                " es " + 
                                server.getDurabiliad());
            
        }
        
    }

    
    public void mostrarEstadisticas(){
        System.out.println("Cantidad de Aeronaves que arribaron: " + this.getCantArribo());
        System.out.println("Cantidad de Aeronaves que aterrizaron: " + this.getCantAterrizado());
        System.out.println("-----------Transito-----------------");
        System.out.println("Tiempo Total: " + this.getTransAcum());
        System.out.println("Tiempo Medio: " + this.getTransAcum()/this.getTransCant());
        System.out.println("Tiempo Maximo: " + this.getTransMax());
        System.out.println("Tiempo Minimo: " + this.getTransMin());
        System.out.println("-----------Espera-----------------");
        this.esperaMed=((double)this.getEsperaAcum() / (double)this.getCantAterrizado());
        System.out.println("Tiempo Total: " + this.getEsperaAcum());
        System.out.println("Tiempo Medio: " + (this.getEsperaMed()));
        System.out.println("Tiempo Maximo: " + this.getEsperaMax());
        System.out.println("Tiempo Minimo: " + this.getEsperaMin());
        System.out.println(this.calcOcio());
        System.out.println("Tamaño cola Maximo: " + this.getTamColaMax());
        System.out.println("Tamaño cola Minimo: " + this.getTamColaMin());
        
        durabilidadFinal();
        
        
    }
    
    /**
     * @return the servers
     */
    public List<Server> getServers() {
        return servers;
    }

    /**
     * @return the tiempoSim
     */
    public int getTiempoSim() {
        return tiempoSim;
    }

    /**
     * @return the ocioMax
     */
    public double getOcioMax() {
        return ocioMax;
    }

    /**
     * @return the ocioMin
     */
    public double getOcioMin() {
        return ocioMin;
    }

    /**
     * @return the ocioAcum
     */
    public double getOcioAcum() {
        return ocioAcum;
    }

    /**
     * @return the transMax
     */
    public double getTransMax() {
        return transMax;
    }

    /**
     * @return the transMin
     */
    public double getTransMin() {
        return transMin;
    }

    /**
     * @return the transAcum
     */
    public double getTransAcum() {
        return transAcum;
    }

    /**
     * @return the transCant
     */
    public int getTransCant() {
        return transCant;
    }

    /**
     * @return the transMed
     */
    public double getTransMed() {
        return transMed;
    }

    /**
     * @return the esperaMax
     */
    public double getEsperaMax() {
        return esperaMax;
    }

    /**
     * @return the esperaMin
     */
    public double getEsperaMin() {
        return esperaMin;
    }

    /**
     * @return the esperaMed
     */
    public double getEsperaMed() {
        return esperaMed;
    }

    /**
     * @return the esperaAcum
     */
    public double getEsperaAcum() {
        return esperaAcum;
    }

    /**
     * @return the cantArribo
     */
    public int getCantArribo() {
        return cantArribo;
    }

    /**
     * @return the cantAterrizado
     */
    public int getCantAterrizado() {
        return cantAterrizado;
    }

    /**
     * @return the tamColaMax
     */
    public int getTamColaMax() {
        return tamColaMax;
    }

    /**
     * @return the tamColaMin
     */
    public int getTamColaMin() {
        return tamColaMin;
    }
    
}
