package com.tpm;

import java.util.List;
import java.util.ArrayList;

public class Resultados {

    /**
     *  Constantes relevantes.
     */
    private final int tiempoSim;
    private final int cantServers;
    private final int cantReplicaciones;

    /**
     * Estadísticas de entidades.
     */

    // Transito
    private List<Double> transMax;
    private List<Double> transMin;
    private List<Double> transMed;


    // Espera
    private List<Double> esperaMax;
    private List<Double> esperaMin;
    private List<Double> esperaMed;

    /**
     * Estadísticas del sistema.
     */

    // Arribados
    private List<Integer> cantArribo;

    // Aterrizados
    private List<Integer> cantAterrizado;

    /**
     * Estadísticas relacionadas a cada servidor.
     */

    // Durabilidad
    private List<List<Double>> durabilidadFinal;

    // Ocio
    private List<List<Double>> ocioMax;
    private List<List<Double>> ocioMin;
    private List<List<Double>> ocioTotal;

    // ¿Cola o colas?
    private List<List<Integer>> tamColaMax;
    private List<List<Integer>> tamColaMin;

    public Resultados(int cantEjecuciones, int cantServers, int tiempoSim) {
        this.ocioMax = new ArrayList<>();
        this.ocioMin = new ArrayList<>();
        this.ocioTotal = new ArrayList<>();

        this.esperaMax = new ArrayList<>();
        this.esperaMin = new ArrayList<>();
        this.esperaMed = new ArrayList<>();

        this.transMax = new ArrayList<>();
        this.transMin = new ArrayList<>();
        this.transMed = new ArrayList<>();

        this.cantAterrizado = new ArrayList<>();
        this.cantArribo = new ArrayList<>();

        this.tamColaMax = new ArrayList<>();
        this.tamColaMin = new ArrayList<>();

        this.durabilidadFinal = new ArrayList<>();
        this.cantReplicaciones = cantEjecuciones;
        this.cantServers = cantServers;
        this.tiempoSim = tiempoSim;
    }

    private Parametro calcParametro(List<? extends Number> muestra) {
        Parametro p = new Parametro();
        float acum = 0, acumCuad = 0;

        p.setN(muestra.size());

        for (Number n : muestra) {
            acum += n.floatValue();
        }

        float mu = acum / muestra.size();
        p.setMu(mu);

        for (Number n : muestra) {
            acumCuad += Math.pow(mu - n.floatValue(), 2);
        }

        float sigma = (float) Math.sqrt(acumCuad / (muestra.size() - 1));
        p.setSigma(sigma);

        return p;
    }

    private void printIntervalo(Parametro pa) {
        System.out.print("[");

        // Hacemos mMedias - 1.96 * (mMedias / sqrt(n)) para el limite inferior del intervalo.
        System.out.print(Float.toString((float) (pa.getMu().floatValue()
                        - (1.96 * (pa.getSigma().floatValue() / Math.sqrt(pa.getN().floatValue()))))
        ));

        //Media de medias.
        System.out.print(" , " + Float.toString(pa.getMu().floatValue()) + " , ");

        // Hacemos mMedias + 1.96 * (mMedias / sqrt(n)) para el limite superior del intervalo.
        System.out.print(Float.toString((float) (pa.getMu().floatValue()
                + (1.96 * (pa.getSigma().floatValue() / Math.sqrt(pa.getN().floatValue()))))
        ));
        System.out.print("]");

        System.out.print('\n');
    }

    public void mostrarResultados() {
        System.out.println("Confianza: 95%");
        System.out.println("Cantidad de replicaciones: " + Integer.toString(cantReplicaciones));
        System.out.println("Tiempo de simulacion: "      + Integer.toString(tiempoSim));
        System.out.println("Formato: [Limite inferior, media, limite superior]");
        System.out.println(" ");

        System.out.println("--Cantidad de aterrizados--");
        printIntervalo(this.calcParametro(this.cantAterrizado));
        System.out.println(" ");

        System.out.println("--Cantidad de arribados--");
        printIntervalo(this.calcParametro(this.cantArribo));
        System.out.println(" ");

        System.out.println("--Transito [min]--");

        System.out.print("Minimo: ");
        printIntervalo(this.calcParametro(this.transMin));

        System.out.print("Medio: ");
        printIntervalo(this.calcParametro(this.transMed));

        System.out.print("Maximo: ");
        printIntervalo(this.calcParametro(this.transMax));
        System.out.println(" ");

        System.out.println("--Espera [min]--");

        System.out.print("Minima: ");
        printIntervalo(this.calcParametro(this.esperaMin));

        System.out.print("Media: ");
        printIntervalo(this.calcParametro(this.esperaMed));

        System.out.print("Maxima: ");
        printIntervalo(this.calcParametro(this.esperaMax));
        System.out.println(" ");

        System.out.println("--Ocio [Total: %, Minimo y Maximo: min]--");

        for (int i = 0; i < cantServers; i++) {

            ArrayList<Double> totalAux = new ArrayList<>();
            ArrayList<Double> minAux   = new ArrayList<>();
            ArrayList<Double> maxAux   = new ArrayList<>();

            /*  Por la forma en la que estan almacenados los valores de
             *  cada replicacion debemos recorrer recoletando el valor
             *  correspondiente al servidor [indice = id - 1]
             */
            for (List<Double> list : ocioMax){
                maxAux.add(list.get(i));
            }

            for (List<Double> list : ocioMin){
                minAux.add(list.get(i));
            }

            for (List<Double> list : ocioTotal){
                totalAux.add(list.get(i));
            }

            // i+1 = indice de arrreglo
            System.out.println("Pista " + Integer.toString(i+1));

            System.out.print("Total: ");
            printIntervalo(this.calcParametro(totalAux));

            System.out.print("Minimo: ");
            printIntervalo(this.calcParametro(minAux));

            System.out.print("Maximo: ");
            printIntervalo(this.calcParametro(maxAux));
        }
        System.out.println(" ");

        System.out.println("--Tamaño de cola--");

        for (int i = 0; i < cantServers; i++) {
            ArrayList<Integer> minAux = new ArrayList<>();
            ArrayList<Integer> maxAux = new ArrayList<>();

            for (List<Integer> list : tamColaMax){
                maxAux.add(list.get(i));
            }

            for (List<Integer> list : tamColaMin){
                minAux.add(list.get(i));
            }

            System.out.println("Pista " + Integer.toString(i+1));

            System.out.print("Minima: ");
            printIntervalo(this.calcParametro(minAux));

            System.out.print("Maxima: ");
            printIntervalo(this.calcParametro(maxAux));
        }
        System.out.println(" ");

        System.out.println("Durabilidad final");

        for (int i = 0; i < cantServers; i++) {
            ArrayList<Double> duraAux = new ArrayList<>();

            for (List<Double> list : durabilidadFinal){
                duraAux.add(list.get(i));
            }

            System.out.println("Pista " + Integer.toString(i+1));
            printIntervalo((this.calcParametro(this.durabilidadFinal.get(i))));
        }
        System.out.println(" ");

    }

    public void setDatosEjecucion(Estadisticas ejecucion) {
        this.ocioMax.add(ejecucion.getOcioMax());
        this.ocioMin.add(ejecucion.getOcioMin());
        this.ocioTotal.add(ejecucion.getOcioAcum());

        this.esperaMax.add(ejecucion.getEsperaMax());
        this.esperaMin.add(ejecucion.getEsperaMin());
        this.esperaMed.add(ejecucion.getEsperaMed());

        this.cantArribo.add(ejecucion.getCantArribo());
        this.cantAterrizado.add(ejecucion.getCantAterrizado());

        this.transMax.add(ejecucion.getTransMax());
        this.transMin.add(ejecucion.getTransMin());
        this.transMed.add(ejecucion.getTransMed());

        this.cantArribo.add(ejecucion.getCantArribo());
        this.cantAterrizado.add(ejecucion.getCantAterrizado());

        this.tamColaMax.add(ejecucion.getTamColaMax());
        this.tamColaMin.add(ejecucion.getTamColaMin());

        this.durabilidadFinal.add(ejecucion.getDurabilidadFinal());
    }
}

