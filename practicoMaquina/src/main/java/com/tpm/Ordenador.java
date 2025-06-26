/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpm;
import java.util.Comparator;
/**
 *
 * @author Lourdes
 */
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
    

