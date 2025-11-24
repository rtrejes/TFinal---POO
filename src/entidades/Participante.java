package entidades;
import java.io.Serializable;

public abstract class Participante implements Serializable {
    private static final long serialVersionUID = 1L;

    private long cod;
    private String nome;

    public Participante(long cod, String nome) {
        this.cod = cod;
        this.nome = nome;
    }

    public long getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract String geraDescricao();
}