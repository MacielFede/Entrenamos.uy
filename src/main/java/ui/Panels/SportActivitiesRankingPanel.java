package ui.Panels;

import javax.swing.table.DefaultTableModel;

public class SportActivitiesRankingPanel extends RankingPanel{
	
	private final String 	rankingTitle 	= "Ranking de actividades deportivas"; 
	private final Object[] 	tableHeaders 	= new Object[]{"Puesto", "Nombre", "Descripción", "Costo", "Clases"};
	private final int 		visibleRows		= 10;
	private final int[] 	minColumnWidths = new int[]{50, 50, 200, 150, 75, 75};
	private final int[] 	maxColumnWidths = new int[]{50, 50, 200, 0, 75, 75};
	
	public SportActivitiesRankingPanel() {
		createTitle(rankingTitle);
		createTable(tableHeaders, visibleRows);
		setColumnWidths(tableHeaders, minColumnWidths, maxColumnWidths);
	}
}
