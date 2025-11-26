package gui;

import aplicacao.ACMETech;

import javax.swing.*;
import java.awt.*;

public class CarregarDadosGUI extends JFrame {
    private ACMETech app;
    private JTextField txtNomeArquivo;
    private JTextArea txtMensagens;

    public CarregarDadosGUI(ACMETech app) {
        super("Carregar Dados");
        this.app = app;

        setLayout(new BorderLayout(10, 10));

        JPanel painelCampos = new JPanel(new GridLayout(1, 2, 5, 5));

        painelCampos.add(new JLabel("Nome do arquivo (sem extensão):"));
        txtNomeArquivo = new JTextField();
        painelCampos.add(txtNomeArquivo);

        add(painelCampos, BorderLayout.NORTH);

        txtMensagens = new JTextArea(5, 20);
        txtMensagens.setEditable(false);
        add(new JScrollPane(txtMensagens), BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton botaoCarregar = new JButton("Carregar");
        JButton botaoLimpar = new JButton("Limpar");
        JButton botaoFechar = new JButton("Fechar");

        painelBotoes.add(botaoCarregar);
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(botaoFechar);

        add(painelBotoes, BorderLayout.SOUTH);

        // ActionListeners
        botaoCarregar.addActionListener(e -> carregar());
        botaoLimpar.addActionListener(e -> limpar());
        botaoFechar.addActionListener(e -> dispose());

        setSize(450, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void carregar() {
        String nome = txtNomeArquivo.getText().trim();

        if (nome.isEmpty()) {
            txtMensagens.append("Erro: Digite um nome de arquivo.\n");
            return;
        }

        try {
            if (app.carregarJSON(nome)) {
                txtMensagens.append("Dados carregados com sucesso do arquivo: " + nome + ".json\n");
            } else {
                txtMensagens.append("Erro ao carregar os dados. Verifique o arquivo.\n");
            }

        } catch (Exception e) {
            txtMensagens.append("Erro inesperado: " + e.getMessage() + "\n");
        }
    }

    private void limpar() {
        txtNomeArquivo.setText("");
        txtMensagens.setText("");
    }

}
