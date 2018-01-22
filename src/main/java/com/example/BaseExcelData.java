package com.example;

/**
 * Created by liuyang on 2018/1/5.
 */
public class BaseExcelData implements IData {

    String defaultPath = "data/";

    @Override
    public Object[][] getData(String caseName, String dataFile) {
        return getData(caseName, dataFile, 0);
    }

    @Override
    public Object[][] getData(String caseName, String dataFile, int rowNum) {
        Object[][] ret = null;
        //ret = addList(caseName, dataFile, rowNum);
        return ret;
    }

    @Override
    public Object[][] getData(String caseName, String dataFile, int beginRowNum, int endRowNum) {
        Object[][] ret = null;
        //ret = addList2(caseName, dataFile, beginRowNum, endRowNum);
        return ret;
    }


}
