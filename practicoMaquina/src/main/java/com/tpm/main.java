package com.tpm;

import java.util.ArrayList;
import java.util.List;

import escenario.DistribucionExp;
import escenario.DistribucionUniforme;
import escenario.ObtenerRandom;

// El tipo puede cambiar de todo, pero no puede cambiar de pasion.

public class main {

    public static void Main (String[] args) {
        List<Server> servers = new ArrayList<>();
        List<Server> serversDisable = new ArrayList<>();

        int cantServer = 5;
        int numdeejecuciones=50;
        Resultados resultados = new Resultados();

        for (int i = 0; i < cantServer; i++) {
            servers.add(new Server(new Cola(), i + 1, 3000));
            servers.get(i).setOcupado(false);
        }
        
        for(int i=0; i<numdeejecuciones;i++){
            Bootstraping bootstraping = new Bootstraping(40000, new ObtenerRandom(), servers,
                                                        new DistribucionExp(0), new DistribucionUniforme(10, 25), serversDisable);
            bootstraping.run();
            resultados.setDatosEjecucion(bootstraping.getEstadisticas());
        }
        
        
        resultados.mostrarResultados();
    }
}
