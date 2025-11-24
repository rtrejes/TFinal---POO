package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Set;
import java.util.TreeSet;

public class CadastroCompradorGUI extends JFrame {

    private JTextField campoCod, campoNome, campoPais, campoEmail;
    private JTextArea areaLog;
    private JButton botaoGravar, botaoLimpar, botaoListar, botaoSair;

    private Set<Comprador> cadastroDeCompradores;

    public CadastroCompradorGui() {
        super("Cadastro de Comprador");

        this.cadastroDeCompradores = new TreeSet<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLayout(new BorderLayout(10, 10));

        JPanel painelFormulario = montarPainelFormulario();

        areaLog = new JTextArea(12, 40);
        areaLog.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(areaLog);
        scrollLog.setBorder(BorderFactory.createTitledBorder("Área de Logs e Cadastros"));

        JPanel painelAcoes = montarPainelAcoes();

        add(painelFormulario, BorderLayout.NORTH);
        add(scrollLog, BorderLayout.CENTER);
        add(painelAcoes, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel montarPainelFormulario() {
        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        campoCod = new JTextField(25);
        campoNome = new JTextField(25);
        campoPais = new JTextField(25);
        campoEmail = new JTextField(25);

        painel.add(new JLabel("Código:"));
        painel.add(campoCod);

        painel.add(new JLabel("Nome:"));
        painel.add(campoNome);

        painel.add(new JLabel("País:"));
        painel.add(campoPais);

        painel.add(new JLabel("Email:"));
        painel.add(campoEmail);

        return painel;
    }

    private JPanel montarPainelAcoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        botaoGravar = new JButton("Cadastrar");
        botaoLimpar = new JButton("Limpar");
        botaoListar = new JButton("Mostrar dados");
        botaoSair = new JButton("Fechar");

        botaoGravar.addActionListener(this::executarCadastro);
        botaoLimpar.addActionListener(this::resetarCampos);
        botaoListar.addActionListener(this::apresentarRelatorio);
        botaoSair.addActionListener(e -> encerrarAplicacao());

        painel.add(botaoGravar);
        painel.add(botaoLimpar);
        painel.add(botaoListar);
        painel.add(botaoSair);

        return painel;
    }

    private void executarCadastro(ActionEvent e) {
        areaLog.setText("");

        try {
            long cod = Long.parseLong(campoCod.getText().trim());
            String nome = campoNome.getText().trim();
            String pais = campoPais.getText().trim();
            String email = campoEmail.getText().trim();

            if (nome.isEmpty() || pais.isEmpty() || email.isEmpty() || campoCod.getText().trim().isEmpty()) {
                areaLog.setText("Erro: Todos os campos devem ser preenchidos.");
                return;
            }

            boolean codigoRepetido = cadastroDeCompradores.stream().anyMatch(c -> c.getCod() == cod);

            if (codigoRepetido) {
                areaLog.setText("Erro: Código " + cod + " já cadastrado no sistema.");
            } else {
                Comprador novoComprador = new Comprador(cod, nome, pais, email);
                cadastroDeCompradores.add(novoComprador);
                areaLog.setText("Comprador cadastrado com sucesso:\n" + novoComprador.toString());
                resetarCampos(null);
                campoCod.requestFocus();
            }

        } catch (NumberFormatException ex) {
            areaLog.setText("Erro de Entrada:\nO Código deve ser um número inteiro válido.");
        } catch (Exception ex) {
            areaLog.setText("Erro inesperado no sistema: " + ex.getMessage());
        }
    }

    private void resetarCampos(ActionEvent e) {
        campoCod.setText("");
        campoNome.setText("");
        campoPais.setText("");
        campoEmail.setText("");
        areaLog.setText("");
        campoCod.requestFocus();
    }

    private void apresentarRelatorio(ActionEvent e) {
        areaLog.setText("");

        if (cadastroDeCompradores.isEmpty()) {
            areaLog.setText("Não há compradores cadastrados no sistema.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- Relatório de Compradores (Ordenado por Código) ---\n");
        for (Comprador c : cadastroDeCompradores) {
            sb.append(c.toString()).append("\n");
        }
        sb.append("---------------------------------------------------------");
        areaLog.setText(sb.toString());
    }

    private void encerrarAplicacao() {
        dispose();
        System.exit(0);
    }
}