/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escenario;

import java.util.Random;

import com.tpm.Randomizer;

/**
 *
 * @author Lourdes
 */
public final class ObtenerRandom implements Randomizer{
    private Random random;
    
        public ObtenerRandom(){
        this.random = new Random(System.currentTimeMillis());
    }

    public ObtenerRandom(long seed){
        this.random = new Random(seed);
    }
    @Override
    public double next() {
        return this.random.nextDouble();
    }
}
