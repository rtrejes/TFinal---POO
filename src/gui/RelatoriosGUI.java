package gui;

import aplicacao.ACMETech;
import entidades.*;

import javax.swing.*;
import java.util.Set;

public class RelatoriosGUI extends JFrame {
    private JTextArea areaTexto;
    private Relatorio relatorio;
    private ACMETech app;

    public RelatoriosGUI(ACMETech app, Relatorio relatorio) {
        super("Relatórios");
        this.app = app;
        this.relatorio = relatorio;
        setSize(1100, 600);
        setLocationRelativeTo(null);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        add(new JScrollPane(areaTexto));

        setVisible(true);

        gerarRelatorioRelativo(app, relatorio);
    }

    private void gerarRelatorioRelativo(ACMETech app, Relatorio relatorio) {
        Set<Fornecedor> fornecedores = app.getFornecedores();
        Set<Tecnologia> tecnologias = app.getTecnologias();
        Set<Venda> vendas = app.getVendas();
        Set<Comprador> compradores = app.getCompradores();

        if (fornecedores.isEmpty() && relatorio == Relatorio.FORNECEDOR){
            areaTexto.setText("Erro: Nenhum fornecedor cadastrado!");
        }
        if (tecnologias.isEmpty() && relatorio == Relatorio.TECNOLOGIA){
            areaTexto.setText("Erro: Nenhuma tecnologia cadastrada!");
        }
        if (vendas.isEmpty() && relatorio == Relatorio.VENDA){
            areaTexto.setText("Erro: Nenhuma venda realizada!");
        }
        if (compradores.isEmpty() && relatorio == Relatorio.COMPRADOR){
            areaTexto.setText("Erro: Nenhum comprador cadastrado!");
        }
        areaTexto.setText(app.gerarRelatorio(relatorio));
    }

    public void setConteudo(String texto) {
        areaTexto.setText(texto);
    }
}
