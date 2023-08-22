package ui.Panels;

import java.util.List;
import javax.swing.SwingConstants;
import dataTypes.DtActivity;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;

public class SportActivitiesRankingPanel extends RankingPanel{
	
	private final String 	rankingTitle 	= "Ranking de actividades deportivas"; 
	private final Object[] 	tableHeaders 	= new Object[]{"Puesto", "Nombre", "Descripción", "Costo", "Clases"};
	private final int[] 	minColumnWidths = new int[]{50, 200, 150, 75, 75};
	private final int[] 	maxColumnWidths = new int[]{50, 200, 0, 75, 75};
	private final int		alignment		= SwingConstants.CENTER;
	private final int 		maxVisibleRows	= 10;
	
	public SportActivitiesRankingPanel() {
		setMaxVisibleRows(maxVisibleRows);
		createTitle(rankingTitle);
		createTable(tableHeaders, alignment);
		setColumnWidths(tableHeaders, minColumnWidths, maxColumnWidths);
		
		ControllerFactory controllerFactory = ControllerFactory.getInstance();
		InstituteInterface instituteController = controllerFactory.getInstituteInterface();
		
		List<DtActivity> activities = instituteController.listSportsActivitiesRanking();
		addRowFromDtActivities(activities);
	}
	
	private void addRowFromDtActivities(List<DtActivity> activities) {
		int i = 1;
		for(DtActivity dt : activities) {
			addRowToTable(
					new Object[]
						{
							i, 
							dt.getName(), 
							dt.getDescription(), 
							dt.getPrice(), 
							dt.getClassesQuantity()
						});
			i++;
		}
	}
}
