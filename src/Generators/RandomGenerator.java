package Generators;

import CharsHolder.CharsList;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public abstract class RandomGenerator {
    public static String GeneratePassword(int upper, int lower, int digit, int special){
        int length= upper+lower+digit+special;
        StringBuilder password = new StringBuilder(length);
        CharsList list=new CharsList();
        for(int i=0;i<upper;i++){
            password.append(list.getUpperChar());

        }
        for(int i=0;i<lower;i++){
            password.append(list.getLowerChar());

        }
        for(int i=0;i<digit;i++){
            password.append(list.getDigitChar());

        }
        for(int i=0;i<special;i++){
            password.append(list.getSymbolChar());

        }
        password=shuffle(password);
        //System.out.println(password);
        return String.valueOf(password);
    }
    public static String SecureGeneratePassword(int upper, int lower, int digit, int special){
        StringBuilder password= new StringBuilder();
        CharsList list=new CharsList();
        for(int i=0;i<upper;i++){
            password.append(list.getSecUpperChar());

        }
        for(int i=0;i<lower;i++){
            password.append(list.getSecLowerChar());

        }
        for(int i=0;i<digit;i++){
            password.append(list.getSecDigitChar());

        }
        for(int i=0;i<special;i++){
            password.append(list.getSecSymbolChar());

        }
        password=shuffle(password);
        return String.valueOf(password);
    }
    private static StringBuilder shuffle(StringBuilder input){
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

    public static String OwnCharGeneratePassword(int digits, String allowedChars) {
        if(digits<=0) digits=8;
        StringBuilder password=new StringBuilder(digits);
        SecureRandom rand = new SecureRandom();
        if(allowedChars.equals("Wprowadz własne znaki!\nWprowadz liczbe znakow!")) {
            CharsList list = new CharsList();
            allowedChars = list.getWhole();
            System.out.println(allowedChars);
        }
        int sizeOfCharList= allowedChars.length();
        for(int x=0; x<digits;x++){
            password.append(allowedChars.charAt(rand.nextInt(sizeOfCharList)));
        }
        System.out.println(password);
        return String.valueOf(password);
    }
}

