package xinxin.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import xinxin.util.UILibraryUtil;


public class Base {

    public static WebDriver webDriver;

    private static Logger logger= Logger.getLogger(Base.class);

    @Parameters(value = {"browserType"})
    @BeforeSuite
    public void init(String browserType){
        if ("ie".equalsIgnoreCase(browserType)){
             webDriver=new InternetExplorerDriver();
        }else if ("firefox".equalsIgnoreCase(browserType)){
             webDriver=new FirefoxDriver();
        }else if ("chrome".equalsIgnoreCase(browserType)){
           // System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
            webDriver=new ChromeDriver();
        }else {
            System.out.println("暂不支持该浏览器");
        }
        webDriver.manage().window().maximize();


    }


//    @AfterSuite
//    public void tearDown(){
//        webDriver.quit();
//    }

    /**
     * 访问页面
     * */
    public void to(String url){
        logger.info("访问页面"+url);
        webDriver.get(url);
    }

    /**
     * 输入数据
     * */
    public void send(String pageKeyword,String uiElementKeyword,String content){
        logger.info("定位到:【"+pageKeyword+"】的页面【"+uiElementKeyword+"】往里面写入【"+content+"】");
        UILibraryUtil.getElementByKeyword(pageKeyword,uiElementKeyword).sendKeys(content);
    }

    /**
     * 点击事件
     * */
    public void click(String pageKeyword,String uiElementKeyword){
        logger.info("点击:【"+pageKeyword+"】的【"+uiElementKeyword+"】");
        UILibraryUtil.getElementByKeyword(pageKeyword,uiElementKeyword).click();
    }


    /**
     * 获取元素
     * */
    public WebElement getelement(String pageKeyword,String uiElementKeyword){
        return  UILibraryUtil.getElementByKeyword(pageKeyword, uiElementKeyword);
    }



    /**
     * 清空内容
     * */
    public void clearcontent(String pageKeyword,String uiElementKeyword){
        UILibraryUtil.getElementByKeyword(pageKeyword,uiElementKeyword).clear();
    }

    /**
     * 获取元素文本内容
     * */
    public String gettext(String pageKeyword,String uiElementKeyword){
       return UILibraryUtil.getElementByKeyword(pageKeyword,uiElementKeyword).getText();
    }

    /**
     * 获取元素div文本内容
     * */
    public String getelementtext(String pageKeyword,String uiElementKeyword){
        return UILibraryUtil.getElementByKeyword(pageKeyword,uiElementKeyword).getAttribute("innerHTML");
    }



}
