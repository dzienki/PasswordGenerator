package generators;

import org.junit.Assert;
import org.junit.Test;

public class PasswordGeneratorTest {

    @Test
    public void generatePasswordWithRandom() {
        String str=PasswordGenerator.GeneratePasswordWithSecureRandom(0,8,5,5);
        Assert.assertEquals(str.length(),18);
        Assert.assertTrue(!ifStringHaveUppercase(str));
        Assert.assertEquals(howManyLowerCaseStringHave(str),8);
    }
    @Test
    public void generatePasswordWithSecureRandom() {
        String str=PasswordGenerator.GeneratePasswordWithSecureRandom(10,10,10,10);
        Assert.assertEquals(str.length(),40);
        Assert.assertTrue(ifStringHaveUppercase(str));
        Assert.assertEquals(howManyLowerCaseStringHave(str),10);
    }
    @Test
    public void generatePasswordWithOwnChar() {
        String str=PasswordGenerator.GeneratePasswordWithOwnChar(3,"aab"+"\n");
        Assert.assertTrue(!str.contains("\n"));
        Assert.assertTrue(str.contains("a")||str.contains("b"));
        Assert.assertEquals(str.length(),3);
        Assert.assertNotEquals(str,"aab"+"\n");
    }

    private static boolean ifStringHaveUppercase(String str){
        char ch;
        for (int x=0; x<str.length();x++){
            ch=str.charAt(x);
            if (Character.isUpperCase(ch)) return true;
        }
        return false;
    }
    private static int howManyLowerCaseStringHave(String str){
        char ch;
        int counter=0;
        for(int x=0;x<str.length();x++){
            ch=str.charAt(x);
            if(Character.isLowerCase(ch)) counter++;
        }
        return counter;
    }

}