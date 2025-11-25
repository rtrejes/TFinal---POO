package entidades;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fornecedor extends Participante implements Comparable<Fornecedor>{
    private Area area;
    private Date fundacao;

    public Fornecedor(long codigo, String nome, Date fundacao, Area area) {
        super(codigo, nome);
        this.fundacao = fundacao;
        this.area = area;
    }

    public Date getFundacao() {
        return fundacao;
    }

    public Area getArea() {
        return area;
    }

    @Override
    public int compareTo(Fornecedor outroFornecedor) {
        return Long.compare(this.getCod(), outroFornecedor.getCod());
    }

    @Override
    public String geraDescricao() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%d;%s;%s;%s",
                this.getCod(),
                this.getNome(),
                sdf.format(fundacao),
                area
        );
    }
    @Override
    public String toString() {
        return this.getNome();
    }
}
