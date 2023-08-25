package ui.Panels;

import javax.swing.JPanel;

import interfaces.ControllerFactory;
import interfaces.InstituteInterface;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import dataTypes.DtInstitute;

import java.util.Map;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ModifySportInsitutePanel extends JPanel {
	private final InstituteInterface ic = ControllerFactory.getInstance().getInstituteInterface();
	private JTextField textFieldDsc = new JTextField();
	private JTextField textFieldUrl = new JTextField();
	private JTextField textFieldName = new JTextField();
	private final JLabel lblTitle = new JLabel("Modificar institución deportiva");
	private JComboBox<String> selectInstitutecomboBox = new JComboBox<>();
	private final JLabel lblSelectInstitute = new JLabel("Seleccione la institución");
	private final JLabel lblName = new JLabel("Nombre");
	private final JLabel lblUrl = new JLabel("URL");
	private final JButton btnAccept = new JButton("Aceptar");
	private final JButton btnCancel = new JButton("Cancelar");
	private final JLabel lblDescription = new JLabel("Descripción");
	
	public ModifySportInsitutePanel() {
		initialize();
		
		selectInstitutecomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == selectInstitutecomboBox) {
					String selectedInstitute = (String)selectInstitutecomboBox.getSelectedItem();
					updateFields(selectedInstitute);	
				}
			}
		});
		
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String selectedInstitute = (String)selectInstitutecomboBox.getSelectedItem();
					ic.modiFySportInstitute(new DtInstitute(selectedInstitute, textFieldDsc.getText(), textFieldUrl.getText(), null));			
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(btnAccept, ex.getMessage(), "Modificar institución deportiva", JOptionPane.ERROR_MESSAGE);
				}
		}});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initUseCase();
			}
		});

        this.setVisible(true);
	}
	
	public void initialize() {
		setLayout(null);
		
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 328, 14);
		fetchInstituteList();
		selectInstitutecomboBox.setBounds(130, 36, 208, 22);
		selectInstitutecomboBox.setSelectedItem("Ver qué wea");
		lblSelectInstitute.setBounds(10, 40, 145, 14);
		lblName.setBounds(10, 96, 71, 14);
		lblUrl.setBounds(10, 158, 71, 14);
		textFieldDsc.setBounds(95, 124, 243, 20);
		textFieldDsc.setColumns(10);
		textFieldUrl.setColumns(10);
		textFieldUrl.setBounds(95, 155, 243, 20);
		btnAccept.setBounds(76, 207, 89, 23);
		
		btnCancel.setBounds(191, 207, 89, 23);
		lblDescription.setBounds(10, 127, 71, 14);
		textFieldName.setColumns(10);
		textFieldName.setBounds(95, 93, 243, 20);
		textFieldName.setEditable(false);
		
		initUseCase();
		
		add(lblTitle);
		add(selectInstitutecomboBox);
		add(lblSelectInstitute);
		add(lblName);
		add(lblUrl);
		add(textFieldDsc);
		add(textFieldUrl);
		add(btnAccept);
		add(btnCancel);
		add(lblDescription);
		add(textFieldName);
	}
	
    private void initUseCase(){
        // Initializes use case
        textFieldName.setText("");
        textFieldName.setEditable(false);
        textFieldUrl.setText("");
        textFieldUrl.setEditable(false);
        textFieldDsc.setText("");
        textFieldDsc.setEditable(false);
        btnCancel.setEnabled(false);
        btnAccept.setEnabled(false);
    }

	
	public void fetchInstituteList() {
		Map<String, DtInstitute> institutesList = ic.listSportInstitutes();
		for (Map.Entry<String, DtInstitute> entry : institutesList.entrySet()) {
	        String key = entry.getKey();
	        DtInstitute dt = entry.getValue();
	        selectInstitutecomboBox.addItem(dt.getName());
	    }
	}
		
	public void updateFields(String selectedInstitute) {
		DtInstitute dt = ic.chooseSportInstitute(selectedInstitute);
		textFieldName.setText(dt.getName());
		textFieldDsc.setText(dt.getDescription());
		textFieldUrl.setText(dt.getUrl());
		 
        textFieldUrl.setEditable(true);
        textFieldDsc.setEditable(true);
        btnCancel.setEnabled(true);
        btnAccept.setEnabled(true);
    }
}
