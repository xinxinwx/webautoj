package xinxin.listener;

import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import xinxin.util.ScreenshotUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AllureReporterListener implements IHookable {

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        // callBack调用runTestMethod方法，将测试结果放到了testResult里面
        callBack.runTestMethod(testResult);
        // testResult调用getThrowable()方法获取用例的异常执行结果
        // 异常结果为空，说明没有异常 ，不为空，说明有异常
        if (testResult.getThrowable() != null) {
            // 有异常，就截图
            //3.将截图嵌入到allure报表
            try {
                saveScreenShot();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * @throws IOException
     * @Attachment是allure提供的注解
     *
     *
     *
     */
    @Attachment
    public byte[] saveScreenShot() throws IOException {
        // 1. 调用截图的方法
        // 将异常case的截图存在这个路径下System.getProperty("user.dir")+"\\target\\screenShot\\
        // 图片名字：System.currentTimeMillis().png
        String baseDir="test-output";
        String screenshotDir="screenshot";
        Date now=new Date();
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
        String dataDir = format.format(now);
        String fileName=now.getTime()+".jpg";
        String filePath=baseDir+ File.separator+screenshotDir+File.separator+dataDir
                +File.separator+fileName;
        // 2.Files.toByteArray(screenshot)将 File类型对象转成byte[] 数组
        File file = ScreenshotUtil.saveScreenshot(filePath);
        byte[] byteArray = Files.toByteArray(file);

        return byteArray;

    }
}
