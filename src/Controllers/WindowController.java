package Controllers;

import CharsHolder.CharsList;
import Generators.RandomGenerator;
import view.Window;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.Toolkit;
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
        getPasswordQuantity().addChangeListener(e -> {
            //System.out.println("value changed: " + getPasswordQuantity().getValue());
            //updateContent();
        });
        getDigitsQuantity().addChangeListener(e -> {
            //System.out.println("value changed: " + getPasswordQuantity().getValue());
            updateContent();
        });
        getSpecialQuantity().addChangeListener(e -> {
            //System.out.println("value changed: " + getSpecialQuantity().getValue());
            updateContent();
        });
        getLowerCaseQuantity().addChangeListener(e -> {
            //System.out.println("value changed: " + getPasswordQuantity().getValue());
            updateContent();
        });
        getUpperCaseQuantity().addChangeListener(e -> {
            //System.out.println("value changed: " + getPasswordQuantity().getValue());
            updateContent();
        });
        getAlgorithmChooser().addActionListener(e->{
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
        });
    }
    private void initButtons(){
        getGenerate().addActionListener(e -> {
            int quantity= (int) getPasswordQuantity().getValue();
            StringBuilder passwords=new StringBuilder();
            //System.out.println(getAlgorithmChooser().getSelectedIndex());
            switch (getAlgorithmChooser().getSelectedIndex()){
                case 0:
                    for(int x = 0; x<quantity; x++){
                        passwords.append(RandomGenerator.GeneratePassword(
                                (int) getUpperCaseQuantity().getValue(),
                                (int) getLowerCaseQuantity().getValue(),
                                (int) getDigitsQuantity().getValue(),
                                (int) getSpecialQuantity().getValue()
                        )).append('\n');
                    }
                    break;
                case 1:
                    for(int x = 0; x<quantity; x++){
                        passwords.append(RandomGenerator.SecureGeneratePassword(
                                (int) getUpperCaseQuantity().getValue(),
                                (int) getLowerCaseQuantity().getValue(),
                                (int) getDigitsQuantity().getValue(),
                                (int) getSpecialQuantity().getValue()
                        )).append('\n');
                    }
                    break;
                case 2:
                    for(int x = 0; x<quantity; x++){
                    passwords.append(RandomGenerator.OwnCharGeneratePassword(
                            Integer.parseInt(getNumberOfCharacters().getText()),
                            getAllowedChar().getText())).append('\n');

            }}
            System.out.println(passwords);
            getPasswordDisplay().setText(passwords.toString());
        });
        getCopyPassword().addActionListener(e-> {
            String myString = getPasswordDisplay().getText();
            StringSelection stringSelection = new StringSelection(myString);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        });
        getSentToEmail().addActionListener(e-> {
        sendEmail();
        }
        );
        getSaveToFile().addActionListener(e->{
            try {
                FileOutputStream out = new FileOutputStream("passwords.txt");
                byte[] strToByte = getPasswordDisplay().getText().getBytes();
                out.write(strToByte);
                out.close();
                System.out.println("zapisano!");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
    }
    private void sendEmail(){
        String to= "";
        // Recipient's email ID needs to be mentioned.
        if(getEmailAdress().getText().contains("@")) to = getEmailAdress().getText();
        else return;
        // Sender's email ID needs to be mentioned
        String from = "widek1234@o2.pl";
        final String username = "widek1234@o2.pl";//change accordingly
        final String password = "o2jestfajne321";//change accordingly

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
            message.setSubject("Testing Subject");

            // Now set the actual message
            message.setText("Hello, this is sample for to check send " +
                    "email using JavaMailAPI ");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    private void updateContent(){
        printCharList();
        int digits=(int)getUpperCaseQuantity().getValue()+(int)getLowerCaseQuantity().getValue()+
                (int)getSpecialQuantity().getValue()+(int)getDigitsQuantity().getValue();
        getNumberOfCharacters().setText(String.valueOf(digits));
    }
    public void printCharList(){
        StringBuilder charsToView= new StringBuilder();
        if((int)getUpperCaseQuantity().getValue()>0){
            for(char x: charsList.getUpperCase()){
                charsToView.append(x);
            }
        }
        if((int)getLowerCaseQuantity().getValue()>0){
            for(char x: charsList.getLowerCase()){
                charsToView.append(x);
            }
        }
        if((int)getDigitsQuantity().getValue()>0){
            for(char x: charsList.getDigits()){
                charsToView.append(x);
            }
        }
        if((int)getSpecialQuantity().getValue()>0){
            for(char x: charsList.getSymbols()){
                charsToView.append(x);
            }
        }
        if(charsToView.length()==0) charsToView.append("Opcja standardowa!\nDuże litery:1\nmałe litery:5\ncyfry:1\nznaki specjalne:1");
        //System.out.println(charsToView.length());
        getAllowedChar().setText(String.valueOf(charsToView));
    }

}
