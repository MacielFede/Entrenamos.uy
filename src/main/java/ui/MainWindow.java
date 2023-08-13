package ui;

import javax.swing.*;

public class MainWindow {

    private JFrame window;

    public MainWindow(){
        // Here we create the main frame and set:
        // - a title for the title bar
        // - its behavior when the user clicks the red X
        // - its size
        // - the location where the window appears (center of the screen)
        window = new JFrame();
        window.setTitle("No pierdan la volunta wachos");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800,500);
        window.setLocationRelativeTo(null);
    }

    public void show(){
        window.setVisible(true);
    }
}
