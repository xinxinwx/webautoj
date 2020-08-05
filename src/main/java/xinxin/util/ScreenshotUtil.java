package xinxin.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import xinxin.base.Base;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static File saveScreenshot(String filePath) {
        File screenshot=null;
        if (Base.webDriver instanceof FirefoxDriver){
            FirefoxDriver firefoxDriver= (FirefoxDriver) Base.webDriver;
            screenshot = firefoxDriver.getScreenshotAs(OutputType.FILE);
        }else if (Base.webDriver instanceof InternetExplorerDriver){
            InternetExplorerDriver firefoxDriver= (InternetExplorerDriver) Base.webDriver;
            screenshot = firefoxDriver.getScreenshotAs(OutputType.FILE);
        }else if (Base.webDriver instanceof ChromeDriver){
            ChromeDriver firefoxDriver= (ChromeDriver) Base.webDriver;
            screenshot = firefoxDriver.getScreenshotAs(OutputType.FILE);
        }
        File destFile=new File(filePath);

        try {
            FileUtils.copyFile(screenshot,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destFile;
    }
}
