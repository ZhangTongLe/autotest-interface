package datas;

import com.liuyang.utils.ExcelUtil;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by liuyang on 2017/12/9.
 */
public class LoginCaseDataProvider {
    /**
     * 数据支持
     * 根据方法名注入数据
     * @param method 方法名
     * @return
     */
    @DataProvider
    public static Object[][] testDatas(Method method) throws FileNotFoundException {

        /**
         * 错误数据
         */

        /**
         * 正常数据
         */


        Object[][] ret = null;
        String filePath = "C:/Users/Administrator/Documents/loginCase.xlsx";
        //filePath = "loginCase.xlsx";
        ExcelUtil excelUtil = null;

        try {
            excelUtil = new ExcelUtil(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (method.getName()) {
            //添加商品单位异常测试数据
            case "testLogin":
                /*ret = new Object[][] {
                        //空数据测试
                        {"", "", false},
                        {"admin", "", false},
                        {"", "123456", false},
                        //数据太短
                        {"123", "45", false},
                        //数据过长
                        {"adminsdfsdfsdfsdfsdfsdfsdfsdf", "sdfsdfsdfffffffffsssssssssssssssssssssss", false},
                        //用户名有特殊字符
                        {"!@#$#%#$%^^&&*{", "!@#$#%#$%^^&&*{", false},
                        //正确数据
                        {"admin", "123456", true}

                };*/
                ret = excelUtil.toArray(0, 4);
                break;

        }
        return ret;
    }

}
