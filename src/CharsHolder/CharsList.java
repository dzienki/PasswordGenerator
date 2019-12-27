package CharsHolder;

import java.security.SecureRandom;
import java.util.Random;

public class CharsList {
    private char[] upperCase;
    private char[] lowerCase;
    private char[] digits;
    private char[] symbols;
    private String whole;
    private Random rand;
    private SecureRandom secureRandom;
    public CharsList(){
        upperCase = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        lowerCase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        digits ="0123456789".toCharArray();
        symbols ="!@#$%^&*(){}[]\\|:\";'<>?,./".toCharArray();
        whole= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*(){}[]\\|:\";'<>?,./";
        rand= new Random();
        secureRandom=new SecureRandom();

    }
    public String getWhole(){
        return whole;
    }
    public char getUpperChar(){
        return upperCase[rand.nextInt(upperCase.length)];
    }
    public char getLowerChar(){
        return lowerCase[rand.nextInt(lowerCase.length)];
    }
    public char getDigitChar(){
        return digits[rand.nextInt(digits.length)];
    }
    public char getSymbolChar(){
        return symbols[rand.nextInt(symbols.length)];
    }public char getSecUpperChar(){
        return upperCase[secureRandom.nextInt(upperCase.length)];
    }
    public char getSecLowerChar(){
        return lowerCase[secureRandom.nextInt(lowerCase.length)];
    }
    public char getSecDigitChar(){
        return digits[secureRandom.nextInt(digits.length)];
    }
    public char getSecSymbolChar(){
        return symbols[secureRandom.nextInt(symbols.length)];
    }
    public char[] getUpperCase() {
        return upperCase;
    }

    public char[] getLowerCase() {
        return lowerCase;
    }

    public char[] getDigits() {
        return digits;
    }

    public char[] getSymbols() {
        return symbols;
    }
}
