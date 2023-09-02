package ui.Panels;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;
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
	private String selectedUser = nonSelectedOption;

	// private Map<String, DtUser> users = new TreeMap<String, DtUser>();
	private String[] userNicknames = null;

	// Constructor
	public UserConsultationPanel() {
		this.initialize();
		// this.setForm();
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

		// Populate initial comboBox
		userNicknames = userController.listUsersByNickname();
		addItemsToComboBox(usersComboBox, userNicknames);
	}

	private void addBaseElements() {
		// Users
		usersComboBox.addItem(nonSelectedOption);
		selectedUser = nonSelectedOption;
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
