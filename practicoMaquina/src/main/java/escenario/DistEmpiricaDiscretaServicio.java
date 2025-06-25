/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escenario;

import com.etapa2.Distribucion;
public class DistEmpiricaDiscretaServicio implements Distribucion {

    @Override
    public double getProbabilidad(double tiempo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getTiempo(double random) {
        if(random<0.38) {
        return 8;
    }
    else if(random<0.7){
        return 10;
    }
    else if(random<0.8){
        return 13;
    }
    else{
        return 15;
    }
    }
    
}
