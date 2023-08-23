package ui.Panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.Box;
import javax.swing.JList;
import javax.swing.JTable;

public class ClassTeachingConsultationPanel extends JPanel{
	private JTable table;

	public ClassTeachingConsultationPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{84, 71, 134, 30, 82, 0};
		gridBagLayout.rowHeights = new int[]{36, 0, 14, 0, 35, 159, 53, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Consulta de dictado de clase");
		titleLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.SOUTH;
		gbc_titleLabel.gridwidth = 5;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);
		
		JLabel instituteLabel = new JLabel("Instituto");
		GridBagConstraints gbc_instituteLabel = new GridBagConstraints();
		gbc_instituteLabel.anchor = GridBagConstraints.EAST;
		gbc_instituteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_instituteLabel.gridx = 1;
		gbc_instituteLabel.gridy = 1;
		add(instituteLabel, gbc_instituteLabel);
		
		JComboBox institutesComboBox = new JComboBox();
		GridBagConstraints gbc_institutesComboBox = new GridBagConstraints();
		gbc_institutesComboBox.gridwidth = 2;
		gbc_institutesComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_institutesComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_institutesComboBox.gridx = 2;
		gbc_institutesComboBox.gridy = 1;
		add(institutesComboBox, gbc_institutesComboBox);
		
		JLabel activityLabel = new JLabel("Actividad deportiva");
		GridBagConstraints gbc_activityLabel = new GridBagConstraints();
		gbc_activityLabel.anchor = GridBagConstraints.EAST;
		gbc_activityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_activityLabel.gridx = 1;
		gbc_activityLabel.gridy = 2;
		add(activityLabel, gbc_activityLabel);
		
		JComboBox activitiesComboBox = new JComboBox();
		GridBagConstraints gbc_activitiesComboBox = new GridBagConstraints();
		gbc_activitiesComboBox.gridwidth = 2;
		gbc_activitiesComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_activitiesComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_activitiesComboBox.gridx = 2;
		gbc_activitiesComboBox.gridy = 2;
		add(activitiesComboBox, gbc_activitiesComboBox);
		
		JLabel classLabel = new JLabel("Clases");
		GridBagConstraints gbc_classLabel = new GridBagConstraints();
		gbc_classLabel.anchor = GridBagConstraints.EAST;
		gbc_classLabel.insets = new Insets(0, 0, 5, 5);
		gbc_classLabel.gridx = 1;
		gbc_classLabel.gridy = 3;
		add(classLabel, gbc_classLabel);
		
		JComboBox classesComboBox = new JComboBox();
		GridBagConstraints gbc_classesComboBox = new GridBagConstraints();
		gbc_classesComboBox.gridwidth = 2;
		gbc_classesComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_classesComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_classesComboBox.gridx = 2;
		gbc_classesComboBox.gridy = 3;
		add(classesComboBox, gbc_classesComboBox);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 3;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 5;
		add(table, gbc_table);
		// TODO Auto-generated constructor stub
	}

}
