package ui.Panels;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

import dataTypes.DtInstitute;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTextPane;

public class AddSportActivityPanel extends JPanel{
	private JComboBox<String> instituteComboBox = new JComboBox<String>();
	private JButton btnConfirm;
	private JButton btnCancel;

	private final String nonSelectedOption			= "Sin seleccionar"; 
	private String selectedInstitute 				= nonSelectedOption;
	
	private Map<String, DtInstitute> institutes = new TreeMap<String, DtInstitute>();
	
	private InstituteInterface instituteController 	= ControllerFactory.getInstance().getInstituteInterface();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public AddSportActivityPanel(){
		setGridLayout();
		setTitleLabel("Alta de actividad deportiva", "Calibri", Font.BOLD, 18);
		setInstituteLabelCombo();
		setButtonPanel();
	}
	
	private void setInstituteLabelCombo() {
		JLabel instituteLabel = new JLabel("Seleccionar instituto");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(instituteLabel, gbc_lblNewLabel);
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 4;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 1;
		add(instituteComboBox, gbc_comboBox);
		instituteComboBox.addItem(nonSelectedOption);
		instituteComboBox.setSelectedItem(nonSelectedOption);
		institutes = instituteController.listSportInstitutes();
		if(institutes != null) {
			for(String value : institutes.keySet()) {
				instituteComboBox.addItem(value);
			}
		}
		
	}
	
	private void setButtonPanel() {
		
		JLabel lblNewLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("✓");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 6;
		gbc_btnNewButton_1.gridy = 3;
		add(btnNewButton, gbc_btnNewButton_1);
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 5;
		gbc_btnNewButton_1.gridy = 10;
		
		JLabel lblDuracin = new JLabel("Duración");
		GridBagConstraints gbc_lblDuracin = new GridBagConstraints();
		gbc_lblDuracin.anchor = GridBagConstraints.EAST;
		gbc_lblDuracin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracin.gridx = 1;
		gbc_lblDuracin.gridy = 4;
		add(lblDuracin, gbc_lblDuracin);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 4;
		add(textField_1, gbc_textField_1);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.gridwidth = 3;
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 5;
		gbc_lblDescripcin.gridy = 4;
		add(lblDescripcin, gbc_lblDescripcin);
		
		JLabel lblNewLabel_3 = new JLabel("Costo");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 5;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 5;
		add(textField_2, gbc_textField_2);
		
		JTextPane textPane = new JTextPane();
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.gridheight = 4;
		gbc_textPane.gridwidth = 3;
		gbc_textPane.insets = new Insets(0, 0, 5, 5);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 5;
		gbc_textPane.gridy = 5;
		add(textPane, gbc_textPane);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 5;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 6;
		add(separator, gbc_separator);
		
		JLabel lblFechaDeInscripcin = new JLabel("Fecha de inscripción");
		GridBagConstraints gbc_lblFechaDeInscripcin = new GridBagConstraints();
		gbc_lblFechaDeInscripcin.gridwidth = 3;
		gbc_lblFechaDeInscripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeInscripcin.gridx = 1;
		gbc_lblFechaDeInscripcin.gridy = 7;
		add(lblFechaDeInscripcin, gbc_lblFechaDeInscripcin);
		
		JSpinner spinner = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 8;
		add(spinner, gbc_spinner);
		
		JSpinner spinner_1 = new JSpinner();
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_1.gridx = 2;
		gbc_spinner_1.gridy = 8;
		add(spinner_1, gbc_spinner_1);
		
		JSpinner spinner_1_1 = new JSpinner();
		GridBagConstraints gbc_spinner_1_1 = new GridBagConstraints();
		gbc_spinner_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_1_1.gridx = 3;
		gbc_spinner_1_1.gridy = 8;
		add(spinner_1_1, gbc_spinner_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Día");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 9;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mes");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 2;
		gbc_lblNewLabel_1_1.gridy = 9;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Año");
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 3;
		gbc_lblNewLabel_1_2.gridy = 9;
		add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		btnConfirm = new JButton("Confirmar");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Confirm add
			}
		});
		btnConfirm.setBackground(Color.GREEN);
		btnConfirm.setForeground(Color.WHITE);
		
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4.gridx = 5;
		gbc_btnNewButton_4.gridy = 10;
		add(btnConfirm, gbc_btnNewButton_4);
		
		btnConfirm.setEnabled(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setBackground(Color.RED);
		btnCancel.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnConfirm_1 = new GridBagConstraints();
		gbc_btnConfirm_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnConfirm_1.gridwidth = 2;
		gbc_btnConfirm_1.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnConfirm_1.gridx = 6;
		gbc_btnConfirm_1.gridy = 10;
		add(btnCancel, gbc_btnConfirm_1);
		
	}
	
	private void setGridLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 20, 20, 20, 20, 20, 20, 20, 20};
		gridBagLayout.rowHeights = new int[]{20, 20, 0, 0, 20, 20, 0, 20, 20, 20, 20};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0};
		setLayout(gridBagLayout);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 9;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		add(separator, gbc_separator);
		
		
	}
	
	private void setTitleLabel(String title, String fontName, int fontType, int fontSize) {
		
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font(fontName, fontType, fontSize));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.SOUTH;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 0;
		gbc_titleLabel.gridwidth = 9;
		gbc_titleLabel.gridx = 0;
		add(titleLabel, gbc_titleLabel);
	}
}
