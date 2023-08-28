package ui;

import repository.Connection;
import ui.Panels.*;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainWindow extends JFrame {

	// We maintain a reference here to start and close the app without leaving any resource behind
	private final Connection dbConnection;
	private final JMenuBar sidebar;
	private JMenu[] sidebarElements;
	private Container mainContainer;

	private JPanel activePanel;
	private String activePopUp;
	private JPopupMenu currentPopupMenu;
    // We should create the home panel and delete this
	private final JPanel homePanel = new JPanel();
	public MainWindow() {
		/*
		 * Here we create the main frame and set: - a title for the title bar - its
		 * behavior when the user clicks the red X - its size - the location where the
		 * window appears (center of the screen) finally we display the window
		 */
		this.setTitle("No pierdan la volunta wachos");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 500));
		this.setLocationRelativeTo(null);
		mainContainer = this.getContentPane();
		mainContainer.setBackground(Color.WHITE); // contrasting bg
		sidebar = createSidebar();
		initializePanels();
		mainContainer.add(sidebar, BorderLayout.LINE_START);
		mainContainer.add(homePanel, BorderLayout.CENTER);
		// The code bellow should be run every time the main windows (the app) closes to close the database connection
		dbConnection = Connection.getInstance();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dbConnection.shutDown();
				super.windowClosed(e);
			}
		});
		this.setVisible(true);
	}
	private void initializePanels(){
		// In this method we should create and set every panel design and set the variables for easy access
		homePanel.add(new JLabel("Hola perra"));
		homePanel.setBackground(Color.RED);
		// Don't forget to initialize the active panel
		activePopUp = "Inicio";
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
          changeActivePanel("Inicio");
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
		menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
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
		switch (title) {
		case "Usuarios" -> {
			JMenuItem modifyUserInfo = createMenuItem("Modificar información del usuario", popupMenu);
			popupMenu.add(modifyUserInfo);
			JMenuItem registrationToClass = createMenuItem("Registro a dictado de clase", popupMenu);
			popupMenu.add(registrationToClass);
			JMenuItem addNewUser = createMenuItem("Agregar nuevo usuario", popupMenu);
			popupMenu.add(addNewUser);

		}
		case "Rankings" -> {
			JMenuItem sportActivitiesRanking = createMenuItem("Actividades deportivas", popupMenu);
			JMenuItem classDictationRanking = createMenuItem("Clases dictadas", popupMenu);
			popupMenu.add(sportActivitiesRanking);
			popupMenu.add(classDictationRanking);
		}
		case "Clases" -> {
			JMenuItem classTeachingConsultation = createMenuItem("Consulta de dictado de clase", popupMenu);
			popupMenu.add(classTeachingConsultation);
		}
		case "Instituciones" -> {
            JMenuItem modifySportInstitute = createMenuItem("Modificar institución deportiva", popupMenu);
            popupMenu.add(modifySportInstitute);
            JMenuItem addInstitute = createMenuItem("Alta de institución deportiva", popupMenu);
            popupMenu.add(addInstitute);
        }
		case "Actividades" -> {
			JMenuItem addSportActivity = createMenuItem("Alta de actividad deportiva", popupMenu);
			popupMenu.add(addSportActivity);
            JMenuItem modifyActivityInfo = createMenuItem("Modificar información de actividad", popupMenu);
            popupMenu.add(modifyActivityInfo);
		}
		default -> System.out.println("You didn't add a JMenuItem");
		}
    return popupMenu;
    }


	private JMenuItem createMenuItem(String title, JPopupMenu popupMenu) {
		JMenuItem menuItem = new JMenuItem(title);
		menuItem.addActionListener(e -> {
			changeActivePanel(title);
			popupMenu.setVisible(false);
		});

		return menuItem;
	}

    private void changeActivePanel(String popUpClicked){
        // This method changes the active panel with the one chosen by the user
        if(!activePopUp.equals(popUpClicked)) {
			JPanel newPanel;
            switch (popUpClicked) {
				case "Actividades deportivas" -> newPanel = new SportActivitiesRankingPanel();
				case "Agregar nuevo usuario" -> newPanel = new NewUserPanel();
                case "Modificar información del usuario" -> newPanel = new ModifyUserDataPanel();
                case "Modificar información de actividad" -> newPanel = new ModifyActivityPanel();
                case "Consulta de dictado de clase" -> newPanel = new ClassTeachingConsultationPanel();
                case "Alta de actividad deportiva" -> newPanel = new AddSportActivityPanel();
                case "Alta de institución deportiva" -> newPanel = new AddInstitutePanel();
                case "Modificar institución deportiva" -> newPanel = new ModifySportInstitutePanel();
                case "Clases dictadas" -> newPanel = new ClassDictationRankingPanel();
                case "Registro a dictado de clase" -> newPanel = new RegistrationToClassPanel();
                default -> newPanel = homePanel;
            }

            newPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (currentPopupMenu != null) {
                        currentPopupMenu.setVisible(false);
                    }
                }
            });
            mainContainer.remove(activePanel);
            mainContainer.add(newPanel, BorderLayout.CENTER);
            mainContainer.revalidate();
            mainContainer.repaint();
			activePopUp = popUpClicked;
            activePanel = newPanel;
        }
    }
}