package Controllers;

import CharsHolder.CharsList;
import Generators.PasswordGenerator;
import view.Window;
import mailSender.sendMail;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileOutputStream;
import java.io.IOException;


//import java.awt.*;


public class WindowController extends Window {
    private CharsList charsList= new CharsList();

    public WindowController(){
        super();
        setVisible(true);
        setTitle("Generator Haseł");
        getPasswordDisplay().setEditable(false);
        printCharList();
        initButtons();
        setResizable(false);
        initSpinners();
        updateContent();
    }
    private void initSpinners(){
        getDigitsQuantity().addChangeListener(e -> {
            updateContent();
        });
        getSpecialQuantity().addChangeListener(e -> {
            updateContent();
        });
        getLowerCaseQuantity().addChangeListener(e -> {
            updateContent();
        });
        getUpperCaseQuantity().addChangeListener(e -> {
            updateContent();
        });
        getAlgorithmChooser().addActionListener(e->{
            checkChosenAlgorithm();
        });
    }
    private void checkChosenAlgorithm(){
        if(getAlgorithmChooser().getSelectedIndex()==2){
            getAllowedChar().setEditable(true);
            getNumberOfCharacters().setEditable(true);
            getSpecialQuantity().setValue(0);
            getDigitsQuantity().setValue(0);
            getLowerCaseQuantity().setValue(0);
            getUpperCaseQuantity().setValue(0);
            getAllowedChar().setText("Wprowadz własne znaki!\nWprowadz liczbe znakow!");}
        else{
            getAllowedChar().setEditable(false);
            updateContent();}
    }
    private void initButtons(){
        getGenerate().addActionListener(e -> {
            int quantity= (int) getPasswordQuantity().getValue();
            StringBuilder passwords=new StringBuilder();
            //System.out.println(getAlgorithmChooser().getSelectedIndex());
            switch (getAlgorithmChooser().getSelectedIndex()){
                case 0:
                    for(int x = 0; x<quantity; x++){
                        passwords.append(PasswordGenerator.GeneratePasswordWithRandom(
                                (int) getUpperCaseQuantity().getValue(),
                                (int) getLowerCaseQuantity().getValue(),
                                (int) getDigitsQuantity().getValue(),
                                (int) getSpecialQuantity().getValue()
                        )).append('\n');
                    }
                    break;
                case 1:
                    for(int x = 0; x<quantity; x++){
                        passwords.append(PasswordGenerator.GeneratePasswordWithSecureRandom(
                                (int) getUpperCaseQuantity().getValue(),
                                (int) getLowerCaseQuantity().getValue(),
                                (int) getDigitsQuantity().getValue(),
                                (int) getSpecialQuantity().getValue()
                        )).append('\n');
                    }
                    break;
                case 2:
                    for(int x = 0; x<quantity; x++){
                    passwords.append(PasswordGenerator.GeneratePasswordWithOwnChar(
                            Integer.parseInt(getNumberOfCharacters().getText()),
                            getAllowedChar().getText())).append('\n');
                    }
                    break;
                default:
                    throw new IndexOutOfBoundsException("Algorytm bez deklaracji");

            }
            System.out.println(passwords);
            getPasswordDisplay().setText(passwords.toString());
        });
        getCopyPassword().addActionListener(e-> {
            coppyPasswordsToClipBoard();
        });
        getSentToEmail().addActionListener(e-> {
            sendMail.sendEmail(getEmailAdress().getText(),getPasswordDisplay().getText());
        });
        getSaveToFile().addActionListener(e->{
            savePasswordsToFile();
        });
    }
    private void coppyPasswordsToClipBoard(){
        String myString = getPasswordDisplay().getText();
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    private void savePasswordsToFile(){
        try {
            FileOutputStream out = new FileOutputStream("passwords.txt");
            byte[] strToByte = getPasswordDisplay().getText().getBytes();
            out.write(strToByte);
            out.close();
            System.out.println("zapisano!");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    private void updateContent(){
        printCharList();
        int digits=(int)getUpperCaseQuantity().getValue()+(int)getLowerCaseQuantity().getValue()+
                (int)getSpecialQuantity().getValue()+(int)getDigitsQuantity().getValue();
        getNumberOfCharacters().setText(String.valueOf(digits));
    }
    private void printCharList(){
        StringBuilder charsToView= new StringBuilder();
        if((int)getUpperCaseQuantity().getValue()>0){
            for(char x: charsList.getUpperList()){
                charsToView.append(x);
            }
        }
        if((int)getLowerCaseQuantity().getValue()>0){
            for(char x: charsList.getLowerList()){
                charsToView.append(x);
            }
        }
        if((int)getDigitsQuantity().getValue()>0){
            for(char x: charsList.getDigitList()){
                charsToView.append(x);
            }
        }
        if((int)getSpecialQuantity().getValue()>0){
            for(char x: charsList.getSymbolsList()){
                charsToView.append(x);
            }
        }
        if(charsToView.length()==0) charsToView.append("Opcja standardowa!\nDuże litery:1\nmałe litery:5\ncyfry:1\nznaki specjalne:1");
        getAllowedChar().setText(String.valueOf(charsToView));
    }

}
