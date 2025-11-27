package gui;

import entidades.Comprador;
import entidades.Tecnologia;

import javax.swing.*;
import java.awt.*;

public class ACMERender extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Tecnologia){
            Tecnologia tecnologia = (Tecnologia) value;
            setText(tecnologia.getModelo());
        } else if (value instanceof Comprador){
            Comprador comprador = (Comprador) value;
            setText(comprador.getNome());
        }

        return this;
    }
}
