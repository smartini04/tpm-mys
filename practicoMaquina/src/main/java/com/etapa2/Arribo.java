/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.etapa2;

import escenario.DistribucionExp;
import escenario.DistribucionNormal;
import escenario.Seleccionador;
import java.util.List;

/**
 *
 * @author Lourdes
 */
public class Arribo extends Evento {

    public Arribo(double clock, Entidad entidad, Distribucion arribo, Distribucion servicio, int orden) {
        super(clock, entidad, arribo, servicio, orden);
    }

    @Override
    public void planificar(FEL fel, Randomizer randomizer, List<Server> servers, Estadisticas estadisticas, List<Server> serversDisable) {
        Seleccionador selec = new Seleccionador();
        // Antes que de seleccionar el server revisa si hay algun server que se habilite
        DistribucionNormal probIncremento = new DistribucionNormal(5, 1);
        double desgaste = probIncremento.getTiempo(randomizer.next());
        
        for (Server server : serversDisable) {
            server.aumDura(desgaste);
            if (server.getDurabiliad() >= 2400) {
                serversDisable.remove(server);
                servers.add(server);
            }
        }




        Server servactual = selec.serverActual(servers);

        if (!servactual.estaOcupado()) {
            //el server esta desocupado
            servactual.setOcupado(true);
            servactual.setEntity(this.getEntidad());
            servactual.setOcioTotal(this.getClock() - servactual.getInicioOcio());
            this.getEntidad().setPista(servactual);
            double tempServicio = this.getServicio().getTiempo(randomizer.next());
            fel.insert(new Salida(this.getClock() + tempServicio, this.getEntidad(), this.getArribo(), this.getServicio(), 0));
        } else {
            //Si no, agregamos una entidad a la cola.
            servactual.getCola().agregar(this.getEntidad());
            estadisticas.setTamCola(servactual.getCola().largo());
            //estadisticas.setInicioEsp(this.getClock());
        }
        this.getEntidad().setClockDeArribo(this.getClock());
        //System.out.println("Entidad arribo "+this.getEntidad());

        //Siempre insertamos en la FEL el evento planificado.
        if (this.getArribo() instanceof DistribucionExp distribucionExp) {
            distribucionExp.setClock(this.getClock());
        }
        fel.insert(new Arribo(this.getClock() + this.getArribo().getTiempo(randomizer.next()), new Entidad(this.getEntidad().getId() + 1), this.getArribo(), this.getServicio(), 10));
        estadisticas.sumarArribo();

    }
}
