package gui;

import entidades.Area;
import entidades.Fornecedor;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

public class CadastroFornecedorGUI extends JFrame {
    private JTextField txtCodigo, txtNome, txtFundacao;
    private SimpleDateFormat sdfFundacao = new SimpleDateFormat("dd/MM/yyyy");
    private JComboBox<Area> comboArea;
    private JTextArea txtMensagens;
    private TreeSet<Fornecedor> fornecedores = new TreeSet<>();

    public CadastroFornecedorGUI() {
        super("Cadastro de Fornecedor");

        setLayout(new BorderLayout(10,10));

        JPanel painel = new JPanel(new GridLayout(4,2,5,5));

        painel.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        painel.add(txtCodigo);

        painel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("Data da Fundação(dd/MM/yyyy):"));
        txtFundacao = new JTextField();
        painel.add(txtFundacao);

        painel.add(new JLabel("Área:"));
        comboArea = new JComboBox<>(Area.values());
        painel.add(comboArea);

        add(painel, BorderLayout.NORTH);

        txtMensagens = new JTextArea(5,5);
        txtMensagens.setEditable(false);
        add(new JScrollPane(txtMensagens), BorderLayout.CENTER);

        // BOTÕES

        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoLimpar = new JButton("Limpar");
        JButton botaoMostrar = new JButton("Mostrar dados");
        JButton botaoFechar = new JButton("Fechar");

        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(botaoMostrar);
        painelBotoes.add(botaoFechar);
        add(painelBotoes, BorderLayout.SOUTH);

        // EVENTOS

        botaoCadastrar.addActionListener(e -> cadastrarFornecedor());
        botaoLimpar.addActionListener(e -> limparCampos());
        botaoMostrar.addActionListener(e -> mostrarFornecedores());
        botaoFechar.addActionListener(e -> dispose());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cadastrarFornecedor() {
        try{
            long codigo = Long.parseLong(txtCodigo.getText());
            String nome = txtNome.getText();
            if (nome.isBlank()) {
                txtMensagens.append("Erro: nome deve ser preenchido!\n");
                return;
            }
            Date fundacao = sdfFundacao.parse(txtFundacao.getText());
            Area area = (Area) comboArea.getSelectedItem();
            Fornecedor fornecedor = new Fornecedor(codigo, nome, fundacao, area);

            if (!fornecedores.add(fornecedor)){
                txtMensagens.append("Erro: código já cadastrado! \n");
            } else {
                txtMensagens.append("Fornecedor cadastrado com sucesso! \n");
            }
        } catch (NumberFormatException e){
            txtMensagens.append("Erro: código deve ser numérico.\n");
        } catch (ParseException e){
            txtMensagens.append("Erro: data inválida! Use o formato dd/MM/yyyy.\n");
        } catch (Exception e){
            txtMensagens.append("Erro desconhecido: " + e.getMessage() + "\n");
        }
    }

    private void limparCampos() {
        txtCodigo.setText("");
        txtNome.setText("");
        txtFundacao.setText("");
        comboArea.setSelectedIndex(0);
        txtMensagens.setText("");
    }

    private void mostrarFornecedores() {
        txtMensagens.setText("");
        if (fornecedores.isEmpty()) {
            txtMensagens.append("Nenhum fornecedor cadastrado!\n");
        } else {
            fornecedores.forEach(f -> txtMensagens.append(f.geraDescricao() + "\n"));
        }
    }
}