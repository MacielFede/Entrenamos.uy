package org.FmFvGMA;

import ui.MainWindow;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        // * This ensures that all our swing code runs in the same thread (called the EventDispatchThread)
        // * We must do it this way because Swing isn't thread save (we do this once and never again)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow main = new MainWindow();
                main.show();
            }
        });

    }
}