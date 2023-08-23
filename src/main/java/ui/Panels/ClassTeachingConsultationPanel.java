package ui.Panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ClassTeachingConsultationPanel extends JPanel{
	private JTable classDataTable;
	private JComboBox<String> institutesComboBox 	= new JComboBox<String>();
	private JComboBox<String> activitiesComboBox 	= new JComboBox<String>();
	private JComboBox<String> classesComboBox		= new JComboBox<String>();
	private String[] tableHeaders 					= new String[] {"Atributo", "Valor"};
	private final String nonSelectedOption			= "Sin seleccionar"; 
	private String selectedInstitute 				= nonSelectedOption;
	private String selectedActivity					= nonSelectedOption;
	private String selectedClass					= nonSelectedOption;

	public ClassTeachingConsultationPanel() {
		initialize();
		setListeners();
		agregarElementosPrueba();
	}
	
	private void initialize() {
		setPanelLayout();
		setTitleLabel("Consulta de dictado de clase", "Calibri", Font.BOLD, 18);
		institutesComboBox 	= createLabelComboBox("Institutos", 1);
		activitiesComboBox	= createLabelComboBox("Actividad deportiva", 2);
		classesComboBox 	= createLabelComboBox("Clases", 3);
		activitiesComboBox.setEnabled(false);
		classesComboBox.setEnabled(false);
		createDataTable();
	}
	
	private void agregarElementosPrueba() {
		institutesComboBox.addItem(nonSelectedOption);
		activitiesComboBox.addItem(nonSelectedOption);
		classesComboBox.addItem(nonSelectedOption);
		for(int i=0; i < 5; i++) {
			institutesComboBox.addItem("Instituto " + i);
		}
		for(int i=0; i < 5; i++) {
			activitiesComboBox.addItem("Actividad deportiva " + i);
		}
		for(int i=0; i < 5; i++) {
			classesComboBox.addItem("Clase " + i);
		}
	}
	
	private void setListeners() {
		institutesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!institutesComboBox.getSelectedItem().toString().equals(selectedInstitute)) {
            		if(institutesComboBox.getSelectedItem().toString().equals(nonSelectedOption)) {
                		activitiesComboBox.setEnabled(false);
                		classesComboBox.setEnabled(false);
                	}
                	else {
                		activitiesComboBox.setEnabled(true);
                		classesComboBox.setEnabled(false);
                	}
            		selectedActivity = nonSelectedOption;
            		activitiesComboBox.setSelectedItem(selectedActivity);
            		selectedClass = nonSelectedOption;
            		classesComboBox.setSelectedItem(selectedClass);
            		classDataTable.setVisible(false);
                	selectedInstitute = institutesComboBox.getSelectedItem().toString();
            	}
            }
        });
		
		activitiesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!activitiesComboBox.getSelectedItem().toString().equals(selectedActivity)) {
            		if(activitiesComboBox.getSelectedItem().toString().equals(nonSelectedOption)) {
                		classesComboBox.setEnabled(false);
                	}
                	else {
                		classesComboBox.setEnabled(true);
                	}
            		selectedClass = nonSelectedOption;
            		classesComboBox.setSelectedItem(selectedClass);
            		classDataTable.setVisible(false);
            		selectedActivity = activitiesComboBox.getSelectedItem().toString();
            	}
            }
        });
		
		classesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!classesComboBox.getSelectedItem().toString().equals(selectedClass)) {
            		if(classesComboBox.getSelectedItem().toString().equals(nonSelectedOption)) {
            			classDataTable.setVisible(false);
                	}
                	else {
                		classDataTable.setVisible(true);
                	}
            		selectedClass = classesComboBox.getSelectedItem().toString();
            	}
            }
        });
	}
	
	private void setPanelLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{91, 0, 80, 83, 0, 14, -62};
		gridBagLayout.rowHeights = new int[]{38, 38, 38, 0, 10, 143, 53, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
	}
	
	private void setTitleLabel(String title, String fontName, int fontType, int fontSize) {
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font(fontName, fontType, fontSize));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.SOUTH;
		gbc_titleLabel.gridwidth = 7;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);
	}
	
	private JComboBox<String> createLabelComboBox(String labelTitle, int gridy) {
		JLabel genericLabel = new JLabel(labelTitle);
		GridBagConstraints gbc_Label = new GridBagConstraints();
		gbc_Label.anchor = GridBagConstraints.EAST;
		gbc_Label.insets = new Insets(0, 0, 5, 5);
		gbc_Label.gridx = 0;
		gbc_Label.gridy = gridy;
		add(genericLabel, gbc_Label);
		
		JComboBox<String> genericComboBox = new JComboBox<String>();
		GridBagConstraints gbc_institutesComboBox = new GridBagConstraints();
		gbc_institutesComboBox.gridwidth = 4;
		gbc_institutesComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_institutesComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_institutesComboBox.gridx = 2;
		gbc_institutesComboBox.gridy = gridy;
		add(genericComboBox, gbc_institutesComboBox);
		
		return genericComboBox;
	}
	
	private void createDataTable() {
		DefaultTableModel model = new DefaultTableModel(0, tableHeaders.length);
		model.setColumnIdentifiers(tableHeaders);
		
	    
		
		classDataTable = new JTable(model);
		
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		classDataTable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        cellRenderer.setHorizontalAlignment(SwingConstants.LEFT);
		classDataTable.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		
		JScrollPane scrollPane = new JScrollPane(classDataTable);
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 4;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 5;
		
		int rowCount = 5;
		int rowHeight = classDataTable.getRowHeight();
		int headerHeight = classDataTable.getTableHeader().getPreferredSize().height;
	    int totalHeight = (rowHeight * rowCount) + headerHeight;
		Dimension preferredSize = new Dimension(classDataTable.getPreferredSize().width, totalHeight);
		classDataTable.setPreferredScrollableViewportSize(preferredSize);
		
		add(scrollPane, gbc_table);
		//classDataTable.setVisible(false);
	}
}
