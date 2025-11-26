package gui;

import aplicacao.ACMETech;
import entidades.Comprador;

import javax.swing.*;
import java.awt.*;

public class AlterarCompradorGUI extends JFrame {

    private ACMETech app;

    private JTextField txtCodigo;
    private JTextField txtPais;
    private JTextField txtNome;
    private JTextField txtEmail;

    private JTextArea txtMensagens;

    public AlterarCompradorGUI(ACMETech app) {
        super("Alterar Comprador");
        this.app = app;

        setLayout(new BorderLayout(10, 10));

        JPanel painelCodigo = new JPanel(new GridLayout(1, 3, 5, 5));

        painelCodigo.add(new JLabel("Código do comprador:"));
        txtCodigo = new JTextField();
        painelCodigo.add(txtCodigo);

        JButton botaoBuscar = new JButton("Buscar");
        painelCodigo.add(botaoBuscar);

        add(painelCodigo, BorderLayout.NORTH);

        JPanel painelDados = new JPanel(new GridLayout(3, 2, 5, 5));

        painelDados.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelDados.add(txtNome);

        painelDados.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        painelDados.add(txtEmail);

        painelDados.add(new JLabel("País:"));
        txtPais = new JTextField();
        painelDados.add(txtPais);

        habilitarDesabilitarCampos(false);

        add(painelDados, BorderLayout.CENTER);

        txtMensagens = new JTextArea(5, 35);
        txtMensagens.setEditable(false);
        add(new JScrollPane(txtMensagens), BorderLayout.EAST);

        // Painel inferior: botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton botaoAlterar = new JButton("Alterar");
        JButton botaoLimpar = new JButton("Limpar");
        JButton botaoFechar = new JButton("Fechar");

        painelBotoes.add(botaoAlterar);
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(botaoFechar);

        add(painelBotoes, BorderLayout.SOUTH);

        // Listeners
        botaoBuscar.addActionListener(e -> buscar());
        botaoAlterar.addActionListener(e -> alterar());
        botaoLimpar.addActionListener(e -> limpar());
        botaoFechar.addActionListener(e -> dispose());

        setSize(700, 280);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void habilitarDesabilitarCampos(boolean habilitado) {
        txtCodigo.setEnabled(!habilitado);
        txtNome.setEnabled(habilitado);
        txtPais.setEnabled(habilitado);
        txtEmail.setEnabled(habilitado);
    }

    private void buscar() {
        String codigoTxt = txtCodigo.getText().trim();

        if (codigoTxt.isEmpty()) {
            txtMensagens.append("Erro: Digite o código do comprador.\n");
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoTxt);
            Comprador comprador = app.buscarComprador(codigo);
            if (comprador == null) {
                txtMensagens.append("Erro: Nenhum comprador encontrado com código " + codigo + ".\n");
                habilitarDesabilitarCampos(false);
                limparCamposEdicao();
                return;
            }

            txtNome.setText(comprador.getNome());
            txtEmail.setText(comprador.getEmail());
            txtPais.setText(comprador.getPais());

            habilitarDesabilitarCampos(true);
            txtMensagens.append("Comprador encontrado. Edite os dados e clique em Alterar.\n");

        } catch (NumberFormatException ex) {
            txtMensagens.append("Erro: Código deve ser um número inteiro.\n");
        } catch (Exception e) {
            txtMensagens.append("Erro inesperado: " + e.getMessage() + "\n");
        }
    }

    private void alterar() {
        if (!txtNome.isEnabled()) {
            txtMensagens.append("Erro: Nenhum comprador carregado. Busque primeiro.\n");
            return;
        }

        long cod = Long.parseLong(txtCodigo.getText());
        String nome = txtNome.getText().trim();
        String pais = txtPais.getText().trim();
        String email = txtEmail.getText().trim();
        if (nome.isEmpty() || pais.isEmpty() || email.isEmpty()) {
            txtMensagens.append("Erro: Preencha todos os campos.\n");
            return;
        }

        try {
            boolean atualizado = app.alterarComprador(cod, nome, pais, email);
            if (atualizado) {
                txtMensagens.append("Comprador atualizado com sucesso.\n");
            } else {
                txtMensagens.append("Erro ao atualizar o comprador.\n");
            }

        } catch (Exception e) {
            txtMensagens.append("Erro inesperado: " + e.getMessage() + "\n");
        }
    }

    private void limpar() {
        txtCodigo.setText("");
        limparCamposEdicao();
        habilitarDesabilitarCampos(false);
        txtMensagens.setText("");
    }

    private void limparCamposEdicao() {
        txtNome.setText("");
        txtPais.setText("");
        txtEmail.setText("");
    }
}