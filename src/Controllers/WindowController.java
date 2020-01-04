package Controllers;

import CharsHolder.CharsList;
import Generators.PasswordGenerator;
import view.Window;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

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
            cheackChooseedAlgorithm();
        });
    }
    private void cheackChooseedAlgorithm(){
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
            sendEmail();
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
    private void sendEmail(){
        String to= "";
        if(getEmailAdress().getText().contains("@")) to = getEmailAdress().getText();
        else return;
        String from = "senderEmail@gmail.pl";
        final String username = "username";//change accordingly
        final String password = "passwoerd";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        //String host = "relay.jangosmtp.net";

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mailtrap.io");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Generated Password");

            // Now set the actual message
            message.setText("Hello, there are your generated passwords:\n" +
                    getPasswordDisplay().getText());

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
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
