package charsHolder;

import java.security.SecureRandom;
import java.util.Random;


public class CharsList {
    private final int firstDigit=48 ,lastDigit=57;
    private final int firstLowerCase=97 ,lastLowerCase=122;
    private final int firstUpperCase=65 ,lastUpperCase=90;
    private char[] symbols;
    private Random rand;
    private SecureRandom secureRandom;
    public CharsList(){
        symbols ="!@#$%^&*(){}[]\\|:\";'<>?,./".toCharArray();
        rand= new Random();
        secureRandom=new SecureRandom();

    }
    public char[] getUpperList(){
        return getStringFromAscii(firstUpperCase, lastUpperCase).toCharArray();
    }
    public char[] getLowerList(){
        return getStringFromAscii(firstLowerCase, lastLowerCase).toCharArray();
    }
    public char[] getDigitList(){
        return getStringFromAscii(firstDigit,lastDigit).toCharArray();
    }
    private String getStringFromAscii(int first, int last) {
        StringBuilder list=new StringBuilder();
        for (int x = first; x<= last; x++){
            list.append((char)x);
        }
        return list.toString();
    }

    public String getWholeCharString(){
        return getStringFromAscii(33,125);
    }
    public char getRandomUpperChar(){
        return (char) (rand.nextInt(lastUpperCase-firstUpperCase)+firstUpperCase);
    }
    public char getRandomLowerChar(){
        return (char) (rand.nextInt(lastLowerCase-firstLowerCase)+firstLowerCase);
    }
    public char getRandomDigitChar(){
        return (char) (rand.nextInt(lastDigit-firstDigit)+firstDigit);
    }
    public char getRandomSymbolChar(){
        return symbols[rand.nextInt(symbols.length)];
    }
    public char getSecureRandomUpperChar(){
        return (char) (secureRandom.nextInt(lastUpperCase-firstUpperCase)+firstUpperCase);
    }
    public char getSecureRandomLowerChar(){
        return (char) (secureRandom.nextInt(lastLowerCase-firstLowerCase)+firstLowerCase);
    }
    public char getSecureRandomDigitChar(){
        return (char) (secureRandom.nextInt(lastDigit-firstDigit)+firstDigit);
    }
    public char getSecureRandomSymbolChar(){
        return symbols[secureRandom.nextInt(symbols.length)];
    }
    public char[] getSymbolsList() {
        return symbols;
    }
}
