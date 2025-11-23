package gui;

import javax.swing.*;

public class RelatoriosGUI extends JFrame {
    private JTextArea areaTexto;

    public RelatoriosGUI() {
        super("Relatórios");
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
