package com.etapa2;
import java.util.ArrayList;
import java.util.Comparator;


// Get busy livin' or get busy diyin'
public final class FEL {

    private ArrayList<Evento> listaEventos;
    private Comparator<Evento> comparador;

    public FEL(Comparator<Evento> comparador) {
        this.listaEventos = new ArrayList<>();
        this.comparador = comparador;
    }

    public Evento inminente() {
        return this.listaEventos.remove(0);
    }

    public void insert(Evento evento) {
        this.listaEventos.add(evento);
        this.listaEventos.sort(this.comparador);
    }

    @Override
    public String toString() {
        String output = "================ FEL ================\n";
        for (Evento e : this.listaEventos) {
            if (e.getEntidad() != null) {
                output += "[" + e.getClock() + "," + e.getOrden() + "," + e.getEntidad().getId() + "]\n";
            } else {
                output += "[" + e.getClock() + "," + e.getOrden() + ",null]\n";
            }
        }

        return output += "*****************************";
    }
}
