package gui;

import aplicacao.ACMETech;

import javax.swing.*;
import java.awt.*;

public class ConsultaMaiorGUI extends JFrame {

    private ACMETech app;

    private JComboBox<String> comboOpcoes;
    private JTextArea txtResultados;

    public ConsultaMaiorGUI(ACMETech app) {
        super("Consultar Maior");
        this.app = app;

        setLayout(new BorderLayout(10, 10));

        JPanel painelTopo = new JPanel(new GridLayout(1, 2, 5, 5));
        painelTopo.add(new JLabel("Escolha a consulta:"));

        comboOpcoes = new JComboBox<>(new String[]{
                "Tecnologia com maior valor",
                "Fornecedor com maior número de tecnologias",
                "Comprador com maior número de vendas",
                "Venda com maior valor"
        });
        painelTopo.add(comboOpcoes);

        add(painelTopo, BorderLayout.NORTH);

        txtResultados = new JTextArea(8, 30);
        txtResultados.setEditable(false);
        add(new JScrollPane(txtResultados), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton botaoConsultar = new JButton("Consultar");
        JButton botaoLimpar = new JButton("Limpar");
        JButton botaoFechar = new JButton("Fechar");

        painelBotoes.add(botaoConsultar);
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(botaoFechar);

        add(painelBotoes, BorderLayout.SOUTH);

        botaoConsultar.addActionListener(e -> consultar());
        botaoLimpar.addActionListener(e -> limpar());
        botaoFechar.addActionListener(e -> dispose());

        setSize(1100, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void consultar() {
        int opcao = comboOpcoes.getSelectedIndex();

        try {
            switch (opcao) {
                case 0:
                    txtResultados.setText(app.consultarTecnologiaMaiorValor());
                    break;
                case 1:
                    txtResultados.setText(app.consultarFornecedorMaiorTecnologias());
                    break;
                case 2:
                    txtResultados.setText(app.consultarCompradorMaiorVendas());
                    break;
                case 3:
                    txtResultados.setText(app.consultarVendaMaiorValor());
                    break;
                default:
                    txtResultados.setText("Opção inválida.");
            }
        } catch (Exception ex) {
            txtResultados.setText("Erro: " + ex.getMessage());
        }
    }

    private void limpar() {
        txtResultados.setText("");
    }
}
