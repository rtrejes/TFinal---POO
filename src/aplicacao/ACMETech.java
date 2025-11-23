package aplicacao;

import gui.TelaInicialGUI;

public class ACMETech {
    public ACMETech(){
        inicializar();
    }

    public static String gerarRelatorioFornecedores() {
        return "A Construir - Relatório de Fornecedores";
    }

    public static String gerarRelatorioTecnologias() {
        return "A Construir - Relatório de Tecnologias";
    }

    public static String gerarRelatorioCompradores() {
        return "A Construir - Relatório de Compradores";
    }

    public static String gerarRelatorioVendas() {
        return "A Construir - Relatório de Vendas";
    }

    public void inicializar(){
    }
    public void executar(){
        new TelaInicialGUI().setVisible(true);
    }
}
