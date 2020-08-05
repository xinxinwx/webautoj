package xinxin;

import org.testng.annotations.Test;
import xinxin.base.Base;

public class SqlTest extends Base {

    @Test
    public void sql(){
        click("首页","配置");
        click("首页","数据库配置");
    }
}
