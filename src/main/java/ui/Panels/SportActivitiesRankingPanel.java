package ui.Panels;

import javax.swing.SwingConstants;

public class SportActivitiesRankingPanel extends RankingPanel{
	
	private final String 	rankingTitle 	= "Ranking de actividades deportivas"; 
	private final Object[] 	tableHeaders 	= new Object[]{"Puesto", "Nombre", "Descripción", "Costo", "Clases"};
	private final int[] 	minColumnWidths = new int[]{50, 200, 150, 75, 75};
	private final int[] 	maxColumnWidths = new int[]{50, 200, 0, 75, 75};
	private final int		alignment		= SwingConstants.CENTER;
	private final int 		maxVisibleRows		= 10;
	
	public SportActivitiesRankingPanel() {
		setMaxVisibleRows(maxVisibleRows);
		createTitle(rankingTitle);
		createTable(tableHeaders, alignment);
		setColumnWidths(tableHeaders, minColumnWidths, maxColumnWidths);
		
		addRowToTable(0, new Object[]{"1", "Prueba", "Descripción1", "$150", "4"});
		addRowToTable(1, new Object[]{"2", "Prueba2", "Descripción2", "$159", "3"});
	}
}
