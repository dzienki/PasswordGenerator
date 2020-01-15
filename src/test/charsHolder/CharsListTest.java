package charsHolder;
import org.junit.Assert;
import org.junit.Test;

public class CharsListTest {

    @Test
    public void getRandomUpperChar() {
        CharsList chars=new CharsList();
        char uppercase= chars.getRandomUpperChar();
        String list= String.valueOf(chars.getUpperList());
        Assert.assertTrue(list.indexOf(uppercase)>=0);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void getSpecialList(){
        CharsList chars=new CharsList();
        char[] ch= chars.getSymbolsList();
        for(int x=0;x<=100;x++){
            System.out.println(ch[x]);
        }
    }
    @Test
    public void getUpperList() {
        CharsList chars=new CharsList();
        Assert.assertEquals(26, chars.getUpperList().length);
    }

    @Test
    public void getLowerList() {
        CharsList chars=new CharsList();
        Assert.assertEquals(26,chars.getLowerList().length);
    }

    @Test
    public void getDigitList() {
        CharsList chars=new CharsList();
        Assert.assertEquals(chars.getDigitList().length,10);
    }

    @Test
    public void getRandomSymbolChar() {
        CharsList chars=new CharsList();
        String str= String.valueOf(chars.getSymbolsList());
        Assert.assertTrue(str.indexOf(chars.getRandomSymbolChar())>=0);
    }

    @Test
    public void getSecureRandomUpperChar() {
        CharsList chars=new CharsList();
        char uppercase= chars.getSecureRandomUpperChar();
        String list= String.valueOf(chars.getUpperList());
        Assert.assertTrue(list.indexOf(uppercase)>=0);
    }
}