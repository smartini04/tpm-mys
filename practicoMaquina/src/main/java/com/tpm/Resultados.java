package com.tpm;

import java.util.List;
import java.util.ArrayList;

public class Resultados {

    private int tiempoSim;

    /*
     * Estadisticas de entidades.
     */

    // Transito
    private List<Double> transMax;
    private List<Double> transMin;
    private List<Double> transMed;

    // Espera
    private List<Double> esperaMax;
    private List<Double> esperaMin;
    private List<Double> esperaMed;

    /*
     * Estadisticas del sistema.
     */

    // Arribados
    private List<Integer> cantArribo;

    // Aterrizados
    private List<Integer> cantAterrizado;

    /*
     * Estadisticas relacionadas a cada servidor.
     */

    // Durabilidad
    private List<List<Double>> durabilidadFinal;

    // Ocio
    private List<List<Double>> ocioMax;
    private List<List<Double>> ocioMin;
    private List<List<Double>> ocioTotal;

    // Cola o colas?
    private List<List<Integer>> tamColaMax;
    private List<List<Integer>> tamColaMin;

    public Resultados() {
        this.ocioMax = new ArrayList<>();
        this.ocioMin = new ArrayList<>();
        this.ocioTotal = new ArrayList<>();

        this.esperaMax = new ArrayList<>();
        this.esperaMin = new ArrayList<>();

        this.transMax = new ArrayList<>();
        this.transMin = new ArrayList<>();

        this.cantAterrizado = new ArrayList<>();
        this.cantArribo = new ArrayList<>();

        this.tamColaMax = new ArrayList<>();
        this.tamColaMin = new ArrayList<>();

        this.durabilidadFinal = new ArrayList<>();
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

        Parametro pa;

        System.out.println("Con una confianza del 95%");
        System.out.println("Formato: [Limite inferior, media, Limite superior]");

        System.out.println("--Cantidad de aterrizados--");
        pa = this.calcParametro(this.cantAterrizado);
        printIntervalo(pa);

        System.out.println("--Cantidad de arribados--");
        pa = this.calcParametro(this.cantArribo);
        printIntervalo(pa);

        System.out.println("--Transito--");

        System.out.print("Minimo: ");
        pa = this.calcParametro(this.transMin);
        printIntervalo(pa);

        System.out.print("Maximo: ");
        pa = this.calcParametro(this.transMax);
        printIntervalo(pa);

        System.out.println("--Espera--");

        System.out.print("Minima: ");
        pa = this.calcParametro(this.esperaMin);
        printIntervalo(pa);

        System.out.print("Maxima: ");
        pa = this.calcParametro(this.esperaMax);
        printIntervalo(pa);

        System.out.println("--Ocio--");

        for (int i = 0; i < ocioMax.get(0).size(); i++) {
            ArrayList<Double> totalAux = new ArrayList<>();
            ArrayList<Double> minAux = new ArrayList<>();
            ArrayList<Double> maxAux = new ArrayList<>();

            for (int j = 0; j < ocioMax.size();j++){
                maxAux.add(ocioMax.get(j).get(i));
                minAux.add(ocioMin.get(j).get(i));
                totalAux.add(ocioTotal.get(j).get(i));
            }

            System.out.println("Servidor" + Integer.toString(i+1));

            System.out.print("Total: ");
            pa = this.calcParametro(totalAux);
            printIntervalo(pa);

            System.out.print("Minimo: ");
            pa = this.calcParametro(minAux);
            printIntervalo(pa);

            System.out.print("Maximo: ");
            pa = this.calcParametro(maxAux);
            printIntervalo(pa);
        }

        System.out.println("--Tamaño de cola--");

        for (int i = 0; i < tamColaMin.get(0).size(); i++) {
            ArrayList<Integer> minAux = new ArrayList<>();
            ArrayList<Integer> maxAux = new ArrayList<>();

            for (int j = 0; j < tamColaMin.size();j++){
                maxAux.add(tamColaMax.get(j).get(i));
                minAux.add(tamColaMin.get(j).get(i));
            }

            System.out.println("Servidor" + Integer.toString(i+1));

            System.out.print("Minima: ");
            printIntervalo(this.calcParametro(minAux));

            System.out.print("Maxima: ");
            printIntervalo(this.calcParametro(maxAux));
        }

        System.out.println("Durabilidad final");

        for (int i = 0; i < durabilidadFinal.get(0).size(); i++) {
            ArrayList<Double> duraAux = new ArrayList<>();

            for (int j = 0; j < tamColaMin.size();j++){
                duraAux.add(durabilidadFinal.get(j).get(i));
            }

            System.out.println("Servidor" + Integer.toString(i+1));
            printIntervalo((this.calcParametro(this.durabilidadFinal.get(i))));
        }


    }

    public void setDatosEjecucion(Estadisticas ejecucion) {
        this.ocioMax.add(ejecucion.getOcioMax());
        this.ocioMin.add(ejecucion.getOcioMin());
        this.ocioTotal.add(ejecucion.getOcioAcum());

        this.esperaMax.add(ejecucion.getEsperaMax());
        this.esperaMin.add(ejecucion.getEsperaMin());

        this.cantArribo.add(ejecucion.getCantArribo());
        this.cantAterrizado.add(ejecucion.getCantAterrizado());

        this.transMax.add(ejecucion.getTransMax());
        this.transMin.add(ejecucion.getTransMin());

        this.cantArribo.add(ejecucion.getCantArribo());
        this.cantAterrizado.add(ejecucion.getCantAterrizado());

        this.tamColaMax.add(ejecucion.getTamColaMax());
        this.tamColaMin.add(ejecucion.getTamColaMin());

        this.durabilidadFinal.add(ejecucion.getDurabilidadFinal());
    }
}

