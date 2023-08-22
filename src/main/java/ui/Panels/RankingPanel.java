package ui.Panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class RankingPanel extends JPanel{
	protected JTable rankingTable;

	public RankingPanel() {
		setLayout(new GridBagLayout());
	}
	
	protected void createTable(Object[] tableHeaders, int visibleRows) {
		GridBagConstraints gbcTable = new GridBagConstraints();
		gbcTable.gridx = 0;
		gbcTable.gridy = 1;
		gbcTable.weightx = 0.7;
		gbcTable.fill = GridBagConstraints.HORIZONTAL;
		gbcTable.insets = new Insets(0, 15, 0, 15);
		
		DefaultTableModel model = new DefaultTableModel(tableHeaders, visibleRows);
		model.setColumnIdentifiers(tableHeaders);
		rankingTable = new JTable(model);
		this.add(new JScrollPane(rankingTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), gbcTable);
	}
	
	protected void createTitle(String rankingTitle) {
	    GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.weightx = 1; 
        gbcTitle.fill = GridBagConstraints.HORIZONTAL; 
        JLabel titleLabel = new JLabel(rankingTitle);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 18)); 
        this.add(titleLabel, gbcTitle);
	}
	
	protected void setColumnWidths(Object[] tableHeaders, int[] minColumnWidths, int[] maxColumnWidths) {
		for(int i=0; i<tableHeaders.length; i++) {
			if(minColumnWidths[i] != 0) {
				this.rankingTable.getColumn(tableHeaders[i]).setMinWidth(minColumnWidths[i]); 
			}
			if(maxColumnWidths[i] != 0) {
				this.rankingTable.getColumn(tableHeaders[i]).setMaxWidth(maxColumnWidths[i]); 
			}
		}
		DefaultTableModel model = (DefaultTableModel) this.rankingTable.getModel();
		model.fireTableDataChanged(); 
	}
}
