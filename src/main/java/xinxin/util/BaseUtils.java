package xinxin.util;

import java.lang.reflect.Method;
import java.util.List;

public class BaseUtils {

    public static Object[][] assembleDatas(String[] cellNames, List<Object> satisfied, Class clazz) {
        Object[][] datas=new Object[satisfied.size()][cellNames.length];
        for (int i = 0; i <satisfied.size(); i++) {
            Object obj = satisfied.get(i);
            for (int j = 0; j <cellNames.length ; j++) {
                String methodName="get"+cellNames[j].substring(0, 1).toUpperCase() + cellNames[j].substring(1);
                //获取要反射的方法名
                Method method= null;
                try {
                    method = clazz.getMethod(methodName);
                    String value = (String) method.invoke(obj);
                    datas[i][j]=value;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return datas;
    }
}
