package entidades;

import java.util.Objects;

public class Tecnologia implements Comparable<Tecnologia> {
    private long id;
    private String nome;
    private String modelo;
    private String descricao;
    private double valorBase;
    private double peso;
    private double temperatura;
    private Fornecedor fornecedorSelecionado;

    public Tecnologia(long id, String nome, String modelo, String descricao, double valorBase, double peso, double temperatura) {
        this.id = id;
        this.nome = nome;
        this.modelo = modelo;
        this.descricao = descricao;
        this.valorBase = valorBase;
        this.peso = peso;
        this.temperatura = temperatura;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
    public Fornecedor getFornecedor() {
        return fornecedorSelecionado;
    }
    public void defineFornecedor(Fornecedor fornecedorSelecionado) {
        this.fornecedorSelecionado = fornecedorSelecionado;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ",\n Modelo: '" + modelo + '\'' +
                ",\n Descrição: '" + descricao + '\'' +
                ",\n Valor Base: " +  valorBase +
                ",\n Peso: " +  peso +
                ",\n Temperatura: " +  temperatura +
                (fornecedorSelecionado != null ? ", entidades.Fornecedor: " + fornecedorSelecionado.getNome() : "");
    }

    @Override
    public int compareTo(Tecnologia outra) {
        return Long.compare(this.id, outra.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Tecnologia that = (Tecnologia) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}

