package com.example;

/**
 * Created by liuyang on 2018/1/5.
 */
public interface IData {

    public Object[][] getData(String caseName, String dataFile);

    public Object[][] getData(String caseName, String dataFile, int rowNum);

    public Object[][] getData(String caseName, String dataFile, int beginNum, int endNum);

}
