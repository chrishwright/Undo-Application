package com.wright.undoapp;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.EmptyStackException;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

/**
 * 
 * @author christopherwright
 *
 */
public class UndoAppGUI {
    
    private static final Logger MS_LOG = Logger.getLogger(UndoAppGUI.class.getName());
    
    private JFrame mainFrame;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton undoButton; 
    private JTextArea textArea;
    private JScrollPane jsp;
    private Stack<JButton> stack = new Stack<>();
    ColorGenerator colGen = new ColorGenerator();
    
    UndoAppGUI() {}
    
    public void init() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initializeGUI();
            }
        });
    }
    
    private void initializeGUI() {
        MS_LOG.info("Initializing GUI...");
        
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            MS_LOG.error(e.getMessage());
        }
        
        mainFrame = new JFrame("Undo Application");
        mainFrame.setSize(600, 400);
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initializeButtons();
        addComponentsToFrame(mainFrame.getContentPane());
        
        mainFrame.setVisible(true);
    }
    
    private void addComponentsToFrame(Container pane) {
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(5, 5, 5, 5);
        c.ipady = 200;
        c.ipadx = 50;
        c.gridheight = 2;
        
        c.gridx = 0;
        c.gridy = 0;
        pane.add(button1, c);
        
        c.gridx = 1;
        c.gridy = 0;
        pane.add(button2, c);
        
        c.gridx = 2;
        c.gridy = 0;
        pane.add(button3, c);
        
        c.ipady = 0;
        c.gridheight = 1;
        
        c.gridx = 3;
        c.gridy = 0;
        pane.add(jsp, c);
        
        c.gridx = 3;
        c.gridy = 1;
        pane.add(undoButton, c);

    }
    
    private void initializeButtons() {
        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        undoButton = new JButton("Undo");
        
        button1.setBackground(Color.BLACK);
        button2.setBackground(Color.BLACK);
        button3.setBackground(Color.BLACK);
        
        button1.setForeground(Color.WHITE);
        button2.setForeground(Color.WHITE);
        button3.setForeground(Color.WHITE);
        
        mainFrame.add(button1);
        mainFrame.add(button2);
        mainFrame.add(button3);
        mainFrame.add(undoButton);
        
        button1.addActionListener(e -> {
            updateButton(button1);
            MS_LOG.info("Button1 clicked...");
            updateTextArea();
        });
        button2.addActionListener(e -> {
            updateButton(button2);
            MS_LOG.info("Button2 clicked...");
            updateTextArea();
        });
        button3.addActionListener(e -> {
            updateButton(button3);
            MS_LOG.info("Button3 clicked...");
            updateTextArea();
        });
        undoButton.addActionListener(e -> {
            MS_LOG.info("UndoButton clicked...");
            try {
                restoreButton();
                updateTextArea();
            }
            catch (RuntimeException runtimeException) {
                MS_LOG.warn(runtimeException.getMessage());
            }
        });
        
        textArea = new JTextArea(12,19);
        jsp = new JScrollPane(textArea, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
    
    private void updateButton(JButton button) {
        JButton tempButton = new JButton();
        tempButton.setBackground(button.getBackground());
        tempButton.setText(button.getText());
        stack.push(tempButton);
        button.setBackground(colGen.generateRandomColor());
    }
    
    private void updateTextArea() {
        StringBuilder newText = new StringBuilder();
        textArea.setText("");
        
        for (JButton button : stack) {
            newText.append(button.getText() + ": " + button.getBackground() + "\n");
        }
        
        textArea.setText(newText.toString());
    }
    
    private void restoreButton() {
        JButton tempButton = new JButton();
        
        try {
            tempButton = stack.pop();
        }
        catch (EmptyStackException emptyStackException) {
            // TODO: Change to custom exception type
            throw new RuntimeException("The stack is empty");
        }
        
        String buttonName = tempButton.getText();
        switch(buttonName) {
            case "1":
                button1.setBackground(tempButton.getBackground());
                break;
            case "2":
                button2.setBackground(tempButton.getBackground());
                break;
            case "3":
                button3.setBackground(tempButton.getBackground());
                break;
            default:
                MS_LOG.info("Stack is empty.");
        }
    }
    
}
