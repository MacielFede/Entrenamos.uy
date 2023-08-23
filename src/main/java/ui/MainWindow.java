package ui;

import ui.panels.ModifyUserDataPanel;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import ui.Panels.SportActivitiesRankingPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


public class MainWindow extends JFrame {

    private final JMenuBar sidebar;
    private JMenu [] sidebarElements;
    private Container mainContainer;

    private JPanel activePanel;
    private JPopupMenu currentPopupMenu;

    private JPanel homePanel = new JPanel();
    private SportActivitiesRankingPanel sportActivitiesRankingPanel = new SportActivitiesRankingPanel();
    private JPanel classesTaughtRankingPanel = new JPanel();

    private JPanel modifyUserDataPanel = new ModifyUserDataPanel();

    public MainWindow() {
        /* Here we create the main frame and set:
        * - a title for the title bar
        * - its behavior when the user clicks the red X
        * - its size
        * - the location where the window appears (center of the screen)
        finally we display the window */
        this.setTitle("No pierdan la volunta wachos");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 500));
        this.setLocationRelativeTo(null);
        mainContainer = this.getContentPane();
        mainContainer.setBackground(Color.WHITE); //contrasting bg
        sidebar = createSidebar();
        initializePanels();
        mainContainer.add(sidebar,
                BorderLayout.LINE_START);
        mainContainer.add(homePanel, BorderLayout.CENTER);
        this.setVisible(true);
        // The code bellow should be run every time the main windows (the app) closes to close the database conection
//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                super.windowClosed(e);
//                gr.close();
//            }
//        });
    }
    private void initializePanels(){
        // In this method we should create and set every panel design and set the variables for easy access
        homePanel.add(new JLabel("Hola perra"));
        homePanel.setBackground(Color.RED);
        classesTaughtRankingPanel.setBackground(Color.BLACK);
        classesTaughtRankingPanel.add(new JLabel("Ranking de clases dictadas"));
        // Don't forget to initialize the active panel
        activePanel = homePanel;
        activePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentPopupMenu != null) {
                    currentPopupMenu.setVisible(false);
                }
            }
        });
    }

    private JMenuBar createSidebar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
        // I do this because the "Inicio" JMenu will only display the home screen and will never change

        JMenu home = createMenu("Inicio");
        home.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                // Here we change the active JPanel and go directly to the home screen
                changeActivePanel(homePanel);
            }
            // I have to implement this 2 methods to this to work
            @Override
            public void menuDeselected(MenuEvent e) {}
            @Override
            public void menuCanceled(MenuEvent e) {}

        });
        sidebarElements = new JMenu[]{home,
                createMenu("Usuarios"),
                createMenu("Clases"),
                createMenu("Instituciones"),
                createMenu("Actividades"),
                createMenu("Rankings")};
        for (JMenu sidebarElement: sidebarElements){
            menuBar.add(sidebarElement);
        }
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
                Color.BLACK));
        return menuBar;
    }

    private JMenu createMenu(String title) {
        JMenu m = new HorizontalMenu(title);
        m.setAlignmentX(Component.LEFT_ALIGNMENT);
        m.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                	if (currentPopupMenu != null) {
    	                currentPopupMenu.setVisible(false);
    	            }
                	JPopupMenu popupMenu = createSubMenu(title);
	        		popupMenu.show(m, m.getX() + m.getWidth(), 0);
	                currentPopupMenu = popupMenu;
                }
            }
        });

        m.setMinimumSize(new Dimension(Integer.MAX_VALUE, m.getMinimumSize().height));
        return m;
    }

    private JPopupMenu createSubMenu(String title) {
    	JPopupMenu popupMenu = new JPopupMenu();
    	switch (title){
            // We must initialize again the panel to save, because
            case "Usuarios" -> {
                modifyUserDataPanel = new ModifyUserDataPanel();
                JMenuItem modifyUserInfo = createMenuItem("Modificar informacion del usuario", popupMenu, modifyUserDataPanel);
                popupMenu.add(modifyUserInfo);
            }
        case "Rankings" -> {
	        	sportActivitiesRankingPanel = new SportActivitiesRankingPanel();
                JMenuItem sportActivitiesRanking = createMenuItem("Actividades deportivas", popupMenu, sportActivitiesRankingPanel);
	        	JMenuItem classesTaughtRanking = createMenuItem("Clases dictadas", popupMenu, classesTaughtRankingPanel);
	        	popupMenu.add(sportActivitiesRanking);
	        	popupMenu.add(classesTaughtRanking);
	        }
	        default -> System.out.println("You didn't add a JMenuItem");
    	}

    	return popupMenu;
    }

    private JMenuItem createMenuItem(String title, JPopupMenu popupMenu, JPanel panelToChange) {
    	JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(e -> {
            changeActivePanel(panelToChange);
            popupMenu.setVisible(false);
        });

        return menuItem;
    }

    private void changeActivePanel(JPanel newPanel){
        // This method changes the active panel with the one chosen by the user
        if (activePanel == null || activePanel.equals(newPanel)) { return; }
        mainContainer.remove(activePanel);
        mainContainer.add(newPanel, BorderLayout.CENTER);
        mainContainer.revalidate();
        mainContainer.repaint();
        activePanel = newPanel;
        activePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentPopupMenu != null) {
                    currentPopupMenu.setVisible(false);
                }
            }
        });
    }
}