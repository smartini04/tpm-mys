package com.etapa2;

import escenario.DistribucionExp;
import escenario.DistribucionUniforme;
import escenario.ObtenerRandom;

import java.util.ArrayList;
import java.util.List;

// El tipo puede cambiar de todo, pero no puede cambiar de pasion.

public class Etapa2 {

    public static void main(String[] args) {
        List<Server> servers = new ArrayList<>();

        int cantServer = 5, cantReplicaciones = 10;

        Resultados resultados = new Resultados();

        for (int i = 0; i < cantServer; i++) {
            servers.add(new Server(new Cola(), i + 1, 3000));
            servers.get(i).setOcupado(false);
        }

        for (int i = 0; i < cantReplicaciones; i++) {
            Bootstraping bootstraping = new Bootstraping(40000, new ObtenerRandom(), servers,
                    new DistribucionExp(0), new DistribucionUniforme(10, 25));
            bootstraping.run();

            resultados.setDatosEjecucion(bootstraping.getEstadisticas());
        }


        resultados.mostrarResultados();
    }
}
