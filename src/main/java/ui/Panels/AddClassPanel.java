package ui.Panels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;

import dataTypes.DtActivity;
import dataTypes.DtInstitute;
import dataTypes.DtProfessor;
import dataTypes.DtUser;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.Color;

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
	private JTextField nameTxt;
	private JTextField urlTxt;

	private JSpinner daySpinner;
	private JSpinner monthSpinner;
	private JSpinner yearSpinner;
	private JSpinner hourSpinner;
	private JSpinner minuteSpinner;

	private final int startDay = 1;
	private final int startMonth = 1;
	private final int startYear = 2023;
	private final int startHour = 0;
	private final int startMinute = 0;

	// Constructor
	public AddClassPanel() {
		this.initialize();
		this.setListeners();
		this.setForm();
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
							professorsComboBox.setEnabled(true);
						}

						// Getting activities for the comboBox
						activities = instituteController
								.selectInstitution(institutesComboBox.getSelectedItem().toString());
						resetComboBox(activitiesComboBox);
						if (activities != null) {
							addItemsToComboBox(activitiesComboBox, activities.keySet());
							activitiesComboBox.setEnabled(true);
						}

					}
					selectedActivity = nonSelectedOption;
					activitiesComboBox.setSelectedItem(selectedActivity);
					selectedInstitute = institutesComboBox.getSelectedItem().toString();
				}
			}
		});

		professorsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (professorsComboBox.getSelectedItem() != null
						&& !professorsComboBox.getSelectedItem().toString().equals(selectedProfessor)) {
					selectedProfessor = professorsComboBox.getSelectedItem().toString();
				}
			}
		});

		activitiesComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (activitiesComboBox.getSelectedItem() != null
						&& !activitiesComboBox.getSelectedItem().toString().equals(selectedActivity)) {
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

	private void setForm() {
		JLabel nameLabel = new JLabel("Nombre");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 1;
		gbc_nameLabel.gridy = 5;
		add(nameLabel, gbc_nameLabel);

		nameTxt = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 5;
		nameTxt.setColumns(10);
		add(nameTxt, gbc_txtName);

		JLabel urlLabel = new JLabel("Url");
		GridBagConstraints gbc_priceLabel = new GridBagConstraints();
		gbc_priceLabel.anchor = GridBagConstraints.EAST;
		gbc_priceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_priceLabel.gridx = 4;
		gbc_priceLabel.gridy = 5;
		add(urlLabel, gbc_priceLabel);

		urlTxt = new JTextField();
		urlTxt.setColumns(10);
		GridBagConstraints gbc_priceTxt = new GridBagConstraints();
		gbc_priceTxt.gridwidth = 2;
		gbc_priceTxt.insets = new Insets(0, 0, 5, 5);
		gbc_priceTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceTxt.gridx = 5;
		gbc_priceTxt.gridy = 5;
		add(urlTxt, gbc_priceTxt);

		SpinnerModel modelDay = new SpinnerNumberModel(startDay, 1, 31, 1);
		SpinnerModel modelMonth = new SpinnerNumberModel(startMonth, 1, 12, 1);
		SpinnerModel modelYear = new SpinnerNumberModel(startYear, 1900, LocalDate.now().getYear(), 1);
		SpinnerModel modelHour = new SpinnerNumberModel(startHour, 0, 23, 1);
		SpinnerModel modelMinute = new SpinnerNumberModel(startMinute, 0, 59, 1);

		JLabel dateTxt = new JLabel("Fecha de inicio");
		GridBagConstraints gbc_dateTxt = new GridBagConstraints();
		gbc_dateTxt.gridwidth = 4;
		gbc_dateTxt.insets = new Insets(0, 0, 5, 5);
		gbc_dateTxt.gridx = 1;
		gbc_dateTxt.gridy = 7;
		add(dateTxt, gbc_dateTxt);

		JLabel creationDateTxt = new JLabel("Fecha de alta");
		GridBagConstraints gbc_creationDateTxt = new GridBagConstraints();
		gbc_creationDateTxt.insets = new Insets(0, 0, 5, 5);
		gbc_creationDateTxt.gridx = 7;
		gbc_creationDateTxt.gridy = 7;
		add(creationDateTxt, gbc_creationDateTxt);
		daySpinner = new JSpinner(modelDay);
		GridBagConstraints gbc_daySpinner = new GridBagConstraints();
		gbc_daySpinner.insets = new Insets(0, 0, 5, 5);
		gbc_daySpinner.gridx = 0;
		gbc_daySpinner.gridy = 8;
		add(daySpinner, gbc_daySpinner);

		monthSpinner = new JSpinner(modelMonth);
		GridBagConstraints gbc_monthSpinner = new GridBagConstraints();
		gbc_monthSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_monthSpinner.gridx = 1;
		gbc_monthSpinner.gridy = 8;
		add(monthSpinner, gbc_monthSpinner);

		yearSpinner = new JSpinner(modelYear);
		GridBagConstraints gbc_yearSpinner = new GridBagConstraints();
		gbc_yearSpinner.anchor = GridBagConstraints.WEST;
		gbc_yearSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_yearSpinner.gridx = 2;
		gbc_yearSpinner.gridy = 8;
		add(yearSpinner, gbc_yearSpinner);

		hourSpinner = new JSpinner(modelHour);
		GridBagConstraints gbc_hourSpinner = new GridBagConstraints();
		gbc_hourSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_hourSpinner.gridx = 3;
		gbc_hourSpinner.gridy = 8;
		add(hourSpinner, gbc_hourSpinner);

		minuteSpinner = new JSpinner(modelMinute);
		GridBagConstraints gbc_minuteSpinner = new GridBagConstraints();
		gbc_minuteSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_minuteSpinner.gridx = 4;
		gbc_minuteSpinner.gridy = 8;
		add(minuteSpinner, gbc_minuteSpinner);

		JSpinner daySpinner_1 = new JSpinner(modelDay);
		GridBagConstraints gbc_daySpinner_1 = new GridBagConstraints();
		gbc_daySpinner_1.insets = new Insets(0, 0, 5, 5);
		gbc_daySpinner_1.gridx = 6;
		gbc_daySpinner_1.gridy = 8;
		add(daySpinner_1, gbc_daySpinner_1);

		JSpinner monthSpinner_1 = new JSpinner(modelMonth);
		GridBagConstraints gbc_monthSpinner_1 = new GridBagConstraints();
		gbc_monthSpinner_1.insets = new Insets(0, 0, 5, 5);
		gbc_monthSpinner_1.gridx = 7;
		gbc_monthSpinner_1.gridy = 8;
		add(monthSpinner_1, gbc_monthSpinner_1);

		JSpinner yearSpinner_1 = new JSpinner(modelYear);
		GridBagConstraints gbc_yearSpinner_1 = new GridBagConstraints();
		gbc_yearSpinner_1.insets = new Insets(0, 0, 5, 0);
		gbc_yearSpinner_1.gridx = 8;
		gbc_yearSpinner_1.gridy = 8;
		add(yearSpinner_1, gbc_yearSpinner_1);

		JLabel daySpinnerLabel = new JLabel("Día");
		GridBagConstraints gbc_daySpinnerLabel = new GridBagConstraints();
		gbc_daySpinnerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_daySpinnerLabel.gridx = 0;
		gbc_daySpinnerLabel.gridy = 9;
		add(daySpinnerLabel, gbc_daySpinnerLabel);

		JLabel monthSpinnerLabel = new JLabel("Mes");
		GridBagConstraints gbc_monthSpinnerLabel = new GridBagConstraints();
		gbc_monthSpinnerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_monthSpinnerLabel.gridx = 1;
		gbc_monthSpinnerLabel.gridy = 9;
		add(monthSpinnerLabel, gbc_monthSpinnerLabel);

		JLabel yearSpinnerLabel = new JLabel("Año");
		GridBagConstraints gbc_yearSpinnerLabel = new GridBagConstraints();
		gbc_yearSpinnerLabel.anchor = GridBagConstraints.WEST;
		gbc_yearSpinnerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_yearSpinnerLabel.gridx = 2;
		gbc_yearSpinnerLabel.gridy = 9;
		add(yearSpinnerLabel, gbc_yearSpinnerLabel);

		JLabel lblHora = new JLabel("Hora");
		GridBagConstraints gbc_lblHora = new GridBagConstraints();
		gbc_lblHora.insets = new Insets(0, 0, 5, 5);
		gbc_lblHora.gridx = 3;
		gbc_lblHora.gridy = 9;
		add(lblHora, gbc_lblHora);

		JLabel lblMinutos = new JLabel("Minutos");
		GridBagConstraints gbc_lblMinutos = new GridBagConstraints();
		gbc_lblMinutos.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinutos.gridx = 4;
		gbc_lblMinutos.gridy = 9;
		add(lblMinutos, gbc_lblMinutos);

		JLabel daySpinnerLabel_1 = new JLabel("Día");
		GridBagConstraints gbc_daySpinnerLabel_1 = new GridBagConstraints();
		gbc_daySpinnerLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_daySpinnerLabel_1.gridx = 6;
		gbc_daySpinnerLabel_1.gridy = 9;
		add(daySpinnerLabel_1, gbc_daySpinnerLabel_1);

		JLabel monthSpinnerLabel_1 = new JLabel("Mes");
		GridBagConstraints gbc_monthSpinnerLabel_1 = new GridBagConstraints();
		gbc_monthSpinnerLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_monthSpinnerLabel_1.gridx = 7;
		gbc_monthSpinnerLabel_1.gridy = 9;
		add(monthSpinnerLabel_1, gbc_monthSpinnerLabel_1);

		JLabel yearSpinnerLabel_1 = new JLabel("Año");
		GridBagConstraints gbc_yearSpinnerLabel_1 = new GridBagConstraints();
		gbc_yearSpinnerLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_yearSpinnerLabel_1.gridx = 8;
		gbc_yearSpinnerLabel_1.gridy = 9;
		add(yearSpinnerLabel_1, gbc_yearSpinnerLabel_1);

		JButton btnConfirm = new JButton("Confirmar");
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setEnabled(false);
		btnConfirm.setBackground(Color.GREEN);
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.gridwidth = 3;
		gbc_btnConfirm.insets = new Insets(0, 0, 0, 5);
		gbc_btnConfirm.gridx = 3;
		gbc_btnConfirm.gridy = 10;
		add(btnConfirm, gbc_btnConfirm);
	}

}
