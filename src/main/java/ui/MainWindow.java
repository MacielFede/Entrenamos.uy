package ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{

    private JPanel mainPanel;
    private JMenuBar sidebar;
    private JMenu homeMenu;

    public MainWindow(){
        // Here we create the main frame and set:
        // - a title for the title bar
        // - its behavior when the user clicks the red X
        // - its size
        // - the location where the window appears (center of the screen)
        // finally we display the window
        this.setTitle("No pierdan la volunta wachos");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setContentPane(mainPanel);
        initialize();
//        Sidebar sidebar = new Sidebar();
//        scrollPanel.getViewport().add(sidebar);
        // The code bellow should be run every time the main windows (the app) closes to close the database conection
//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                super.windowClosed(e);
//                gr.close();
//            }
//        });
    }

    private void initialize(){
        sidebar = new JMenuBar();
        sidebar.setLayout(new GridLayout(0,1));
        homeMenu = new JMenu("Inicio");
        sidebar.add(homeMenu);
        this.setJMenuBar(sidebar);
    }
}


import java.awt.*;
        import java.awt.event.*;
        import javax.swing.*;

/**
 * @author ges
 * @author kwalrath
 */
/* MenuLayoutDemo.java requires no other files. */

public class MenuLayoutDemo {
    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
        menuBar.add(createMenu("Menu 1"));
        menuBar.add(createMenu("Menu 2"));
        menuBar.add(createMenu("Menu 3"));

        menuBar.setBorder(BorderFactory.createMatteBorder(0,0,0,1,
                Color.BLACK));
        return menuBar;
    }

    // used by createMenuBar
    public JMenu createMenu(String title) {
        JMenu m = new HorizontalMenu(title);
        m.add("Menu item #1 in " + title);
        m.add("Menu item #2 in " + title);
        m.add("Menu item #3 in " + title);

        JMenu submenu = new HorizontalMenu("Submenu");
        submenu.add("Submenu item #1");
        submenu.add("Submenu item #2");
        m.add(submenu);

        return m;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MenuLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MenuLayoutDemo demo = new MenuLayoutDemo();
        Container contentPane = frame.getContentPane();
        contentPane.setBackground(Color.WHITE); //contrasting bg
        contentPane.add(demo.createMenuBar(),
                BorderLayout.LINE_START);

        //Display the window.
        frame.setSize(300, 150);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    class HorizontalMenu extends JMenu {
        HorizontalMenu(String label) {
            super(label);
            JPopupMenu pm = getPopupMenu();
            pm.setLayout(new BoxLayout(pm, BoxLayout.LINE_AXIS));
        }

        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        public Dimension getMaximumSize() {
            return getPreferredSize();
        }

        public void setPopupMenuVisible(boolean b) {
            boolean isVisible = isPopupMenuVisible();
            if (b != isVisible) {
                if ((b==true) && isShowing()) {
                    //Set location of popupMenu (pulldown or pullright).
                    //Perhaps this should be dictated by L&F.
                    int x = 0;
                    int y = 0;
                    Container parent = getParent();
                    if (parent instanceof JPopupMenu) {
                        x = 0;
                        y = getHeight();
                    } else {
                        x = getWidth();
                        y = 0;
                    }
                    getPopupMenu().show(this, x, y);
                } else {
                    getPopupMenu().setVisible(false);
                }
            }
        }
    }
}