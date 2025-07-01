package com.tpm;
import java.util.List;

public class FinDeSimulacion extends Evento {
    private Bootstraping sim;

    public FinDeSimulacion(int clock, int orden, Bootstraping boots,Estadisticas estadistica) {
        super(clock, null, null, null, orden);
        this.sim = boots;
    }

    @Override
    public void planificar(FEL fel, Randomizer randomizer, List<Server> servers, Estadisticas estadisticas, List<Server> serversDisable) {
        this.sim.setStop(true);
    }
}
