/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptdecrypt;

/**
 *
 * @author Nirzak
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class EncryptDecrypt extends JPanel implements ActionListener {

    //Labels to identify the fields
    private JLabel KeyLabel;
    private JLabel TypeLabel;
    private JLabel FileLabel;
    private JLabel Imagelabel;

    //Strings for the labels
    private static String KeyString = "Encode/Decode Key  : ";
    private static String TypeString = "Encryption  Type      : ";
    private static String FileString = "Path   to   File           : ";

    //Fields for data entry
    private JTextField KeyField;
    private JTextField FileField;
    private JComboBox TypeList;

    //Button for access
    private JButton btnEncode;
    private JButton btnDecode;
    private JButton btnFile;

    // Font for writting
    private Font labelFont;
    private Font textFont;
    private Font buttonFont;
    private Font ComboFont;
    
    // For using hand cursor
    private  Cursor cursor;
    
    //For showing header image
    private ImageIcon image;
    
    
    public EncryptDecrypt() {
        // TypeArray for Combo Box
        String[] TypeArr = {"RC4", "DES", "3DES"};

        // Block for controling fonts
        labelFont = new Font("TIMES NEW ROMAN", Font.BOLD, 18);
        textFont = new Font("TIMES NEW ROMAN", Font.BOLD, 20);
        buttonFont = new Font("TIMES NEW ROMAN", Font.BOLD, 22);
        
        // Creating hand cursor 
        cursor= new Cursor(Cursor.HAND_CURSOR);
        
        //Creating image & imagelabel
        image= new ImageIcon(getClass().getResource("Project_image.jpg"));
        
        //Creating the labels & set their properties
        Imagelabel= new JLabel(image);
        Imagelabel.setBounds(0,0, 900, 200);
        
        
        KeyLabel = new JLabel(KeyString);
        KeyLabel.setBounds(10, 220, 175, 40);
        KeyLabel.setBackground(Color.BLACK);
        KeyLabel.setOpaque(true);
        KeyLabel.setForeground(Color.orange);
        KeyLabel.setFont(labelFont);

        TypeLabel = new JLabel(TypeString);
        TypeLabel.setBounds(10, 270, 175, 40);
        TypeLabel.setBackground(Color.black);
        TypeLabel.setOpaque(true);
        TypeLabel.setForeground(Color.orange);
        TypeLabel.setFont(labelFont);

        FileLabel = new JLabel(FileString);
        FileLabel.setBounds(10, 320, 175, 40);
        FileLabel.setBackground(Color.black);
        FileLabel.setOpaque(true);
        FileLabel.setForeground(Color.orange);
        FileLabel.setFont(labelFont);

        //Creating the text fields and set their properties.
        KeyField = new JTextField("");
        KeyField.setBounds(220, 220, 500, 40);
        KeyField.setBackground(Color.black);
        KeyField.setForeground(Color.green);
        KeyField.setFont(textFont);
        KeyField.setToolTipText("Please input key more than 8 characters");

        TypeList = new JComboBox(TypeArr);
        TypeList.setBounds(220, 270, 500, 40);
        TypeList.setBackground(Color.black);
        TypeList.setForeground(Color.green);
        TypeList.setToolTipText("Please use the Combo Box to use other methods");
        TypeList.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 24));
        
        TypeList.setSelectedIndex(0);

        FileField = new JTextField("");
        FileField.setBounds(220, 320, 500, 40);
        FileField.setBackground(Color.black);
        FileField.setForeground(Color.green);
        FileField.setCursor(cursor);
        FileField.setFont(textFont);

        //Creating buttons & set their properties.
        btnEncode = new JButton("Encode File");
        btnEncode.setBounds(20, 370, 160, 50);
        btnEncode.setBackground(Color.black);
        btnEncode.setForeground(Color.yellow);
        btnEncode.setFont(buttonFont);
        btnEncode.setCursor(cursor);
        btnEncode.setToolTipText("Click here to encode the file");
        btnEncode.setVerticalTextPosition(AbstractButton.CENTER);
        btnEncode.setHorizontalTextPosition(AbstractButton.CENTER);
        btnEncode.addActionListener(this);

        btnDecode = new JButton("Decode File");
        btnDecode.setBounds(220, 370, 500, 50);
        btnDecode.setBackground(Color.black);
        btnDecode.setForeground(Color.yellow);
        btnDecode.setFont(buttonFont);
        btnDecode.setCursor(cursor);
        btnDecode.setToolTipText("Click here to decode the file");
        btnDecode.setVerticalTextPosition(AbstractButton.CENTER);
        btnDecode.setHorizontalTextPosition(AbstractButton.CENTER);
        btnDecode.addActionListener(this);

        btnFile = new JButton("Select File");
        btnFile.setBounds(720, 320, 150, 50);
        btnFile.setBackground(Color.black);
        btnFile.setForeground(Color.yellow);
        btnFile.setFont(buttonFont);
        btnFile.setCursor(cursor);
        btnFile.setToolTipText("Click here to select the file");
        btnFile.setVerticalTextPosition(AbstractButton.CENTER);
        btnFile.setHorizontalTextPosition(AbstractButton.CENTER);
        btnFile.addActionListener(this);


        // Setting panel components to panel
        this.add(Imagelabel);
        this.add(KeyLabel);
        this.add(TypeLabel);
        this.add(FileLabel);

        this.add(KeyField);
        this.add(TypeList);
        this.add(FileField);

        this.add(btnFile);
        this.add(btnEncode);
        this.add(btnDecode);

        this.setBounds(0, 0, 900, 500);
        this.setLayout(null);
        this.setBackground(Color.darkGray);
    }
        //Action Event Block
    @Override
    public void actionPerformed(ActionEvent e) {
        //action event for encoding
        if (e.getSource() == btnEncode) {
            String key = KeyField.getText();
            String filePath = FileField.getText();
            int typeKey = TypeList.getSelectedIndex();

            if (key.length() < 8 && filePath.length() < 1) {
                JOptionPane.showMessageDialog(this, "Error. The key length must be 8 char minimum and specify a path to the file.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                switch (typeKey) {
                    case 0:
                        //encode RC4
                        new RC4().Encode(key, filePath);
                        break;
                    case 1:
                        //encode DES
                        new DES().Encode(key, filePath);
                        break;
                    case 2:
                        //encode 3DES
                        new TripleDES().Encode(key, filePath);
                        break;
                }
            }
        } //action event for decoding
        else if (e.getSource() == btnDecode) {
            String key = KeyField.getText();
            String filePath = FileField.getText();
            int typeKey = TypeList.getSelectedIndex();

            if (key.length() < 8 && filePath.length() < 1) {
                JOptionPane.showMessageDialog(this, "Error. The key length must be 8 char minimum and specify a path to the file.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                switch (typeKey) {
                    case 0:
                        //decode RC4
                        new RC4().Decode(key, filePath);
                        break;
                    case 1:
                        //decode DES
                        new DES().Decode(key, filePath);
                        break;
                    case 2:
                        //decode 3DES
                        new TripleDES().Decode(key, filePath);
                        break;

                }
            }
        } //action event for choosing file to encrypt or decrypt
        else if (e.getSource() == btnFile) {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(EncryptDecrypt.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                FileField.setText(file.getPath());
            }
        }
    }
    //Creating and showing frame
    private static void createAndShowFrame() {
        JFrame frame = new JFrame("EncryptDecrypt Master");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(250, 250, 900, 500);
        frame.setResizable(false);
        frame.add(new EncryptDecrypt());
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowFrame();
            }
        });
    }
}
