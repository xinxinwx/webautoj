package xinxin.util;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import xinxin.base.Base;
import xinxin.entity.Page;
import xinxin.entity.UIElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UILibraryUtil {

    private static Logger logger= Logger.getLogger(Base.class);
    public static List<Page> pages=new ArrayList<Page>();

    static {
       loadPages(PropertiesUtil.getExcelPath("uilibrary.path"));
    }

    /**
     * uiLibraryPath   ui库xml文件路径
     * */
    private static void loadPages(String uiLibraryPath) {
        SAXReader reader=new SAXReader();
        try {
            InputStream inputStream=new FileInputStream(new File(uiLibraryPath));
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            List<Element> pagesElement=root.elements("Page");
            for (Element pageElement:pagesElement) {
                String pageKeyword = pageElement.attributeValue("keyword");
                List<Element> uiElements = pageElement.elements("UIElement");
                List<UIElement> uiElementList=new ArrayList<UIElement>();
                for (Element uiElement:uiElements) {
                    String uiElementKeyword = uiElement.attributeValue("keyword");
                    String id = uiElement.attributeValue("by");
                    String value = uiElement.attributeValue("value");
                    UIElement uiEl=new UIElement(uiElementKeyword,id,value);
                    uiElementList.add(uiEl);
                }
                Page page=new Page(pageKeyword,uiElementList);
                pages.add(page);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * pageKeyword   页面关键字
     * uiElementKeyword   元素关键字
     * */
    public static WebElement getElementByKeyword(String pageKeyword,String uiElementKeyword){
        WebElement element=null;
        for (Page page:pages) {
          if (pageKeyword.equals(page.getKeyword())){
              List<UIElement> uiElements = page.getUiElements();
              for (UIElement uiElement:uiElements) {
                if (uiElementKeyword.equals(uiElement.getKeyword())){
                    String by = uiElement.getBy();
                    String value = uiElement.getValue();
                    element=getVisibleElement(by,value);
                }
              }
          }
        }
        if (element==null){
            logger.info("获取元素失败");
        }
        return element;
    }

    private static WebElement getVisibleElement(String by, String value) {
        WebDriverWait wait=new WebDriverWait(Base.webDriver,20);
        By locator=null;
        WebElement webElement=null;
        if ("id".equalsIgnoreCase(by)){
            locator=By.id(value);
        }else if ("name".equalsIgnoreCase(by)){
            locator=By.name(value);
        }else if ("xpath".equalsIgnoreCase(by)){
            locator=By.xpath(value);
        }
        else if ("className".equalsIgnoreCase(by)){
            locator=By.className(value);
        }
        else if ("cssSelector".equalsIgnoreCase(by)){
            locator=By.cssSelector(value);
        }
        else if ("tagName".equalsIgnoreCase(by)){
            locator=By.tagName(value);
        }
        else if ("linkText".equalsIgnoreCase(by)){
            locator=By.linkText(value);
        }
        else if ("partialLinkText".equalsIgnoreCase(by)){
            locator=By.partialLinkText(value);
        }else {
            System.out.println("暂时不支持此类型");
        }
        try{
            webElement=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch (Exception e){
            if (e instanceof TimeoutException){
                logger.info("根据by:【"+by+"】,value【"+value+"】定位元素超时");
            }else {
                e.printStackTrace();
                System.out.println(e.toString());
            }
        }
        return webElement;
    }


}
