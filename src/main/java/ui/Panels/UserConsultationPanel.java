package ui.Panels;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import dataTypes.DtClass;
import dataTypes.DtMember;
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

	private DtUser chosenUser = null;
	private Set<String> userNicknames;

	private JTextField nicknameTextField;
	private JTextField nameTextField;
	private JTextField surnameTextField;
	private JTextField mailTextField;
	private JTextField dateTextField;

	// Constructor
	public UserConsultationPanel() {
		this.initialize();
		this.setForm();
		this.setListeners();
		this.addBaseElements();

		// Populate initial comboBox
		String[] fetchedUsers = userController.listUsersByNickname();
		Set<String> nicknameSet = new HashSet<>(Arrays.asList(fetchedUsers));
		nicknameSet.remove("<Nicknames>");
		userNicknames = nicknameSet;
		addItemsToComboBox(usersComboBox, nicknameSet);
	}

	private void initialize() {
		setPanelLayout();
		setTitleLabel("Consulta de usuario", "Calibri", Font.BOLD, 18);
		// Create comboboxes in swing
		usersComboBox = createLabelComboBox("Seleccione el usuario", 1);
		activitiesComboBox = createLabelComboBox("Actividades relacionadas", 2);
		classesComboBox = createLabelComboBox("Clases relacionadas", 3);
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
			nicknameTextField.setEditable(false);
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
			nameTextField.setEditable(false);
		}
		{
			surnameTextField = new JTextField();
			surnameTextField.setColumns(10);
			GridBagConstraints gbc_surnameTextField = new GridBagConstraints();
			gbc_surnameTextField.gridwidth = 3;
			gbc_surnameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_surnameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_surnameTextField.gridx = 4;
			gbc_surnameTextField.gridy = 5;
			add(surnameTextField, gbc_surnameTextField);
			surnameTextField.setEnabled(false);
			surnameTextField.setEditable(false);
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
			mailTextField.setEditable(false);

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
			dateTextField.setEditable(false);
		}
	}

	private void addBaseElements() {
		// Users
		usersComboBox.addItem(nonSelectedOption);
		selectedUser = nonSelectedOption;
		// Activities
		activitiesComboBox.addItem(nonSelectedOption);
		selectedActivity = nonSelectedOption;
		activitiesComboBox.setEnabled(false);
		// Classes
		classesComboBox.addItem(nonSelectedOption);
		classesComboBox.setEnabled(false);
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

	private void addItemsToComboBox(JComboBox<String> comboBox, Set<String> values) {
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
				// Fetch user data
				if (usersComboBox.getSelectedItem() != null
						&& !usersComboBox.getSelectedItem().toString().equals(selectedUser)) {
					resetForm();
					selectedUser = usersComboBox.getSelectedItem().toString();
					fetchSelectedUserData(selectedUser);
				}
			}
		});
	}

	private void fetchSelectedUserData(String nickname) {
		chosenUser = userController.chooseUser(nickname);
		if (Objects.isNull(chosenUser)) {
			return;
		}
		// Set textfield values
		nicknameTextField.setText(chosenUser.getNickname());
		nicknameTextField.setEnabled(true);
		nameTextField.setText(chosenUser.getName());
		nameTextField.setEnabled(true);
		surnameTextField.setText(chosenUser.getLastName());
		surnameTextField.setEnabled(true);
		mailTextField.setText(chosenUser.getEmail());
		mailTextField.setEnabled(true);

		// Set date textfield value
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = dateFormat.format(chosenUser.getBornDate());
		dateTextField.setText(formattedDate);
		dateTextField.setEnabled(true);

		// Get user enrolled classes
		if (chosenUser instanceof DtMember) {
			activitiesComboBox.setEnabled(false);
			classesComboBox.setEnabled(true);
			Map<String, DtClass> memberClasses = userController.getMemberEnrolledClasses(chosenUser.getNickname());
			// Add classes to comboBox
			if (memberClasses != null) {
				Set<String> classesToAdd = new HashSet<>();
				for (Map.Entry<String, DtClass> entry : memberClasses.entrySet()) {
					DtClass aClass = entry.getValue();
					classesToAdd.add(aClass.getName());
				}
				addItemsToComboBox(classesComboBox, classesToAdd);
			} else { // No classes!
				classesComboBox.setEnabled(false);
			}
		} else if (chosenUser instanceof DtProfessor) {
			// Can have both of them
			activitiesComboBox.setEnabled(true);
			classesComboBox.setEnabled(true);

			// Adding classes and activities
			Map<String, DtClass> professorClasses = ((DtProfessor) chosenUser).getRelatedClasses();
			if (professorClasses != null) {
				Set<String> profClassesToAdd = new HashSet<>();
				for (Map.Entry<String, DtClass> entry : professorClasses.entrySet()) {
					DtClass aClass = entry.getValue();
					profClassesToAdd.add(aClass.getName());
				}
				addItemsToComboBox(classesComboBox, profClassesToAdd);
			} else { // No classes!
				classesComboBox.setEnabled(false);
				activitiesComboBox.setEnabled(false);
			}

		}
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
		// ComboBoxes
		activitiesComboBox.removeAllItems();
		activitiesComboBox.setEnabled(false);
		activitiesComboBox.addItem(nonSelectedOption);
		selectedActivity = nonSelectedOption;
		classesComboBox.removeAllItems();
		classesComboBox.setEnabled(false);
		classesComboBox.addItem(nonSelectedOption);
		selectedClass = nonSelectedOption;
	}

}
