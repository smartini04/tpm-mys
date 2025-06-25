package com.etapa2;
public class Parametro {
    private Number mu;
    private Number sigma;
    
    
    public Parametro(){    
    }
    
    public Parametro(Number mu , Number sigma){
        this.mu = mu;
        this.sigma=sigma;
        
    }

    public Number getMu() {
        return mu;
    }

    public void setMu(Number mu) {
        this.mu = mu;
    }

    public Number getSigma() {
        return sigma;
    }

    public void setSigma(Number sigma) {
        this.sigma = sigma;
    }
}
