package com.wright.undoapp;

import org.apache.log4j.Logger;

/**
 * Undo Example Application
 * @author christopherwright
 *
 */
public class App
{
    
    private static final Logger MS_LOG = Logger.getLogger(App.class.getName());
    
    public static void main( String[] args )
    {
        MS_LOG.info("Starting App...");
        UndoAppGUI gui = new UndoAppGUI();
        gui.init();
    }
    
}
