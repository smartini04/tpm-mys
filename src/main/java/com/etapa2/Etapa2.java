/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.etapa2;

import escenario.DistribucionExp;
import escenario.DistribucionUniforme;
import escenario.ObtenerRandom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lourdes
 */
public class Etapa2 {
    public static void main(String[] args) {
        List<Server> servers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            servers.add(new Server(new Cola(), i + 1, 3000));
            servers.get(i).setOcupado(false);
        }
        new Bootstraping(100, new ObtenerRandom(), servers, new DistribucionExp(0), new DistribucionUniforme(10, 25)).run();

    }
}
