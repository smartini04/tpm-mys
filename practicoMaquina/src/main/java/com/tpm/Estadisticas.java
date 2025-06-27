package com.tpm;

import java.util.ArrayList;
import java.util.List;

// Get busy livin' or get busy diyin'
// Los grandes artistas copian, los genios roban.
public class  Estadisticas {

    private List<Server> servers;

    private int tiempoSim;

    // Ocio
    private List<Double> ocioMax;
    private List<Double> ocioMin;
    private List<Double> ocioAcum;

    // Transito
    private double transMax;
    private double transMin;
    private double transAcum;
    private int    transCant;
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

    private List<Double> durabilidadFinal;

    // Tamanos de cola max y min de cada servidor.
    private List<Integer> tamColaMax;
    private List<Integer> tamColaMin;

    public Estadisticas(List<Server> servers, int tiempoSim) {
        this.tiempoSim  = tiempoSim;
        this.servers    = servers;
        this.tamColaMax = new ArrayList<>();
        this.tamColaMin = new ArrayList<>();
        this.ocioMin    = new ArrayList<>();
        this.ocioAcum   = new ArrayList<>();
        this.ocioMax    = new ArrayList<>();
        this.durabilidadFinal = new ArrayList<>();

        // Agregamos elementos para que el size de las listas
        // sea igual a la cantidad de servidores
        for (int i = 0 ; i < servers.size();i++){
            this.tamColaMax.add(0);
            this.tamColaMin.add(0);
            this.ocioMax.add(0.0);
            this.ocioMin.add(0.0);
            this.ocioAcum.add(0.0);
            this.durabilidadFinal.add(0.0);
        }
    }

    public void setEspera(double espera) {
        if (this.getEsperaMin() == 0) {
            this.esperaMin = espera;
        }
        if (espera > this.getEsperaMax()) {
            this.esperaMax = espera;
        } else if (espera < this.getEsperaMin()) {
            if (espera != 0) {
                this.esperaMin = espera;
            }
        }

        this.esperaAcum += espera;

    }

    public void setTrans(double trans) {
        if (this.getTransMin() == 0) {
            this.transMin = trans;
        }
        if (trans > this.getTransMax()) {
            this.transMax = trans;
        } else if (trans < this.getTransMin()) {
            if (trans > 0) {
                this.transMin = trans;
            }
        }

        this.transCant++;
        this.transAcum += trans;
    }

    public void setTamCola(int tam, int id) {


        if (this.getTamColaMin().get(id - 1 ) == 0) {
            this.tamColaMin.set(id - 1,tam);
        }
        if (tam > this.getTamColaMax().get(id - 1)) {
            this.tamColaMax.set(id - 1,tam);
        } else if (tam < this.getTamColaMin().get(id - 1)) {
            if (tam > 0) {
                this.tamColaMin.set(id - 1,tam) ;
            }
        }
    }

    public void sumarArribo() {
        this.cantArribo++;
    }

    public void sumarAterrizado() {
        this.cantAterrizado++;
    }

    public void recolectaEstadoFinal(){
        for(Server s : servers){
            this.ocioMax.set(s.getID() - 1,s.getOcioMax());
            this.ocioMin.set(s.getID() - 1,s.getOcioMin());
            this.ocioAcum.set(s.getID()- 1,s.getOcioTotal());
            this.durabilidadFinal.set(s.getID()- 1,s.getDurabilidad());

        }
    }

    public void mostrarEstadisticas() {
        System.out.println("Cantidad de Aeronaves que arribaron: " + this.getCantArribo());
        System.out.println("Cantidad de Aeronaves que aterrizaron: " + this.getCantAterrizado());
        System.out.println("-----------Transito-----------------");
        System.out.println("Tiempo Total: " + this.getTransAcum());
        System.out.println("Tiempo Medio: " + this.getTransAcum() / this.getTransCant());
        System.out.println("Tiempo Maximo: " + this.getTransMax());
        System.out.println("Tiempo Minimo: " + this.getTransMin());
        System.out.println("-----------Espera-----------------");
        this.esperaMed = ((double) this.getEsperaAcum() / (double) this.getCantAterrizado());
        System.out.println("Tiempo Total: " + this.getEsperaAcum());
        System.out.println("Tiempo Medio: " + (this.getEsperaMed()));
        System.out.println("Tiempo Maximo: " + this.getEsperaMax());

        //Genera un string con los ocios individuales de cada servidor.
        String stringOcio = "Ocios:\n";
        double porc;
        for (Server s : this.getServers()) {
            porc = ((double) s.getOcioTotal() / (double) this.getTiempoSim()) * 100;
            stringOcio += "Server " + s.getID()
                    + " \n Tiempo total: " + s.getOcioTotal()
                    + "\n Tiempo minimo: " + s.getOcioMin()
                    + "\n Tiempo maximo: " + s.getOcioMax()
                    + "\n Porcentaje de ocio: " + porc + "%" + "\n";

        }
        System.out.print(stringOcio);

        System.out.println("Tiempo Minimo: " + this.getEsperaMin());
        System.out.println("Tamaño cola Maximo: " + this.getTamColaMax());
        System.out.println("Tamaño cola Minimo: " + this.getTamColaMin());

        //Durabilidad final de cada una de las pistas
        for (Server server : this.getServers()) {
            System.out.println("Durabiliad final de la pista "
                    + server.getID()
                    + " es "
                    + server.getDurabilidad());

        }

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
    public List<Double> getOcioMax() {
        return ocioMax;
    }

    /**
     * @return the ocioMin
     */
    public List<Double> getOcioMin() {
        return ocioMin;
    }

    /**
     * @return the ocioAcum
     */
    public List<Double> getOcioAcum() {
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
    public List<Integer> getTamColaMax() {
        return tamColaMax;
    }

    /**
     * @return the tamColaMin
     */
    public List<Integer> getTamColaMin() { return tamColaMin;}

    public List<Double> getDurabilidadFinal(){
        return this.durabilidadFinal;
    }

}
