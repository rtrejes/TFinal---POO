package gui;

import aplicacao.ACMETech;

import javax.swing.*;
import java.awt.*;

public class SalvarDadosGUI extends JFrame {
    private ACMETech app;
    private JTextField txtNomeArquivo;
    private JTextArea txtMensagens;

    public SalvarDadosGUI(ACMETech app) {
        super("Salvar Dados");
        this.app = app;

        setLayout(new BorderLayout(10,10));

        JPanel painelCampos = new JPanel(new GridLayout(1,2,5,5));


        painelCampos.add(new JLabel("Nome do arquivo (sem extensão):"));
        txtNomeArquivo = new JTextField();
        painelCampos.add(txtNomeArquivo);

        add(painelCampos, BorderLayout.NORTH);

        txtMensagens = new JTextArea(5, 20);
        txtMensagens.setEditable(false);
        add(new JScrollPane(txtMensagens), BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoLimpar = new JButton("Limpar");
        JButton botaoFechar = new JButton("Fechar");

        painelBotoes.add(botaoSalvar);
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(botaoFechar);

        add(painelBotoes, BorderLayout.SOUTH);

        // ActionListeners
        botaoSalvar.addActionListener(e -> salvar());
        botaoLimpar.addActionListener(e -> limpar());
        botaoFechar.addActionListener(e -> dispose());

        setSize(450, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void salvar() {
        String nome = txtNomeArquivo.getText().trim();

        if (nome.isEmpty()) {
            txtMensagens.append("Erro: Digite um nome de arquivo.\n");
            return;
        }

        try {
            if (app.salvarJSON(nome)) {
                txtMensagens.append("Dados salvos com sucesso no arquivo: " + nome + ".json\n");
            } else {
                txtMensagens.append("Erro ao salvar os dados.\n");
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
