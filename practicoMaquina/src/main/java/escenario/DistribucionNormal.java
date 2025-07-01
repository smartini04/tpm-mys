package escenario;

import com.tpm.Distribucion;
import java.util.Random;

public class DistribucionNormal implements Distribucion {
    private int mu;
    private int sigma;
    private int cantVar;
    private Random rdom;

    public DistribucionNormal(int mu, int sigma){
        this.mu = mu;
        this.sigma = sigma;
        this.cantVar = 50;
        this.rdom = new Random();
    }
    
    @Override
    public double getProbabilidad(double tiempo) {
        return 0;
    }

    @Override
    public double getTiempo(double random) {
        double suma = 0 , z ;

        for (int i = 0; i <= this.cantVar; i++){
            suma += rdom.nextDouble();
        }

        z = (suma - this.cantVar * (1/2)) / Math.sqrt(this.cantVar) * (1/12);

        return z * this.sigma + this.mu;
    }
    
}
