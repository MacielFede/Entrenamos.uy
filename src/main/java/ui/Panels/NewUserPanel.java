package ui.Panels;

import dataTypes.DtInstitute;
import dataTypes.DtProfessor;
import dataTypes.DtUser;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;
import interfaces.UserInterface;
//package eclipseHDP;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class NewUserPanel extends JPanel {
	private JTextField nicknameTextField;
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JTextField lastnameTextField;
	private JComboBox<Integer> dayComboBox;
	private JComboBox<Integer> monthComboBox;
	private JComboBox<Integer> yearComboBox;
	private JTextArea descriptionTextArea;
	private JTextArea biographyTextArea;
	private JTextField webPageTextField;
	private JCheckBox checkBoxIsTeacher;
	private JLabel lblNickname;
	private JLabel lblEmail;
	private JLabel lblName;
	private JLabel lblLastname;
	private JLabel lblDescription;
	private JLabel lblBiography;
	private JLabel lblWebPage;
	private JLabel lblBirthDate;
	private JButton saveButton;
	private JLabel lblNewLabel;
	private JComboBox instituteComboBox;

	private final UserInterface uc = ControllerFactory.getInstance().getUserInterface();
	private final InstituteInterface ic = ControllerFactory.getInstance().getInstituteInterface();

	/**
	 * Create the panel.
	 */
	public NewUserPanel() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{49, 114, 212, 114, 0};
		gridBagLayout.rowHeights = new int[]{19, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 59, 122, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		lblNickname = new JLabel("Nickname");
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.anchor = GridBagConstraints.EAST;
		gbc_lblNickname.gridx = 1;
		gbc_lblNickname.gridy = 2;
		add(lblNickname, gbc_lblNickname);

		nicknameTextField = new JTextField();
		nicknameTextField.setText("JoeDoe");
		GridBagConstraints gbc_nicknameTextField = new GridBagConstraints();
		gbc_nicknameTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_nicknameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nicknameTextField.gridx = 2;
		gbc_nicknameTextField.gridy = 2;
		add(nicknameTextField, gbc_nicknameTextField);
		nicknameTextField.setColumns(10);

		lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 3;
		add(lblEmail, gbc_lblEmail);

		emailTextField = new JTextField();
		emailTextField.setText("JoeDoe@gmail.com");
		emailTextField.setColumns(10);
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.anchor = GridBagConstraints.NORTH;
		gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_emailTextField.gridx = 2;
		gbc_emailTextField.gridy = 3;
		add(emailTextField, gbc_emailTextField);

		lblName = new JLabel("Nombre");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 4;
		add(lblName, gbc_lblName);

		nameTextField = new JTextField();
		nameTextField.setText("Joe");
		nameTextField.setColumns(10);
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nameTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_nameTextField.gridx = 2;
		gbc_nameTextField.gridy = 4;
		add(nameTextField, gbc_nameTextField);

		lblLastname = new JLabel("Apellido");
		GridBagConstraints gbc_lblLastname = new GridBagConstraints();
		gbc_lblLastname.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastname.anchor = GridBagConstraints.EAST;
		gbc_lblLastname.gridx = 1;
		gbc_lblLastname.gridy = 5;
		add(lblLastname, gbc_lblLastname);

		lastnameTextField = new JTextField();
		lastnameTextField.setText("Doe");
		lastnameTextField.setColumns(10);
		GridBagConstraints gbc_lastnameTextField = new GridBagConstraints();
		gbc_lastnameTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_lastnameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_lastnameTextField.gridx = 2;
		gbc_lastnameTextField.gridy = 5;
		add(lastnameTextField, gbc_lastnameTextField);

		lblBirthDate = new JLabel("Fecha de nacimiento");
		GridBagConstraints gbc_lblBirthDate = new GridBagConstraints();
		gbc_lblBirthDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirthDate.anchor = GridBagConstraints.EAST;
		gbc_lblBirthDate.gridx = 0;
		gbc_lblBirthDate.gridy = 6;
		add(lblBirthDate, gbc_lblBirthDate);

		dayComboBox = new JComboBox<>(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31});
		GridBagConstraints gbc_dayComboBox = new GridBagConstraints();
		gbc_dayComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_dayComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_dayComboBox.gridx = 1;
		gbc_dayComboBox.gridy = 6;
		add(dayComboBox, gbc_dayComboBox);

		monthComboBox = new JComboBox<>(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});
		GridBagConstraints gbc_monthComboBox = new GridBagConstraints();
		gbc_monthComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_monthComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_monthComboBox.gridx = 2;
		gbc_monthComboBox.gridy = 6;
		add(monthComboBox, gbc_monthComboBox);

		//CARGAR ANIOS
		Integer[] years = new Integer[100];
		for (int i = 0; i < 100; i++) {
			years[i] = LocalDate.now().getYear() - i;
		}
		yearComboBox = new JComboBox<>(years);
		GridBagConstraints gbc_yearComboBox = new GridBagConstraints();
		gbc_yearComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_yearComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_yearComboBox.gridx = 3;
		gbc_yearComboBox.gridy = 6;
		add(yearComboBox, gbc_yearComboBox);

		checkBoxIsTeacher = new JCheckBox("Es profesor?");
		GridBagConstraints gbc_checkBoxIsTeacher = new GridBagConstraints();
		gbc_checkBoxIsTeacher.insets = new Insets(0, 0, 5, 5);
		gbc_checkBoxIsTeacher.gridx = 2;
		gbc_checkBoxIsTeacher.gridy = 7;
		add(checkBoxIsTeacher, gbc_checkBoxIsTeacher);
		checkBoxIsTeacher.addActionListener(e -> {
			changeCheckBoxState();
		});

		lblDescription = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 1;
		gbc_lblDescription.gridy = 8;
		add(lblDescription, gbc_lblDescription);

		descriptionTextArea = new JTextArea();
		descriptionTextArea.setEnabled(false);
		descriptionTextArea.setText("Hello world!!");
		GridBagConstraints gbc_descriptionTextArea = new GridBagConstraints();
		gbc_descriptionTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionTextArea.fill = GridBagConstraints.BOTH;
		gbc_descriptionTextArea.gridx = 2;
		gbc_descriptionTextArea.gridy = 8;
		add(descriptionTextArea, gbc_descriptionTextArea);

		lblBiography = new JLabel("Biografia");
		GridBagConstraints gbc_lblBiography = new GridBagConstraints();
		gbc_lblBiography.insets = new Insets(0, 0, 5, 5);
		gbc_lblBiography.gridx = 1;
		gbc_lblBiography.gridy = 9;
		add(lblBiography, gbc_lblBiography);

		biographyTextArea = new JTextArea();
		biographyTextArea.setEnabled(false);
		biographyTextArea.setText("Hi, i'm Joe Doe. A developer...");
		GridBagConstraints gbc_biographyTextArea = new GridBagConstraints();
		gbc_biographyTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_biographyTextArea.fill = GridBagConstraints.BOTH;
		gbc_biographyTextArea.gridx = 2;
		gbc_biographyTextArea.gridy = 9;
		add(biographyTextArea, gbc_biographyTextArea);

		lblWebPage = new JLabel("Pagina web");
		GridBagConstraints gbc_lblWebPage = new GridBagConstraints();
		gbc_lblWebPage.insets = new Insets(0, 0, 5, 5);
		gbc_lblWebPage.gridx = 1;
		gbc_lblWebPage.gridy = 10;
		add(lblWebPage, gbc_lblWebPage);

		webPageTextField = new JTextField();
		webPageTextField.setEnabled(false);
		webPageTextField.setText("www.JoeDoe.com");
		webPageTextField.setColumns(10);
		GridBagConstraints gbc_webPageTextField = new GridBagConstraints();
		gbc_webPageTextField.insets = new Insets(0, 0, 5, 5);
		gbc_webPageTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_webPageTextField.gridx = 2;
		gbc_webPageTextField.gridy = 10;
		add(webPageTextField, gbc_webPageTextField);

		lblNewLabel = new JLabel("Instituto");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 11;
		add(lblNewLabel, gbc_lblNewLabel);

		instituteComboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 11;
		instituteComboBox.setEnabled(false);
		add(instituteComboBox, gbc_comboBox);

		fetchInstituteList();

		saveButton = new JButton("Confirmar");
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.insets = new Insets(0, 0, 0, 5);
		gbc_saveButton.gridx = 3;
		gbc_saveButton.gridy = 12;
		add(saveButton, gbc_saveButton);
		saveButton.addActionListener(e -> {
			addNewUser();
		});
	}

	public void fetchInstituteList() {
		instituteComboBox.addItem("Sin seleccionar");
		Map<String, DtInstitute> institutesList = ic.listSportInstitutes();
		for (Map.Entry<String, DtInstitute> entry : institutesList.entrySet()) {
			String key = entry.getKey();
			DtInstitute dt = entry.getValue();
			instituteComboBox.addItem(dt.getName());
		}
	}

	private void changeCheckBoxState() {
		if(checkBoxIsTeacher.isSelected()){
			descriptionTextArea.setEnabled(true);
			biographyTextArea.setEnabled(true);
			webPageTextField.setEnabled(true);
			instituteComboBox.setEnabled(true);

		}else{
			descriptionTextArea.setEnabled(false);
			biographyTextArea.setEnabled(false);
			webPageTextField.setEnabled(false);
			instituteComboBox.setEnabled(false);
		}
	}

	private void addNewUser() {
		// saves the use case (if no exception is thrown starts the use case again)
		String nickname 	= (String) nicknameTextField.getText();
		String name 		= nameTextField.getText();
		String lastname 	= lastnameTextField.getText();
		String email 		= emailTextField.getText();
		int month 			= (int) monthComboBox.getSelectedItem();
		int year 			= (int) yearComboBox.getSelectedItem();
		int day 			= (int) dayComboBox.getSelectedItem();
		Date bornDate 		= new Date(year-1900, month-1, day);
//		LocalDate bornDate 		= LocalDate.of(year, month, day); DEBERIAMOS USAR ESTA LIBRERIA PERO HAY QUE CAMBIAR LA IMPLEMENTACION DE LOS DT Y LAS CLASES
		boolean isTeacher 	= checkBoxIsTeacher.isSelected();
		String description 	= (String) descriptionTextArea.getText();
		String biography 	= (String) biographyTextArea.getText();
		String webPage		= (String) webPageTextField.getText();
		String institute 	= (String) instituteComboBox.getSelectedItem();
		if (isTeacher && (institute == null || institute.isEmpty() || institute.equals("Sin seleccionar"))){
			JOptionPane.showMessageDialog(this, "Debes seleccionar un instituto para el profesor",
					"Crear nuevo usuario", JOptionPane.ERROR_MESSAGE);
			return ;
		}
		try {
			if(isTeacher)
				uc.newUser(new DtProfessor(description, biography, webPage, nickname, name, lastname, email, bornDate), institute);
			else
				uc.newUser(new DtUser(nickname, name, lastname, email, bornDate), institute);

			JOptionPane.showMessageDialog(this, "Usuario creado exitosamente.",
					"Crear nuevo usuario", JOptionPane.INFORMATION_MESSAGE);
			restartUseCase();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Crear nuevo usuario", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void restartUseCase() {
		// Restarts the use case
		nameTextField.setText("Joe");
		lastnameTextField.setText("Doe");
		emailTextField.setText("JoeDoe@gmail.com");
		descriptionTextArea.setText("Hello World!!");
		biographyTextArea.setText("Hi, i'm Joe Doe. A developer...");
		webPageTextField.setText("www.JoeDoe.com");
		instituteComboBox.setSelectedIndex(0);
	}

}
