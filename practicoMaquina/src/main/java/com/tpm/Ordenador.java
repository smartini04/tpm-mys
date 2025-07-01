package com.tpm;
import java.util.Comparator;

public class Ordenador implements Comparator <Evento> {

    @Override
    public int compare(Evento e1, Evento e2) {
      if(e1.getClock() < e2.getClock()){
            return -1;
        }
        else if (e1.getClock() > e2.getClock()){
            return 1;
        }
        else if (e1.getOrden() < e2.getOrden()) {
            return -1;
        }
        else if (e1.getOrden() > e2.getOrden()) {
            return 1;
        }
        else {
            return 0;
        }
    }
    }
    

