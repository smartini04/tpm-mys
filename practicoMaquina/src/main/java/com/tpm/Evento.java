package com.tpm;
import java.util.List;

public abstract class Evento {
    double clock;
    private Entidad entidad;
    private Distribucion arribo;
    private Distribucion servicio;
    private int orden; //ordena por si es de salida o de arribo
    
    public Evento (double clock, Entidad entidad,Distribucion arribo,Distribucion servicio,int orden){
        this.clock=clock;
        this.arribo=arribo;
        this.servicio=servicio;
        this.entidad=entidad;
        this.orden=orden;
    }

    /**
     * @return the clock
     */
    public double getClock() {
        return clock;
    }

    /**
     * @param clock the clock to set
     */
    public void setClock(int clock) {
        this.setClock(clock);
    }

    /**
     * @return the entidad
     */
    public Entidad getEntidad() {
        return entidad;
    }

    /**
     * @param entidad the entidad to set
     */
    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    /**
     * @return the dist
     */
   

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }
    
    public abstract void planificar(FEL fel, Randomizer randomizer, List<Server> servers, Estadisticas estadisticas, List<Server> serversDisable);

    /**
     * @param clock the clock to set
     */
    public void setClock(double clock) {
        this.clock = clock;
    }

    /**
     * @return the arribo
     */
    public Distribucion getArribo() {
        return arribo;
    }

    /**
     * @param arribo the arribo to set
     */
    public void setArribo(Distribucion arribo) {
        this.arribo = arribo;
    }

    /**
     * @return the servicio
     */
    public Distribucion getServicio() {
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(Distribucion servicio) {
        this.servicio = servicio;
    }

    
   
}
