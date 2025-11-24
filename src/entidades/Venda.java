package entidades;

import java.util.Date;

public class Venda implements Comparable<Venda>{
    private long num;
    private Date data;
    private double valorFinal;
    private Fornecedor fornecedor;
    private Tecnologia tecnologia;
    private Comprador comprador;
    private int quantidadeVendas;

    public Venda(long num, Date data, double valor, Fornecedor fornecedor, Tecnologia tecnologia, Comprador comprador, int quantidadeVendas) {
        this.num = num;
        this.data = data;
        this.fornecedor = fornecedor;
        this.tecnologia = tecnologia;
        this.comprador = comprador;
        this.quantidadeVendas = quantidadeVendas + 1;
        this.valorFinal = calculaValorFinal(valor, quantidadeVendas);
    }

    public double calculaValorFinal(double valor, int quantidadeVendas){
        if (quantidadeVendas >= 10) {
            return valor * fornecedor.getArea().getValorBaseAcrescimo() * 1.1;
        }
        return valor * fornecedor.getArea().getValorBaseAcrescimo() * (1+(0.1*quantidadeVendas));
    }

    public Date getData() {
        return data;
    }

    public double getValorFinal() {
        return valorFinal;
    }


    public String geraDescricao() {
        return ""; // TODO
    }

    public Comprador getComprador() {
        return comprador;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public int getQuantidadeVendas() {
        return quantidadeVendas;
    }

    public Tecnologia getTecnologia() {
        return tecnologia;
    }

    @Override
    public int compareTo(Venda outraVenda) {
        return Long.compare(outraVenda.num, this.num);
    }
}
