package escenario;

import com.etapa2.Distribucion;
import java.util.Random;

public class DistribucionNormal implements Distribucion {
    private int mu;
    private int sigma;
    private int cantVar;

    public DistribucionNormal(int mu, int sigma){
        this.mu = mu;
        this.sigma = sigma;
        this.cantVar = 50;
    }
    
    @Override
    public double getProbabilidad(double tiempo) {
        return 0;
    }

    @Override
    public double getTiempo(double random) {
        Random rdom = new Random();
        double suma = 0, z = 0;

        for (int i = 0; i <= this.cantVar; i++){
            suma += rdom.nextDouble();
        }

        z = (suma - this.cantVar * (1/2)) / this.cantVar * (1/12);

        return z * this.sigma + this.mu;
    }
    
}
