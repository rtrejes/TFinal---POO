package gui;

import aplicacao.ACMETech;

import javax.swing.*;
import java.awt.*;

public class TelaInicialGUI extends JFrame {
    private CadastroFornecedorGUI cadastroFornecedorGUI;
    private CadastroTecnologiaGUI cadastroTecnologiaGUI;
    private CadastroCompradorGUI cadastroCompradorGUI;
    private CadastroVendaGUI cadastroVendaGUI;
    private RelatoriosGUI relatoriosGUI;
    private RemoverVendaGUI removerVendaGUI;
    private AlterarCompradorGUI alterarCompradorGUI;
    private ConsultaMaiorGUI consultaMaiorGUI;

    public TelaInicialGUI() {
        super("ACMETech - Venda de tecnologias");

        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Botoes Cadastros
        JButton botaoFornecedor = new JButton("Cadastrar Fornecedor");
        JButton botaoTecnologia = new JButton("Cadastrar Tecnologia");
        JButton botaoComprador = new JButton("Cadastrar Comprador");
        JButton botaoVenda = new JButton("Cadastrar Venda");

        // Botoes Relatorios
        JButton botaoRelatorioFornecedor = new JButton("Relatório de Fornecedores");
        JButton botaoRelatorioTecnologia = new JButton("Relatório de Tecnologias");
        JButton botaoRelatorioComprador = new JButton("Relatório de Compradores");
        JButton botaoRelatorioVenda = new JButton("Relatório de Vendas");

        // Botoes Adicionais
        JButton botaoRemoverVenda = new JButton("Remover Venda");
        JButton botaoAlterarComprador = new JButton("Alterar Comprador");

        JButton botaoConsultaMaior = new JButton("Consultar Maior");
        JButton botaoSalvar = new JButton("Salvar Dados");
        JButton botaoCarregar = new JButton("Carregar Dados");

        JButton botaoSair = new JButton("Finalizar Sistema");
        botaoSair.setPreferredSize(new Dimension(300, 80));


        // Panels
            // Painel Principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            // Painel de Cadastros
        JPanel painelCadastros = new JPanel(new GridLayout(2, 2, 10, 10));
        painelCadastros.setBorder(BorderFactory.createTitledBorder("Cadastros"));

        painelCadastros.add(botaoFornecedor);
        painelCadastros.add(botaoTecnologia);
        painelCadastros.add(botaoComprador);
        painelCadastros.add(botaoVenda);
            // Painel de Relatorios
        JPanel painelRelatorios = new JPanel(new GridLayout(2, 2, 10, 10));
        painelRelatorios.setBorder(BorderFactory.createTitledBorder("Relatórios"));

        painelRelatorios.add(botaoRelatorioFornecedor);
        painelRelatorios.add(botaoRelatorioTecnologia);
        painelRelatorios.add(botaoRelatorioComprador);
        painelRelatorios.add(botaoRelatorioVenda);
        painelPrincipal.add(painelCadastros);
            // Painel de Operações
        JPanel painelOperacoes = new JPanel(new GridLayout(1, 3, 10, 10));
        painelOperacoes.setBorder(BorderFactory.createTitledBorder("Operações"));

        painelOperacoes.add(botaoRemoverVenda);
        painelOperacoes.add(botaoAlterarComprador);
        painelOperacoes.add(botaoConsultaMaior);
            // Painel de Arquivo
        JPanel painelArquivos = new JPanel(new GridLayout(1, 2, 10, 10));
        painelArquivos.setBorder(BorderFactory.createTitledBorder("Arquivos"));

        painelArquivos.add(botaoSalvar);
        painelArquivos.add(botaoCarregar);
            // Painel para Sair
        JPanel painelSair = new JPanel();
        painelSair.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        painelSair.add(botaoSair);

        // Adicionar tudo ao Painel Principal
        painelPrincipal.add(painelCadastros);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelRelatorios);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelOperacoes);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelArquivos);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelSair);

        // Adiciona o painelPrincipal no JFrame
        add(painelPrincipal, BorderLayout.CENTER);

        // ActionListeners > Ações ao clicar nos botões
            // Cadastros
        botaoFornecedor.addActionListener(e -> {
            if (cadastroFornecedorGUI == null) {
                cadastroFornecedorGUI = new CadastroFornecedorGUI();

                cadastroFornecedorGUI.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        cadastroFornecedorGUI = null;
                    }
                });
            } else {
                cadastroFornecedorGUI.toFront();
                cadastroFornecedorGUI.requestFocus();
            }
            cadastroFornecedorGUI.setVisible(true);
        });
        botaoTecnologia.addActionListener(e -> {
            if (cadastroTecnologiaGUI == null) {
                cadastroTecnologiaGUI = new CadastroTecnologiaGUI();

                cadastroTecnologiaGUI.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        cadastroTecnologiaGUI = null;
                    }
                });
            } else {
                cadastroTecnologiaGUI.toFront();
                cadastroTecnologiaGUI.requestFocus();
            }
            cadastroTecnologiaGUI.setVisible(true);
        });
        botaoComprador.addActionListener(e -> {
            if (cadastroCompradorGUI == null) {
                cadastroCompradorGUI = new CadastroCompradorGUI();

                cadastroCompradorGUI.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        cadastroCompradorGUI = null;
                    }
                });
            } else {
                cadastroCompradorGUI.toFront();
                cadastroCompradorGUI.requestFocus();
            }
            cadastroCompradorGUI.setVisible(true);
        });
        botaoVenda.addActionListener(e -> {
            if (cadastroVendaGUI == null) {
                cadastroVendaGUI = new CadastroVendaGUI();

                cadastroVendaGUI.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        cadastroVendaGUI = null;
                    }
                });
            } else {
                cadastroVendaGUI.toFront();
                cadastroVendaGUI.requestFocus();
            }
            cadastroVendaGUI.setVisible(true);
        });

            // Relatorios
        botaoRelatorioFornecedor.addActionListener(e -> abrirRelatorio("Relatório de Fornecedores"));
        botaoRelatorioTecnologia.addActionListener(e -> abrirRelatorio("Relatório de Tecnologias"));
        botaoRelatorioComprador.addActionListener(e -> abrirRelatorio("Relatório de Compradores"));
        botaoRelatorioVenda.addActionListener(e -> abrirRelatorio("Relatório de Vendas"));

            // Remover/Alterar/Consultar
        botaoRemoverVenda.addActionListener(e -> new RemoverVendaGUI());
        botaoAlterarComprador.addActionListener(e -> new AlterarCompradorGUI());
        botaoConsultaMaior.addActionListener(e -> new ConsultaMaiorGUI());

            // Salvar e Carregar
        botaoSalvar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Salvar dados ainda precisa ser implementado!"));        // TODO
        botaoCarregar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Carregar dados ainda precisa ser implementado!"));    // TODO

            // Encerrar
        botaoSair.addActionListener(e -> System.exit(0));
    }

    private void abrirRelatorio(String tituloJanela) {

        if (relatoriosGUI == null) {
            relatoriosGUI = new RelatoriosGUI();

            relatoriosGUI.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    relatoriosGUI = null;
                }
            });
        }

        String resultado = switch (tituloJanela){
        // Determina qual relatório vai fazer
        case "Relatório de Fornecedores" -> ACMETech.gerarRelatorioFornecedores();
        case "Relatório de Tecnologias" -> ACMETech.gerarRelatorioTecnologias();
        case "Relatório de Compradores" -> ACMETech.gerarRelatorioCompradores();
        case "Relatório de Vendas" -> ACMETech.gerarRelatorioVendas();
        default -> "Relatório inválido";
        };


        relatoriosGUI.setTitle(tituloJanela);
        relatoriosGUI.setConteudo(resultado);
        relatoriosGUI.setVisible(true);
        relatoriosGUI.toFront();
    }
}
