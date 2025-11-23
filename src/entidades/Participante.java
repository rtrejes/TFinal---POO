package entidades;

public abstract class Participante {
    private long cod;
    private String nome;

    public Participante(long codigo, String nome) {
        this.cod = codigo;
        this.nome = nome;
    }

    public long getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public abstract String geraDescricao();
}
