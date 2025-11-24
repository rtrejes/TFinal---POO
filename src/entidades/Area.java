package entidades;

public enum Area {
    TI(1.2),
    ANDROIDES(1.15),
    EMERGENTE(1.25),
    ALIMENTOS(1.10);

    private double valorBaseAcrescimo;

    Area(double valorBaseAcrescimo){
        this.valorBaseAcrescimo = valorBaseAcrescimo;
    }

    public double getValorBaseAcrescimo() {
        return valorBaseAcrescimo;
    }
}