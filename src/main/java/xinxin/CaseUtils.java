package xinxin;

import xinxin.util.BaseUtils;
import xinxin.util.ExcelUtils;
import xinxin.util.PropertiesUtil;

import java.util.ArrayList;
import java.util.List;

public class CaseUtils {

    public static List<TestData> cases1=new ArrayList<TestData>();

    static {
        List<TestData> testlist = ExcelUtils.load(PropertiesUtil.getExcelPath("excel.path"), "登录", TestData.class);
        cases1.addAll(testlist);
    }


    public static Object[][] getDatas(String[] cellNames){
        List<Object> satisfied=new ArrayList<Object>();
        for (TestData testData : cases1) {
             satisfied.add(testData);
        }
        Class clazz=TestData.class;
        return BaseUtils.assembleDatas(cellNames, satisfied, clazz);
    }




}
