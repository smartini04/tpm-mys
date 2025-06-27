package com.tpm;

import escenario.DistribucionExp;
import escenario.DistribucionUniforme;
import escenario.ObtenerRandom;

import java.util.ArrayList;
import java.util.List;

// El tipo puede cambiar de todo, pero no puede cambiar de pasion.
public class Main_tpm {

    public static void main(String[] args) {

        int cantServer = 5, cantEjecuciones = 50;
        Resultados resultados = new Resultados();

        for (int i = 0; i < cantEjecuciones; i++) {

            List<Server> servers = new ArrayList<>();
            List<Server> serversDisable = new ArrayList<>();

            for (int j = 0; j < cantServer; j++) {
                servers.add(new Server(new Cola(), j + 1, 3000));
                servers.get(j).setOcupado(false);
            }

            Bootstraping bootstraping = new Bootstraping(40000, new ObtenerRandom(), servers,
                    new DistribucionExp(0), new DistribucionUniforme(10, 25), serversDisable);
            bootstraping.run();
            resultados.setDatosEjecucion(bootstraping.getEstadisticas());
        }

        resultados.mostrarResultados();
    }
}
