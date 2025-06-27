package com.tpm;
import java.util.List;

public class Bootstraping {

    private int tsimulacion;
    private Randomizer randomizer;
    private FEL fel;
    private List<Server> server;
    private List<Server> serverDisable;
    private boolean stop;
    private Estadisticas estadisticas;

    public Bootstraping(int tsimulacion, Randomizer randomizer, List<Server> server, Distribucion arribo, Distribucion servicio, List<Server> serverDisable) {
        this.tsimulacion = tsimulacion;
        this.fel = new FEL(new Ordenador());
        this.randomizer = randomizer;
        this.server = server;
        this.serverDisable = serverDisable;
        this.stop = false;
        this.estadisticas = new Estadisticas(server, tsimulacion);
        this.fel.insert(new FinDeSimulacion(tsimulacion, 5, this, this.estadisticas));
        this.fel.insert(new Arribo(0, new Entidad(1), arribo, servicio, 10));
    }

    public void setStop(boolean flag) {
        this.stop = flag;
    }

    public Estadisticas getEstadisticas(){
        return this.estadisticas;
    } 
    
    public void run() {

        Evento event;
        while (!(this.stop)) {
            event = this.fel.inminente();
            event.planificar(fel, randomizer, server, this.estadisticas,serverDisable);
        }

        this.estadisticas.recolectaOcio();
    }
}
