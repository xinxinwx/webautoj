package xinxin;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import xinxin.base.Base;
import xinxin.util.Assertion;
import xinxin.util.UILibraryUtil;


public class Mytest extends Base {


    @Test(dataProvider = "testdp")
    public void run(String tel,String pwd,String expect,String expectelement)  {
        to("http://xinxinapi.icu:9001/#/login");
        webDriver.navigate().refresh();
        send("首页","用户名",tel);
        send("首页","密码",pwd);
        click("首页","登录");
        Assertion.assertTextPresent(getelement("首页",expectelement),expect);

    }

    @DataProvider(name="testdp")
    public Object[][] createData() {
        String[] cellNames={"tel","pwd","expect","expectelement"};
        return CaseUtils.getDatas(cellNames);
    }


//    @Test
//    public void run()  {
//        to("http://xinxinapi.icu:9001/#/login");
//
//
//    }


}
