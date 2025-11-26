package entidades;

public class Tecnologia implements Comparable<Tecnologia> {
    private long id;
    private String modelo;
    private String descricao;
    private double valorBase;
    private double peso;
    private double temperatura;
    private Fornecedor fornecedor;

    public Tecnologia(long id, String modelo, String descricao, double valorBase, double peso, double temperatura) {
        this.id = id;
        this.modelo = modelo;
        this.descricao = descricao;
        this.valorBase = valorBase;
        this.peso = peso;
        this.temperatura = temperatura;
    }

    public Tecnologia(long id, String modelo, String descricao, double valorBase, double peso, double temperatura, Fornecedor fornecedor) {
        this.id = id;
        this.modelo = modelo;
        this.descricao = descricao;
        this.valorBase = valorBase;
        this.peso = peso;
        this.temperatura = temperatura;
        this.fornecedor = fornecedor;
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
        return fornecedor;
    }

    public void defineFornecedor(Fornecedor fornecedorSelecionado) {
        this.fornecedor = fornecedorSelecionado;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ",\n Modelo: '" + modelo + '\'' +
                ",\n Descrição: '" + descricao + '\'' +
                ",\n Valor Base: " + valorBase +
                ",\n Peso: " + peso +
                ",\n Temperatura: " + temperatura +
                (fornecedor != null ? ", Fornecedor: " + fornecedor.getNome() : "");
    }

    public String geraDescricao() {
        return String.format("%s;%s;%s;%.2f;%.2f;%s",
                this.getModelo(),
                this.getDescricao(),
                this.getValorBase(),
                this.getPeso(),
                this.getTemperatura(),
                (fornecedor != null ? "Fornecedor: " + fornecedor.getNome() : "")
        );
    }

    @Override
    public int compareTo(Tecnologia outra) {
        return Long.compare(this.id, outra.id);
    }
}
