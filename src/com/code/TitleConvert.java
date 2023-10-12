package com.code;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleConvert {
    private JFrame frame;
    private JTextField inputField;
    private JButton editButton;
    private JButton copyButton;
    private  JButton clearButton;
    private JLabel outputLabel;

    public TitleConvert() {
        // Create main frame
        frame = new JFrame("Title Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(null);

        // Create and place components
        inputField = new JTextField(20);
        inputField.setBounds(20,25,250,30);

        editButton = new JButton("Edit");
        editButton.setBounds(300,25,70,30);

        copyButton = new JButton("Copy");
        copyButton.setBounds(300,65,70,30);

        clearButton = new JButton("Clear");
        clearButton.setBounds(300,105,70,30);

        outputLabel = new JLabel("Edited Text Will Appear Here");
        outputLabel.setBounds(20,75,280,30);

        // Add components to the frame
        frame.add(inputField);
        frame.add(editButton);
        frame.add(copyButton);
        frame.add(clearButton);
        frame.add(outputLabel);

        // Add action listener to the edit button
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                String[] words = inputText.split("[\\p{Punct}\\s]+");
                StringBuilder finalText = new StringBuilder();

                //Check if input starts with number
                //If it does - convert to _NUM_
                //If not - just start with capital letter
                if(isNumber(words[0])){
                    finalText.append("_")
                            .append(words[0])
                            .append("_");
                    for (int i = 1; i < words.length; i++) {
                        finalText.append(convertToTitleCase(words[i]));
                    }
                }
                else{
                    for (int i = 0; i < words.length; i++) {
                        finalText.append(convertToTitleCase(words[i]));
                    }
                }
                outputLabel.setText("Edited Text: " + finalText);
            }
        });

        // Add action listener to the copy button
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String editedText = outputLabel.getText().substring("Edited Text: ".length());

                // Copy to the clipboard
                StringSelection stringSelection = new StringSelection(editedText);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });

        // Add action listener to the copy button
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                inputField.setText("");
                outputLabel.setText("Edited Text Will Appear Here");
            }
        });

        frame.setVisible(true);
    }

    // Convert a word to title case
    private static String convertToTitleCase(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

    // Check if String is a number
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TitleConvert();
            }
        });
    }
}
