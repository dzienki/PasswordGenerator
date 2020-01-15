package view;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class WindowTest {

    @Test
    public void getPasswordDisplay() {
        Window window=new Window();
        JTextArea textArea=new JTextArea();
        Assert.assertNotNull(window.getPasswordDisplay());
        Assert.assertNotSame(textArea,window.getPasswordDisplay());
        Assert.assertEquals(window.getPasswordDisplay().getText(),"");
        window.getPasswordDisplay().setText("ass");
        Assert.assertEquals(window.getPasswordDisplay().getText(),"ass");
    }

    @Test
    public void getUpperCaseQuantity() {
        Window window=new Window();
        JSpinner spinner=new JSpinner();
        Assert.assertNotNull(window.getUpperCaseQuantity());
        Assert.assertNotSame(spinner,window.getUpperCaseQuantity());
        Assert.assertEquals((int)window.getUpperCaseQuantity().getValue(),2);
    }
}