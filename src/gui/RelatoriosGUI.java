package gui;

import aplicacao.ACMETech;

import javax.swing.*;

public class RelatoriosGUI extends JFrame {
    private JTextArea areaTexto;
    private ACMETech app;

    public RelatoriosGUI(ACMETech app) {
        super("Relatórios");
        this.app = app;
        setSize(600, 400);
        setLocationRelativeTo(null);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        add(new JScrollPane(areaTexto));
    }

    public void setConteudo(String texto) {
        areaTexto.setText(texto);
    }
}
