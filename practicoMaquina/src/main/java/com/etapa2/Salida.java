package com.etapa2;

import escenario.DistribucionNormal;
import escenario.Seleccionador;
import java.util.List;

public class Salida extends Evento {

    public Salida(double clock, Entidad entidad, Distribucion arribo, Distribucion servicio, int orden) {
        super(clock, entidad, arribo, servicio, orden);
    }

    @Override
    public void planificar(FEL fel, Randomizer randomizer, List<Server> servers, Estadisticas estadisticas, List<Server> serversDisable) {
        //Seleccionador selec=new Seleccionador();
        //Server servactual= selec.serverActual(servers);

        Server servactual = super.getEntidad().getPista();

        if (!servactual.getCola().estaVacio()) {

            // Si la cola no esta vacia.
            Entidad entidadSalida = servactual.getCola().getPrimero();
            estadisticas.setTamCola(servactual.getCola().largo());
            estadisticas.setEspera(this.getClock() - entidadSalida.getClockDeArribo());

            entidadSalida.setPista(servactual);

            double tempServicio = this.getServicio().getTiempo(randomizer.next());
            Salida eventoSalida = new Salida((this.getClock() + tempServicio), entidadSalida, this.getArribo(), this.getServicio(), 0);
            fel.insert(eventoSalida);

        } else {

            // Marcamos desocupado al server.
            servactual.setOcupado(false);
            DistribucionNormal probDesgaste = new DistribucionNormal(5, 1);
            double desgaste = probDesgaste.getTiempo(randomizer.next());
            servactual.decrDura(desgaste);
            servactual.setInicioOcio(this.getClock());
            
            if(servactual.getDurabiliad() <= 300){
                serversDisable.add(servactual);
                servers.remove(servactual);
            }

            System.out.println("Marcamos el servidor libre.");

        }
        estadisticas.sumarAterrizado();
        estadisticas.setTrans(this.getClock() - this.getEntidad().getClockDeArribo());
    }

}
