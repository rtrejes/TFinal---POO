package entidades;

import java.util.Date;

public class Venda {
    private long num;
    private Date data;
    private double valorFinal;

    public Venda(long num, Date data, double valorFinal) {
        this.num = num;
        this.data = data;
        this.valorFinal = valorFinal;
    }
    public double calculaValorFinal(){
        // TODO: Implementar método
        return -1;
    }
}
