package ui;

import ui.panels.ModifyUserDataPanel;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {

    private final JMenuBar sidebar;
    private JMenu [] sidebarElements;
    private Container mainContainer;

    private JPanel activePanel;
    private final JPanel homePanel = new JPanel();
    private final JPanel professorPanel = new JPanel();

    private final JPanel modifyUserDataPanel = new ModifyUserDataPanel();

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
        professorPanel.add(new JLabel("Hola zorra"));
        professorPanel.setBackground(Color.GREEN);
        // Don't forget to initialize the active panel
        activePanel = homePanel;
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
        sidebarElements = new JMenu[]{home, createMenu("Usuarios"), createMenu("Clases"),
                createMenu("Instituciones"), createMenu("Actividades"), createMenu("Rankings")};
        for (JMenu sidebarElement: sidebarElements){
            menuBar.add(sidebarElement);
        }
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
                Color.BLACK));
        return menuBar;
    }

    private JMenu createMenu(String title) {
        JMenu m = new HorizontalMenu(title);
        m.setAlignmentX(-1);
        // Here we should add all the submenu needed (we could use a Switch statement so that if the title is ... we add some JMenuItem
        // I think this is the best to not have boilerplate code.
        // So, everytime we need a JMenuItem in a menu, we only need to add it here (with its respective action listener which you can add in its constructor)
        // ! Remember, the "Inicio" JMenu will not have submenus.
        List<JComponent> subMenus = new ArrayList<>();
        switch (title){
            case "Usuarios" -> {
                JMenu professors = new HorizontalMenu("Profesores");
                JMenu members = new HorizontalMenu("Miembros");
                JMenuItem modifyUserInfo = new JMenuItem("Modificar informacion basica");
                modifyUserInfo.addActionListener(e -> {
                    changeActivePanel(modifyUserDataPanel);
                });
                subMenus.add(modifyUserInfo);
                subMenus.add(professors);
                subMenus.add(members);
            }
            case "Profesores" -> {
                JMenuItem listProfessors = new JMenuItem("Listar profesores");
                listProfessors.addActionListener(e -> {
                    changeActivePanel(professorPanel);
                });
                subMenus.add(listProfessors);
            }
            default -> System.out.println("You didn't add a JMenuItem");
        }
        for(JComponent item: subMenus){
            m.add(item);
        }
        return m;
    }

    private void changeActivePanel(JPanel newPanel){
        // This method changes the active panel with the one chosen by the user
        if (activePanel == null || activePanel.equals(newPanel)) { return; }
        mainContainer.remove(activePanel);
        mainContainer.add(newPanel, BorderLayout.CENTER);
        mainContainer.revalidate();
        mainContainer.repaint();
        activePanel = newPanel;
    }
}