package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Venda implements Comparable<Venda> {
    private long num;
    private Date data;
    private double valorFinal;
    private Tecnologia tecnologia;
    private Comprador comprador;
    private long quantidadeVendas;

    public Venda(long num, Date data, Tecnologia tecnologia, Comprador comprador, long quantidadeVendas) {
        this.num = num;
        this.data = data;
        this.tecnologia = tecnologia;
        this.comprador = comprador;
        this.quantidadeVendas = quantidadeVendas;
        this.valorFinal = calculaValorFinal(tecnologia.getValorBase(), quantidadeVendas);
    }

    public double calculaValorFinal(double valor, long quantidadeVendas) {
        if (quantidadeVendas >= 10) {
            return valor * tecnologia.getFornecedor().getArea().getValorBaseAcrescimo() * 1.1;
        }
        return valor * tecnologia.getFornecedor().getArea().getValorBaseAcrescimo() * (1 + (0.01 * quantidadeVendas));
    }

    public long getNum() {
        return num;
    }

    public Date getData() {
        return data;
    }

    public double getValorFinal() {
        return valorFinal;
    }


    public String geraDescricao() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        /*
        return this.num + ";"
                + sdf.format(data) + ";"
                + this.getTecnologia().geraDescricao() + ";"
                + this.getComprador().geraDescricao() + ";"
                + this.valorFinal + ";"
                + this.quantidadeVendas;*/
        return String.format("%d;%s;%s;%s;%.2f;%d",
                this.num,
                sdf.format(data),
                this.getTecnologia().geraDescricao(),
                this.getComprador().geraDescricao(),
                this.valorFinal,
                this.quantidadeVendas
        );
    }


    public Comprador getComprador() {
        return comprador;
    }

    public Tecnologia getTecnologia() {
        return tecnologia;
    }

    @Override
    public int compareTo(Venda outraVenda) {
        return Long.compare(outraVenda.num, this.num);
    }
}
