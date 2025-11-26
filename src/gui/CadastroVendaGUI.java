package gui;

import aplicacao.ACMETech;

import javax.swing.*;

public class CadastroVendaGUI extends JFrame {
    private JTextArea areaMensagens;
    private ACMETech app;

    public CadastroVendaGUI(ACMETech app){
        super("Cadastro de Venda");
        this.app = app;

        areaMensagens = new JTextArea(10, 50);
        areaMensagens.setEditable(false);
        JScrollPane scrollMensagens = new JScrollPane(areaMensagens);
        scrollMensagens.setBorder(BorderFactory.createTitledBorder("Área de Logs e Cadastros"));
    }
}
