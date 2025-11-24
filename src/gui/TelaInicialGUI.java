package gui;

import aplicacao.ACMETech;
import entidades.Relatorio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;

public class TelaInicialGUI extends JFrame {
    private ACMETech app;
    private CadastroFornecedorGUI cadastroFornecedorGUI;
    private CadastroTecnologiaGUI cadastroTecnologiaGUI;
    private CadastroCompradorGUI cadastroCompradorGUI;
    private CadastroVendaGUI cadastroVendaGUI;
    private RelatoriosGUI relatoriosGUI;
    private RemoverVendaGUI removerVendaGUI;
    private AlterarCompradorGUI alterarCompradorGUI;
    private ConsultaMaiorGUI consultaMaiorGUI;
    private Clip clipMusica;

    public TelaInicialGUI(ACMETech app) {
        super("ACMETech - Venda de tecnologias");
        this.app = app;

        JLabel bannerAnimado = new JLabel(new ImageIcon("src/recursos/ACME-animacao.gif"));
        bannerAnimado.setPreferredSize(new Dimension(400, 270));

        ImageIcon fotoProf = new ImageIcon("src/recursos/Marcelo_Yamaguti2.jpeg");
        Image img = fotoProf.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel labelFoto = new JLabel(new ImageIcon(img));

        ImageIcon fotoProf2 = new ImageIcon("src/recursos/Marcelo_Yamaguti.jpg");
        Image img2 = fotoProf2.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel labelEsquerda = new JLabel(new ImageIcon(img2));


        setSize(1200, 800);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Botoes Cadastros
        JButton botaoFornecedor = new JButton("Cadastrar Fornecedor");
        JButton botaoTecnologia = new JButton("Cadastrar Tecnologia");
        JButton botaoComprador = new JButton("Cadastrar Comprador");
        JButton botaoVenda = new JButton("Cadastrar Venda");

        // Botoes Relatorios
        JButton botaoRelatorioFornecedor = new JButton("Mostrar relatório de fornecedores");
        JButton botaoRelatorioTecnologia = new JButton("Mostrar relatório de tecnologias");
        JButton botaoRelatorioComprador = new JButton("Mostrar relatório de compradores");
        JButton botaoRelatorioVenda = new JButton("Mostrar relatório de vendas");

        // Botoes Adicionais
        JButton botaoRemoverVenda = new JButton("Remover dados de uma venda");
        JButton botaoAlterarComprador = new JButton("Alterar dados de um comprador");

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
            // Painel inferior
        JPanel painelInferior = new JPanel(new GridBagLayout());
        painelInferior.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 20);
        painelInferior.add(labelEsquerda, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        painelInferior.add(botaoSair, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 20, 0, 0);
        painelInferior.add(labelFoto, gbc);

        // Adiciona tudo no Painel Principal
        painelPrincipal.add(painelCadastros);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelRelatorios);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelOperacoes);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelArquivos);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(painelInferior);

        // Adiciona o bannerAnimado no JFrame
        add(bannerAnimado, BorderLayout.NORTH);

        // Adiciona o painelPrincipal no JFrame
        add(painelPrincipal, BorderLayout.CENTER);

        // ActionListeners > Ações ao clicar nos botões
            // Cadastros
        botaoFornecedor.addActionListener(e -> {
            if (cadastroFornecedorGUI == null) {
                cadastroFornecedorGUI = new CadastroFornecedorGUI(app);

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
                cadastroVendaGUI = new CadastroVendaGUI(app);

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
        botaoRelatorioFornecedor.addActionListener(e -> abrirRelatorio("Relatório de Fornecedores", Relatorio.FORNECEDOR));
        botaoRelatorioTecnologia.addActionListener(e -> abrirRelatorio("Relatório de Tecnologias", Relatorio.TECNOLOGIA));
        botaoRelatorioComprador.addActionListener(e -> abrirRelatorio("Relatório de Compradores", Relatorio.COMPRADOR));
        botaoRelatorioVenda.addActionListener(e -> abrirRelatorio("Relatório de Vendas", Relatorio.VENDA));

            // Remover/Alterar/Consultar
        botaoRemoverVenda.addActionListener(e -> {
            if (removerVendaGUI == null) {
                removerVendaGUI = new RemoverVendaGUI();

                removerVendaGUI.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        removerVendaGUI = null;
                    }
                });
            } else {
                removerVendaGUI.toFront();
                removerVendaGUI.requestFocus();
            }
            removerVendaGUI.setVisible(true);
        });
        botaoAlterarComprador.addActionListener(e -> {
            if (alterarCompradorGUI == null) {
                alterarCompradorGUI = new AlterarCompradorGUI();

                alterarCompradorGUI.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        alterarCompradorGUI = null;
                    }
                });
            } else {
                alterarCompradorGUI.toFront();
                alterarCompradorGUI.requestFocus();
            }
            alterarCompradorGUI.setVisible(true);
        });
        botaoConsultaMaior.addActionListener(e -> {
            if (consultaMaiorGUI == null) {
                consultaMaiorGUI = new ConsultaMaiorGUI();

                consultaMaiorGUI.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        consultaMaiorGUI = null;
                    }
                });
            } else {
                consultaMaiorGUI.toFront();
                consultaMaiorGUI.requestFocus();
            }
            consultaMaiorGUI.setVisible(true);
        });

            // Salvar e Carregar
        botaoSalvar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Salvar dados ainda precisa ser implementado!"));        // TODO
        botaoCarregar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Carregar dados ainda precisa ser implementado!"));    // TODO

            // Encerrar
        botaoSair.addActionListener(e -> System.exit(0));

        tocarMusica("src/recursos/Background_calm.wav");
    }

    private void abrirRelatorio(String tituloJanela, Relatorio relatorio) {
        if (relatoriosGUI == null) {
            relatoriosGUI = new RelatoriosGUI(app);

            relatoriosGUI.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    relatoriosGUI = null;
                }
            });
        }

        String resultado = app.gerarRelatorio(relatorio);

        relatoriosGUI.setTitle(tituloJanela);
        relatoriosGUI.setConteudo(resultado);
        relatoriosGUI.setVisible(true);
        relatoriosGUI.toFront();
    }


    private void tocarMusica(String pathMusica) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new java.io.File(pathMusica));
            clipMusica = AudioSystem.getClip();
            clipMusica.open(audio);
            FloatControl volume = (FloatControl) clipMusica.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-20.0f);
            clipMusica.loop(Clip.LOOP_CONTINUOUSLY);
            clipMusica.start();
        } catch (Exception e) {
            System.out.println("Erro ao tocar música: " + e.getMessage());
        }
    }
}
