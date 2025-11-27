package gui;

import aplicacao.ACMETech;
import entidades.Comprador;
import entidades.Relatorio;
import entidades.Tecnologia;
import entidades.Venda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class CadastroVendaGUI extends JFrame {

    private JTextField campoNum, campoData;
    private JComboBox<Tecnologia> campoCod;
    private JComboBox<Comprador> campoId;
    private JTextArea areaLog;
    private JButton botaoGravar, botaoLimpar, botaoListar, botaoSair;
    private ACMETech app;

    public CadastroVendaGUI(ACMETech app) {
        super("Cadastro de Vendas");
        this.app = app;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 550);
        setLayout(new BorderLayout(10, 10));

        JPanel painelFormulario = montarPainelFormulario();

        areaLog = new JTextArea(14, 40);
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
        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        campoNum = new JTextField(25);
        campoData = new JTextField(25);
        campoCod = new JComboBox<>(app.getTecnologias().toArray(new Tecnologia[0]));
        ACMERender render = new ACMERender();
        campoCod.setRenderer(render);
        campoId = new JComboBox<>(app.getCompradores().toArray(new Comprador[0]));
        campoId.setRenderer(render);

        painel.add(new JLabel("Número da Venda:"));
        painel.add(campoNum);

        painel.add(new JLabel("Data (dd/MM/yyyy):"));
        painel.add(campoData);

        painel.add(new JLabel("Tecnologia:"));
        painel.add(campoCod);

        painel.add(new JLabel("Comprador:"));
        painel.add(campoId);


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
        botaoSair.addActionListener(e -> dispose());

        painel.add(botaoGravar);
        painel.add(botaoLimpar);
        painel.add(botaoListar);
        painel.add(botaoSair);

        return painel;
    }

    private void executarCadastro(ActionEvent e) {
        areaLog.setText("");

        try {
            long num = Long.parseLong(campoNum.getText().trim());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataLida = campoData.getText().trim();
            Date data = sdf.parse(dataLida);
            Tecnologia t = (Tecnologia) campoCod.getSelectedItem();
            Comprador c = (Comprador) campoId.getSelectedItem();
            if (dataLida.isEmpty()) {
                areaLog.setText("Erro: A data deve ser preenchida.");
                return;
            }
            if (t == null || c == null) {
                areaLog.setText("Erro: Comprador e tecnologia devem ser selecionados.");
                return;
            }
            if (app.cadastrarVenda(num, data, c.getCod(), t.getId())) {
                areaLog.setText("Venda cadastrada com sucesso!\n");
                campoNum.setText("");
                campoData.setText("");
            } else {
                areaLog.setText("Erro: código já cadastrado! \n");
            }
        } catch (NumberFormatException ex) {
            areaLog.setText("Erro: Número, códigos e quantidade devem ser valores numéricos inteiros.");
        } catch (ParseException ex){
            areaLog.append("Erro: data inválida! Use o formato dd/MM/yyyy.\n");
        } catch (Exception ex) {
            areaLog.setText("Erro ao cadastrar venda: " + ex.getMessage());
        }
    }

    private void resetarCampos(ActionEvent e) {
        campoNum.setText("");
        campoData.setText("");
        areaLog.setText("");
        campoNum.requestFocus();
    }

    private void apresentarRelatorio(ActionEvent e) {
        areaLog.setText("");

        Set<Venda> vendas = app.getVendas();

        if (vendas.isEmpty()) {
            areaLog.setText("Não há vendas cadastradas no sistema.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- Relatório de Vendas (Ordenado por Número) ---\n");

        for (Venda v : vendas) {
            sb.append(v.geraDescricao()).append("\n");
        }

        sb.append("---------------------------------------------------------");
        areaLog.setText(sb.toString());
    }
}

