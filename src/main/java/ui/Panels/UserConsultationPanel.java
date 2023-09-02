package ui.Panels;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import dataTypes.DtClass;
import dataTypes.DtProfessor;
import dataTypes.DtUser;
import interfaces.ControllerFactory;
import interfaces.UserInterface;

public class UserConsultationPanel extends JPanel {
	private final UserInterface userController = ControllerFactory.getInstance().getUserInterface();

	private final String nonSelectedOption = "Sin seleccionar";
	private JComboBox<String> usersComboBox = new JComboBox<String>();
	private JComboBox<String> activitiesComboBox = new JComboBox<String>();
	private JComboBox<String> classesComboBox = new JComboBox<String>();
	private String selectedUser = nonSelectedOption;
	private String selectedActivity = nonSelectedOption;
	private String selectedClass = nonSelectedOption;

	// private Map<String, DtUser> users = new TreeMap<String, DtUser>();
	private String[] userNicknames = null;
	private JTextField nicknameTextField;
	private JTextField nameTextField;
	private JTextField surnameTextfield;
	private JTextField mailTextField;
	private JTextField dateTextField;

	// Constructor
	public UserConsultationPanel() {
		this.initialize();
		this.setForm();
		this.setListeners();
		this.addBaseElements();

		/*
		 * Map<String, DtClass> classesPrueba =
		 * userController.getMemberEnrolledClasses("JoeDoe2"); if (classesPrueba !=
		 * null) { for (Map.Entry<String, DtClass> entry : classesPrueba.entrySet()) {
		 * DtClass aClass = entry.getValue(); System.out.println(aClass.getName()); }
		 * 
		 * }
		 */
	}

	private void initialize() {
		setPanelLayout();
		setTitleLabel("Consulta de usuario", "Calibri", Font.BOLD, 18);
		// Create comboboxes in swing
		usersComboBox = createLabelComboBox("Seleccione el usuario", 1);
		activitiesComboBox = createLabelComboBox("Actividades", 2);
		activitiesComboBox = createLabelComboBox("Clases", 3);

		// Populate initial comboBox
		userNicknames = userController.listUsersByNickname();
		addItemsToComboBox(usersComboBox, userNicknames);

	}

	private void setForm() {
		{
			JLabel nicknameLabel = new JLabel("Nickname");
			GridBagConstraints gbc_nicknameLabel = new GridBagConstraints();
			gbc_nicknameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nicknameLabel.gridx = 0;
			gbc_nicknameLabel.gridy = 4;
			add(nicknameLabel, gbc_nicknameLabel);
		}
		{
			JLabel nameLabel = new JLabel("Nombre");
			GridBagConstraints gbc_nameLabel = new GridBagConstraints();
			gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nameLabel.gridx = 2;
			gbc_nameLabel.gridy = 4;
			add(nameLabel, gbc_nameLabel);
		}
		{
			JLabel surnameLabel = new JLabel("Apellido");
			GridBagConstraints gbc_surnameLabel = new GridBagConstraints();
			gbc_surnameLabel.gridwidth = 3;
			gbc_surnameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_surnameLabel.gridx = 4;
			gbc_surnameLabel.gridy = 4;
			add(surnameLabel, gbc_surnameLabel);
		}
		{
			nicknameTextField = new JTextField();
			GridBagConstraints gbc_nicknameTextField = new GridBagConstraints();
			gbc_nicknameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_nicknameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nicknameTextField.gridx = 0;
			gbc_nicknameTextField.gridy = 5;
			add(nicknameTextField, gbc_nicknameTextField);
			nicknameTextField.setColumns(10);
			nicknameTextField.setEnabled(false);
		}
		{
			nameTextField = new JTextField();
			nameTextField.setColumns(10);
			GridBagConstraints gbc_nameTextField = new GridBagConstraints();
			gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameTextField.gridx = 2;
			gbc_nameTextField.gridy = 5;
			add(nameTextField, gbc_nameTextField);
			nameTextField.setEnabled(false);
		}
		{
			surnameTextfield = new JTextField();
			surnameTextfield.setColumns(10);
			GridBagConstraints gbc_surnameTextfield = new GridBagConstraints();
			gbc_surnameTextfield.gridwidth = 3;
			gbc_surnameTextfield.insets = new Insets(0, 0, 5, 5);
			gbc_surnameTextfield.fill = GridBagConstraints.HORIZONTAL;
			gbc_surnameTextfield.gridx = 4;
			gbc_surnameTextfield.gridy = 5;
			add(surnameTextfield, gbc_surnameTextfield);
			surnameTextfield.setEnabled(false);
		}
		{
			JLabel mailLabel = new JLabel("Mail");
			GridBagConstraints gbc_mailLabel = new GridBagConstraints();
			gbc_mailLabel.insets = new Insets(0, 0, 5, 5);
			gbc_mailLabel.gridx = 0;
			gbc_mailLabel.gridy = 6;
			add(mailLabel, gbc_mailLabel);
		}
		{
			JLabel dateLabel = new JLabel("Fecha de nacimiento");
			GridBagConstraints gbc_dateLabel = new GridBagConstraints();
			gbc_dateLabel.insets = new Insets(0, 0, 5, 5);
			gbc_dateLabel.gridx = 2;
			gbc_dateLabel.gridy = 6;
			add(dateLabel, gbc_dateLabel);
		}
		{
			mailTextField = new JTextField();
			mailTextField.setColumns(10);
			GridBagConstraints gbc_mailTextField = new GridBagConstraints();
			gbc_mailTextField.insets = new Insets(0, 0, 5, 5);
			gbc_mailTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_mailTextField.gridx = 0;
			gbc_mailTextField.gridy = 7;
			add(mailTextField, gbc_mailTextField);
			mailTextField.setEnabled(false);
		}
		{
			dateTextField = new JTextField();
			dateTextField.setColumns(10);
			GridBagConstraints gbc_dateTextField = new GridBagConstraints();
			gbc_dateTextField.insets = new Insets(0, 0, 5, 5);
			gbc_dateTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_dateTextField.gridx = 2;
			gbc_dateTextField.gridy = 7;
			add(dateTextField, gbc_dateTextField);
			dateTextField.setEnabled(false);
		}
	}

	private void addBaseElements() {
		// Users
		usersComboBox.addItem(nonSelectedOption);
		selectedUser = nonSelectedOption;
		// Activities
		activitiesComboBox.addItem(nonSelectedOption);
		selectedActivity = nonSelectedOption;
		// Classes
		classesComboBox.addItem(nonSelectedOption);
		selectedClass = nonSelectedOption;
	}

	private void setPanelLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 20, 20, 20, 20, 20, 20, 20, 20 };
		gridBagLayout.rowHeights = new int[] { 20, 20, 0, 0, 20, 20, 0, 20, 20, 20, 20 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0 };
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
		gbc_titleLabel.gridwidth = 7;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
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
		gbc_institutesComboBox.gridwidth = 3;
		gbc_institutesComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_institutesComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_institutesComboBox.gridx = 3;
		gbc_institutesComboBox.gridy = gridy;
		add(genericComboBox, gbc_institutesComboBox);

		return genericComboBox;
	}

	private void addItemsToComboBox(JComboBox<String> comboBox, String[] values) {
		for (String value : values) {
			comboBox.addItem(value);
		}
	}

	private void resetComboBox(JComboBox<String> comboBox) {
		comboBox.removeAllItems();
		comboBox.addItem(nonSelectedOption);
	}

	private void setListeners() {

		usersComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (usersComboBox.getSelectedItem() != null
						&& !usersComboBox.getSelectedItem().toString().equals(selectedUser)) {
					selectedUser = usersComboBox.getSelectedItem().toString();
				}
			}
		});
	}

	private void fetchSelectedUserData(String nickname) {
		DtUser userChosen = userController.chooseUser(nickname);
		if (Objects.isNull(userChosen)) {
			return;
		}
		// Sets the inputs with the user information
		/*
		 * emailTextField.setText(userChosen.getEmail());
		 * nameTextField.setText(userChosen.getName()); nameTextField.setEnabled(true);
		 * nameTextField.setEditable(true);
		 * lastnameTextField.setText(userChosen.getLastName());
		 * lastnameTextField.setEnabled(true); lastnameTextField.setEditable(true); //
		 * Here I put the day -1 because I need the index of the item to be selected in
		 * the array
		 * dayComboBox.setSelectedIndex(Integer.parseInt(userChosen.getBornDate().
		 * toString().substring(8,10)) -1); dayComboBox.setEnabled(true);
		 * monthComboBox.setSelectedIndex(userChosen.getBornDate().getMonth());
		 * monthComboBox.setEnabled(true); activeUserYearBorn =
		 * userChosen.getBornDate().getYear(); changeYearComboBox("START");
		 * yearComboBox.setEnabled(true); saveButton.setEnabled(true);
		 * cancelButton.setEnabled(true);
		 */
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

}
