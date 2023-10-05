package ui.Panels;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel(){
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{5, 66, 37, 68, 0, 52, -62};
        gridBagLayout.rowHeights = new int[]{50, 38, 38, 0, 10, 80, 53, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE, 0.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel lblNewLabel = new JLabel("!Bienvenido administrador!");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 4;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 0;
        add(lblNewLabel, gbc_lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("<html>Para poder acceder a las funcionalidades de esta aplicacion debe seleccionar la accion a realizar en la barra lateral.<br><h4>Recuerde tener cuidado con las acciones que realiza, las mismas tendran un efecto permanente en la organizacion<h4></html>");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTH;
        gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel_1.gridheight = 4;
        gbc_lblNewLabel_1.gridwidth = 4;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 2;
        add(lblNewLabel_1, gbc_lblNewLabel_1);
    }

}
