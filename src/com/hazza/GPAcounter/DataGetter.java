package com.hazza.GPAcounter;

import jxl.Sheet;
import jxl.Workbook;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hazza on 7/17/17.
 */
public class DataGetter {
    private static double allGpa, allCreadits, yearGpa, yearCredits, term1Gpa, term1Credits, term2Gpa, term2Credits;

    private static Set<String> getYears(String filePath, int sheetIndex) {
        allGpa = 0.0;
        allCreadits = 0.0;
        Set<String> years = new HashSet<>();
        ExcelReader er = new ExcelReader(filePath);
        Workbook wb = er.getWorkBook();
        Sheet sheet = wb.getSheet(sheetIndex);
        int rows = sheet.getRows();
        for (int i = 0; i < rows; i++)
            years.add(sheet.getCell(0, i).getContents().trim());

        er.close();
        return years;
    }

    private static String getYearData(String year, String filePath, int sheetIndex) {
        yearGpa = 0.0;
        yearCredits = 0.0;
        term1Gpa = 0.0;
        term1Credits = 0.0;
        term2Gpa = 0.0;
        term2Credits = 0.0;
        ExcelReader er = new ExcelReader(filePath);
        Workbook wb = er.getWorkBook();
        Sheet sheet = wb.getSheet(sheetIndex);
        int rows = sheet.getRows();
        for (int i = 0; i < rows; i++) {
            if (sheet.getCell(0, i).getContents().trim().equals(year)
                    && sheet.getCell(15, i).getContents() != null
                    && !sheet.getCell(15, i).getContents().equals("")) {
                double gpa = Double.parseDouble(sheet.getCell(15, i).getContents());
                double credit = Double.parseDouble(sheet.getCell(6, i).getContents());
                int term = Integer.parseInt(sheet.getCell(1, i).getContents());
                allGpa += gpa * credit;
                allCreadits += credit;
                yearGpa += gpa * credit;
                yearCredits += credit;
                if (term == 1) {
                    term1Gpa += gpa * credit;
                    term1Credits += credit;
                } else if (term == 2) {
                    term2Gpa += gpa * credit;
                    term2Credits += credit;
                }
            }

        }

        er.close();
        String res = year + "\n" + "学年 计入GPA总学分: " + yearCredits + " GPA: " + yearGpa / yearCredits + "\n"
                + "第一学期 计入GPA学分: " + term1Credits + " GPA: " + term1Gpa / term1Credits + "\n"
                + "第二学期 计入GPA学分: " + term2Credits + " GPA: " + term2Gpa / term2Credits + "\n";
        return res;
    }

    public static String getAllData(String filePath, int sheetIndex) {
        StringBuffer sb = new StringBuffer();
        Set<String> years = getYears(filePath, sheetIndex);
        for (String year : years)
            sb.append(getYearData(year, filePath, sheetIndex));
        String res = "总评" + "\n" + "计入GPA总学分: " + allCreadits + " GPA: " + allGpa / allCreadits;
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append(res);

        return sb.toString();
    }




    public static void main(String[] args) {
        String filePath = "/home/hazza/data.xls";
        DataGetter app = new DataGetter();
        DataGetter.getAllData(filePath, 2);

    }
}
