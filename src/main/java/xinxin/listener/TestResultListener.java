package xinxin.listener;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import xinxin.util.ScreenshotUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestResultListener extends TestListenerAdapter {

        public void onTestFailure(ITestResult tr) {
            super.onTestFailure(tr);
            String baseDir="test-output";
            String screenshotDir="screenshot";
            String testNameDir = tr.getTestContext().getCurrentXmlTest().getName();
            Date now=new Date();
            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
            String dataDir = format.format(now);
            String fileName=now.getTime()+".jpg";
            String filePath=baseDir+ File.separator+screenshotDir+File.separator+
                    testNameDir+File.separator+dataDir
                    +File.separator+fileName;
            File file = ScreenshotUtil.saveScreenshot(filePath);
           // String accessPath=getImgString(file);



        }

//    private String getImgString(File file) {
//      String absolutePath=file.getAbsolutePath();
//      String accessPath=absolutePath.replaceAll()
//      String img="<img src='"++"'></img>";
//      return null;
//    }


}
