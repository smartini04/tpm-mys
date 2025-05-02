/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.etapa2;
import java.util.ArrayList;
/**
 *
 * @author Lourdes
 */
public class Cola {
    private ArrayList<Entidad> cola;
    
    public Cola(){
        this.cola=new ArrayList<>();
    }

    public ArrayList<Entidad> getCola() {
        return cola;
    }
    
    public Entidad getPrimero(){
        Entidad getingo = this.cola.getFirst();
        this.cola.removeFirst();
        return getingo;
    }

    public boolean estaVacio(){
        return this.cola.isEmpty();
    }

    public void agregar(Entidad e){
        this.cola.add(e);
    }
    
    public int largo(){
        return this.cola.size();
    }
}

