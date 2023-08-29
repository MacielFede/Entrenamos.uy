package ui.Panels;

import java.util.List;
import javax.swing.SwingConstants;

import dataTypes.DtClass;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;

public class ClassDictationRankingPanel extends RankingPanel{
	
	private final String 	rankingTitle 	= "Ranking de dictados de clases"; 
	private final Object[] 	tableHeaders 	= new Object[]{"Puesto", "Nombre", "Fecha", "Url", "Cant. Socios"};
	private final int[] 	minColumnWidths = new int[]{50, 200, 150, 150, 50};
	private final int[] 	maxColumnWidths = new int[]{0, 0, 0, 0, 0};
	private final int		alignment		= SwingConstants.CENTER;
	private final int 		maxVisibleRows	= 10;
	
	public ClassDictationRankingPanel() {
		setMaxVisibleRows(maxVisibleRows);
		createTitle(rankingTitle);
		createTable(tableHeaders, alignment);
		setColumnWidths(tableHeaders, minColumnWidths, maxColumnWidths);
		
		ControllerFactory controllerFactory = ControllerFactory.getInstance();
		InstituteInterface ic = controllerFactory.getInstituteInterface();
		
		List<DtClass> classes = ic.listClassesDictationRanking();
		addRowFromDtClass(classes);
	}
	
	private void addRowFromDtClass(List<DtClass> classes) {
	    int i = 1;
		for(DtClass dt : classes) {
			addRowToTable(
					new Object[]
						{
							i,
		                    dt.getName(),
		                    dt.getDateAndTime().toString(),
		                    dt.getUrl(),
		                    dt.getEnrollmentsQuantity()
						});
			i++;
		}
	}
}
