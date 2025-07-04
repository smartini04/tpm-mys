package com.tpm;

import escenario.DistribucionExp;
import escenario.DistribucionNormal;
import escenario.Seleccionador;
import java.util.Iterator;
import java.util.List;

public class Arribo extends Evento {

    public Arribo(double clock, Entidad entidad, Distribucion arribo, Distribucion servicio, int orden) {
        super(clock, entidad, arribo, servicio, orden);
    }

    @Override
    public void planificar(FEL fel, Randomizer randomizer, List<Server> servers, Estadisticas estadisticas, List<Server> serversDisable) {
        Seleccionador selec = new Seleccionador();

        // Antes que de seleccionar el server revisa si hay algun server que se habilite
        DistribucionNormal probIncremento = new DistribucionNormal(18, 3);
        double desgaste = probIncremento.getTiempo(randomizer.next());

        Iterator<Server> iterator = serversDisable.iterator();
        while (iterator.hasNext()) {
            Server server = iterator.next();
            server.aumDura(desgaste);
            if (server.getDurabilidad() >= 2400) {
                servers.add(server);
                iterator.remove(); // elimina de forma segura el elemento actual
            }
        }

        Server servactual = selec.serverActual(servers);

        if (!servactual.estaOcupado()) {

            //El server esta desocupado.
            servactual.setOcupado(true);
            servactual.setEntity(this.getEntidad());
            servactual.setOcioTotal(this.getClock() - servactual.getInicioOcio());
            this.getEntidad().setPista(servactual);
            double tempServicio = this.getServicio().getTiempo(randomizer.next());
            fel.insert(new Salida(this.getClock() + tempServicio, this.getEntidad(), this.getArribo(), this.getServicio(), 0));
        } else {

            //Si no, agregamos una entidad a la cola.
            servactual.getCola().agregar(this.getEntidad());
            estadisticas.setTamCola(servactual.getCola().largo(),servactual.getID());
            
        }

        this.getEntidad().setClockDeArribo(this.getClock());
        
        //Siempre insertamos en la FEL el evento planificado.
        if (this.getArribo() instanceof DistribucionExp distribucionExp) {
            distribucionExp.setClock(this.getClock());
        }
        fel.insert(new Arribo(this.getClock() + this.getArribo().getTiempo(randomizer.next()), new Entidad(this.getEntidad().getId() + 1), this.getArribo(), this.getServicio(), 10));
        estadisticas.sumarArribo();

    }
}
