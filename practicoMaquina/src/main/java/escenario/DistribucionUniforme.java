/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escenario;

import com.tpm.Distribucion;

/**
 *
 * @author Lourdes
 */
public class DistribucionUniforme implements Distribucion {
    private double a; //limite inferior
    private double b; //limite superior
    
    public DistribucionUniforme(double a, double b){
        this.a=a;
        this.b=b;
    }

    @Override
    public double getProbabilidad(double tiempo) {
        if (tiempo < a) return 0.0;
        if (tiempo > b) return 1.0;
        return (tiempo - a) / (b - a);
    }

    @Override
    public double getTiempo(double random) {
        return a + random * (b - a);
    }
    
}
