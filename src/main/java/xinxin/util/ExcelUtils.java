package xinxin.util;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {


    public static <T> List<T> load(String excelPath,String sheetName,Class<T> clz){

        List<T> list = new ArrayList<T>();
          
        try {
            Workbook workbook= WorkbookFactory.create(new File(excelPath));
            Sheet sheet = workbook.getSheet(sheetName);
            //获取第一行
            Row titleRow = sheet.getRow(0);
            //获取最后一列的序号
            int lastCellNum = titleRow.getLastCellNum();
            String[] fields=new String[lastCellNum];
            //循环处理每一列
            for (int i = 0; i <lastCellNum ; i++) {
                //根据索引获取对应的列
                Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                //设置列的类型为字符串
                cell.setCellType(CellType.STRING);
                //获取列值
                String title = cell.getStringCellValue();
                if (title.equals("")){
                    continue;
                }
                title = title.substring(0, title.indexOf("("));
                fields[i]=title;
            }
            int lastRowIndex = sheet.getLastRowNum();
            //循环处理每一个数据
            for (int i = 1; i<=lastRowIndex; i++) {
                T obj = clz.newInstance();
                //拿到一个数据行
                Row dataRow = sheet.getRow(i);
                if (dataRow==null){
                    continue;
                }
               //拿到此数据行的每一列
                for (int j = 0; j <=lastCellNum; j++) {
                    Cell cell = dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    //设置列的类型为字符串
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    if (value.equals("")){
                        continue;
                    }
                    String methodName="set"+fields[j].substring(0, 1).toUpperCase() + fields[j].substring(1);
                    //获取要反射的方法名
                    Method method=clz.getMethod(methodName,String.class);
                    //完成反射调用
                    method.invoke(obj,value);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      return list;
    }
}
