package com.etapa2;

import escenario.Seleccionador;
import java.util.List;

public class Salida extends Evento {

    public Salida(double clock, Entidad entidad, Distribucion arribo, Distribucion servicio, int orden) {
        super(clock, entidad, arribo,servicio, orden);
    }

    @Override
    public void planificar(FEL fel, Randomizer randomizer, List<Server> servers, Estadisticas estadisticas) {
        Seleccionador selec = new Seleccionador();
        Server servactual = selec.serverActual(servers);

        System.out.println(servactual.getCola().estaVacio());

       if(!servactual.getCola().estaVacio()){
            
            // Si la cola no esta vacia.
            Entidad entidadSalida = servactual.getCola().getPrimero();
            estadisticas.setTamCola(servactual.getCola().largo());
            estadisticas.setEspera(this.getClock() - entidadSalida.getClockDeArribo());
            
            entidadSalida.setPista(servactual);
            
            //int tempServicio = this.getDist().tiempoDeServicio(randomizer.next());
            double tempServicio = this.getServicio().getTiempo(randomizer.next());
            Salida eventoSalida = new Salida((this.getClock() + tempServicio), entidadSalida, this.getArribo(),this.getServicio(),0);
            fel.insert(eventoSalida);
            
        }else{
            
            // Marcamos desocupado al server.
            servactual.setOcupado(false);
            servactual.setInicioOcio(this.getClock());
            System.out.println("Marcamos el servidor libre.");
        
        }

        estadisticas.sumarAterrizado();
        estadisticas.setTrans(this.getClock() - this.getEntidad().getClockDeArribo());
    }
    
}
