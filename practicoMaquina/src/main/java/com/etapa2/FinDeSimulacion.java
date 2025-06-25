/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.etapa2;

import java.util.List;

/**
 *
 * @author Lourdes
 */
public class FinDeSimulacion extends Evento {
    private Bootstraping sim;

    public FinDeSimulacion(int clock, int orden, Bootstraping boots,Estadisticas estadistica) {
        super(clock, null, null, null, orden);
        this.sim = boots;
    }

    @Override
    public void planificar(FEL fel, Randomizer randomizer, List<Server> servers, Estadisticas estadisticas) {
        this.sim.setStop(true);
    }
}
