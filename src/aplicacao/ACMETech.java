package aplicacao;

import entidades.*;
import gui.*;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class ACMETech {
    private TreeSet<Fornecedor> fornecedores = new TreeSet<>();
    private TreeSet<Tecnologia> tecnologias = new TreeSet<>();
    private TreeSet<Comprador> compradores = new TreeSet<>();
    private TreeSet<Venda> vendas = new TreeSet<>();

    public ACMETech() {
        inicializar();
    }

    public Set<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public boolean cadastrarFornecedor(long codigo, String nome, Date fundacao, Area area) {
        Fornecedor fornecedor = new Fornecedor(codigo, nome, fundacao, area);
        return fornecedores.add(fornecedor);
    }

    public boolean cadastrarVenda(long num, Date data, double valor, Fornecedor fornecedor, Tecnologia tecnologia, Comprador comprador, int quantidadeVendas) {
        for (Venda venda : vendas) {
            if (venda.getComprador().equals(comprador)){
                quantidadeVendas++;
            }
        }
        Venda v = new Venda(num,data,valor,fornecedor,tecnologia,comprador,quantidadeVendas);
        return vendas.add(v);
    }

    public String gerarRelatorio(Relatorio relatorio) {
        StringBuilder stringRelatorio = new StringBuilder();
        switch (relatorio) {
            case FORNECEDOR -> {
                for (Fornecedor f : fornecedores) {
                    stringRelatorio.append(f.geraDescricao());
                    stringRelatorio.append("\n");
                }
            }
            case COMPRADOR -> {
                for (Comprador c : compradores) {
                    stringRelatorio.append(c.geraDescricao());
                    stringRelatorio.append("\n");
                }
            }
            case VENDA -> {
                for (Venda v : vendas) {
                    stringRelatorio.append(v.geraDescricao());
                    stringRelatorio.append("\n");
                }
            }
            case TECNOLOGIA -> {
                for (Tecnologia t : tecnologias) {
                    stringRelatorio.append(t.geraDescricao());
                    stringRelatorio.append("\n");
                }
            }
        }
        return stringRelatorio.toString();
    }

    public void inicializar() {
    }

    public void executar() {
        new TelaInicialGUI(this).setVisible(true);
    }
}
