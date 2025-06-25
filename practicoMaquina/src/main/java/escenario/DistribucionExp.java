package escenario;

import com.etapa2.Distribucion;

public class DistribucionExp implements Distribucion {
    private double mu;
    private double clock;
    
    public DistribucionExp(){
    }
    
    public DistribucionExp(double clock){
        this.clock=clock;
    }


    @Override
    public double getProbabilidad(double tiempo) {
       double lambda = 1.0 / this.getMu(); // Usa el mu actual
    return 1 - Math.exp(-lambda * tiempo);
    } 

    @Override
    public double getTiempo(double random) {
        double tiempoDia=(this.getClock() / 60) % 24;
        if((tiempoDia>=9 && tiempoDia<13) || (tiempoDia>=20 && tiempoDia<23)){
        this.setMu(9.0);
        }
        else{
            this.setMu(15.0);}
        double lambda=1.0/this.getMu();
        return -Math.log(1-random)/lambda;
        }

    /**
     * @return the mu
     */
    public double getMu() {
        return mu;
    }

    /**
     * @param mu the mu to set
     */
    public void setMu(double mu) {
        this.mu = mu;
    }

    /**
     * @return the clock
     */
    public double getClock() {
        return clock;
    }

    /**
     * @param clock the clock to set
     */
    public void setClock(double clock) {
        this.clock = clock;
    }
    
    
}
