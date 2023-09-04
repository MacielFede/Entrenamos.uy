package ui.Panels;

import dataTypes.DtUser;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;
import interfaces.UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.*;

public class RegistrationToClassPanel  extends JPanel {
	private final UserInterface uc = ControllerFactory.getInstance().getUserInterface();
	private final InstituteInterface ic = ControllerFactory.getInstance().getInstituteInterface();
	private JButton btnConfirm;
	private JComboBox<String> institutesComboBox 	= new JComboBox<String>();
	private JComboBox<String> activitiesComboBox 	= new JComboBox<String>();
	private JComboBox<String> classesComboBox		= new JComboBox<String>();
	private JComboBox<String> usersComboBox			= new JComboBox<String>();

	private final String nonSelectedOption			= "Sin seleccionar";
	
	public RegistrationToClassPanel(){
		setGridLayout();
		setTitleLabel("Registro a dictado de clase", "Calibri", Font.BOLD, 18);
		institutesComboBox 	= createLabelComboBox("Instituto", 2);
		activitiesComboBox	= createLabelComboBox("Actividad deportiva", 3);
		classesComboBox 	= createLabelComboBox("Clase", 4);
		usersComboBox 		= createLabelComboBox("Usuario", 5);

		btnConfirm = new JButton("Confirmar");
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		btnConfirm.setBackground(Color.GREEN);
		btnConfirm.setForeground(Color.BLACK);
		gbc_btnConfirm.gridwidth = 3;
		gbc_btnConfirm.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfirm.gridx = 3;
		gbc_btnConfirm.gridy = 8;
		btnConfirm.setEnabled(false);
		btnConfirm.addActionListener(e -> {
			confirm();
		});
		add(btnConfirm, gbc_btnConfirm);
		activitiesComboBox.setEnabled(false);
		classesComboBox.setEnabled(false);
		usersComboBox.addItem(nonSelectedOption);
		institutesComboBox.addItem(nonSelectedOption);
		addItemsToComboBox(usersComboBox, Set.of(uc.listMembersByNickname()));
		addItemsToComboBox(institutesComboBox, ic.listSportInstitutes().keySet());
		institutesComboBox.addActionListener(e -> {
			chooseInstitute();
		});
		activitiesComboBox.addActionListener(e -> {
			chooseActivity();
		});
		classesComboBox.addActionListener(e -> {
			chooseClass();
		});
		usersComboBox.addActionListener(e -> {
			chooseUser();
		});
	}

	private void addItemsToComboBox(JComboBox<String> comboBox, Set<String> values) {
		for(String value : values) {
			comboBox.addItem(value);
		}
		comboBox.setEnabled(true);
	}
	
	private void setGridLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 20, 20, 20, 20, 20, 20, 20, 20};
		gridBagLayout.rowHeights = new int[]{20, 20, 20, 20, 20, 20, 0, 20, 20, 20, 20};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0};
		setLayout(gridBagLayout);
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
	
	private JComboBox<String> createLabelComboBox(String labelTitle, int gridy) {
		JLabel genericLabel = new JLabel(labelTitle);
		GridBagConstraints gbc_Label = new GridBagConstraints();
		gbc_Label.gridwidth = 2;
		gbc_Label.anchor = GridBagConstraints.EAST;
		gbc_Label.insets = new Insets(0, 0, 5, 5);
		gbc_Label.gridx = 1;
		gbc_Label.gridy = gridy;
		add(genericLabel, gbc_Label);
		
		JComboBox<String> genericComboBox = new JComboBox<String>();
		GridBagConstraints gbc_institutesComboBox = new GridBagConstraints();
		gbc_institutesComboBox.gridwidth = 4;
		gbc_institutesComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_institutesComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_institutesComboBox.gridx = 3;
		gbc_institutesComboBox.gridy = gridy;
		add(genericComboBox, gbc_institutesComboBox);
		
		return genericComboBox;
	}
	
	private void resetComboBox(JComboBox<String> comboBox) {
		if (comboBox.getModel().getSize() > 0){
			comboBox.removeAllItems();
		}
		comboBox.addItem(nonSelectedOption);
		comboBox.setEnabled(false);
	}

	private void chooseInstitute(){
		if (institutesComboBox.getSelectedItem() == null || Objects.equals(institutesComboBox.getSelectedItem(), nonSelectedOption)) {
			resetComboBox(activitiesComboBox);
			resetComboBox(classesComboBox);
		} else {
			resetComboBox(activitiesComboBox);
			addItemsToComboBox(activitiesComboBox, ic.selectInstitution(institutesComboBox.getSelectedItem().toString()).keySet());
			resetComboBox(classesComboBox);
		}
		btnConfirm.setEnabled(false);
	}

	private void chooseActivity(){
		if (activitiesComboBox.getSelectedItem() == null || Objects.equals(activitiesComboBox.getSelectedItem(), nonSelectedOption)) {
			resetComboBox(classesComboBox);
		} else {
			resetComboBox(classesComboBox);
			addItemsToComboBox(classesComboBox, ic.chooseActivity(activitiesComboBox.getSelectedItem().toString()).keySet());
		}
		btnConfirm.setEnabled(false);
	}

	private void chooseClass(){
		chooseUser();
	}

	private void chooseUser(){
		btnConfirm.setEnabled(!Objects.equals(usersComboBox.getSelectedItem(), nonSelectedOption) && !Objects.equals(classesComboBox.getSelectedItem(), nonSelectedOption));
	}

	private void confirm(){
		String selectedClass = (String) classesComboBox.getSelectedItem();
		String selectedUser = (String) usersComboBox.getSelectedItem();
		Float price = ic.getActivity((String) activitiesComboBox.getSelectedItem()).getPrice();
		DtUser user = uc.chooseUser(selectedUser);
		try{
			btnConfirm.setEnabled(false);
			uc.addEnrollment(selectedClass, user, price);
			JOptionPane.showMessageDialog(this, "El registro se a completado con éxito!",
					"Modificar información de usuario", JOptionPane.INFORMATION_MESSAGE);
			resetComboBox(activitiesComboBox);
			resetComboBox(classesComboBox);
			usersComboBox.setSelectedIndex(0);
			institutesComboBox.setSelectedIndex(0);
		} catch (Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Modificar información de usuario", JOptionPane.ERROR_MESSAGE);
			btnConfirm.setEnabled(true);
		}
	}
}
