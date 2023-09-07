package ui.Panels;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtInstitute;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JInternalFrame;

public class AddSportActivityPanel extends JPanel{
	private JButton btnConfirm;
	private JButton btnVerifyName;
	private JButton btnUpImg;

	private File selectedFile;
	
	private JTextField nameTxt;
	private JTextField priceTxt;
	private JTextPane descriptionTxt;

	private JSpinner daySpinner;
	private JSpinner monthSpinner;
	private JSpinner yearSpinner;
	private JSpinner durationSpinner;

	private JLabel lblImagen;
	
	private Set<String> institutes;
	private String selectedName;

	private JComboBox<String> instituteComboBox 	= new JComboBox<String>();
	private final String nonSelectedOption			= "Sin seleccionar"; 
	private final int startDuration = 30;
	private final int startDay = 1;
	private final int startMonth = 1;
	private final int startYear = 2023;

	private InstituteInterface instituteController 	= ControllerFactory.getInstance().getInstituteInterface();

	public AddSportActivityPanel(){
		setGridLayout();
		setTitleLabel("Alta de actividad deportiva", "Calibri", Font.BOLD, 18);
		setInstituteLabelCombo();
		setForm();
		setButtonPanel();
		addButtonListeners();
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
		institutes = instituteController.listSportInstitutes().keySet();
		if(institutes != null) {
			for(String value : institutes) {
				instituteComboBox.addItem(value);
			}
		}
	}

	private void setForm() {
		JLabel nameLabel = new JLabel("Nombre");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 2;
		gbc_nameLabel.gridy = 3;
		add(nameLabel, gbc_nameLabel);

		nameTxt = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.gridwidth = 3;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 3;
		gbc_txtName.gridy = 3;
		nameTxt.setColumns(10);
		add(nameTxt, gbc_txtName);

		JLabel durationLabel = new JLabel("Duración");
		GridBagConstraints gbc_durationLabel = new GridBagConstraints();
		gbc_durationLabel.anchor = GridBagConstraints.EAST;
		gbc_durationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_durationLabel.gridx = 1;
		gbc_durationLabel.gridy = 4;
		add(durationLabel, gbc_durationLabel);
		
		SpinnerModel modelDurationSpinner = new SpinnerNumberModel(startDuration, 30, 360, 30);
		durationSpinner = new JSpinner(modelDurationSpinner);
		
		GridBagConstraints gbc_durationSpinner = new GridBagConstraints();
		gbc_durationSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_durationSpinner.gridx = 2;
		gbc_durationSpinner.gridy = 4;
		add(durationSpinner, gbc_durationSpinner);
		
		JLabel durationLabel_2 = new JLabel("Minutos");
		GridBagConstraints gbc_durationLabel_2 = new GridBagConstraints();
		gbc_durationLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_durationLabel_2.gridx = 3;
		gbc_durationLabel_2.gridy = 4;
		add(durationLabel_2, gbc_durationLabel_2);

		JLabel descriptionLabel = new JLabel("Descripción");
		GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.gridwidth = 3;
		gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionLabel.gridx = 5;
		gbc_descriptionLabel.gridy = 4;
		add(descriptionLabel, gbc_descriptionLabel);

		descriptionTxt = new JTextPane();
		GridBagConstraints gbc_descriptionText = new GridBagConstraints();
		gbc_descriptionText.gridheight = 3;
		gbc_descriptionText.gridwidth = 3;
		gbc_descriptionText.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionText.fill = GridBagConstraints.BOTH;
		gbc_descriptionText.gridx = 5;
		gbc_descriptionText.gridy = 5;
		add(descriptionTxt, gbc_descriptionText);

		JLabel priceLabel = new JLabel("Costo");
		GridBagConstraints gbc_priceLabel = new GridBagConstraints();
		gbc_priceLabel.anchor = GridBagConstraints.EAST;
		gbc_priceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_priceLabel.gridx = 1;
		gbc_priceLabel.gridy = 5;
		add(priceLabel, gbc_priceLabel);

		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		GridBagConstraints gbc_priceTxt = new GridBagConstraints();
		gbc_priceTxt.gridwidth = 2;
		gbc_priceTxt.insets = new Insets(0, 0, 5, 5);
		gbc_priceTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceTxt.gridx = 2;
		gbc_priceTxt.gridy = 5;
		add(priceTxt, gbc_priceTxt);

		JLabel dateTxt = new JLabel("Fecha de inscripción");
		GridBagConstraints gbc_dateTxt = new GridBagConstraints();
		gbc_dateTxt.gridwidth = 3;
		gbc_dateTxt.insets = new Insets(0, 0, 5, 5);
		gbc_dateTxt.gridx = 1;
		gbc_dateTxt.gridy = 7;
		add(dateTxt, gbc_dateTxt);
		
		SpinnerModel modelDay = new SpinnerNumberModel(startDay, 1, 31, 1);
		daySpinner = new JSpinner(modelDay);
		GridBagConstraints gbc_daySpinner = new GridBagConstraints();
		gbc_daySpinner.insets = new Insets(0, 0, 5, 5);
		gbc_daySpinner.gridx = 1;
		gbc_daySpinner.gridy = 8;
		add(daySpinner, gbc_daySpinner);
		
		JLabel daySpinnerLabel = new JLabel("Día");
		GridBagConstraints gbc_daySpinnerLabel = new GridBagConstraints();
		gbc_daySpinnerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_daySpinnerLabel.gridx = 1;
		gbc_daySpinnerLabel.gridy = 9;
		add(daySpinnerLabel, gbc_daySpinnerLabel);

		SpinnerModel modelMonth = new SpinnerNumberModel(startMonth, 1, 12, 1);
		monthSpinner = new JSpinner(modelMonth);
		GridBagConstraints gbc_monthSpinner = new GridBagConstraints();
		gbc_monthSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_monthSpinner.gridx = 2;
		gbc_monthSpinner.gridy = 8;
		add(monthSpinner, gbc_monthSpinner);

		JLabel monthSpinnerText = new JLabel("Mes");
		GridBagConstraints gbc_monthSpinnerText = new GridBagConstraints();
		gbc_monthSpinnerText.insets = new Insets(0, 0, 5, 5);
		gbc_monthSpinnerText.gridx = 2;
		gbc_monthSpinnerText.gridy = 9;
		add(monthSpinnerText, gbc_monthSpinnerText);

		SpinnerModel modelYear = new SpinnerNumberModel(2023, 1900, LocalDate.now().getYear(), 1);
		yearSpinner = new JSpinner(modelYear);
		GridBagConstraints gbc_yearSpinner = new GridBagConstraints();
		gbc_yearSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_yearSpinner.gridx = 3;
		gbc_yearSpinner.gridy = 8;
		add(yearSpinner, gbc_yearSpinner);

		JLabel modelYearText = new JLabel("Año");
		GridBagConstraints gbc_modelYearText = new GridBagConstraints();
		gbc_modelYearText.insets = new Insets(0, 0, 5, 5);
		gbc_modelYearText.gridx = 3;
		gbc_modelYearText.gridy = 9;
		add(modelYearText, gbc_modelYearText);
	}

	private void addButtonListeners() {
		lblImagen = new JLabel("Imagen");
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.anchor = GridBagConstraints.EAST;
		gbc_lblImagen.gridwidth = 2;
		gbc_lblImagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagen.gridx = 5;
		gbc_lblImagen.gridy = 8;
		add(lblImagen, gbc_lblImagen);
		
		JPanel thisPanel = this;
		
		JLabel lblImagenName = new JLabel("");
		GridBagConstraints gbc_lblImagenName = new GridBagConstraints();
		gbc_lblImagenName.anchor = GridBagConstraints.NORTH;
		gbc_lblImagenName.gridwidth = 2;
		gbc_lblImagenName.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagenName.gridx = 6;
		gbc_lblImagenName.gridy = 9;
		lblImagenName.setForeground(Color.RED);
		add(lblImagenName, gbc_lblImagenName);
		
		
		btnUpImg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png", "gif");
                fileChooser.setFileFilter(filter);
                
                int result = fileChooser.showOpenDialog(thisPanel);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    lblImagenName.setText(selectedFile.getName());;
                }
            }
        });
		
		btnVerifyName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameTxt.getText() != null && !nameTxt.getText().isEmpty()) {
					selectedName = nameTxt.getText();
					if(instituteController.checkActivityAvialability(selectedName)) {
						displayWindow("Validación", "Está disponible el nombre " + selectedName, JOptionPane.INFORMATION_MESSAGE);
						btnConfirm.setEnabled(true);
					}
					else {
						displayWindow("Error de ingreso", "Ya existe una actividad de nombre " + selectedName, JOptionPane.ERROR_MESSAGE);
						nameTxt.setText("");
					}
				}
			}
		});
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameTxt.getText() == null || nonSelectedOption.equals(nameTxt.getText())) {
					displayWindow("Error de ingreso", "Debe validar el nombre antes de ingresar", JOptionPane.ERROR_MESSAGE);
					btnConfirm.setEnabled(false);
				}
				else if(!verifyDate()) {
					displayWindow("Error de ingreso", "Ingrese una fecha válida", JOptionPane.ERROR_MESSAGE);
				}
				else if(instituteComboBox.getSelectedItem().equals(nonSelectedOption)) {
					displayWindow("Error de ingreso", "Seleccione un instituto", JOptionPane.ERROR_MESSAGE);
				}
				else if(!verifyFloat(priceTxt.getText().replace(",", "."))) {
					displayWindow("Error de ingreso", "Formato de costo incorrecto", JOptionPane.ERROR_MESSAGE);
				}
				else {
					SpinnerNumberModel model = (SpinnerNumberModel) daySpinner.getModel();
					int day = (int) model.getNumber(); 
					model = (SpinnerNumberModel) monthSpinner.getModel();
					int month = (int) model.getNumber(); 
					model = (SpinnerNumberModel) yearSpinner.getModel();
					int year = (int) model.getNumber();  
					model = (SpinnerNumberModel) durationSpinner.getModel();
					int duration = (int) model.getNumber();
					
					Calendar calendar = Calendar.getInstance();
			        calendar.set(year, month - 1, day); // minus 1, because the months start in 0 in Calendar

			        Date fecha = calendar.getTime();
					instituteController.addNewSportActivity(
							new DtActivity(
									nameTxt.getText(), 
									descriptionTxt == null ? null : descriptionTxt.getText(), 
									duration, 
									priceTxt.getText().isEmpty() ? 0 : Float.parseFloat(priceTxt.getText().replace(",", ".")), 
									calendar.getTime(), 
									0,
									new TreeMap<String, DtClass>()), 
							instituteComboBox.getSelectedItem().toString());
					displayWindow("Éxito", "Se ha creado la actividad deportiva con éxito.", JOptionPane.INFORMATION_MESSAGE);
					
					File destino = new File("../../resources/" + selectedFile.getName());
					try {
						Files.copy(selectedFile.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					resetForm();
				}
			}
		});
	}
	
	private void resetForm() {
        Component[] components = this.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }else if(component instanceof JTextPane) {
            	((JTextPane) component).setText(""); 
            }
        }
        daySpinner.setValue(startDay);
    	monthSpinner.setValue(startMonth);
    	yearSpinner.setValue(startYear);
    	durationSpinner.setValue(startDuration);
    	
    	instituteComboBox.setSelectedItem(nonSelectedOption);
    	btnConfirm.setEnabled(false);
    }

	private boolean verifyFloat(String floatValue) {
		try {
			if(floatValue.isEmpty()) {
				return true;
			}
	        float parsedValue = Float.parseFloat(floatValue);
	        return true; 
	    } catch (NumberFormatException e) {
	        return false; 
	    }
	}
	
	private boolean verifyDate() {
		SpinnerNumberModel model = (SpinnerNumberModel) daySpinner.getModel();
		int day = (int) model.getNumber(); 
		model = (SpinnerNumberModel) monthSpinner.getModel();
		int month = (int) model.getNumber(); 
		model = (SpinnerNumberModel) yearSpinner.getModel();
		int year = (int) model.getNumber(); 
		try {
			LocalDate date = LocalDate.of(year, month, day);
			return true; 
		} catch (Exception e) {
			return false; 
		}
	}

	private void displayWindow(String titleLabel, String message, int messageType) {
		JOptionPane.showMessageDialog(this, message, titleLabel, messageType);
	}

	private void setButtonPanel() {
		btnVerifyName = new JButton("✓");
		btnVerifyName.setForeground(new Color(255, 255, 255));
		btnVerifyName.setBackground(new Color(0, 0, 255));
		GridBagConstraints gbc_btnVerifyName = new GridBagConstraints();
		gbc_btnVerifyName.insets = new Insets(0, 0, 5, 5);
		gbc_btnVerifyName.gridx = 6;
		gbc_btnVerifyName.gridy = 3;
		add(btnVerifyName, gbc_btnVerifyName);

		btnConfirm = new JButton("Confirmar");
		btnConfirm.setBackground(Color.GREEN);
		btnConfirm.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.insets = new Insets(0, 0, 0, 5);
		gbc_btnConfirm.gridwidth = 2;
		gbc_btnConfirm.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnConfirm.gridx = 6;
		gbc_btnConfirm.gridy = 10;
		add(btnConfirm, gbc_btnConfirm);
		btnConfirm.setEnabled(false);
		
		btnUpImg = new JButton("↑");
		GridBagConstraints gbc_btnUpImg = new GridBagConstraints();
		gbc_btnUpImg.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpImg.gridx = 7;
		gbc_btnUpImg.gridy = 8;
		add(btnUpImg, gbc_btnUpImg);
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
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 0;
		gbc_titleLabel.gridwidth = 9;
		gbc_titleLabel.gridx = 0;
		add(titleLabel, gbc_titleLabel);
	}
}
