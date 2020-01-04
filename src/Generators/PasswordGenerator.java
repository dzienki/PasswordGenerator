package Generators;

import CharsHolder.CharsList;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public abstract class PasswordGenerator {
    public static String GeneratePasswordWithRandom(int upper, int lower, int digit, int special){
        int length= upper+lower+digit+special;
        if (length==0) {
            upper=1;
            lower=5;
            digit=1;
            special=1;
        }
        CharsList list=new CharsList();
        StringBuilder password = new StringBuilder(length);
        for(int i=0;i<upper;i++){
            password.append(list.getRandomUpperChar());

        }
        for(int i=0;i<lower;i++){
            password.append(list.getRandomLowerChar());

        }
        for(int i=0;i<digit;i++){
            password.append(list.getRandomDigitChar());

        }
        for(int i=0;i<special;i++){
            password.append(list.getRandomSymbolChar());

        }
        password= shuffleCharacters(password);
        return String.valueOf(password);
    }
    public static String GeneratePasswordWithSecureRandom(int upper, int lower, int digit, int special){
        StringBuilder password= new StringBuilder();
        int length= upper+lower+digit+special;
        if (length==0) {
            upper=1;
            lower=5;
            digit=1;
            special=1;
        }
        CharsList list=new CharsList();
        for(int i=0;i<upper;i++){
            password.append(list.getSecureRandomUpperChar());

        }
        for(int i=0;i<lower;i++){
            password.append(list.getSecureRandomLowerChar());

        }
        for(int i=0;i<digit;i++){
            password.append(list.getSecureRandomDigitChar());

        }
        for(int i=0;i<special;i++){
            password.append(list.getSecureRandomSymbolChar());

        }
        password= shuffleCharacters(password);
        return String.valueOf(password);
    }
    private static StringBuilder shuffleCharacters(StringBuilder input){
        List<Character> characters = new ArrayList<Character>();
        for(char c:input.toString().toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output;
    }

    public static String GeneratePasswordWithOwnChar(int digits, String allowedChars) {
        if(digits<=0) digits=8;
        StringBuilder password=new StringBuilder(digits);
        SecureRandom rand = new SecureRandom();
        if(allowedChars.equals("Wprowadz własne znaki!\nWprowadz liczbe znakow!")) {
            CharsList list = new CharsList();
            allowedChars = list.getWholeCharString();
        }
        allowedChars= deleteEndLines(allowedChars);
        int sizeOfCharList= allowedChars.length();
        for(int x=0; x<digits;x++){
            password.append(allowedChars.charAt(rand.nextInt(sizeOfCharList)));
        }
        return String.valueOf(password);
    }
    private static String deleteEndLines(String Chars){
        return Chars.replaceAll("\n","");
    }
}

