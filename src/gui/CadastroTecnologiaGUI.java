package gui;

import aplicacao.ACMETech;
import entidades.Area;
import entidades.Fornecedor;
import entidades.Tecnologia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CadastroTecnologiaGUI extends JFrame implements ActionListener {

    private JTextField campoId, campoNome, campoModelo, campoDesc, campoValorBase, campoPeso, campoTemperatura;
    private JComboBox<Fornecedor> comboFornecedor;
    private JTextArea areaMensagens;
    private JButton btnCadastrar, btnLimpar, btnMostrarTodos, btnFechar;
    private ACMETech app;


    public CadastroTecnologiaGUI(ACMETech app) {
        super("Nova Tecnologia - Cadastro");
        this.app = app;


        defineFornecedor();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 600);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JPanel painelCampos = criarPainelCampos();

        areaMensagens = new JTextArea(10, 50);
        areaMensagens.setEditable(false);
        JScrollPane scrollMensagens = new JScrollPane(areaMensagens);
        scrollMensagens.setBorder(BorderFactory.createTitledBorder("Área de Logs e Cadastros"));

        JPanel painelBotoes = criarPainelBotoes();

        add(painelCampos, BorderLayout.NORTH);
        add(scrollMensagens, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    private Date criarData(String dataTexto) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dataTexto);
        } catch (ParseException e) {
            return new Date();
        }
    }

    private void defineFornecedor() {
        app.getFornecedores().add(new Fornecedor(103, "Google", criarData("29/02/2024"), Area.ANDROIDES));
        app.getFornecedores().add(new Fornecedor(102, "Microsoft", criarData("21/08/2019"), Area.TI));
        app.getFornecedores().add(new Fornecedor(101, "Oracle", criarData("12/08/2014"), Area.TI));
    }

    private JPanel criarPainelCampos() {
        JPanel painel = new JPanel(new GridLayout(8, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));


        painel.add(new JLabel("ID:"));
        campoId = new JTextField(10);
        painel.add(campoId);

        painel.add(new JLabel("Nome da tecnologia:"));
        campoNome = new JTextField(30);
        painel.add(campoNome);

        painel.add(new JLabel("Modelo:"));
        campoModelo = new JTextField(15);
        painel.add(campoModelo);

        painel.add(new JLabel("Descrição:"));
        campoDesc = new JTextField(50);
        painel.add(campoDesc);

        painel.add(new JLabel("Valor Base:"));
        campoValorBase = new JTextField(15);
        painel.add(campoValorBase);

        painel.add(new JLabel("Peso:"));
        campoPeso = new JTextField(15);
        painel.add(campoPeso);

        painel.add(new JLabel("Temperatura (°C):"));
        campoTemperatura = new JTextField(15);
        painel.add(campoTemperatura);

        painel.add(new JLabel("Selecione o fornecedor:"));

        comboFornecedor = new JComboBox<>();
        for (Fornecedor f : app.getFornecedores()) {
            comboFornecedor.addItem(f);
        }
        painel.add(comboFornecedor);

        return painel;
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

        btnCadastrar = new JButton("Cadastrar");
        btnLimpar = new JButton("Limpar");
        btnMostrarTodos = new JButton("Mostrar cadastros");
        btnFechar = new JButton("Fechar");

        btnCadastrar.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnMostrarTodos.addActionListener(this);
        btnFechar.addActionListener(this);

        painel.add(btnCadastrar);
        painel.add(btnLimpar);
        painel.add(btnMostrarTodos);
        painel.add(btnFechar);

        return painel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCadastrar) {
            cadastrarTecnologia();
        } else if (e.getSource() == btnLimpar) {
            limparCampos();
        } else if (e.getSource() == btnMostrarTodos) {
            mostrarTodosCadastros();
        } else if (e.getSource() == btnFechar) {
            dispose();
        }
    }

    private void cadastrarTecnologia() {
        areaMensagens.setText("");

        try {
            String aux = campoId.getText().trim();
            //int id = Integer.parseInt(campoId.getText().trim());
            String nome = campoNome.getText().trim();
            String modelo = campoModelo.getText().trim();
            String descricao = campoDesc.getText().trim();
            double valorBase = Double.parseDouble(campoValorBase.getText().trim());
            double peso = Double.parseDouble(campoPeso.getText().trim());
            double temperatura = Double.parseDouble(campoTemperatura.getText().trim());
            Fornecedor fornecedorSelecionado = (Fornecedor) comboFornecedor.getSelectedItem();
            if (aux.isEmpty() || fornecedorSelecionado == null) {
                throw new IllegalArgumentException("Erro: Preencha todos os campos obrigatórios.");
            }
            if (aux.isEmpty() || campoValorBase.getText().isEmpty()) {
                throw new IllegalArgumentException("Campos obrigatórios vazios.");
            }
            long id = Long.parseLong(campoId.getText());


            Tecnologia novaTec = new Tecnologia(id, nome, modelo, descricao, valorBase, peso, temperatura);
            novaTec.defineFornecedor(fornecedorSelecionado);
            if (app.cadastrarTecnologia(novaTec)) {
                areaMensagens.append("  -- CADASTRO REALIZADO COM SUCESSO! -- \n");
                areaMensagens.append("     Nova Tecnologia: " + novaTec);
            } else {
                areaMensagens.append(" ERRO DE CADASTRO:\n");
                areaMensagens.append(" O identificador (ID)" + id + " já está cadastrado no sistema.\n");
                areaMensagens.append(" Tente um ID diferente.");
            }


        } catch (NumberFormatException e) {
            areaMensagens.append("  ERRO DE ENTRADA:\n");
            areaMensagens.append("  Os campos ID, Valor Base, Peso e Temperatura devem ser composto apenas por números. Detalhe: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            areaMensagens.append("  ERRO DE VALIDAÇÃO:\n");
            areaMensagens.append("    " + e.getMessage());
        } catch (Exception e) {
            areaMensagens.append(" ERRO NO SISTEMA:\n");
            areaMensagens.append(" Detalhes: " + e.getMessage());
        }
    }


    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
        campoModelo.setText("");
        campoDesc.setText("");
        campoValorBase.setText("");
        campoPeso.setText("");
        campoTemperatura.setText("");
        comboFornecedor.setSelectedIndex(0);
        areaMensagens.setText("");
    }

    private void mostrarTodosCadastros() {
        Set<Tecnologia> tecnologias = app.getTecnologias();
        areaMensagens.setText("");

        if (tecnologias.isEmpty()) {
            areaMensagens.append("Nenhuma tecnologia cadastrada ainda.");
            return;
        }

        areaMensagens.append("--- Tecnologias Cadastradas (" + tecnologias.size() + ") ---\n");
        for (Tecnologia t : tecnologias) {
            areaMensagens.append(t.toString() + "\n");
        }
        areaMensagens.append("--------------------------------------------------");
    }
}