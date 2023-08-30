package ui.Panels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import dataTypes.DtActivity;
import dataTypes.DtInstitute;
import dataTypes.DtProfessor;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;

import javax.swing.JTextField;

public class AddClassPanel extends JPanel {

	private JComboBox<String> institutesComboBox = new JComboBox<String>();
	private JComboBox<String> professorsComboBox = new JComboBox<String>();
	private JComboBox<String> activitiesComboBox = new JComboBox<String>();
	private final String nonSelectedOption = "Sin seleccionar";
	private String selectedInstitute = nonSelectedOption;
	private String selectedProfessor = nonSelectedOption;
	private String selectedActivity = nonSelectedOption;
	private Map<String, DtInstitute> institutes = new TreeMap<String, DtInstitute>();
	private Map<String, DtActivity> activities = new TreeMap<String, DtActivity>();
	private Map<String, DtProfessor> professors = new TreeMap<String, DtProfessor>();

	private InstituteInterface instituteController = ControllerFactory.getInstance().getInstituteInterface();
	private JTextField nameTextField;
	private JTextField urlTextField;

	// Constructor
	public AddClassPanel() {
		this.initialize();
		this.setListeners();
		this.addBaseElements();
		this.institutes = instituteController.listSportInstitutes();
		if (institutes != null) {
			addItemsToComboBox(institutesComboBox, institutes.keySet());
		}
	}

	private void addBaseElements() {
		// Institutes
		institutesComboBox.addItem(selectedInstitute);
		selectedInstitute = nonSelectedOption;
		institutesComboBox.setSelectedItem(selectedInstitute);
		// Professors
		professorsComboBox.addItem(selectedProfessor);
		selectedProfessor = nonSelectedOption;
		professorsComboBox.setSelectedItem(selectedProfessor);
		// Activities
		activitiesComboBox.addItem(selectedActivity);
		selectedActivity = nonSelectedOption;
		activitiesComboBox.setSelectedItem(selectedActivity);
	}

	private void initialize() {
		setPanelLayout();
		setTitleLabel("Alta de dictado de clase", "Calibri", Font.BOLD, 18);
		institutesComboBox = createLabelComboBox("Institutos", 1);
		professorsComboBox = createLabelComboBox("Profesores", 2);
		activitiesComboBox = createLabelComboBox("Actividad deportiva", 3);
		// Initialize them disabled, they depend on the institute
		professorsComboBox.setEnabled(false);
		activitiesComboBox.setEnabled(false);
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

		JLabel nameLabel = new JLabel("Nombre");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 4;
		add(nameLabel, gbc_nameLabel);

		nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.gridx = 1;
		gbc_nameTextField.gridy = 4;
		add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		JLabel urlLabel = new JLabel("Url");
		GridBagConstraints gbc_urlLabel = new GridBagConstraints();
		gbc_urlLabel.anchor = GridBagConstraints.EAST;
		gbc_urlLabel.insets = new Insets(0, 0, 5, 5);
		gbc_urlLabel.gridx = 3;
		gbc_urlLabel.gridy = 4;
		add(urlLabel, gbc_urlLabel);

		urlTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 4;
		add(urlTextField, gbc_textField);
		urlTextField.setColumns(10);

		JSeparator separatorDate = new JSeparator();
		GridBagConstraints gbc_separatorDate = new GridBagConstraints();
		gbc_separatorDate.gridwidth = 5;
		gbc_separatorDate.insets = new Insets(0, 0, 5, 5);
		gbc_separatorDate.gridx = 0;
		gbc_separatorDate.gridy = 6;
		add(separatorDate, gbc_separatorDate);
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

	private void resetComboBox(JComboBox<String> comboBox) {
		comboBox.removeAllItems();
		comboBox.addItem(nonSelectedOption);
	}

	private void setListeners() {
		institutesComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (institutesComboBox.getSelectedItem() != null
						&& !institutesComboBox.getSelectedItem().toString().equals(selectedInstitute)) {
					if (institutesComboBox.getSelectedItem().toString().equals(nonSelectedOption)) {
						activitiesComboBox.setEnabled(false);
						professorsComboBox.setEnabled(false);
					} else {
						
						// Getting professors for the comboBox
						DtInstitute sI = institutes.get(institutesComboBox.getSelectedItem().toString());
						professors = sI.getProfessors();
						resetComboBox(professorsComboBox);
						if (professors != null) {
							addItemsToComboBox(professorsComboBox, professors.keySet());
						}
												
						// Getting activities for the comboBox
						activities = instituteController.selectInstitution(institutesComboBox.getSelectedItem().toString());
						resetComboBox(activitiesComboBox);
						if (activities != null) {
							addItemsToComboBox(activitiesComboBox, activities.keySet());
						}
						activitiesComboBox.setEnabled(true);
					}
					selectedActivity = nonSelectedOption;
					activitiesComboBox.setSelectedItem(selectedActivity);
					selectedInstitute = institutesComboBox.getSelectedItem().toString();
				}
			}
		});

		/*
		 * professorsComboBox.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { if
		 * (professorsComboBox.getSelectedItem() != null &&
		 * !professorsComboBox.getSelectedItem().toString().equals(selectedProfessor)) {
		 * 
		 * selectedProfessor = professorsComboBox.getSelectedItem().toString(); } } });
		 */

		activitiesComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (activitiesComboBox.getSelectedItem() != null
						&& !activitiesComboBox.getSelectedItem().toString().equals(selectedActivity)) {
					/*
					 * if
					 * (activitiesComboBox.getSelectedItem().toString().equals(nonSelectedOption)) {
					 * 
					 * }
					 */

					selectedActivity = activitiesComboBox.getSelectedItem().toString();
				}
			}
		});
	}

	private void addItemsToComboBox(JComboBox<String> comboBox, Set<String> values) {
		for (String value : values) {
			comboBox.addItem(value);
		}
	}

}
