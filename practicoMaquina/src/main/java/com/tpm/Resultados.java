package com.tpm;

import java.util.List;
import java.util.ArrayList;

public class Resultados {

    // Ocio
    private List<Double> ocioMax;
    private List<Double> ocioMin;
    private List<Double> ocioTotal;

    // Transito
    private List<Double> transMax;
    private List<Double> transMin;

    // Espera
    private List<Double> esperaMax;
    private List<Double> esperaMin;

    // Arribados
    private List<Integer> cantArribo;

    // Aterrizados
    private List<Integer> cantAterrizado;

    // Cola o colas?
    private List<Integer> tamColaMax;
    private List<Integer> tamColaMin;

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
    }

    private void printIntervalo(Parametro pa){
        System.out.println("Limite inferior: "  + Double.toString(pa.getMu().doubleValue() - 
                                                (1.96 * (pa.getSigma().doubleValue()/ Math.sqrt(pa.getN().doubleValue()))) 
                                                ) );

        System.out.println("Media: "  + Double.toString(pa.getMu().doubleValue())   );
        System.out.println("Sigma: "  + Double.toString(pa.getSigma().doubleValue())   );


        System.out.println("Limite superior: "  + Double.toString(pa.getMu().doubleValue() + 
                                                (1.96 * (pa.getSigma().doubleValue()/ Math.sqrt(pa.getN().doubleValue())) ) 
                                                    ));

        System.out.print('\n');
    }

    public void mostrarResultados(){
        System.out.println("Con una confianza del 95%");
        Parametro pa;

        pa = this.calcParametro(this.cantAterrizado);
        System.out.println("--Cantidad de aterrizados--");
        printIntervalo(pa);

        pa = this.calcParametro(this.cantArribo);
        System.out.println("--Cantidad de arribados--");
        printIntervalo(pa);

        pa = this.calcParametro(this.transMax);
        System.out.println("--Tiempo minimo de transito--");
        printIntervalo(pa);

        pa = this.calcParametro(this.transMin);
        System.out.println("--Tiempo maximo de transito--");
        printIntervalo(pa);

        pa = this.calcParametro(this.esperaMin);
        System.out.println("--Espera minima--");
        printIntervalo(pa);

        pa = this.calcParametro(this.esperaMax);
        System.out.println("--Espera maxima--");
        printIntervalo(pa);

        pa = this.calcParametro(this.ocioTotal);
        System.out.println("--Tiempo de ocio total--");
        printIntervalo(pa);

        pa = this.calcParametro(this.ocioMin);
        System.out.println("--Tiempo de ocio minimo--");
        printIntervalo(pa);

        pa = this.calcParametro(this.ocioMax);
        System.out.println("--Tiempo de ocio maximo--");
        printIntervalo(pa);

        pa = this.calcParametro(this.tamColaMin);
        System.out.println("--Tamaño de cola minima--");
        printIntervalo(pa);

        pa = this.calcParametro(this.tamColaMax);
        System.out.println("--Tamaño de cola maxima--");
        printIntervalo(pa);
    }

    private Parametro calcParametro(List<? extends Number> muestra) {
        Parametro p = new Parametro();
        double acum = 0, acumCuad = 0;

        for (Number n : muestra) {
            acum += n.doubleValue();
        }

        double mu = acum / muestra.size();
        p.setMu(mu);

        for (Number n : muestra) {
            acumCuad += Math.pow( mu - n.doubleValue(), 2);
        }
        
        double sigma = Math.sqrt( acumCuad /( muestra.size() - 1) );
        p.setSigma(sigma);
        
        return p;
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
    }
}
