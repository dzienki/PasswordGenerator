package view;

import javax.swing.*;

public class Window extends JFrame{
    private static final int WIDTH=600;
    private static final int HEIGHT=600;

    private JPanel panel1;
    private JTextArea passwordDisplay;
    //private JSpinner passwordLength;
    private JSpinner passwordQuantity;
    private JSpinner digitsQuantity;
    private JSpinner specialQuantity;
    private JSpinner upperCaseQuantity;
    private JSpinner lowerCaseQuantity;
    private JComboBox<String> algorithmChooser;
    private JButton generate;
    private JButton copyPassword;
    private JButton saveToFile;
    private JButton sentToEmail;
    private JTextArea allowedChar;
    private JTextField numberOfCharacters;
    private JTextField emailAdress;


    public Window(){
        setSize(WIDTH,HEIGHT);
        setContentPane(panel1);
        setLocationRelativeTo(null);

    }


    public JTextField getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public JTextArea getPasswordDisplay() {
        return passwordDisplay;
    }


    public JSpinner getPasswordQuantity() {
        return passwordQuantity;
    }

    public JSpinner getDigitsQuantity() {
        return digitsQuantity;
    }

    public JSpinner getSpecialQuantity() {
        return specialQuantity;
    }

    public JSpinner getUpperCaseQuantity() {
        return upperCaseQuantity;
    }

    public JSpinner getLowerCaseQuantity() {
        return lowerCaseQuantity;
    }

    public JComboBox<String> getAlgorithmChooser() {
        return algorithmChooser;
    }

    public JButton getGenerate() {
        return generate;
    }

    public JButton getCopyPassword() {
        return copyPassword;
    }

    public JButton getSaveToFile() {
        return saveToFile;
    }

    public JButton getSentToEmail() {
        return sentToEmail;
    }

    public JTextArea getAllowedChar() {
        return allowedChar;
    }

    private void createUIComponents() {
        //passwordLength=new JSpinner(modelTau);
        digitsQuantity=new JSpinner(new SpinnerNumberModel(2, 0, 1000, 1));
        specialQuantity=new JSpinner(new SpinnerNumberModel(2, 0, 1000, 1));
        upperCaseQuantity=new JSpinner(new SpinnerNumberModel(2, 0, 1000, 1));
        lowerCaseQuantity=new JSpinner(new SpinnerNumberModel(2, 0, 1000, 1));
        passwordQuantity=new JSpinner(new SpinnerNumberModel(2, 0, 1000, 1));
        algorithmChooser=new JComboBox<>(new String[]{"Random Generator", "Secure Generator", "Your own chars"});
        // TODO: place custom component creation code here
    }

    public JTextField getEmailAdress() {
        return emailAdress;
    }
}
