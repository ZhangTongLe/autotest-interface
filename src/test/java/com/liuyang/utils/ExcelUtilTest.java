package com.liuyang.utils;

import com.alibaba.fastjson.JSON;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by liuyang on 2018/1/5.
 */
public class ExcelUtilTest {

    String filePath;

    @BeforeTest
    public void setUp() throws Exception {
        filePath = "c:/username.xls";
    }

    @Test
    public void testSetPattern() throws Exception {
        System.out.println("user.dir = " + System.getProperty("user.dir"));
        System.out.println("user.home = " + System.getProperty("user.home"));
        System.out.println();
    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void testToString1() throws Exception {

    }

    @Test
    public void testIsExcel() throws Exception {
        boolean actual = ExcelUtil.isExcel(filePath);
        boolean expected = true;
        Assert.assertEquals(actual, expected);

    }

    @Test
    public void testRead() throws Exception {
        ExcelUtil excelUtil = new ExcelUtil(filePath);
        List<List<String>> actual = excelUtil.read(0, 1, 5);
        for (List<String> list : actual) {
            System.out.println("行：" );
            for (String str : list) {
                System.out.println(str);
            }
        }
    }

    @Test
    public void testRead1() throws Exception {

    }

    @Test
    public void testRead2() throws Exception {

    }

    @Test
    public void testToList() throws Exception {
        ExcelUtil excelUtil = new ExcelUtil(filePath);
        Object[][] actual = excelUtil.toArray(0, 1, 5);
        System.out.println(JSON.toJSONString(actual));
    }

    @Test
    public void testWrite() throws Exception {

    }

    @Test
    public void testWrite1() throws Exception {

    }

    @Test
    public void testWrite2() throws Exception {

    }

    @Test
    public void testWrite3() throws Exception {

    }

    @Test
    public void testSetStyle() throws Exception {

    }

    @Test
    public void testMakeStyle() throws Exception {

    }

    @Test
    public void testRegion() throws Exception {

    }

    @Test
    public void testIsRowNull() throws Exception {

    }

    @Test
    public void testCreateRow() throws Exception {

    }

    @Test
    public void testIsCellNull() throws Exception {

    }

    @Test
    public void testCreateCell() throws Exception {

    }

    @Test
    public void testGetRowCount() throws Exception {

    }

    @Test
    public void testGetColumnCount() throws Exception {

    }

    @Test
    public void testSetValueAt() throws Exception {

    }

    @Test
    public void testGetValueAt() throws Exception {

    }

    @Test
    public void testSetRowValue() throws Exception {

    }

    @Test
    public void testGetRowValue() throws Exception {

    }

    @Test
    public void testGetColumnValue() throws Exception {

    }

    @Test
    public void testGetSheetCount() throws Exception {

    }

    @Test
    public void testCreateSheet() throws Exception {

    }

    @Test
    public void testSetSheetName() throws Exception {

    }

    @Test
    public void testGetSheetName() throws Exception {

    }

    @Test
    public void testGetSheetIndex() throws Exception {

    }

    @Test
    public void testRemoveSheetAt() throws Exception {

    }

    @Test
    public void testRemoveRow() throws Exception {

    }

    @Test
    public void testSetSheetOrder() throws Exception {

    }

    @Test
    public void testClearSheet() throws Exception {

    }

    @Test
    public void testGetWorkbook() throws Exception {

    }

    @Test
    public void testClose() throws Exception {

    }

}