package ui.Panels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import interfaces.ControllerFactory;
import interfaces.InstituteInterface;

import java.awt.Label;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

public class AddInstitutePanel extends JPanel {

	private JTextField textField;
	private JTextField descriptionTextField;
	private JTextField urlTextField;
	private JButton btnConfirm;
	private InstituteInterface instituteController = ControllerFactory.getInstance().getInstituteInterface();

	/**
	 * Create the panel.
	 */
	public AddInstitutePanel() {
		setGridLayout();
		setTitleLabel("Alta de institucion deportiva", "Calibri", Font.BOLD, 18);
		setButtons();
		addButtonListeners();
	}

	private void addButtonListeners() {
		// Confirm button events
		btnConfirm.addActionListener(e -> {
			String insertedName = textField.getText();
			String insertedDescription = descriptionTextField.getText();
			String insertedUrl = urlTextField.getText();
			if (insertedName.equals("")  || insertedDescription.equals("") || insertedUrl.equals("")) {
				displayWindow("Error de ingreso", "Por favor rellene todos los campos.", JOptionPane.ERROR_MESSAGE);
			} else if (!instituteController.checkInstitutionAvialability(insertedName)) {
				displayWindow("Error de ingreso", "Ya existe un instituto con el nombre ingresado.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				// Register institution
				instituteController.registerInstitution(insertedName, insertedDescription, insertedUrl);
				displayWindow("Éxito", "Se ha creado la institucion deportiva con éxito.", JOptionPane.INFORMATION_MESSAGE);
				resetForm();
			}

		});

	}

	private void setButtons() {

	}

	private void setTitleLabel(String title, String fontName, int fontType, int fontSize) {
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font(fontName, fontType, fontSize));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.SOUTH;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 2;
		gbc_titleLabel.gridy = 3;
		gbc_titleLabel.gridwidth = 10;
		gbc_titleLabel.gridx = 0;
		add(titleLabel, gbc_titleLabel);
	}

	private void displayWindow(String titleLabel, String message, int messageType) {
		JOptionPane.showMessageDialog(this, message, titleLabel, messageType);
	}

	private void resetForm() {
		Component[] components = this.getComponents();
		for (Component component : components) {
			if (component instanceof JTextField) {
				((JTextField) component).setText("");
			} else if (component instanceof JTextPane) {
				((JTextPane) component).setText("");
			}
		}
	}

	private void setGridLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 20, 0, 20, 20, 20, 20, 20, 20, 20 };
		gridBagLayout.rowHeights = new int[] { 20, 20, 0, 0, 0, 20, 0, 0, 0, 0, 0, 20, 0, 0, 0, 20, 20, 0, 20, 20 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				0.0, 1.0, 1.0, 0.0, 1.0, 1.0 };
		setLayout(gridBagLayout);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 10;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		add(separator, gbc_separator);

		Label label = new Label("Nombre");
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridheight = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 7;
		add(label, gbc_label);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridheight = 2;
		gbc_textField.gridwidth = 5;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 7;
		add(textField, gbc_textField);
		textField.setColumns(10);

		Label label_1 = new Label("Descripcion");
		label_1.setFont(new Font("Dialog", Font.BOLD, 12));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 9;
		add(label_1, gbc_label_1);

		descriptionTextField = new JTextField();
		GridBagConstraints gbc_descriptionTextField = new GridBagConstraints();
		gbc_descriptionTextField.gridwidth = 5;
		gbc_descriptionTextField.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_descriptionTextField.gridx = 3;
		gbc_descriptionTextField.gridy = 9;
		add(descriptionTextField, gbc_descriptionTextField);
		descriptionTextField.setColumns(10);

		Label label_2 = new Label("Url");
		label_2.setFont(new Font("Dialog", Font.BOLD, 12));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 2;
		gbc_label_2.gridy = 10;
		add(label_2, gbc_label_2);

		urlTextField = new JTextField();
		urlTextField.setColumns(10);
		GridBagConstraints gbc_urlTextField = new GridBagConstraints();
		gbc_urlTextField.gridwidth = 5;
		gbc_urlTextField.insets = new Insets(0, 0, 5, 5);
		gbc_urlTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_urlTextField.gridx = 3;
		gbc_urlTextField.gridy = 10;
		add(urlTextField, gbc_urlTextField);
		btnConfirm = new JButton("Confirmar");
		btnConfirm.setToolTipText("Confirmar alta");
		btnConfirm.setForeground(new Color(0, 0, 0));
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConfirm.setBackground(new Color(0, 255, 64));
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfirm.gridx = 4;
		gbc_btnConfirm.gridy = 13;
		add(btnConfirm, gbc_btnConfirm);

		JSeparator separatorDate = new JSeparator();
		GridBagConstraints gbc_separatorDate = new GridBagConstraints();
		gbc_separatorDate.gridwidth = 6;
		gbc_separatorDate.insets = new Insets(0, 0, 5, 5);
		gbc_separatorDate.gridx = 0;
		gbc_separatorDate.gridy = 14;
		add(separatorDate, gbc_separatorDate);

	}

}
