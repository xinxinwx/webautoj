package xinxin.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import xinxin.base.Base;

public class Assertion {

    /**
     * 判断当前页面url是否包含指定的内容  urlcontains
     * */
    public static void assertUrlContains(String urlcontains){
        WebDriverWait wait=new WebDriverWait(Base.webDriver,30);
        boolean isDirectedToLogin=true;
        try{
            wait.until(ExpectedConditions.urlContains(urlcontains));
        }catch (Exception e){
            isDirectedToLogin=false;
        }
        Assert.assertTrue(isDirectedToLogin);
    }

    /**
     * 断言文本值是否正确
     * */
    public static void assertTextPresent(WebElement element,String text){
        WebDriverWait wait=new WebDriverWait(Base.webDriver,30);
        Boolean textToBePresentInElment=true;
        try{
            textToBePresentInElment = wait.until(ExpectedConditions.textToBePresentInElement(element, text));

        }catch (Exception e){
            textToBePresentInElment=false;
        }
        Assert.assertTrue(textToBePresentInElment);
    }

    /**
     * 元素是否可以点击
     * */
    public static void assertElementToBeClickable(WebElement element){
        WebDriverWait wait=new WebDriverWait(Base.webDriver,30);
        boolean ToBeClickable=true;
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception e){
            ToBeClickable=false;
        }
        Assert.assertTrue(ToBeClickable);
    }
}
