package com.hazza.GPAcounter;

import jxl.Sheet;
import jxl.Workbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by hazza on 7/17/17.
 */
public class ExcelReader {
    private FileInputStream in;
    private Workbook wb;

    public ExcelReader(String filePath) {
        try {
            in = new FileInputStream(filePath);
            wb = Workbook.getWorkbook(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Workbook getWorkBook() {
        return wb;
    }

    public void close() {
        try {
            in.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
